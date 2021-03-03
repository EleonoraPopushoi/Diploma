package test;

import data.DataHelper;
import lombok.val;
import org.junit.jupiter.api.Test;
import rest.RestApiHelper;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiTest {
    @Test
    public void shouldCheckStatusViaValidDebitCard() {
        val validCardInformation = DataHelper.getValidCardInformation();
        final String response = RestApiHelper.fillPaymentFormByDebitCard(validCardInformation);
        assertTrue(response.contains("APPROVED"), "Is true when status is approved");
    }

    @Test
    public void shouldCheckStatusViaInvalidDebitCard() {
        val invalidCardInformation = DataHelper.getInvalidCardInformation();
        final String response = RestApiHelper.fillPaymentFormByDebitCard(invalidCardInformation);
        assertTrue(response.contains("DECLINED"), "Is true when status is declined");
    }

    @Test
    public void shouldCheckStatusViaValidCreditCard() {
        val validCardInformation = DataHelper.getValidCardInformation();
        final String response = RestApiHelper.fillPaymentFormByCreditCard(validCardInformation);
        assertTrue(response.contains("APPROVED"), "Is true when status is approved");
    }

    @Test
    public void shouldCheckStatusViaInvalidCreditCard() {
        val invalidCardInformation = DataHelper.getInvalidCardInformation();
        final String response = RestApiHelper.fillPaymentFormByCreditCard(invalidCardInformation);
        assertTrue(response.contains("DECLINED"), "Is true when status is declined");
    }
}
