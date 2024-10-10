package com.ssafy.yeohaengdam.notice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Notice {

    public int noticeId;

    public int userId;

    public String title;

    public String content;

    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;


}
