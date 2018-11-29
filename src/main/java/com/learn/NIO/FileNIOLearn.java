package com.learn.NIO;

import javafx.scene.shape.CircleBuilder;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class FileNIOLearn {
    public static void main(String[] args) throws IOException {
//        FileToBinnaryStrFile("D:\\cache.txt");
//        FileOutputStream file = new FileOutputStream("D:/cache.txt");
//        FileChannel fileChannel = file.getChannel();
//        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
//        byteBuffer.put("卧槽".getBytes("utf-8"));
//        byteBuffer.put(byteArrayToStr("卧槽".getBytes("utf-8")).getBytes());
//        byteBuffer.put("|".getBytes());
//
//        byteBuffer.put("卧槽".getBytes("gb2312"));
//        byteBuffer.put(byteArrayToStr("卧槽".getBytes("gb2312")).getBytes());
//        byteBuffer.put("|".getBytes());
//
//        byteBuffer.put("卧槽".getBytes());
//        byteBuffer.put(byteArrayToStr("卧槽".getBytes()).getBytes());
//
//        byteBuffer.flip();
//        byteBuffer.mark();
//        fileChannel.write(byteBuffer);
////        byteBuffer = (ByteBuffer) byteBuffer.reset();
////        fileChannel.write(byteBuffer);
////        fileChannel.write(byteBuffer);
//        fileChannel.close();
//        ByteBuffer b2 = ByteBuffer.allocate(1024);
//       FileInputStream file1 = new FileInputStream("D:/cache.txt");
//        fileChannel = file1.getChannel();
//        fileChannel.read(b2);
//        if (b2.hasArray()){
//        String s = new String(b2.array(),"gb2312");
//        System.out.println(s);
//        }
        String def = new String();
        String iso8859 = new String("bianma".getBytes("ISO-8859-1"),"ISO-8859-1");
        String gb2312 = new String("编码".getBytes("gb2312"),"gb2312");
        String gbk = new String("编码".getBytes("gbk"),"gbk");
        String utf8 = new String("编码".getBytes("utf8"));
        System.out.println(iso8859);
        System.out.println(gb2312);
        System.out.println(new String(gb2312.getBytes(),"utf-8"));
        System.out.println(gbk);

    }

    public static String byteArrayToStr(byte[] objs){
        StringBuilder sb= new StringBuilder();
        for (int i = 0; i < objs.length; i++) {
            Object obj = objs[i];
            sb.append(obj+"");
        }
        return sb.toString();
    }

    public static void FileToBinnaryStrFile(String filepath) throws IOException {
        File sourcefile = new File(filepath);
        FileInputStream sourcefilein = new FileInputStream(sourcefile);
        FileOutputStream file_binnary = new FileOutputStream(new File(sourcefile.getParentFile().getPath()+"/"+sourcefile.getName().substring(0,sourcefile.getName().lastIndexOf(".")))+"_binarry.txt",true);
        FileChannel fic = sourcefilein.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        float totalsize = sourcefilein.available();
        float readedsize = 0l;
        int readcount=0;
        while ((readcount=fic.read(byteBuffer))!=-1){
            file_binnary.write(byteArrayToStr(byteBuffer.array()).getBytes());
            file_binnary.write(System.lineSeparator().getBytes());
            byteBuffer.clear();
            readedsize += readcount;
            System.out.println("当前进度为: " + (readedsize / totalsize) * 100l +"%");
        }
    }
}
