package com.propellerads.utils.remotejavarobot.client;

import com.propellerads.utils.remoterobot.commons.protocol.Request;
import com.propellerads.utils.remoterobot.commons.protocol.Response;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class Client {
    private static int MAX_TRANSFER_SIZE = 10485760;

    private static Logger LOGGER = LoggerFactory.getLogger(Client.class);

    public Client() {
    }

    public Response sendMessage(Request request, String ip, int port) {
        Response response = null;

        try {
            NioSocketConnector socketConnector = new NioSocketConnector();
            ObjectSerializationCodecFactory factory = new ObjectSerializationCodecFactory();
            factory.setDecoderMaxObjectSize(MAX_TRANSFER_SIZE);
            factory.setEncoderMaxObjectSize(MAX_TRANSFER_SIZE);
            socketConnector.getFilterChain().addLast("codec", new ProtocolCodecFilter(factory));
            socketConnector.setHandler(new ClientHandler(request));
            ConnectFuture connectFuture = socketConnector.connect(new InetSocketAddress(ip, port));
            connectFuture.awaitUninterruptibly();
            if (connectFuture.isConnected()) {
                IoSession session = connectFuture.getSession();
                session.getConfig().setUseReadOperation(true);
                session.getCloseFuture().awaitUninterruptibly();
                response = ((ClientHandler) session.getHandler()).getResponse();
                session.close(true);
            } else {
                LOGGER.error("Server is not reachable... IP[" + ip + "] " + "Port[" + port + "]");
            }
            socketConnector.dispose();
        } catch (Exception e) {
            LOGGER.error("Error in interaction with Remote Robot Server: " + e.getMessage() );
        }

        // Log result of interaction
        if (response.isSuccess()) {
            LOGGER.info(response.getMessage());
        } else {
            LOGGER.error(response.getMessage());
        }

        return response;
    }
}
