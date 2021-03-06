package com.propellerads.utils.remoterobot.server;

import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
    private static int MAX_TRANSFER_SIZE = 10485760;

    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    public static void main(String[] var0) throws IOException, AWTException {
        String portParamString;
        if (var0.length > 0) {
            portParamString = var0[0];
        } else {
            portParamString = "5555";
        }

        if (portParamString.length() == 4) {
            int port = Integer.parseInt(portParamString);
            NioSocketAcceptor socketAcceptor = new NioSocketAcceptor();
            ObjectSerializationCodecFactory objectSerializationCodecFactory = new ObjectSerializationCodecFactory();
            objectSerializationCodecFactory.setDecoderMaxObjectSize(MAX_TRANSFER_SIZE);
            objectSerializationCodecFactory.setEncoderMaxObjectSize(MAX_TRANSFER_SIZE);
            socketAcceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(objectSerializationCodecFactory));
            socketAcceptor.setHandler(new ServerHandler());
            socketAcceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
            socketAcceptor.bind(new InetSocketAddress(port));
            LOGGER.info("Remote Java Robot server is started on port " + portParamString);
        } else {
            LOGGER.error("Invalid port number");
        }
    }
}
