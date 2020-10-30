package main.java.com.too.example.fileReadWrite;

// AIO读取和写入文件

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

//public class AIO {
//
//
//    public static void main(String[] args) {
//        ReadFromFile readFromFile = new ReadFromFile();
//
//    }
//}



