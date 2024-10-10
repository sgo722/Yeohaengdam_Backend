package com.ssafy.yeohaengdam.spot.dto;

import lombok.Data;

public class SpotData {

    @Data
    public static class GugunCode{
        private int gugunCode;
        private String gugunName;
    }

    @Data
    public static class SpotInfo{
        private int contentId;
        private int contentTypeId;
        private String title;
        private String addr1;
        private String addr2;
        private String tel;
        private String zipcode;
        private String img1;
        private String img2;
        private int sidoCode;
        private int gugunCode;
        private double latitude;
        private double longitude;
        private String description;
    }
}
