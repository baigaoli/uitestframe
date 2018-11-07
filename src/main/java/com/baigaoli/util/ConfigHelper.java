package com.baigaoli.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by likaisong on 2018/11/7.
 */
public class ConfigHelper {
    private static Logger logger = Logger.getLogger("ConfigHelper");
    private static Properties properties;

    public static Properties getProperty(String configPath) {
        BufferedInputStream in = null;
        try {
            properties = new Properties();
            in = new BufferedInputStream(new FileInputStream(configPath));
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException("读取配置失败：" + configPath , e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.severe(e.getMessage());
                }
            }

        }
        return properties;
    }

    public static Object getgetPropertyValue(String configPath, String key){
        return getProperty(configPath).get(key);
    }
}
