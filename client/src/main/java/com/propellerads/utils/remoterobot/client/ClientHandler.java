package com.propellerads.utils.remoterobot.client;

import com.propellerads.utils.remoterobot.commons.protocol.Request;
import com.propellerads.utils.remoterobot.commons.protocol.Response;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class ClientHandler extends IoHandlerAdapter {
    private final Request request;
    private boolean finished;
    private Response response;

    public ClientHandler(Request request) {
        this.request = request;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public Response getResponse() {
        return this.response;
    }

    @Override
    public void sessionOpened(IoSession session) {
        session.write(request);
    }

    @Override
    public void messageReceived(IoSession session, Object rawCommandResponse) {
        this.response = (Response)rawCommandResponse;
        System.out.println(response.getMessage());
    }

    public void exceptionCaught(IoSession var1, Throwable var2) {
        var2.printStackTrace();
        var1.close(true);
    }
}
