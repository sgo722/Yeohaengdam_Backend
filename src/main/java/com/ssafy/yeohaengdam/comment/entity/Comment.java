package com.ssafy.yeohaengdam.comment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private int commentId;
    
    private int articleId;

    private int userId;

    private String nickname;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
