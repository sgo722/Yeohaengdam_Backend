package com.ssafy.yeohaengdam.notice.service;

import com.ssafy.yeohaengdam.algorithm.StringCheckService;
import com.ssafy.yeohaengdam.article.entity.SearchCriteria;
import com.ssafy.yeohaengdam.notice.entity.Notice;
import com.ssafy.yeohaengdam.notice.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.ssafy.yeohaengdam.notice.dto.NoticeData.*;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{

    private final NoticeMapper noticeMapper;
    private final StringCheckService stringCheckService;

    @Override
    public List<Detail> findAllByAlgorithm(SearchCriteria searchCriteria) {
        List<Detail> allNotices = noticeMapper.findAllNotices();

        List<Detail> filteredNotices = null;
        // KMP 알고리즘을 사용하여 필터링
        if(!searchCriteria.getKeyword().isEmpty()) {
            filteredNotices = allNotices.stream()
                    .filter(notice -> stringCheckService.isContain(notice.getTitle(), searchCriteria.getKeyword()))
                    .collect(Collectors.toList());
        }else{
            filteredNotices = allNotices;
        }

        // 페이징 처리
        int start = (searchCriteria.getPage() - 1) * searchCriteria.getSize();
        int end = Math.min(start + searchCriteria.getSize(), filteredNotices.size());

        List<Detail> pagedNotices = filteredNotices.subList(start, end);

        return pagedNotices.stream()
                .map(notice -> Detail.builder()
                        .noticeId(notice.getNoticeId())
                        .title(notice.getTitle())
                        .content(notice.getContent())
                        .createdAt(notice.getCreatedAt())
                        .userId(notice.getUserId())
                        .nickname(notice.getNickname())
                        .totalCount(allNotices.size())
                        .updatedAt(notice.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<Detail> findAll(SearchCriteria searchCriteria) {
        int start = searchCriteria.getPage() - 1;
        return noticeMapper.findAll(searchCriteria.getKeyword(), start, searchCriteria.getSortBy(), searchCriteria.getSize());
    }

    @Override
    public void create(int userId, Create create) {
        Notice newNotice = Notice.builder()
                .userId(userId)
                .title(create.getTitle())
                .content(create.getContent())
                .build();
        noticeMapper.create(newNotice);
    }


    @Override
    public void delete(int noticeId, int userId) {
        noticeMapper.delete(noticeId);
    }

    @Override
    public Detail findById(int noticeId) {
        return noticeMapper.findById(noticeId);
    }

    @Override
    public void update(int noticeId, Create update, int userId) {
        Notice updatedNotice = Notice.builder()
                .noticeId(noticeId)
                .title(update.getTitle())
                .content(update.getContent())
                .build();
        noticeMapper.update(updatedNotice);
    }
}
