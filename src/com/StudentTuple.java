package com;

public class StudentTuple {
    private String name;
    private int password;
    private int id;
    private String address;

    @SuppressWarnings("WeakerAccess")
    public StudentTuple() {
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public int getPassword() {
        return password;
    }

    void setPassword(int password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    void setAddress(String address) {
        this.address = address;
    }
}
