package com.propellerads.utils.remotejavarobot.client;

import com.propellerads.utils.remoterobot.commons.utils.Base64;
import com.propellerads.utils.remoterobot.commons.protocol.Request;
import com.propellerads.utils.remoterobot.commons.protocol.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;

import static com.propellerads.utils.remoterobot.commons.protocol.Operation.*;

public class RemoteRobot {

    private static Logger LOGGER = LoggerFactory.getLogger(Client.class);

    private int port;
    private String ip;

    public RemoteRobot(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void changePort(int var1) {
        this.port = var1;
    }

    public void changeIP(String var1) {
        this.ip = var1;
    }

    /**
     * Press & release keyboard key
     *
     * @param keyCode {@link java.awt.event.KeyEvent}
     * @return
     */
    public boolean pressAndReleaseKey(int keyCode) {
        return pressKey(keyCode) && releaseKey(keyCode);
    }

    /**
     * Press keyboard key on remote server
     *
     * @param keyCode {@link java.awt.event.KeyEvent}
     * @return
     */
    public boolean pressKey(int keyCode) {
        return sendCommandMessage(
                new Request(KEY_PRESS, keyCode)
        ).isSuccess();
    }

    /**
     * Release keyboard key on remote server
     *
     * @param keyCode {@link java.awt.event.KeyEvent}
     * @return
     */
    public boolean releaseKey(int keyCode) {
        return sendCommandMessage(
                new Request(KEY_RELEASE, keyCode)
        ).isSuccess();
    }

    /**
     * Get color of pixel at remote server's screen
     *
     * @param x
     * @param y
     * @return {@link Color}
     */
    public Color getPixelColor(int x, int y) {
        Response response = sendCommandMessage(
                new Request(GET_PIXEL_COLOR, x, y)
        );
        return (Color) response.getResultObject();
    }

    /**
     * Capture remote screen
     *
     * @return byte[]  - bytes of PNG image
     */
    public byte[] captureScreen() {
        try {
            Response response = sendCommandMessage(new Request(CAPTURE_SCREEN));
            if (response.isSuccess() && response.getResultObject() != null) {
                Base64 base64 = new Base64();
                return Base64.decode(response.getResultObject().toString());
            }
        } catch (Exception e) {
            LOGGER.error("Error while capturing remote screen : " + e.getMessage());
        }
        return null;
    }

    /**
     * Capture remote screen with saving to required file
     *
     * @param fileNameSaveTo
     * @return
     */
    public boolean captureScreen(String fileNameSaveTo) {
        Response response = sendCommandMessage(new Request(CAPTURE_SCREEN));
        if (response.isSuccess() && response.getResultObject() != null) {
            saveByteArrayToFile(fileNameSaveTo, (byte[]) response.getResultObject());
            return true;
        }
        return false;
    }

    /**
     * Capture remote screen partially
     *
     * @return byte[]  - bytes of PNG image
     */
    public byte[] capturePartiallyScreen(int x0, int y0, int x1, int y1) {
        try {
            Response response = sendCommandMessage(new Request(CAPTURE_SCREEN_PARTIALLY, x0, y0, x1, y1));
            if (response.isSuccess() && response.getResultObject() != null) {
                Base64 base64 = new Base64();
                return Base64.decode(response.getResultObject().toString());
            }
        } catch (Exception e) {
            LOGGER.error("Error while capturing remote screen : " + e.getMessage());
        }
        return null;
    }

    /**
     * Capture remote screen partially with saving to required file
     *
     * @param fileNameSaveTo
     * @return
     */
    public boolean capturePartiallyScreen(String fileNameSaveTo, int x0, int y0, int x1, int y1) {
        Response response = sendCommandMessage(new Request(CAPTURE_SCREEN_PARTIALLY, x0, y0, x1, y1));
        if (response.isSuccess() && response.getResultObject() != null) {
            saveByteArrayToFile(fileNameSaveTo, (byte[]) response.getResultObject());
            return true;
        }
        return false;
    }

    private void saveByteArrayToFile(String fileNameSaveTo, byte[] imageBytes) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(fileNameSaveTo));
            fileOutputStream.write(Base64.decode(imageBytes.toString()));
            fileOutputStream.close();
            fileOutputStream.flush();
        } catch (Exception e) {
            LOGGER.error("Error while saving remote screen to local file : " + e.getMessage());
        }
    }

    /**
     * Mouse click at remote server
     *
     * @param x          - coordinates
     * @param y          /
     * @param buttonMask {@link java.awt.event.InputEvent} f.e. InputEvent.BUTTON1_MASK
     * @return flag of success
     */
    public boolean mouseClick(int x, int y, int buttonMask) {
        return sendCommandMessage(
                new Request(MOUSE_CLICK, x, y, buttonMask)
        ).isSuccess();
    }

    private Response sendCommandMessage(Request request) {
        return new Client().sendMessage(request, this.ip, this.port);
    }
}
