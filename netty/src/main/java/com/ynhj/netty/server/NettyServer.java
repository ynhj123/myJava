package com.ynhj.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetSocketAddress;

/**
 * @date: 2023/2/23
 * @author: yangniuhaojiang
 * @title: NettyServer
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class NettyServer {
    private static final Logger LOGGER = LogManager.getLogger(NettyServer.class);
    // 通过nio方式来接收连接和处理连接
    private EventLoopGroup bg = new NioEventLoopGroup();
    private EventLoopGroup wg = new NioEventLoopGroup();

    // 启动引导器
    private ServerBootstrap b = new ServerBootstrap();


    public void run() {
        //1 设置reactor 线程
        b.group(bg, wg);
        //2 设置nio类型的channel
        b.channel(NioServerSocketChannel.class);
        //3 设置监听端口
        String ip = "0.0.0.0";
        b.localAddress(new InetSocketAddress(ip, 8888));
        //4 设置通道选项
        b.option(ChannelOption.SO_KEEPALIVE, true);
        b.option(ChannelOption.ALLOCATOR,
                PooledByteBufAllocator.DEFAULT);

        //5 装配流水线
        b.childHandler(new ChannelInitializer<SocketChannel>() {
            //有连接到达时会创建一个channel
            protected void initChannel(SocketChannel ch) throws Exception {
                // 管理pipeline中的Handler
//                ch.pipeline().addLast(new MyDecode());
                ch.pipeline().addLast(new CustomDecodeByteMessage());

//                ch.pipeline().addLast(new MyDecoderName());

            }
        });
        // 6 开始绑定server
        // 通过调用sync同步方法阻塞直到绑定成功

        ChannelFuture channelFuture = null;
        boolean isStart = false;
        while (!isStart) {
            try {
                channelFuture = b.bind().sync();
                LOGGER.info("server启动, 端口为： " + channelFuture.channel().localAddress());
                isStart = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            // 7 监听通道关闭事件
            // 应用程序会一直等待，直到channel关闭
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            closeFuture.sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 8 优雅关闭EventLoopGroup，
            // 释放掉所有资源包括创建的线程
            wg.shutdownGracefully();
            bg.shutdownGracefully();
        }

    }
}
