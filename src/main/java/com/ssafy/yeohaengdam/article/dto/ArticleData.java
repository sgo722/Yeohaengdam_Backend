package com.ssafy.yeohaengdam.article.dto;

import com.ssafy.yeohaengdam.article.entity.Image;
import com.ssafy.yeohaengdam.comment.dto.CommentData;
import com.ssafy.yeohaengdam.comment.entity.Comment;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleData {

    @Data
    public static class ArticleInfo{
        private int articleId;
        private int userId;
        private String nickname;
        private String profileImage;
        private String title;
        private String content;
        private int hit;
//        private int likeCount;
//        private boolean isLiked;

        private int totalCount;
        private List<Image> imageUrls;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    public static class Detail{
        private int articleId;
        private int userId;
        private String nickname;
        private String profileImage;
        private String title;
        private String content;
        private int hit;
        //        private int likeCount;
//        private boolean isLiked;
        private int totalCount;
        private List<Image> imageUrls;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    public static class Create{
        @NotNull(message = "제목을 입력해주세요")
        private String title;

        @NotNull(message = "내용을 입력해주세요")
        private String content;
    }

    @Data
    public static class Update{

        private int articleId;
        private String title;
        private String content;
        private LocalDateTime updatedAt;
    }
}
