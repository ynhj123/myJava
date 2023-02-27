package com.ynhj.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @date: 2023/2/24
 * @author: yangniuhaojiang
 * @title: CoustomDecode
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class CustomDecodeByteMessage extends ByteToMessageDecoder {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 2) {
            return;
        }
        //记录当前ByteBuf的读指针位置，以便下面取报文长度字节
        //pos是一个完整报文的开始位置，报文整体会在ByteBuf中移动，类似内存管理，所以基于字节的判断报文长度等等，都是基于pos，
        // 否则可以在byteBuf.readBytes（）之后加，byteBuf.discardReadBytes();整理ByteBuf，使pos回到0开始位置
        int pos = byteBuf.readerIndex();
        LOGGER.debug(pos);
        int msgLen = ((byteBuf.getByte(pos + 1)) << 8) | (byteBuf.getByte(pos));

        //收到的报文长度不足一个完整的报文，继续接收
        if (byteBuf.readableBytes() < msgLen) {
            return;
        }

        byte[] msgContent = new byte[msgLen + 2];
        byteBuf.readBytes(msgContent);
        //提出完整报文(readBytes读到msg中)，放到list给下一个Handler处理
        if (msgLen > 0) {
            list.add(msgContent);
        }

    }
}
