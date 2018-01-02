package com.data_structure;

import java.util.Date;

public class receptionist {
    private String receptionist_no;
    private String receptionist_name;
    private String receptionist_telNumber;
    private Date work_time;
    private String receptionist_password;
    private String hotel_no;

    public receptionist() {
    }

    public String getReceptionist_no() {
        return receptionist_no;
    }

    public void setReceptionist_no(String receptionist_no) {
        this.receptionist_no = receptionist_no;
    }

    public String getReceptionist_name() {
        return receptionist_name;
    }

    public void setReceptionist_name(String receptionist_name) {
        this.receptionist_name = receptionist_name;
    }

    public String getReceptionist_telNumber() {
        return receptionist_telNumber;
    }

    public void setReceptionist_telNumber(String receptionist_telNumber) {
        this.receptionist_telNumber = receptionist_telNumber;
    }

    public Date getWork_time() {
        return work_time;
    }

    public void setWork_time(Date work_time) {
        this.work_time = work_time;
    }

    public String getReceptionist_password() {
        return receptionist_password;
    }

    public void setReceptionist_password(String receptionist_password) {
        this.receptionist_password = receptionist_password;
    }

    public String getHotel_no() {
        return hotel_no;
    }

    public void setHotel_no(String hotel_no) {
        this.hotel_no = hotel_no;
    }
}
