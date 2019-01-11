package com.easytest.utils.remotejavarobot.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.util.ArrayList;
import java.util.List;

public class ClientHandler extends IoHandlerAdapter {
    private final List value;
    private boolean finished;
    private List response;

    public ClientHandler(List var1) {
        this.value = var1;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public List getResponse() {
        return this.response;
    }

    public void sessionOpened(IoSession var1) {
        var1.write(this.value);
    }

    public void messageReceived(IoSession var1, Object var2) {
        this.response = (ArrayList)var2;
        String var3 = this.response.get(0).toString();
        String var4 = this.response.get(1).toString();
        System.out.println(var4);
    }

    public void exceptionCaught(IoSession var1, Throwable var2) {
        var2.printStackTrace();
        var1.close(true);
    }
}
