package com.ssafy.yeohaengdam.spot.mapper;

import com.ssafy.yeohaengdam.spot.dto.SpotData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import static com.ssafy.yeohaengdam.spot.dto.SpotData.*;

@Mapper
public interface SpotMapper {


    public List<GugunCode> findGugunBySido(int sidoCode, String sidoName);

    public List<SpotInfo> findSpotByCondition(int sidoCode, int gugunCode, int contentCode);
}
