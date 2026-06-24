package edu.rit.swen352.tdd.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test suite for the {@link SimpleBankAccount} component.
 */
class SimpleBankAccountTest {

    @Test
    @DisplayName("ctor with an initial balance")
        void testConstructorWithInitislBalnce() {
        SimpleBankAccount account = new SimpleBankAccount(100.0f);

        assertEquals(100.0f, account.getBalance());
    }

}
