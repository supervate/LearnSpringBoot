package com.learn.Websocket.controller;

import com.learn.Websocket.pojo.UserWsSession;
import com.learn.Websocket.utils.WebSocketUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/websocket")
public class WebSocketController {

    /**
     * @return 返回所有在线的用户
     */
    @RequestMapping("onlineusers")
    public List OnlineUsers(){
        List<String> olusers = new ArrayList<>();
        for (int i = 0; i < WebSocketUtil.getAllOnlineUserSession().size(); i++) {
            UserWsSession userWsSession = WebSocketUtil.getAllOnlineUserSession().get(i);
            olusers.add(userWsSession.getUserid());
        }
        return olusers;
    }

    /**
     * @param userId 发送用户的Id
     * @param message 发送的消息
     * @param acceptUserId  接收的用户Id list
     * @param isSendToAll 是否指定用户 指定则acceptUserId数组中的用户将收到消息
     */
    @RequestMapping("message")
    public void SendMessage(@RequestParam String userId,@RequestParam String message, String[] acceptUserId,@RequestParam(defaultValue = "true") boolean isSendToAll){
        //如果标志为向指定人发送，则取出接受列表中的用户，并转发消息
        message = userId + " : " + message;
        if (!isSendToAll){
            WebSocketUtil.sendToSomeoneMessage(message,acceptUserId);
        }
        else {
            WebSocketUtil.sendMessageForAll(message);
        }
    }
}
