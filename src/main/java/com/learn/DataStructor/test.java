package com.learn.DataStructor;

import com.learn.pojo.ResponseMessage;

public class test {
    public static void main(String[] args) {
        SingleLink link = new SingleLink<>();
        SingleLink<ResponseMessage> responseMessageSingleLink = new SingleLink<>();
        responseMessageSingleLink.add(ResponseMessage.GetSuccessMessage("测试一下"));
        responseMessageSingleLink.add(ResponseMessage.GetSuccessMessage("测试二下"));
        responseMessageSingleLink.add(ResponseMessage.GetSuccessMessage("测试三下"));
        link.add("1");
        link.add(responseMessageSingleLink);
        System.out.println(link.toString());
        link.inverse();
        System.out.println(link.toString());
        ((SingleLink)link.getValue(0)).inverse();
        System.out.println(link.toString());
    }
}
