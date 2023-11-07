package guru.qa.tests;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

@Tag("browserstack")
public class BrowserstackTests extends TestBase {

    @Test
    @Description("Проверка непустой поисковой выдачи")
    void successfulSearchTest() {
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

}
