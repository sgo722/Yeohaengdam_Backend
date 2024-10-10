package com.ssafy.yeohaengdam.course.entity;

import com.ssafy.yeohaengdam.spot.entity.Spot;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Schedule {

    private int scheduleId;

    private int courseId;

    private Spot spot;

    private int orderIndex;
}
