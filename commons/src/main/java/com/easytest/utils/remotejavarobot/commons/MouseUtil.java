package com.easytest.utils.remotejavarobot.commons;

import java.util.ArrayList;

public class MouseUtil extends JavaRobotUtil {
    public MouseUtil() {
    }

    public ArrayList mouseClick(int x, int y, int mouseButton) {
        ArrayList var4 = new ArrayList();
        String var5 = "";
        String var6 = "";
        var4.add(SupportedOperations.MOUSECLICK);

        try {
            this.robot.mouseMove(x, y);
            this.robot.mousePress(mouseButton);
            this.robot.mouseRelease(mouseButton);
            var5 = "Clicked co-ordinates x:" + x + " y:" + y;
            System.out.println(var5);
            var4.add(var5);
        } catch (Exception var8) {
            var5 = "Unable to mouse click Exception occurred: " + var8.getMessage();
            var4.add(var5);
            System.out.println(var5);
        }

        return var4;
    }
}
