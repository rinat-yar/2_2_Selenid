package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderingCardDeliveryTest {

    public String generateTestDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format((DateTimeFormatter.ofPattern(pattern)));
    }

    @Test
    void FillingOutTheForm() {
        open("http://localhost:9999");
        String planDate = generateTestDate(6, "dd.MM.yyyy");

        $("[data-test-id=city] input").sendKeys("Санкт-Петербург");
        $("[data-test-id=city] input").shouldBe(Condition.value("Санкт-Петербург"));

        $(By.cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(planDate);
        $(By.cssSelector("[data-test-id=date] input")).shouldBe(Condition.value(planDate));

        $("[data-test-id = name] input").setValue("Ярмухамедов Ринат");
        $("[data-test-id = name] input").shouldBe(Condition.value("Ярмухамедов Ринат"));

        $("[data-test-id = phone] input").setValue("+79520000000");
        $("[data-test-id = phone] input").shouldBe(Condition.value("+79520000000"));

        $("[data-test-id = agreement]").click();
        $("[data-test-id = agreement] input").shouldBe(Condition.selected);

        $("div.grid-col button.button_view_extra").click();

        $("[data-test-id=notification]").shouldHave(text("Встреча успешно забронирована на " + planDate), Duration.ofSeconds(15)).shouldBe(visible);
    }
}
