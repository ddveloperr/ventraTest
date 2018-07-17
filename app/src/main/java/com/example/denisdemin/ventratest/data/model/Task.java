package com.example.denisdemin.ventratest.data.model;

public class Task {
    private String header;
    private String date;
    private String comments;
    private String status;

    public String statusNew="New";
    public String statusPending="Pending";
    public String statusDone="Done";


    public Task(String header, String date, String comments) {
        this.header = header;
        this.date = date;
        this.comments = comments;
        this.status = statusNew;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
