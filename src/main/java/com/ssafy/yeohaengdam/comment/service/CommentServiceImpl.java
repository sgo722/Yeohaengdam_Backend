package com.ssafy.yeohaengdam.comment.service;

import com.ssafy.yeohaengdam.comment.dto.CommentData;
import com.ssafy.yeohaengdam.comment.entity.Comment;
import com.ssafy.yeohaengdam.comment.mapper.CommentMapper;
import com.ssafy.yeohaengdam.user.entity.RoleType;
import com.ssafy.yeohaengdam.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ssafy.yeohaengdam.comment.dto.CommentData.*;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentMapper commentMapper;
    @Override
    public void create(User user, Create create) {
        Comment newComment = Comment.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .articleId(create.getArticleId())
                .content(create.getContent())
                .build();
        commentMapper.create(newComment);
    }

    @Override
    public void delete(User user, int commentId) {
        Detail comment = commentMapper.findById(commentId);
        if(comment.getUserId() != user.getUserId()){
            throw new IllegalArgumentException("댓글 수정 권한이 없는 사용자입니다.");
        }
        commentMapper.delete(commentId);
    }

    @Override
    public void update(User user, Update update){
        Detail comment = commentMapper.findById(update.getCommentId());

        if(comment.getUserId() != user.getUserId()){
            if(!user.getRoleType().equals(RoleType.ADMIN)) {
                throw new IllegalArgumentException("게시글 수정 권한이 없는 사용자입니다.");
            }
        }
        Comment updatedComment = Comment.builder()
                .commentId(update.getCommentId())
                .content(update.getContent())
                .build();
        commentMapper.update(updatedComment);
    }

    @Override
    public List<Detail> findByArticleId(int articleId) {
        return commentMapper.findByArticleId(articleId);
    }
}
