package com.application.usedallea.utils;

import com.application.usedallea.product.dto.ProductRegisterDto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeCalculator {

    public static void calculateTimeDifference(ProductRegisterDto productDTO, LocalDateTime now){
        LocalDateTime createdAt = productDTO.getCreatedAt();  //생성시간
        long minutesAgo = Duration.between(createdAt,now).toMinutes();
        long hoursAgo = Duration.between(createdAt, now).toHours();
        long daysAgo = ChronoUnit.DAYS.between(createdAt, now);
        long weeksAgo = ChronoUnit.WEEKS.between(createdAt,now);
        productDTO.setMinutesAgo(minutesAgo);
        productDTO.setHoursAgo(hoursAgo);
        productDTO.setDaysAgo(daysAgo);
        productDTO.setWeeksAgo(weeksAgo);
    }

}
