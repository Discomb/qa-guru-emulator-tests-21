package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.drivers.BrowserstackDriver;
import guru.qa.drivers.LocalDriver;
import guru.qa.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    public static final String deviceHost = System.getProperty("deviceHost");

    @BeforeAll
    static void beforeAll() {
        if (deviceHost.equals("browserstack")) {
            Configuration.browser = BrowserstackDriver.class.getName();
        } else if (deviceHost.equals("emulator")) {
            Configuration.browser = LocalDriver.class.getName();
        }

        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach() {
        Attach.pageSource();
        if (deviceHost.equals("browserstack")) {
            String sessionId = sessionId().toString();
            closeWebDriver();
            Attach.addVideo(sessionId);
        } else if (deviceHost.equals("emulator")) {
            Attach.screenshotAs("Last screenshot");
            closeWebDriver();
        }
    }
}
