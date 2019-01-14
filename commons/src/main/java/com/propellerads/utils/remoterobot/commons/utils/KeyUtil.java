package com.propellerads.utils.remoterobot.commons.utils;

import com.propellerads.utils.remoterobot.commons.protocol.Response;

import java.awt.event.KeyEvent;

import static com.propellerads.utils.remoterobot.commons.protocol.Operation.*;

public class KeyUtil extends JavaRobotUtil {

    public Response keyPress(int keyCode) {
        return executeOperation(
                KEY_PRESS,
                () -> {
                    robot.keyPress(keyCode);
                    robot.delay(250);
                    return null;
                },
                (result) -> "Pressed " + KeyEvent.getKeyText(keyCode),
                "Unable to press " + KeyEvent.getKeyText(keyCode) + " key."
        );
    }

    public Response keyRelease(int keyCode) {
        return executeOperation(
                KEY_RELEASE,
                () -> {
                    robot.keyRelease(keyCode);
                    robot.delay(250);
                    return null;
                },
                (result) -> "Released " + KeyEvent.getKeyText(keyCode),
                "Unable to release " + KeyEvent.getKeyText(keyCode) + " key."
        );
    }
}
