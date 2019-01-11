package com.easytest.utils.remotejavarobot.client;


import com.easytest.utils.remotejavarobot.commons.Base64;
import com.easytest.utils.remotejavarobot.commons.SupportedOperations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Robotil {
    private int inPort;
    private String strIP;
    private Client client;

    public Robotil(String ip, int port) {
        this.strIP = ip;
        this.inPort = port;
        this.client = new Client();
    }

    public void changePort(int var1) {
        this.inPort = var1;
    }

    public void changeIP(String var1) {
        this.strIP = var1;
    }

    public boolean pressAndReleaseKey(int key) {
        boolean var2 = false;
        this.pressKey(key);
        this.releaseKey(key);
        return true;
    }

    public boolean pressKey(int key) {
        ArrayList command = new ArrayList();
        command.add(SupportedOperations.KEYPRESS);
        command.add(key);
        Client var4 = new Client();
        return client.sendMessage(command, this.strIP, this.inPort);
    }

    public boolean putFile(String var1, String var2) {
        boolean var3 = false;

        try {
            ArrayList command = new ArrayList();
            command.add(SupportedOperations.PUTFILE);
            FileInputStream var5 = new FileInputStream(var1);
            command.add(var5);
            command.add(var2);
            Client var6 = new Client();
            var3 = var6.sendMessage(command, this.strIP, this.inPort);
        } catch (Exception var7) {
            ;
        }

        return var3;
    }

    public boolean captureScreen(String var1) {
        boolean var2 = false;

        try {
            ArrayList var3 = new ArrayList();
            var3.add(SupportedOperations.CAPTURESCREEN);
            Client var4 = new Client();
            List var5 = var4.getRemoteScreen(var3, this.strIP, this.inPort);
            if (var5.size() == 3) {
                Base64 var6 = new Base64();
                byte[] var7 = Base64.decode(var5.get(2).toString());
                File var8 = new File(var1);
                FileOutputStream var9 = new FileOutputStream(var8);
                var9.write(var7);
                var9.close();
                var9.flush();
                var2 = true;
            } else {
                var2 = false;
            }
        } catch (Exception var10) {
            var2 = false;
        }

        return var2;
    }

    public boolean invokeApplication(String var1) {
        boolean var2 = false;
        ArrayList var3 = new ArrayList();
        var3.add(SupportedOperations.INVOKEAPPLICATION);
        var3.add(var1);
        Client var4 = new Client();
        var2 = var4.sendMessage(var3, this.strIP, this.inPort);
        return var2;
    }

    public boolean mouseClick(int var1, int var2, int var3) {
        boolean var4 = false;
        ArrayList var5 = new ArrayList();
        var5.add(SupportedOperations.MOUSECLICK);
        var5.add("" + var1);
        var5.add("" + var2);
        var5.add("" + var3);
        Client var6 = new Client();
        var4 = var6.sendMessage(var5, this.strIP, this.inPort);
        return var4;
    }

    public boolean releaseKey(int var1) {
        boolean var2 = false;
        ArrayList var3 = new ArrayList();
        var3.add(SupportedOperations.KEYRELEASE);
        var3.add(var1);
        Client var4 = new Client();
        var2 = var4.sendMessage(var3, this.strIP, this.inPort);
        return var2;
    }

    private boolean sendCommandMessage(List<Object> command){
        return client.sendMessage(command, strIP, inPort);
    }
}
