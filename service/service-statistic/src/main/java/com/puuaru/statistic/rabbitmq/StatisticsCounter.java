package com.puuaru.statistic.rabbitmq;

import com.puuaru.statistic.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Consumer;

/**
 * @Description: LoginCounter
 * @Author: puuaru
 * @Date: 2023/3/22
 */
@Component
public class LoginCounter {
    private final StatisticsDailyService statisticsDailyService;

    @Autowired
    public LoginCounter(StatisticsDailyService statisticsDailyService) {
        this.statisticsDailyService = statisticsDailyService;
    }

    @Bean
    public Consumer<Map<String, String>> loginCount() {
        return params -> {
            statisticsDailyService.updateStatistic(params.get("type"), params.get("date"));
        };
    }
}
