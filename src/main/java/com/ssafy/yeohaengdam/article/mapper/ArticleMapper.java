package com.ssafy.yeohaengdam.article.mapper;

import com.ssafy.yeohaengdam.article.entity.Article;
import com.ssafy.yeohaengdam.article.entity.Image;
import com.ssafy.yeohaengdam.article.entity.SearchCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import static com.ssafy.yeohaengdam.article.dto.ArticleData.*;

@Mapper
public interface ArticleMapper {

    public List<ArticleInfo> list(String keyword, String sortBy, int start, int size);

    public Detail findById(int articleId);
    public int create(Article article);

    public int update(Article article);

    public int delete(int articleId);

    public void createImage(String storedName, int articleId);

    public void deleteImage(int articleId);

    public void updateHit(int articleId);

    List<ArticleInfo> findByUserId(int userId, String keyword, String sortBy, int start, int size);
}
