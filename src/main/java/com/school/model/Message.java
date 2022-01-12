package com.school.model;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue
    private long id;
    
    private String username; //источник сообщения
    private Date dat; //дата сообщения
    private String sendname; //приемник сообщения
    private String send; //сообщение

    public Message() {
    }

    public Message(String username, Date dat, String sendname, String send) {
        this.username = username;
        this.dat = dat;
        this.sendname = sendname;
        this.send = send;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDat() {
        return dat;
    }

    public void setDat(Date dat) {
        this.dat = dat;
    }

    public String getSend() {
        return send;
    }

    public String getSendname() {
        return sendname;
    }

    public void setSendname(String sendname) {
        this.sendname = sendname;
    }

    public void setSend(String send) {
        this.send = send;
    }
    
    
}
