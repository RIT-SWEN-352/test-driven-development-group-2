package edu.rit.swen352.tdd.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test suite for the {@link SimpleBankAccount} component.
 */
class SimpleBankAccountTest {

    @Test
    @DisplayName("ctor with an initial balance")
        void testConstructorInitialBalnce() {
        SimpleBankAccount account = new SimpleBankAccount(100.0f);

        assertEquals(100.0f, account.getBalance());
    }

    @Test
    @DisplayName("No-arg constructor defaults balance to zero")
    void testNoArgConstructor() {
        SimpleBankAccount account = new SimpleBankAccount();

        assertEquals(0.0f, account.getBalance());
    }

    @Test
    @DisplayName("getBalance returns current balance")
    void testGetBalance() {
        SimpleBankAccount account = new SimpleBankAccount(42.5f);

        assertEquals(42.5f, account.getBalance());
    }

    @Test
    @DisplayName("New account is empty")
    void testIsAccountEmptyTrue() {
        SimpleBankAccount account = new SimpleBankAccount();

        assertTrue(account.isAccountEmpty());
    }

    @Test
    @DisplayName("Account with positive balance is not empty")
    void testIsAccountEmptyFalse() {
        SimpleBankAccount account = new SimpleBankAccount(10.0f);

        assertFalse(account.isAccountEmpty());
    }

    @Test
    @DisplayName("Deposit increases balance")
    void testDeposit() {
        SimpleBankAccount account = new SimpleBankAccount(100.0f);

        account.deposit(25.0f);

        assertEquals(125.0f, account.getBalance());
    }

    @Test
    @DisplayName("Depositing zero leaves balance unchanged")
    void testDepositZero() {
        SimpleBankAccount account = new SimpleBankAccount(100.0f);

        account.deposit(0.0f);

        assertEquals(100.0f, account.getBalance());
    }

    @Test
    @DisplayName("Withdraw decreases balance")
    void testWithdraw() {
        SimpleBankAccount account = new SimpleBankAccount(100.0f);

        account.withdraw(40.0f);

        assertEquals(60.0f, account.getBalance());
    }

    @Test
    @DisplayName("Cannot withdraw more than current balance")
    void testWithdrawMoreThanBalance() {
        SimpleBankAccount account = new SimpleBankAccount(50.0f);

        assertThrows(
                IllegalArgumentException.class,
                () -> account.withdraw(60.0f)
        );
    }

    @Test
    @DisplayName("Formats balance as currency")
    void testToString() {
        SimpleBankAccount account = new SimpleBankAccount(20.5f);

        assertEquals("$20.50", account.toString());
    }

    @Test
    @DisplayName("Cannot withdraw negative amount")
    void testWithdrawNegativeAmount() {
        SimpleBankAccount account = new SimpleBankAccount(100.0f);

        assertThrows(
                IllegalArgumentException.class,
                () -> account.withdraw(-10.0f)
        );
    }

    @Test
    @DisplayName("Cannot deposit negative amount")
    void testDepositNegativeAmount() {
        SimpleBankAccount account = new SimpleBankAccount(100.0f);

        assertThrows(
                IllegalArgumentException.class,
                () -> account.deposit(-10.0f)
        );
    }

    @Test
    @DisplayName("Cannot create account with negative balance")
    void testConstructorRejectsNegativeBalance() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new SimpleBankAccount(-1.0f)
        );
    }



}
