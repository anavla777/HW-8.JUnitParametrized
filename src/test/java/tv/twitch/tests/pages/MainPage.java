package tv.twitch.tests.pages;

import com.codeborne.selenide.SelenideElement;
import tv.twitch.tests.pages.components.LanguageSwitcher;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private final SelenideElement
            searchInput = $("[data-a-target='tw-input']"),
            signUpButton= $("[data-a-target='signup-button']");

    LanguageSwitcher languageSwitcher=new LanguageSwitcher();

    public void checkLocalization(String language, String description) {
        languageSwitcher.selectLanguage(language);
        signUpButton.shouldHave(text(description));
    }
    public MainPage openPage(String path) {
        open(path);
        return this;
    }
    public MainPage findCategory(String category) {
        $("[data-a-id='card-" + category.replaceAll("\\s","") + "']")
                .scrollIntoView(false).click();
        return this;
    }
    public void verifyCategory(String category) {
        $("h1").shouldHave(text(category));
    }
    public MainPage findStreamer(String streamer) {
        searchInput.setValue(streamer).pressEnter();
        $(byText(streamer)).click();
        return this;
    }
    public void verifyStreamer(String streamer) {
        $("h1").shouldHave(text(streamer));
        $("[data-test-selector='video-player__video-layout']").shouldBe(visible);
    }
}
