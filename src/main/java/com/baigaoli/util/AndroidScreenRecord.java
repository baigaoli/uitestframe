package com.baigaoli.util;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by likaisong on 2018/11/19.
 */
public class AndroidScreenRecord {
    private static Logger logger = Logger.getLogger("AndroidScreenRecord");
    private boolean screenAlwaysRun = true;
    private int screenRecordNum = 0;
    private long timeLimit = 180;//录屏最多180秒
    private String deviceId;
    private List<String> screenRecordFileNameList = new ArrayList();

    public void startRecordingScreenLongTime() throws InterruptedException {
        while (screenAlwaysRun){
            ++ screenRecordNum;
            if(screenRecordNum >= 5){
                screenAlwaysRun = false;
            }
            startRecordingScreen();
            Thread.sleep(timeLimit * 1000);
        }
    }

    /**
     * 开始录屏
     */
    private void startRecordingScreen() {
        DesiredCapabilities capabilities = Capabilities.getCapabilities();
        deviceId = capabilities.getCapability("udid").toString();
        logger.info("start record screen");
        String screenRecordFileName = String.format("/sdcard/screenrecord_%d.mp4", System.currentTimeMillis());
        screenRecordFileNameList.add(screenRecordFileName);
        try {
            execute(String.format("adb -s %s shell screenrecord  --time-limit %d %s", deviceId, timeLimit, screenRecordFileName));
        } catch (Exception e){
            logger.severe("start record screen failed: " + e.getMessage());
        }
    }

    private void execute(String command) throws IOException, InterruptedException {
        execute(command, false);
    }

    /**
     * 停止录屏
     * @throws IOException
     */
    public void stopRecordingScreen() throws IOException {
        screenAlwaysRun = false;
        logger.info("stop record screen");
        try {
            execute(String.format("ps -ef|grep screenrecord|grep %s|grep -v grep|awk '{print $2}'|xargs kill", deviceId), true);
        } catch (Exception e){
            logger.severe("stop record screen failed: " + e.getMessage());
        }
    }

    /**
     * 从手机内存中拉录屏文件
     * @param path
     * @param recordingName
     */
    public void pullRecordingScreen(String path, String recordingName) {
        logger.info("upload record screen");
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        Iterator iterator = screenRecordFileNameList.iterator();
        while (iterator.hasNext()){
            String screenRecordFileName = (String) iterator.next();
            String uploadScreenFile = String.format("%s/%s_%d.mp4", path, recordingName, System.currentTimeMillis());
            try {
                execute(String.format("adb -s %s pull %s %s", this.deviceId, screenRecordFileName, uploadScreenFile), true);
                execute(String.format("adb -s %s shell rm %s", this.deviceId, screenRecordFileName), true);
            } catch (Exception e){
                logger.severe("upload record screen failed: " + e.getMessage());
            }
        }
    }

    private void execute(String command, boolean waitFor) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(new String[]{"bash", "-c", command});
        Process process = processBuilder.start();
        if (waitFor){
            process.waitFor();
        }
    }
}
