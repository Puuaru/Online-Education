package com.puuaru.statistic.mapper;

import com.puuaru.statistic.entity.StatisticsDaily;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 网站统计日数据 Mapper 接口
 * </p>
 *
 * @author puuaru
 * @since 2023-03-03
 */
@Mapper
public interface StatisticsDailyMapper extends BaseMapper<StatisticsDaily> {
    void updateStatistics(String type, String date);

    void refreshStatistics(String type, Integer count, String date);
}
