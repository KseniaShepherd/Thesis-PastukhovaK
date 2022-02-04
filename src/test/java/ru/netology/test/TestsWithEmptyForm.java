package ru.netology.test;

import com.codeborne.selenide.Selenide;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.TourPurchasePage;
import ru.netology.sql.SQLRequest;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsWithEmptyForm {
    public DataHelper.InvalidCardNumbers invalidCardNumbers = DataHelper.getInvalidCardNumber();
    public DataHelper.InvalidMonth invalidMonth = DataHelper.getInvalidMonth();
    public DataHelper.InvalidYear invalidYear = DataHelper.getInvalidYear();
    public DataHelper.InvalidOwner invalidOwner = DataHelper.getInvalidOwner();
    public DataHelper.InvalidCVC invalidCVC = DataHelper.getInvalidCVC();
    TourPurchasePage tourPurchasePage;

    @BeforeEach
    public void openPage() {
        Selenide.open("http://localhost:8080/");
        tourPurchasePage = new TourPurchasePage();
        SQLRequest.clearDB();
    }

    @Test
    public void shouldFailToPurchaseWithACardWithEmptyForm() {
        tourPurchasePage.buyTourWithACard(invalidCardNumbers.getEmptyCardNumber(), invalidMonth.getEmptyMonth(),
                invalidYear.getEmptyYear(), invalidOwner.getEmptyOwner(), invalidCVC.getEmptyCvc());
        String actualMessageBelowFieldCardNumber = $("#root > div > form > fieldset > div:nth-child(1) " +
                "> span > span > span.input__sub").getText();
        String actualMessageBelowFieldMonth = $("#root > div > form > fieldset > div:nth-child(2) > span > " +
                "span.input-group__input-case.input-group__input-case_invalid > span > span > span.input__sub").getText();
        String actualMessageBelowFieldYear = $("#root > div > form > fieldset > div:nth-child(2) > span > span:nth-child(2)" +
                " > span > span > span.input__sub").getText();
        String actualMessageBelowFieldOwner = $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) " +
                "> span > span > span.input__sub").getText();
        String actualMessageBelowFieldCVC = $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(2)" +
                " > span > span > span.input__sub").getText();

        String expectedMessageBelowFieldCardNumber = "Поле обязательно для заполнения";
        String expectedMessageBelowFieldMonth = "Поле обязательно для заполнения";
        String expectedMessageBelowFieldYear = "Поле обязательно для заполнения";
        String expectedMessageBelowFieldOwner = "Поле обязательно для заполнения";
        String expectedMessageBelowFieldCVC = "Поле обязательно для заполнения";

        assertEquals(expectedMessageBelowFieldCardNumber, actualMessageBelowFieldCardNumber);
        assertEquals(expectedMessageBelowFieldMonth, actualMessageBelowFieldMonth);
        assertEquals(expectedMessageBelowFieldYear, actualMessageBelowFieldYear);
        assertEquals(expectedMessageBelowFieldOwner, actualMessageBelowFieldOwner);
        assertEquals(expectedMessageBelowFieldCVC, actualMessageBelowFieldCVC);

        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getDebitPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldFailToPurchaseWithALoanWithEmptyForm() {
        tourPurchasePage.buyTourWithALoan(invalidCardNumbers.getEmptyCardNumber(), invalidMonth.getEmptyMonth(),
                invalidYear.getEmptyYear(), invalidOwner.getEmptyOwner(), invalidCVC.getEmptyCvc());
        String actualMessageBelowFieldCardNumber = $("#root > div > form > fieldset > div:nth-child(1) " +
                "> span > span > span.input__sub").getText();
        String actualMessageBelowFieldMonth = $("#root > div > form > fieldset > div:nth-child(2) > span > " +
                "span.input-group__input-case.input-group__input-case_invalid > span > span > span.input__sub").getText();
        String actualMessageBelowFieldYear = $("#root > div > form > fieldset > div:nth-child(2) > span > span:nth-child(2)" +
                " > span > span > span.input__sub").getText();
        String actualMessageBelowFieldOwner = $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) " +
                "> span > span > span.input__sub").getText();
        String actualMessageBelowFieldCVC = $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(2)" +
                " > span > span > span.input__sub").getText();

        String expectedMessageBelowFieldCardNumber = "Поле обязательно для заполнения";
        String expectedMessageBelowFieldMonth = "Поле обязательно для заполнения";
        String expectedMessageBelowFieldYear = "Поле обязательно для заполнения";
        String expectedMessageBelowFieldOwner = "Поле обязательно для заполнения";
        String expectedMessageBelowFieldCVC = "Поле обязательно для заполнения";

        assertEquals(expectedMessageBelowFieldCardNumber, actualMessageBelowFieldCardNumber);
        assertEquals(expectedMessageBelowFieldMonth, actualMessageBelowFieldMonth);
        assertEquals(expectedMessageBelowFieldYear, actualMessageBelowFieldYear);
        assertEquals(expectedMessageBelowFieldOwner, actualMessageBelowFieldOwner);
        assertEquals(expectedMessageBelowFieldCVC, actualMessageBelowFieldCVC);

        val expectedStatus = DataHelper.getEmptyCardStatus();
        val actualStatus = SQLRequest.getCreditPaymentStatus();
        assertEquals(expectedStatus, actualStatus);
    }
}
