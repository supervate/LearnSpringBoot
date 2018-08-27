package com.learn.Websocket.pojo;

import javax.websocket.Session;

public class UserWsSession {
    String userid;
    Session session;

    public UserWsSession(String userid, Session session) {
        this.userid = userid;
        this.session = session;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
