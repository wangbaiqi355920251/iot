package io.iti.modules.iot.socket.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class TcpMessageHandler extends ChannelInboundHandlerAdapter {

    private final static Logger LOGGER = LoggerFactory.getLogger(TcpMessageHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ByteBuf in = (ByteBuf) msg;
            int readableBytes = in.readableBytes();
            byte[] bytes = new byte[readableBytes];
            in.readBytes(bytes);
            LOGGER.info("read from client:"+ new String(bytes));

        }
        finally {
            ReferenceCountUtil.release(msg);
        }
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String ip = ctx.channel().remoteAddress().toString();
        String port = ctx.channel().id().toString();
        LOGGER.info("TCP Channel is Active from {}",ip);
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String ip = ctx.channel().remoteAddress().toString();
        String port = ctx.channel().id().toString();
        LOGGER.info("TCP Channel is disconnected to {}",ip);
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
