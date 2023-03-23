package com.puuaru.statistic.rabbitmq;

import com.puuaru.statistic.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Consumer;

/**
 * @Description: StatisticsCounter
 * @Author: puuaru
 * @Date: 2023/3/22
 */
@Component
public class StatisticsCounter {
    private final StatisticsDailyService statisticsDailyService;

    @Autowired
    public StatisticsCounter(StatisticsDailyService statisticsDailyService) {
        this.statisticsDailyService = statisticsDailyService;
    }

    @Bean
    public Consumer<Map<String, String>> updateStatistics() {
        return params -> {
            statisticsDailyService.updateStatistics(params.get("type"), params.get("date"));
        };
    }
}
