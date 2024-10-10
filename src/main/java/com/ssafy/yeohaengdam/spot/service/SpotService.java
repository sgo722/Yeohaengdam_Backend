package com.ssafy.yeohaengdam.spot.service;

import com.ssafy.yeohaengdam.spot.dto.SpotData;

import java.util.List;

import static com.ssafy.yeohaengdam.spot.dto.SpotData.*;

public interface SpotService {

    public List<GugunCode> findGugunBySido(int sidoCode, String sidoName);

    public List<SpotInfo> findSpotByCondition(int sidoCode, int gugunCode, int contentCode);
}
