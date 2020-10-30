package main.java.com.too.example.aio.client;

import main.java.com.too.example.aio.ChannelAdapter;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;

/**
 * 客户端消息处理器
 */
public class AioClientHandler extends ChannelAdapter {

    public AioClientHandler(AsynchronousSocketChannel channel, Charset charset) {
        super(channel, charset);
    }

    @Override
    public void completed(Integer result, Object attachment) {

    }

    @Override
    public void failed(Throwable exc, Object attachment) {

    }
}
