package com.login;

import java.util.Date;

public class Query {
    // 房间类型
    private String roomStyle;
    // 房价
    //
    //
    //
    // 预定开始时间
    private Date startDate;
    // 预定结束时间
    private Date endDate;
    // 人数
    private int numOfPeople;

    public Query(){

    }

    public String getRoomStyle() {
        return roomStyle;
    }

    public void setRoomStyle(String roomStyle) {
        this.roomStyle = roomStyle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }
}
