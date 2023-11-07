package guru.qa.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:auth.properties"
})
public interface AuthConfig extends Config {

    @Key("browserstack.accessKey")
    String getAccessKey();

    @Key("browserstack.userName")
    String getUserName();

    @Key("browserstack.url")
    String getUrl();

}
