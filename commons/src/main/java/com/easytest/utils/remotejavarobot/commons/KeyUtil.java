package com.easytest.utils.remotejavarobot.commons;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyUtil extends JavaRobotUtil {
    public KeyUtil() {
    }

    public ArrayList action(SupportedOperations var1, int var2) {
        ArrayList var3 = new ArrayList();
        String var4 = "";
        if (var1.equals(SupportedOperations.KEYPRESS)) {
            var4 = this.keyPress(var2);
        } else if (var1.equals(SupportedOperations.KEYRELEASE)) {
            var4 = this.keyRelease(var2);
        }

        var3.add(var1);
        var3.add(var4);
        return var3;
    }

    public String keyPress(int var1) {
        String var2 = "";

        try {
            this.robot.keyPress(var1);
            this.robot.delay(250);
            var2 = "Pressed " + KeyEvent.getKeyText(var1);
        } catch (Exception var4) {
            var2 = "Unable to press " + KeyEvent.getKeyText(var1) + " key Exception occurred: " + var4.getMessage();
        }

        System.out.println(var2);
        return var2;
    }

    public String keyRelease(int var1) {
        String var2 = "";

        try {
            this.robot.keyRelease(var1);
            this.robot.delay(250);
            var2 = "Released " + KeyEvent.getKeyText(var1);
        } catch (Exception var4) {
            var2 = "Unable to release " + KeyEvent.getKeyText(var1) + " key Exception occurred: " + var4.getMessage();
        }

        System.out.println(var2);
        return var2;
    }
}
