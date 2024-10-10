package com.ssafy.yeohaengdam.article.entity;

import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Image {
    private Integer id;

    private String originalName;

    private String storedName;

    private Integer articleId;
}
