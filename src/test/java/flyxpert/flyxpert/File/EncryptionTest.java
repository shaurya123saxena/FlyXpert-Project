package flyxpert.flyxpert.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class EncryptionTest {

    @Test
    void decryptReversesEncryptForAnyCode() {
        String password = "Fly@Xpert2025";
        for (int code = 0; code < 100; code++) {
            assertEquals(password, Encryption.decrypt(Encryption.encrypt(password, code), code));
        }
    }

    @Test
    void encryptedTextDiffersFromPlainText() {
        assertNotEquals("secret", Encryption.encrypt("secret", 7));
    }

    @Test
    void constantEncryptionRoundTrips() {
        String password = "admin";
        String stored = Encryption.constantEncrypt(password);
        assertNotEquals(password, stored);
        assertEquals(password, Encryption.constantDecrypt(stored));
    }

    @Test
    void emptyPasswordStaysEmpty() {
        assertEquals("", Encryption.encrypt("", 42));
        assertEquals("", Encryption.constantDecrypt(""));
    }

    @Test
    void generatedCodeIsBetweenZeroAndNinetyNine() {
        for (int i = 0; i < 50; i++) {
            int code = Encryption.generateCode();
            assertTrue(code >= 0 && code < 100, "code out of range: " + code);
        }
    }
}
