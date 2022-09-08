package io.iti.modules.iot.socket.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.iti.common.utils.MsgUtils;
import io.iti.modules.iot.product.entity.RuleEntity;
import io.iti.modules.iot.product.service.RuleService;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.netty.channel.socket.DatagramPacket;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
@ChannelHandler.Sharable
public class UdpMessageHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    String m;
    @Autowired
    private RuleService ruleService;
//    private RuleEntity ruleEntity;
    @Value("${netty.udpsocket.path}")
    private String path;
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleChannelInboundHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            String uri = fullHttpRequest.uri();
            if (!uri.equals(path)) {
                ctx.channel().writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND))
                        .addListener(ChannelFutureListener.CLOSE);
                return;
            }
        }
        else {
            if (acceptInboundMessage(msg)) {
                this.channelRead0(ctx, (DatagramPacket) msg);
            } else {
                ctx.fireChannelRead(msg);
            }
        }
        ReferenceCountUtil.release(msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        LOGGER.info("Recv UDP client msg from sender remote addr:"+msg.sender().getAddress() +":"+msg.sender().getPort());
        LOGGER.info(msg.content().toString(CharsetUtil.UTF_8));
        m = msg.content().toString(CharsetUtil.UTF_8);
        MsgUtils.MsgAnalysis(m,ruleService);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        LOGGER.info("UDP Channel is Active");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        LOGGER.info("UDP Channel is Not Active");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}

