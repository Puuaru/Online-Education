package com.puuaru.statistic.controller;


import com.puuaru.statistic.entity.StatisticsDaily;
import com.puuaru.statistic.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/{begin}/{end}")
    public Map<String, Object> showData(@PathVariable("begin") String begin, @PathVariable("end") String end) {
        return statisticsDailyService.showData(begin, end);
    }

}

