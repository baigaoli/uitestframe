package com.baigaoli.util;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by likaisong on 2018/11/6.
 */
public class Capabilities {
    private static Set<String> capaKeys = new HashSet(Arrays.asList(Constants.capabilitiesKeys));
    public static String getUrl(){
        return String.format("%s:%s/wd/hub", Constants.IP, Constants.PORT);
    }

    public static DesiredCapabilities getCapabilities(){
        return getCapabilitiesByProperties(ConfigHelper.getProperty(Constants.PROTERTIES_FILE_NAME));
    }

    public static DesiredCapabilities getCapabilitiesByProperties(Properties properties){
        Map<String, String> map = new HashMap(properties);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        map.entrySet().stream().filter((entry) -> {
            return capaKeys.contains(entry.getKey());
        }).forEach((entry) -> {
            capabilities.setCapability(entry.getKey(), entry.getValue());
        });
        return capabilities;
    }



}
