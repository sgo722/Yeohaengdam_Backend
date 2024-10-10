package com.ssafy.yeohaengdam.course.entity;

import com.ssafy.yeohaengdam.spot.entity.Spot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Course {

    private int courseId;

    private int userId;

    private String title;

    private String description;

    private List<Schedule> schedules;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
