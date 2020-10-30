package main.java.com.too.example.fileReadWrite;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ReadFromFile {

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        Path path = Paths.get("D:\\log.txt");
        //
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path);

        ByteBuffer buffer = ByteBuffer.allocate(100_000);
        // 返回一个Future接口
        Future<Integer> res = channel.read(buffer, 0);
        while (!res.isDone()) {
            ProfitCalculator.calculateTax();
        }
        int bytesRead = res.get();
        System.out.println("bytes read [" + bytesRead + "]");
        //输出结果
        byte[] array = buffer.array();
        System.out.println(array.toString());   //
        System.out.println(new String(array));

        System.out.write(array, 0, array.length);
    }
}
