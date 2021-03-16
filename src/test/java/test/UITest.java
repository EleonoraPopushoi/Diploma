package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SqlUtils;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.CardData;
import page.TripPage;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.equalTo;

public class UITest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        String appUrl = System.getProperty("app.url");
        open(appUrl);
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @SneakyThrows
    void shouldSuccessWithValidDebitCard() {
        val tripPage = new TripPage();
        val CardData = tripPage.selectBuyByDebitCard();
        val validCardInformation = DataHelper.getValidCardInformation();
        CardData.fillCardInformationForSelectedWay(validCardInformation);
        CardData.checkIfPaymentSuccessful();
        val paymentId = SqlUtils.getPaymentId();
        val statusForPaymentByDebitCard = SqlUtils.getStatusForPaymentByDebitCard(paymentId);
        val paymentAmount = SqlUtils.getPaymentAmount(paymentId);
        assertEquals("APPROVED", statusForPaymentByDebitCard);
        assertEquals("4500000", paymentAmount);

    }

    @Test
    @SneakyThrows
    void shouldSuccessWithValidCreditCard() {
        val tripPage = new TripPage();
        val CardData = tripPage.selectBuyByCreditCard();
        val validCardInformation = DataHelper.getValidCardInformation();
        CardData.fillCardInformationForSelectedWay(validCardInformation);
        CardData.checkIfPaymentSuccessful();
        val paymentId = SqlUtils.getPaymentId();
        val statusForPaymentByCreditCard = SqlUtils.getStatusForPaymentByCreditCard(paymentId);
        assertEquals("APPROVED", statusForPaymentByCreditCard);
    }

    @Test
    @SneakyThrows
    void shouldNotSuccessWithInvalidDebitCard() {
        val tripPage = new TripPage();
        val CardData = tripPage.selectBuyByDebitCard();
        val invalidCardInformation = DataHelper.getInvalidCardInformation();
        CardData.fillCardInformationForSelectedWay(invalidCardInformation);
        CardData.checkIfPaymentNotSuccessful();
        val paymentId = SqlUtils.getPaymentId();
        val statusForPaymentByDebitCard = SqlUtils.getStatusForPaymentByDebitCard(paymentId);
        assertThat(statusForPaymentByDebitCard, equalTo("DECLINED"));
    }

    @Test
    @SneakyThrows
    void shouldNotSuccessWithInvalidCreditCard() {
        val tripPage = new TripPage();
        val CardData = tripPage.selectBuyByCreditCard();
        val validCardInformation = DataHelper.getInvalidCardInformation();
        CardData.fillCardInformationForSelectedWay(validCardInformation);
        CardData.checkIfPaymentNotSuccessful();
        val paymentId = SqlUtils.getPaymentId();
        val statusForPaymentByCreditCard = SqlUtils.getStatusForPaymentByCreditCard(paymentId);
        assertThat(statusForPaymentByCreditCard, equalTo("DECLINED"));
    }

    @Test
    void shouldNotSuccessWithWrongCardNumber() {
        val tripPage = new TripPage();
        val cardData = tripPage.selectBuyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationWithWrongLongCardNumber();
        cardData.fillCardInformationForSelectedWay(invalidCardInformation);
        cardData.checkIfWrongFormatOfField();
        val cardData2 = tripPage.selectBuyByDebitCard();
        cardData2.fillCardInformationForSelectedWay(invalidCardInformation);
        cardData2.checkIfWrongFormatOfField();
    }

    @Test
    void shouldNotSuccessWithShortestCardNumber() {
        val tripPage = new TripPage();
        val cardData = tripPage.selectBuyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationWithShortestCardNumber();
        cardData.fillCardInformationForSelectedWay(invalidCardInformation);
        cardData.checkIfWrongFormatOfField();
        val cardData2 = tripPage.selectBuyByDebitCard();
        cardData2.fillCardInformationForSelectedWay(invalidCardInformation);
        cardData2.checkIfWrongFormatOfField();
    }

    @Test
    void shouldNotSuccessWithWrongMonth() {
        val tripPage = new TripPage();
        val cardData = tripPage.selectBuyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationWithWrongMonth();
        cardData.fillCardInformationForSelectedWay(invalidCardInformation);
        cardData.checkIfWrongFormatOfField();
        val cardData2 = tripPage.selectBuyByDebitCard();
        cardData2.fillCardInformationForSelectedWay(invalidCardInformation);
        cardData2.checkIfWrongFormatOfField();
    }

    @Test
    void shouldNotSuccessWithWrongYear() {
        val tripPage = new TripPage();
        val cardData = tripPage.selectBuyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationWithWrongYear();
        cardData.fillCardInformationForSelectedWay(invalidCardInformation);
        cardData.checkIfWrongFormatOfField();
        val cardData2 = tripPage.selectBuyByDebitCard();
        cardData2.fillCardInformationForSelectedWay(invalidCardInformation);
        cardData2.checkIfWrongFormatOfField();
    }

    @Test
    void shouldNotSuccessWithWrongYearFromOneNumber() {
        val tripPage = new TripPage();
        val cardData = tripPage.selectBuyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationWithWrongYearWithOneNumber();
        cardData.fillCardInformationForSelectedWay(invalidCardInformation);
        cardData.checkIfWrongFormatOfField();
        val cardData2 = tripPage.selectBuyByDebitCard();
        cardData2.fillCardInformationForSelectedWay(invalidCardInformation);
        cardData2.checkIfWrongFormatOfField();
    }

    @Test
    void shouldNotSuccessWithWrongCVC() {
        val tripPage = new TripPage();
        val cardData = tripPage.selectBuyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationWithWrongCvc();
        cardData.fillCardInformationForSelectedWay(invalidCardInformation);
        cardData.checkIfWrongFormatOfField();
        val cardData2 = tripPage.selectBuyByDebitCard();
        cardData2.fillCardInformationForSelectedWay(invalidCardInformation);
        cardData2.checkIfWrongFormatOfField();
    }

    @Test
    void shouldNotSuccessWithWrongName() {
        val tripPage = new TripPage();
        val cardData = tripPage.selectBuyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationWithWrongHolderName();
        cardData.fillCardInformationForSelectedWay(invalidCardInformation);
        cardData.checkIfWrongFormatOfField();
        val cardData2 = tripPage.selectBuyByDebitCard();
        cardData2.fillCardInformationForSelectedWay(invalidCardInformation);
        cardData2.checkIfWrongFormatOfField();
    }

    @Test
    void shouldNotSuccessWithoutName() {
        val tripPage = new TripPage();
        val cardData = tripPage.selectBuyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationWithoutName();
        cardData.fillCardInformationForSelectedWay(invalidCardInformation);
        cardData.checkIfWrongFormatOfField();
        val cardData2 = tripPage.selectBuyByDebitCard();
        cardData2.fillCardInformationForSelectedWay(invalidCardInformation);
        cardData2.checkIfWrongFormatOfField();
    }
}
