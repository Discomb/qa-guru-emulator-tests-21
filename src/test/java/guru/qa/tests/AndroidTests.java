package guru.qa.tests;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

@Tag("android")
public class AndroidTests extends TestBase {

    @Test
    @Description("Проверка непустой поисковой выдачи")
    void successfulSearchTest() {
        back();
        step("Поиск через строку поиска", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Проверка непустой выдачи", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(CollectionCondition.sizeGreaterThan(0)));
    }

    @Test
    @Description("Проверка открытия статьи")
    void openArticleTest() {
        back();
        step("Поиск через строку поиска", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("France");
        });
        step("Проверка непустой выдачи", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(CollectionCondition.sizeGreaterThan(0)));
        step("Открытие первого элемента из списка", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title")).first().click());
        step("Проверка элементов на странице с ошибкой", () -> {
            $(id("org.wikipedia.alpha:id/view_wiki_error_icon")).should(exist);
            $(id("org.wikipedia.alpha:id/view_wiki_error_text")).shouldHave(text("An error occurred"));
            $(id("org.wikipedia.alpha:id/view_wiki_error_button")).should(exist);
            $(id("org.wikipedia.alpha:id/view_wiki_error_button")).shouldHave(text("GO BACK"));
        });
    }

    @Test
    @Tag("local")
    @Description("Проверка слайдера")
    void onboardingTest() {
        step("Проверка первого экрана", () -> {
            $(id("org.wikipedia.alpha:id/imageViewCentered")).shouldBe(visible);
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("The Free Encyclopedia\n" +
                    "…in over 300 languages"));
            $(id("org.wikipedia.alpha:id/addLanguageButton")).shouldBe(visible);
            $(id("org.wikipedia.alpha:id/addLanguageButton")).shouldBe(visible);
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();

        });
        step("Проверка второго экрана", () -> {
            $(id("org.wikipedia.alpha:id/imageViewCentered")).shouldBe(visible);
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("New ways to explore"));
            $(id("org.wikipedia.alpha:id/secondaryTextView")).shouldHave(text("Dive down the Wikipedia rabbit hole" +
                    " with a constantly updating Explore feed"));
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step("Проверка третьего экрана", () -> {
            $(id("org.wikipedia.alpha:id/imageViewCentered")).shouldBe(visible);
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Reading lists with sync"));
            $(id("org.wikipedia.alpha:id/secondaryTextView")).shouldHave(text("You can make reading lists " +
                    "from articles you want to read later"));
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step("Проверка четвертого экрана", () -> {
            $(id("org.wikipedia.alpha:id/imageViewCentered")).shouldBe(visible);
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Send anonymous data"));
            $(id("org.wikipedia.alpha:id/secondaryTextView")).shouldHave(text("Help make the app better " +
                    "by letting us know how you use it"));
            $(id("org.wikipedia.alpha:id/rejectButton")).shouldBe(visible);
            $(id("org.wikipedia.alpha:id/acceptButton")).shouldBe(visible);
            $(id("org.wikipedia.alpha:id/acceptButton")).click();
            $(id("org.wikipedia.alpha:id/main_toolbar_wordmark")).shouldBe(visible);
        });
    }

}
