package com.ssafy.yeohaengdam.spot.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Gugun {
    private int gugunCode;
    private String gugunName;

}
