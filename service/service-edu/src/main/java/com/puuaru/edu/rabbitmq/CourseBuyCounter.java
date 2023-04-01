package com.puuaru.edu.rabbitmq;

import com.puuaru.edu.mapper.EduCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @Description: CourseBuyCounter
 * @Author: puuaru
 * @Date: 2023/3/31
 */
@Component
public class CourseBuyCounter {
    private final EduCourseMapper courseMapper;

    @Autowired
    public CourseBuyCounter(EduCourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Bean
    public Consumer<Long> updateCourseBuyCount() {
        return (courseId) -> {
            System.out.println("============ =================");
            courseMapper.updateCourseBuyCount(courseId);
        };
    }
}
