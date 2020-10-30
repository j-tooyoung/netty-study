package main.java.com.too.example.fileReadWrite;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

// nio读取和写文件
public class NIO {

    static void readNIO() {
        String pathNam = "D:\\log.txt";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(pathNam));
            FileChannel channel = fis.getChannel();

            int capacity = 100;
            ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);
            System.out.println(byteBuffer.limit() + " " + byteBuffer.capacity() + " " + byteBuffer.position());
            int len = -1;
            while ((len = channel.read(byteBuffer)) != -1) {
                //				 * 注意，读取后，将位置置为0，将limit置为容量, 以备下次读入到字节缓冲中，从0开始存储
                byteBuffer.clear();
                byte[] bytes = byteBuffer.array();

                System.out.write(bytes, 0, len);
                System.out.println(bytes);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    static void writeNIO() {
        String fileName = "D:\\out.txt";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(fileName));
            FileChannel channel = fos.getChannel();
            ByteBuffer src = Charset.forName("utf-8").encode("too哈哈");
            // 字节缓冲的容量和limit会随着数据长度变化，不是固定不变的
            System.out.println("初始化容量和limit: " + src.capacity() + " " + src.limit());
            int len = 0;
            // write != -1会死循环
            while ((len = channel.write(src)) != 0) {
            // 注意，这里不需要clear，将缓冲中的数据写入到通道中后 第二次接着上一次的顺序往下读
                System.out.println("写入长度：" + len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
//        readNIO();
        writeNIO();
    }

}
