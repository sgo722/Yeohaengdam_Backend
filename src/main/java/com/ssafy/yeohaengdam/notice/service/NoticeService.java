package com.ssafy.yeohaengdam.notice.service;

import com.ssafy.yeohaengdam.article.entity.SearchCriteria;
import com.ssafy.yeohaengdam.notice.entity.Notice;

import java.util.List;

import static com.ssafy.yeohaengdam.notice.dto.NoticeData.*;


public interface NoticeService {

    public List<Detail> findAllByAlgorithm(SearchCriteria searchCriteria);

    public List<Detail> findAll(SearchCriteria searchCriteria);

    public void create(int userId, Create create);


    public void delete(int noticeId, int userId);

    public Detail findById(int noticeId);

    public void update(int noticeId, Create update, int userId);
}
