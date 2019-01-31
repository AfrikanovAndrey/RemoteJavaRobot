package com.github.remoterobot.commons.utils;

import com.github.remoterobot.commons.protocol.Response;

import java.awt.event.KeyEvent;

import static com.github.remoterobot.commons.protocol.Operation.*;

public class KeyUtil extends JavaRobotUtil {

    public Response keyPress(int keyCode) {
        return executeOperation(
                KEY_PRESS,
                () -> {
                    robot.keyPress(keyCode);
                    robot.keyRelease(keyCode);
                    return null;
                },
                (result) -> "Pressed " + KeyEvent.getKeyText(keyCode),
                "Unable to press " + KeyEvent.getKeyText(keyCode) + " key."
        );
    }
}
