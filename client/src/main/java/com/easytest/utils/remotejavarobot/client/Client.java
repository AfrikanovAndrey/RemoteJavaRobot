package com.easytest.utils.remotejavarobot.client;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private static int MAX_TRANSFER_SIZE = 10485760;

    public Client() {
    }

    public boolean sendMessage(List commandOperands, String ip, int port) {
        boolean var4 = false;

        try {
            NioSocketConnector var5 = new NioSocketConnector();
            ObjectSerializationCodecFactory var6 = new ObjectSerializationCodecFactory();
            var6.setDecoderMaxObjectSize(MAX_TRANSFER_SIZE);
            var6.setEncoderMaxObjectSize(MAX_TRANSFER_SIZE);
            var5.getFilterChain().addLast("codec", new ProtocolCodecFilter(var6));
            var5.setHandler(new ClientHandler(commandOperands));
            ConnectFuture var7 = var5.connect(new InetSocketAddress(ip, port));
            var7.awaitUninterruptibly();
            if (var7.isConnected()) {
                IoSession var8 = var7.getSession();
                var8.getConfig().setUseReadOperation(true);
                var8.getCloseFuture().awaitUninterruptibly();
                var8.close(true);
                var4 = true;
            } else {
                var4 = false;
                System.out.println("Server is not reachable... IP[" + ip + "] " + "Port[" + port + "]");
            }

            var5.dispose();
        } catch (Exception var9) {
            var4 = false;
        }

        return var4;
    }

    public List getRemoteScreen(ArrayList var1, String var2, int var3) {
        List var4 = null;

        try {
            NioSocketConnector var5 = null;
            var5 = new NioSocketConnector();
            ObjectSerializationCodecFactory var6 = new ObjectSerializationCodecFactory();
            var6.setDecoderMaxObjectSize(MAX_TRANSFER_SIZE);
            var6.setEncoderMaxObjectSize(MAX_TRANSFER_SIZE);
            var5.getFilterChain().addLast("codec", new ProtocolCodecFilter(var6));
            var5.setHandler(new ClientHandler(var1));
            ConnectFuture var7 = var5.connect(new InetSocketAddress(var2, var3));
            var7.awaitUninterruptibly();
            if (var7.isConnected()) {
                IoSession var8 = var7.getSession();
                var8.getConfig().setUseReadOperation(true);
                var8.getCloseFuture().awaitUninterruptibly();
                var4 = ((ClientHandler) var8.getHandler()).getResponse();
                var8.close(true);
            } else {
                System.out.println("Server is not reachable... IP[" + var2 + "] " + "Port[" + var3 + "]");
            }

            var5.dispose();
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        return var4;
    }
}
