package com.puuaru.statistic.schedule;

import com.puuaru.statistic.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;

/**
 * @Description: StatInitSchedule
 * @Author: puuaru
 * @Date: 2023/3/9
 */
@Component
public class StatInitSchedule {
    private final StatisticsDailyService statisticsDailyService;

    @Autowired
    public StatInitSchedule(StatisticsDailyService statisticsDailyService) {
        this.statisticsDailyService = statisticsDailyService;
    }

    @Scheduled(cron = "1 0 0 * * ?")
    public void initStatisticDataDaily() {
        Clock defaultZone = Clock.systemDefaultZone();
        statisticsDailyService.initDateStatisticsData(LocalDate.now(defaultZone).toString());
        statisticsDailyService.refreshData(LocalDate.now(defaultZone).minusDays(1).toString());
    }
}
