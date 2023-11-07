package guru.qa.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties"
})
public interface MobileConfig extends org.aeonbits.owner.Config {

    @Key("browserstack.device")
    @DefaultValue("Google Pixel 3")
    String getDevice();

    @Key("browserstack.os_version")
    @DefaultValue("9.0")
    String getOS();

    @Key("browserstack.app")
    @DefaultValue("bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c")
    String getApp();
}
