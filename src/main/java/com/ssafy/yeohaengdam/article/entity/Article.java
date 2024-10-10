package com.ssafy.yeohaengdam.article.entity;

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
public class Article {

    private int articleId;

    private int userId;

    private String title;

    private String content;

    private int hit;

    private int likeCount;

    private boolean isLiked;

    private List<String> imageUrls;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
