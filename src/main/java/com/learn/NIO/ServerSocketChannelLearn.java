package com.learn.NIO;

import com.mysql.fabric.xmlrpc.Client;

import javax.persistence.criteria.Selection;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class ServerSocketChannelLearn {
    public static void main(String[] args) throws IOException {
//        startServer();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        System.out.println(buffer.position());//0 返回缓冲区的当前位置 position
        System.out.println(buffer.limit());//1024 返回 Buffer 的界限(limit) 的位置
        System.out.println(buffer.capacity());//1024 返回Buffer的capacity 大小
        System.out.println("---------------------------------");
        //将数据存入缓冲区
        buffer.put("中文".getBytes());

        System.out.println(buffer.position());//5
        System.out.println(buffer.limit());//1024
        System.out.println(buffer.capacity());//1024
        System.out.println("---------------------------------");

        //切换到读取数据的模式
        buffer.flip();

        System.out.println(buffer.position());//0
        System.out.println(buffer.limit());//5
        System.out.println(buffer.capacity());//1024
        System.out.println("---------------------------------");

        buffer.rewind();

        System.out.println(buffer.position());//0
        System.out.println(buffer.limit());//5
        System.out.println(buffer.capacity());//1024
        System.out.println("---------------------------------");

        buffer.clear();
        System.out.println(buffer.position());//0
        System.out.println(buffer.limit());//5
        System.out.println(buffer.capacity());//1024

    }

    public static void startServer() throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            Selector selector = Selector.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8888));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            selector.select();
            System.out.println("选择完毕");
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                //清除已被解决的事件
                iterator.remove();
                if (key.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel client = server.accept();
                    client.configureBlocking(false);
                    client.write(ByteBuffer.wrap("你访问成功了.".getBytes("utf-8")));
                    client.register(selector, SelectionKey.OP_READ);
                }
                if (key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    socketChannel.read(byteBuffer);
                }
            }
        }
    }
}
