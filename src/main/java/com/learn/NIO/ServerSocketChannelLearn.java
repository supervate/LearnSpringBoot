package com.learn.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class ServerSocketChannelLearn {
    public static void main(String[] args) {

    }
    public static void startServer() throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();
        serverSocketChannel.bind(new InetSocketAddress(8888));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    }
}
