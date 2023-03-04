package com.puuaru.statistic.controller;


import com.puuaru.statistic.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author puuaru
 * @since 2023-03-03
 */
@RestController
@RequestMapping("/statistic")
public class StatisticsDailyController {

    private final StatisticsDailyService statisticsDailyService;

    @Autowired
    public StatisticsDailyController(StatisticsDailyService statisticsDailyService) {
        this.statisticsDailyService = statisticsDailyService;
    }

    /**
     * 根据日期获取统计信息，当前环境下为初始化统计信息
     * @param date
     * @return
     */
    @GetMapping("/{date}")
    @ApiOperation("根据日期获取统计信息，当前环境下为初始化统计信息")
    public Boolean getStatisticDataByDate(@PathVariable("date") String date) {
        return statisticsDailyService.initDateStatisticsData(date);
    }

}

