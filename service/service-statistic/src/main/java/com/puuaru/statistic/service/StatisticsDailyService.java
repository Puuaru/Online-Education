package com.puuaru.statistic.service;

import com.puuaru.statistic.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author puuaru
 * @since 2023-03-03
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {
    void initDateStatisticsData(String date);
}
