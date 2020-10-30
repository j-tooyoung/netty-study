package org.example.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

//用于处理编码，在byte开始和结束加上02 03
public class MyEncoder extends MessageToByteEncoder {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        String s = msg.toString();
        byte[] bytes = s.getBytes();
        byte[] send = new byte[bytes.length + 2];
        send[0] = 0x02;
        send[send.length - 1] = 0x03;
        System.arraycopy(bytes, 0, send, 1, bytes.length);

        out.writeInt(send.length);
        out.writeBytes(send);
    }
}
