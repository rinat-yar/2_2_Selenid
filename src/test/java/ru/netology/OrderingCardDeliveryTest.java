package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderingCardDeliveryTest {
    public String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }
    @Test
    void FillingOutTheForm() {

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Санкт-Петербург");

        String currentDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id = 'date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id = 'date'] input").sendKeys(currentDate);

        $("[data-test-id = 'name'] input").setValue("Ярмухамедов Ринат");

        $("[data-test-id = phone] input").setValue("+79520000000");

        $("[data-test-id = agreement]").click();

        $("button.button").click();

        $("[data-test-id=notification]").shouldHave(text("Встреча успешно забронирована на " + currentDate), Duration.ofSeconds(15)).shouldBe(visible);
        }
    }
