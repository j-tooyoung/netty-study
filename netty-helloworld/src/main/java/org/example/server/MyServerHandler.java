package org.example.server;

import io.netty.channel.*;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Date;

// 使用Netty编写业务层的代码，我们需要继承ChannelInboundHandlerAdapter 或SimpleChannelInboundHandler类
//  继承SimpleChannelInboundHandler类之后，会在接收到数据后会自动release掉数据占用的Bytebuffer资源。并且继承该类需要指定数据格式。
// 而继承ChannelInboundHandlerAdapter则不会自动释放，需要手动调用
@ChannelHandler.Sharable
public class MyServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 为新连接发送庆祝
        ctx.write("Welcome to " + InetAddress.getLocalHost().getHostName() + "!\r\n");
        ctx.write("It is " + new Date() + " now.\r\n");
        ctx.flush();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }

    //业务逻辑处理
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // Generate and write a response.
        String response;
        boolean close = false;
        if (msg.isEmpty()) {
            response = "Please type something.\r\n";
        } else if ("bye".equals(msg.toLowerCase())) {
            response = "Have a good bye! \r\n";
            close = true;
        } else {
            response = "Did you say " + msg + "?\r\n";
        }
        ChannelFuture channelFuture = ctx.write(response);
        if (close) {
            channelFuture.addListener(ChannelFutureListener.CLOSE);
        }
    }
}
//public class MyServerHandler extends ChannelInboundHandlerAdapter {
//
//    /**
//     * 建立连接，发送一条庆祝消息
//     *
//     * @param ctx
//     * @throws Exception
//     */
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ctx.write("welcome to " + InetAddress.getLocalHost().getHostName());
//        ctx.write("It is  " + new Date() + " now.\r\n");
//        ctx.flush();
//    }
//
//    @Override
//    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//
//    }
//
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        ctx.flush();
//    }
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        ctx.close();
//        cause.printStackTrace();
//    }
//}
