package main.java.com.too.example.fileReadWrite;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WriteToFile {

    public static void main(String[] args) throws IOException {
        AsynchronousFileChannel channel = AsynchronousFileChannel.
                open(Paths.get("/log.txt"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        CompletionHandler<Integer, Object> completionHandler = new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                System.out.println("Attachment: " + attachment + " " + result
                        + " bytes written");
                System.out.println("CompletionHandler Thread ID: "
                        + Thread.currentThread().getId());
                System.out.println();
            }

            @Override
            public void failed(Throwable e, Object attachment) {
                System.err.println("Attachment: " + attachment + " failed with:");
                e.printStackTrace();
            }
        };
        System.out.println("Main Thread iD: " + Thread.currentThread().getId());

        channel.write(ByteBuffer.wrap("sample".getBytes()), 0, "first write", completionHandler);
        channel.write(ByteBuffer.wrap("box".getBytes()), 0, "second write", completionHandler);

    }
}
