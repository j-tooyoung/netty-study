package com.example.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

//NettyClient客户端，用于测试
public class ApiTest {

    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();


        try {
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.AUTO_READ, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            socketChannel.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
                            socketChannel.pipeline().addLast(new StringEncoder(Charset.forName("UTF-8")));
                            socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 客户端接收到消息：" + msg);
                                }
                            });
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8080).sync();
            System.out.println(" client start done. ");

            //向服务端发送信息
            channelFuture.channel().writeAndFlush("你好，SpringBoot启动的netty服务端，我是https://bugstack.cn博主，付政委。这是我的公众号<bugstack虫洞栈>，关注我获取全套源码。“我的结尾是一个换行符，用于传输半包粘包处理”\r\n");
            channelFuture.channel().writeAndFlush("你好，SpringBoot启动的netty服务端，我是https://bugstack.cn博主，付政委。这是我的公众号<bugstack虫洞栈>，关注我获取全套源码。“我的结尾是一个换行符，用于传输半包粘包处理”\r\n");
            channelFuture.channel().writeAndFlush("你好，SpringBoot启动的netty服务端，我是https://bugstack.cn博主，付政委。这是我的公众号<bugstack虫洞栈>，关注我获取全套源码。“我的结尾是一个换行符，用于传输半包粘包处理”\r\n");
            channelFuture.channel().writeAndFlush("你好，SpringBoot启动的netty服务端，我是https://bugstack.cn博主，付政委。这是我的公众号<bugstack虫洞栈>，关注我获取全套源码。“我的结尾是一个换行符，用于传输半包粘包处理”\r\n");
            channelFuture.channel().writeAndFlush("你好，SpringBoot启动的netty服务端，我是https://bugstack.cn博主，付政委。这是我的公众号<bugstack虫洞栈>，关注我获取全套源码。“我的结尾是一个换行符，用于传输半包粘包处理”\r\n");
            channelFuture.channel().closeFuture().syncUninterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }
}
