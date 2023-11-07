package guru.qa.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${deviceHost}.properties"
})
public interface MobileConfig extends org.aeonbits.owner.Config {

    @Key("device")
    String getDevice();

    @Key("os_version")
    String getOS();

    @Key("app")
    String getApp();
}
