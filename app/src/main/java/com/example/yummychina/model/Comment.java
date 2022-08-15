package com.example.yummychina.model;

import java.text.SimpleDateFormat;
import java.util.Date;

// This class is a model, encapsulating the comment infor needed by app
public class Comment {
    private String fromWhom;
    private String content;
    private Date date;

    public Comment(){};

    public Comment(String fromWhom, String content, Date date) {
        this.fromWhom = fromWhom;
        this.content = content;
        this.date = date;
    }

    public String getFromWhom() {
        return fromWhom;
    }

    public void setFromWhom(String fromWhom) {
        this.fromWhom = fromWhom;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateToString() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        return formatter.format(this.date);
    }

}
