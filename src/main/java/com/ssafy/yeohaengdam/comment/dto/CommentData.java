package com.ssafy.yeohaengdam.comment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

public class CommentData {

    @Data
    public static class Detail{
        private int commentId;
        private int articleId;
        private int userId;
        private String nickname;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    public static class Create{
        private int articleId;

        @NotNull(message = "댓글 내용을 입력해주세요")
        private String content;
    }

    @Data
    public static class Update{
        private int commentId;

        @NotNull(message = "댓글 내용을 입력해주세요")
        private String content;
    }
}
