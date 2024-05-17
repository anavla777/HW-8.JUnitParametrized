package tv.twitch.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import tv.twitch.tests.data.Language;
import tv.twitch.tests.pages.MainPage;

import java.util.stream.Stream;

public class TwitchWebTest extends TestBase {
    MainPage mainpage = new MainPage();
    @EnumSource(Language.class)
    @ParameterizedTest(name ="Twitch Main Page has correct text on {0} language")
    @Tag("WEB")
    @DisplayName("Twitch Main Page has correct text on RU and EN languages")
    void twitchMainPageShouldDisplayCorrectTextTest(Language language) {
        mainpage
                .openPage("/")
                .checkLocalization(language.name(), language.description);
    }

    @ValueSource(strings = {"Heroes of Might and Magic III: The Restoration of Erathia", "League of Legends", "Fortnite"})
    @ParameterizedTest(name="Selected category should have {0} name")
    @DisplayName("Selected category should have correct category name")
    public void categoryPageShouldDisplayCorrectTextTest(String category) {
        mainpage
                .openPage("/directory")
                .findCategory(category)
                .verifyCategory(category);
    }

    @MethodSource("streamerShouldHaveCorrectNicknameAndVideoPlayerTest")
    @ParameterizedTest(name="Streamer {0} should have correct name and video player")
    @DisplayName("Streamer should have correct name and video player")
    public void streamerShouldHaveCorrectNicknameAndVideoPlayerTest(String streamer) {
        mainpage
                .openPage("/")
                .findStreamer(streamer)
                .verifyStreamer(streamer);
    }

    static Stream<Arguments> streamerShouldHaveCorrectNicknameAndVideoPlayerTest(){
        return Stream.of(
                Arguments.of("Anakq"),
                Arguments.of("shroud"),
                Arguments.of("PewDiePie")
        );
    }
}
