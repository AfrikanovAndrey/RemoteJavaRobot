package com.easytest.utils.remotejavarobot.commons;

import java.awt.*;

public class JavaRobotUtil {
    protected Robot robot = null;

    public JavaRobotUtil() {
        try {
            this.robot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dispose() {
        this.robot = null;
    }
}
