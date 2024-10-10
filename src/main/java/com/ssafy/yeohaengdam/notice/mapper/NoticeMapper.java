package com.ssafy.yeohaengdam.notice.mapper;

import com.ssafy.yeohaengdam.notice.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import static com.ssafy.yeohaengdam.notice.dto.NoticeData.*;

@Mapper
public interface NoticeMapper {

    public List<Detail> findAllNotices();

    public List<Detail> findAll(String keyword, int start, String sortBy, int size);

    public void create(Notice Notice);

    public void update(Notice Notice);

    public void delete(int noticeId);

    public Detail findById(int noticeId);

}
