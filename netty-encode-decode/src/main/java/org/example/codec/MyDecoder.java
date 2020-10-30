package org.example.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

//用于处理解码，02开始 03结束
public class MyDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int size = in.readableBytes();
        byte[] bytes = new byte[size];

        in.readBytes(bytes);
        byte begin = bytes[0];
        byte end = bytes[bytes.length - 1];

        //无开始符，只有结束符，数据丢包
        if (begin != 0x02 && end == 0x03) {
            System.out.println("bytebuf数据丢包");
            ctx.writeAndFlush("error");
            return;
        }
        //有开始符，无结束符号，数据半包。置位指针，接收余下数据
        if (begin != 0x02 || end != 0x03) {
            in.resetReaderIndex();
            System.out.println("提示；byteBuf数据，有开始符，无结束符号，数据半包。置位指针，接收余下数据。");
            return;
        }
        // 数据完整
        // todo
        System.out.println(JSON.toJSONString(bytes));
        // 获取object
        ByteBuf copy = in.copy(1, size - 1);
        // 填充
        out.add(copy.toString(Charset.forName("GBK")));
    }
}
