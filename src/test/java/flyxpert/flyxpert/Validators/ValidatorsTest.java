package flyxpert.flyxpert.Validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ValidatorsTest {

    private final Validator name = new ValidateName();
    private final Validator number = new ValidateNumber();
    private final Validator airport = new ValidateAirports();
    private final Validator time = new ValidateTime();

    @Test
    void acceptsRealisticNames() {
        assertTrue(name.validateData("Shaurya Saxena"));
        assertTrue(name.validateData("Mary-Jane"));
        assertTrue(name.validateData("Rao, A."));
    }

    @Test
    void rejectsNamesWithDigitsOrSymbols() {
        assertFalse(name.validateData("John3"));
        assertFalse(name.validateData("a@b"));
        assertFalse(name.validateData(""));
        assertFalse(name.validateData(null));
    }

    @Test
    void numbersMustBeDigitsOnly() {
        assertTrue(number.validateData("4111111111111111"));
        assertTrue(number.validateData("0"));
        assertFalse(number.validateData("12a4"));
        assertFalse(number.validateData("-5"));
        assertFalse(number.validateData("1 000"));
        assertFalse(number.validateData(""));
        assertFalse(number.validateData(null));
    }

    @Test
    void airportCodesAreThreeLetters() {
        assertTrue(airport.validateData("DEL"));
        assertTrue(airport.validateData("maa"));
        assertFalse(airport.validateData("DE"));
        assertFalse(airport.validateData("DELH"));
        assertFalse(airport.validateData("D3L"));
        assertFalse(airport.validateData(null));
    }

    @Test
    void timesUseTwelveHourFormat() {
        assertTrue(time.validateData("9:30AM"));
        assertTrue(time.validateData("09:05PM"));
        assertTrue(time.validateData("12:00PM"));
        assertFalse(time.validateData("13:00PM"));
        assertFalse(time.validateData("0:30AM"));
        assertFalse(time.validateData("9:60AM"));
        assertFalse(time.validateData("9:30"));
        assertFalse(time.validateData("9:30 am"));
    }

    @Test
    void nullTimeIsInvalidInsteadOfCrashing() {
        assertFalse(time.validateData(null));
    }
}
