package io.iti.modules.iot.socket.runner;

import io.iti.modules.iot.socket.handler.TcpMessageHandler;
import io.iti.modules.iot.socket.handler.UdpMessageHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class NettyBootstrapRunner implements ApplicationRunner, ApplicationListener<ContextClosedEvent>, ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyBootstrapRunner.class);

    @Value("${netty.udpsocket.port}")
    private int udp_port;

    @Value("${netty.tcpsocket.port}")
    private int tcp_port;

    @Value("${netty.udpsocket.ip}")
    private String ip;

    @Value("${netty.udpsocket.max-frame-size}")
    private long maxFrameSize;

    private ApplicationContext applicationContext;

    private Channel udp_channel,tcp_channel;

    private NioEventLoopGroup udp_acceptGroup,bossGroup,workerGroup;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void run(ApplicationArguments args) throws Exception {

        try {
            //启动udp
            Bootstrap udp_bsp = new Bootstrap();
            this.udp_acceptGroup = new NioEventLoopGroup();
            udp_bsp.group(udp_acceptGroup).channel(NioDatagramChannel.class).option(ChannelOption.SO_BROADCAST,true)
                    .handler(new ChannelInitializer<NioDatagramChannel>() {
                        @Override
                        protected void initChannel(NioDatagramChannel nioDatagramChannel) throws Exception {
                            nioDatagramChannel.pipeline().addLast(applicationContext.getBean(UdpMessageHandler.class));
                        }});
            this.udp_channel = udp_bsp.bind(this.ip,this.udp_port).sync().channel();
            LOGGER.info("udpsocket server started at ip={},port={}", this.ip, this.udp_port);

            //启动tcp
            this.bossGroup = new NioEventLoopGroup();
            this.workerGroup = new NioEventLoopGroup();

            ServerBootstrap tcp_bsp = new ServerBootstrap();
            tcp_bsp.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).
                    childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(applicationContext.getBean(TcpMessageHandler.class));
                        }
                    });
            tcp_bsp.option(ChannelOption.SO_BACKLOG,128);
            tcp_bsp.childOption(ChannelOption.SO_KEEPALIVE,true);
            this.tcp_channel = tcp_bsp.bind(this.ip,this.tcp_port).sync().channel();
            LOGGER.info("tcpsocket server started at ip={},port={}", this.ip, this.tcp_port);

//            一定要写在最后面，否则当前线程会被阻塞
            this.udp_channel.closeFuture().sync();
            this.tcp_channel.closeFuture().sync();


        } finally {
            this.udp_acceptGroup.shutdownGracefully();
            this.bossGroup.shutdownGracefully();
            this.workerGroup.shutdownGracefully();
        }

    }

    public void onApplicationEvent(ContextClosedEvent event) {
        if (this.tcp_channel != null || this.udp_channel !=null) {
            this.tcp_channel.close();
            LOGGER.info("tcpSocket server stopped.");
            this.udp_channel.close();
            LOGGER.info("udpSocket server stopped.");
        }
        LOGGER.info("Bootstrap Application stopped.");

    }
}
