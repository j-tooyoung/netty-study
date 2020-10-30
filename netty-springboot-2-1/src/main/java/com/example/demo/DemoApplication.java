package com.example.demo;

import com.example.demo.server.NettyServer;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Value("${netty.host}")
    private String host;
    @Value("${netty.port}")
    private int port;

    @Autowired
    private NettyServer nettyServer;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(host, port);
        ChannelFuture channelFuture = nettyServer.bind(inetSocketAddress);
        // 钩子方法
        Runtime.getRuntime().addShutdownHook(new Thread(() -> nettyServer.destroy()));
        // todo 区别
//        channelFuture.channel().close();
        channelFuture.channel().closeFuture().syncUninterruptibly();
    }
}
