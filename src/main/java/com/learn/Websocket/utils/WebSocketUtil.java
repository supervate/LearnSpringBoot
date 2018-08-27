package com.learn.Websocket.utils;

import com.learn.Websocket.pojo.UserWsSession;
import org.springframework.messaging.converter.StringMessageConverter;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketUtil {

    /**
     * 简单使用map进行存储在线的session
     *
     */
    private static final Map<String, UserWsSession> ONLINE_SESSION = new ConcurrentHashMap<>();

    public static void addSession(String userId,Session session) {
        //putIfAbsent 添加键—值对的时候，先判断该键值对是否已经存在
        //不存在：新增，并返回null
        //存在：不覆盖，直接返回已存在的值
//        ONLINE_SESSION.putIfAbsent(userNick, session);
        //简单示例 不考虑复杂情况。。怎么简单怎么来了。。
        ONLINE_SESSION.put(userId,new UserWsSession(userId,session ));
    }

    public static void removeSession(String userId) {
        ONLINE_SESSION.remove(userId);
    }

    /**
     * @param message 发送的消息
     * @param userId 接收方的userid 多个
     * @return 未收到信息的userid
     */
    public static List<String> sendToSomeoneMessage(String message, String...userId){
        List<String> offuserId = new ArrayList<>();
        for (int i = 0; i < userId.length; i++) {
            String userid = userId[i];
            if (null == ONLINE_SESSION.get(userid)) {
                offuserId.add(userid);
                continue;
            }
            sendMessage(ONLINE_SESSION.get(userid).getSession(),message);
        }
        return offuserId;
    }
    /**
     * 向某个用户发送消息
     * @param session 某一用户的session对象
     * @param message
     */
    public static void sendMessage(Session session, String message) {
        if(session == null) {
            return;
        }
        // getAsyncRemote()和getBasicRemote()异步与同步
        RemoteEndpoint.Async async = session.getAsyncRemote();
        //发送消息
        async.sendText(message);
    }

    /**
     * 向所有在线人发送消息
     * @param message
     */
    public static void sendMessageForAll(String message) {
        //jdk8 新方法
        ONLINE_SESSION.forEach((userId, userWsSession) -> sendMessage(userWsSession.getSession(), message));
    }

    public static List<UserWsSession> getAllOnlineUserSession(){
        return new ArrayList<>(ONLINE_SESSION.values());
    }
}