package com.ssafy.yeohaengdam.spot.service;

import com.ssafy.yeohaengdam.spot.dto.SpotData;
import com.ssafy.yeohaengdam.spot.mapper.SpotMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ssafy.yeohaengdam.spot.dto.SpotData.*;

@Service
@RequiredArgsConstructor
public class SpotServiceImpl implements SpotService{

    private final SpotMapper spotMapper;
    @Override
    public List<GugunCode> findGugunBySido(int sidoCode, String sidoName) {
        return spotMapper.findGugunBySido(sidoCode, sidoName);
    }

    @Override
    public List<SpotInfo> findSpotByCondition(int sidoCode, int gugunCode, int contentCode) {
        return spotMapper.findSpotByCondition(sidoCode, gugunCode, contentCode);
    }
}
