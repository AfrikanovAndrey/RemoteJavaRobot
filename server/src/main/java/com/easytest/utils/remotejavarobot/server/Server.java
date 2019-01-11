package com.easytest.utils.remotejavarobot.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class Server {
    private static int MAX_TRANSFER_SIZE = 10485760;

    public Server() {
    }

    public static void main(String[] var0) throws IOException {
        String var1 = "";
        if (var0.length > 0) {
            var1 = var0[0];
        } else {
            var1 = "6666";
        }

        if (var1.length() == 4) {
            int var2 = Integer.parseInt(var1);
            NioSocketAcceptor var3 = new NioSocketAcceptor();
            ObjectSerializationCodecFactory var4 = new ObjectSerializationCodecFactory();
            var4.setDecoderMaxObjectSize(MAX_TRANSFER_SIZE);
            var4.setEncoderMaxObjectSize(MAX_TRANSFER_SIZE);
            var3.getFilterChain().addLast("codec", new ProtocolCodecFilter(var4));
            var3.setHandler(new ServerHandler());
            var3.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
            var3.bind(new InetSocketAddress(var2));
            System.out.println("Robotil server is ready... Port[" + var1 + "]");
        } else {
            System.out.println("Invalid port number");
        }

    }
}
