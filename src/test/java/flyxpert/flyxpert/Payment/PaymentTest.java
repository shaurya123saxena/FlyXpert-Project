package flyxpert.flyxpert.Payment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PaymentTest {

    @Test
    void cardAddsThreePercentFee() {
        PaymentMethod card = new Card("Asha Rao", "4111111111111111", "12/28", "123");
        assertEquals(1030.0, card.calculateTotalPriceWithFees(1000.0), 1e-9);
        assertEquals("Card", card.getPaymentMethodName());
    }

    @Test
    void paypalAddsFourPercentPlusFixedFee() {
        PaymentMethod paypal = new Paypal("asha@example.com");
        assertEquals(1045.0, paypal.calculateTotalPriceWithFees(1000.0), 1e-9);
        assertEquals("Paypal", paypal.getPaymentMethodName());
    }

    @Test
    void zeroSubtotalStillChargesPaypalFixedFee() {
        assertEquals(0.0, new Card().calculateTotalPriceWithFees(0), 1e-9);
        assertEquals(5.0, new Paypal().calculateTotalPriceWithFees(0), 1e-9);
    }

    @Test
    void classPricesFollowEconomyRatios() {
        Price price = new Price(100f);
        assertEquals(100f, Float.parseFloat(price.getEconomyClassPrice()));
        assertEquals(200f, Float.parseFloat(price.getBussniessClassPrice()));
        assertEquals(500f, Float.parseFloat(price.getFirstClassPrice()));
    }

    @Test
    void priceFromTextMatchesPriceFromNumber() {
        Price fromText = new Price("250.5");
        Price fromNumber = new Price(250.5f);
        assertEquals(fromNumber.getBussniessClassPrice(), fromText.getBussniessClassPrice());
        assertEquals(fromNumber.getFirstClassPrice(), fromText.getFirstClassPrice());
    }

    @Test
    void adjustingEconomyPriceRescalesOtherClasses() {
        Price price = new Price(100f);
        price.adjustPricesWithRatio("80");
        assertEquals(160f, Float.parseFloat(price.getBussniessClassPrice()));
        assertEquals(400f, Float.parseFloat(price.getFirstClassPrice()));
    }
}
