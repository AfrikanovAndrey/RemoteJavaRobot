package com.propellerads.utils.remoterobot.server;

import com.propellerads.utils.remoterobot.commons.utils.KeyUtil;
import com.propellerads.utils.remoterobot.commons.utils.MouseUtil;
import com.propellerads.utils.remoterobot.commons.protocol.Request;
import com.propellerads.utils.remoterobot.commons.protocol.Response;
import com.propellerads.utils.remoterobot.commons.utils.ScreenUtil;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class ServerHandler extends IoHandlerAdapter {
    private Response response;

    public void sessionOpened(IoSession session) {
        session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        session.setAttribute("Values: ");
    }

    public void messageReceived(IoSession session, Object requestObject) {
        Request request = (Request) requestObject;

        switch (request.getCommand()) {
            case KEY_PRESS:
                response = new KeyUtil().keyPress(
                        Integer.parseInt(request.getOperands().get(0).toString())
                );
                break;

            case KEY_RELEASE:
                response = new KeyUtil().keyRelease(
                        Integer.parseInt(request.getOperands().get(0).toString())
                );
                break;

            case MOUSE_CLICK:

                response = new MouseUtil().mouseClick(
                        Integer.parseInt(request.getOperands().get(0).toString()),
                        Integer.parseInt(request.getOperands().get(1).toString()),
                        Integer.parseInt(request.getOperands().get(2).toString())
                );
                break;

            case CAPTURE_SCREEN:
                response = new ScreenUtil().captureScreen();
                break;

            case CAPTURE_SCREEN_PARTIALLY:
                response = new ScreenUtil().capturePartOfScreen(
                        Integer.parseInt(request.getOperands().get(0).toString()),
                        Integer.parseInt(request.getOperands().get(1).toString()),
                        Integer.parseInt(request.getOperands().get(2).toString()),
                        Integer.parseInt(request.getOperands().get(3).toString())
                );
                break;


            case GET_PIXEL_COLOR:
                response = new ScreenUtil().getColor(
                        Integer.parseInt(request.getOperands().get(0).toString()),
                        Integer.parseInt(request.getOperands().get(1).toString())
                );
                break;

            default:
                throw new RuntimeException("Not supported operation: " + request.getCommand());
        }

        session.write(this.response);
        session.close(false);
        this.response = null;
    }

    public void sessionIdle(IoSession session, IdleStatus idleStatus) {
        session.close(true);
    }

    public void exceptionCaught(IoSession session, Throwable cause) {
        cause.printStackTrace();
        session.close(true);
    }
}
