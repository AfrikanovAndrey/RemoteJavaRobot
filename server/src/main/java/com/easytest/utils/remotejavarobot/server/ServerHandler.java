package com.easytest.utils.remotejavarobot.server;

import com.easytest.utils.remotejavarobot.commons.KeyUtil;
import com.easytest.utils.remotejavarobot.commons.MouseUtil;
import com.easytest.utils.remotejavarobot.commons.SupportedOperations;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.util.ArrayList;

public class ServerHandler extends IoHandlerAdapter {
    ArrayList responseMessage;

    public ServerHandler() {
    }

    public void sessionOpened(IoSession var1) {
        var1.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        var1.setAttribute("Values: ");
    }

    public void messageReceived(IoSession var1, Object var2) {
        ArrayList var3 = (ArrayList)var2;
        String var4 = var3.get(0).toString();
        SupportedOperations var5 = SupportedOperations.valueOf(var4);
        switch(var5) {
            case KEYPRESS:
            case KEYRELEASE:
                int var6 = Integer.parseInt(var3.get(1).toString());
                KeyUtil var7 = new KeyUtil();
                this.responseMessage = var7.action(var5, var6);
                var7.dispose();
                break;
//            case INVOKEAPPLICATION:
//                String var8 = var3.get(1).toString();
//                RunUtil var9 = new RunUtil();
//                this.responseMessage = var9.InvokeApplication(var8);
//                break;
//            case PUTFILE:
//                InputStream var10 = (InputStream)var3.get(1);
//                String var11 = var3.get(2).toString();
//                FileUtil var12 = new FileUtil();
//                this.responseMessage = var12.PutFile(var10, var11);
//                break;
            case MOUSECLICK:
                int var13 = Integer.parseInt((String)var3.get(1));
                int var14 = Integer.parseInt((String)var3.get(2));
                int var15 = Integer.parseInt((String)var3.get(3));
                MouseUtil var16 = new MouseUtil();
                this.responseMessage = var16.mouseClick(var13, var14, var15);
                break;
//            case CAPTURESCREEN:
//                ScreenshotUtil var17 = new ScreenshotUtil();
//                this.responseMessage = var17.captureScreen();
//                var17.dispose();
//                break;
            default:
                throw new RuntimeException("Not supported operation: " + var5.toString());
        }

        var1.write(this.responseMessage);
        var1.close(false);
        this.responseMessage.clear();
    }

    public void sessionIdle(IoSession var1, IdleStatus var2) {
        var1.close(true);
    }

    public void exceptionCaught(IoSession var1, Throwable var2) {
        var2.printStackTrace();
        var1.close(true);
    }
}
