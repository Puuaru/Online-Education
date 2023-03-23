package com.puuaru.statistic.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puuaru.statistic.Feign.UcenterClient;
import com.puuaru.statistic.entity.StatisticsDaily;
import com.puuaru.statistic.mapper.StatisticsDailyMapper;
import com.puuaru.statistic.service.StatisticsDailyService;
import com.puuaru.statistic.vo.DataVO;
import com.puuaru.utils.FeignUtils;
import com.puuaru.utils.ResultCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author puuaru
 * @since 2023-03-03
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    private final UcenterClient ucenterClient;
    private final RedisTemplate redisTemplate;

    @Autowired
    public StatisticsDailyServiceImpl(UcenterClient ucenterClient, RedisTemplate redisTemplate) {
        this.ucenterClient = ucenterClient;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Boolean initDateStatisticsData(String date) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", date);
        StatisticsDaily data = super.getOne(wrapper);
        if (data != null) {
            return true;
        }

        ResultCommon respond = ucenterClient.statRegister(date);
        Integer registerNum = FeignUtils.parseResult(respond, Integer.class);
        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setDateCalculated(date);
        statisticsDaily.setRegisterNum(registerNum);
        //statisticsDaily.setCourseNum(0);
        //statisticsDaily.setLoginNum(0);
        //statisticsDaily.setVideoViewNum(0);
        statisticsDaily.setCourseNum(RandomUtil.randomInt(500));
        statisticsDaily.setLoginNum(RandomUtil.randomInt(500));
        statisticsDaily.setVideoViewNum(RandomUtil.randomInt(500));
        super.save(statisticsDaily);
        return true;
    }

    @Override
    public Map<String, Object> showData(String begin, String end) {
        refreshData();
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        if (!ObjectUtils.isEmpty(begin) && !"undefined".equals(begin)) {
            wrapper.ge("date_calculated", begin);
        }
        if (!ObjectUtils.isEmpty(end) && !"undefined".equals(end)) {
            wrapper.le("date_calculated", end);
        }
        List<StatisticsDaily> list = super.list(wrapper);
        List<String> xData = list.stream().map(StatisticsDaily::getDateCalculated).collect(Collectors.toList());
        List<DataVO> yData = new ArrayList<>();
        DataVO loginNum = new DataVO("每日登录人数", list.stream().map(StatisticsDaily::getLoginNum).collect(Collectors.toList()));
        DataVO registerNum = new DataVO("每日注册人数",  list.stream().map(StatisticsDaily::getRegisterNum).collect(Collectors.toList()));
        DataVO videoViewNum = new DataVO("每日视频观看数", list.stream().map(StatisticsDaily::getVideoViewNum).collect(Collectors.toList()));
        DataVO courseNum = new DataVO("课程浏览量", list.stream().map(StatisticsDaily::getCourseNum).collect(Collectors.toList()));
        yData.add(loginNum);
        yData.add(registerNum);
        yData.add(videoViewNum);
        yData.add(courseNum);

        map.put("dateList", xData);
        map.put("countList", yData);
        return map;
    }

    @Override
    public void updateStatistics(String type, String date) {
        baseMapper.updateStatistics(type, date);
    }

    private void refreshData() {
        if (!redisTemplate.hasKey("count_stat")) {
            return;
        }
        Map<String, Integer> countStat = redisTemplate.opsForHash().entries("count_stat");
        countStat.entrySet().stream().forEach((entry) -> {
            // 更新数据
            baseMapper.refreshStatistics(entry.getKey(), entry.getValue());
            // 计数器归零
            redisTemplate.opsForHash().put("count_stat", entry.getKey(), 0);
        });
    }
}
