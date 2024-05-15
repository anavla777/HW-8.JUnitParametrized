package tv.twitch.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
        @BeforeAll
        static void beforeAll() {
            Configuration.baseUrl="https://www.twitch.tv";
            Configuration.browserSize="3840x2160";
            Configuration.pageLoadStrategy="eager";
        }
        @AfterEach
    void closeBrowser(){
            Selenide.closeWebDriver();
        }
    }
