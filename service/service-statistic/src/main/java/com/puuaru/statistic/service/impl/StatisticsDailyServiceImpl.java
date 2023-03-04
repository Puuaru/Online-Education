package com.puuaru.statistic.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.puuaru.statistic.Feign.UcenterClient;
import com.puuaru.statistic.entity.StatisticsDaily;
import com.puuaru.statistic.mapper.StatisticsDailyMapper;
import com.puuaru.statistic.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puuaru.utils.FeignUtils;
import com.puuaru.utils.ResultCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    public StatisticsDailyServiceImpl(UcenterClient ucenterClient) {
        this.ucenterClient = ucenterClient;
    }

    @Override
    public Boolean initDateStatisticsData(String date) {
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
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", date);
        super.remove(wrapper);
        return super.save(statisticsDaily);
    }
}
