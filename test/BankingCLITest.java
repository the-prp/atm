import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.jvm.hotspot.utilities.Assert;

import static org.junit.jupiter.api.Assertions.*;

class BankingCLITest extends BankingCLI {

    private final int PIN = 1234;
    private final int ACCOUNT_NUMBER = 1;

    @BeforeEach
    void setUp() {

        Account goodAccount = new Account(ACCOUNT_NUMBER, PIN, "Jerry Seinfeld");
        accounts.put(goodAccount.getAccountNumber(), goodAccount);
    }

    /**
     * Tests that when an incorrect PIN is given, the account
     * does not get logged in to.
     */
    @Test
    public void badPin_test() {
        boolean authenticated = login(1, 1111);

        Assert.that(!authenticated, "User was authenticated with the wrong PIN.");
    }

    /**
     * Checks if a user is logged in after logging in
     */
    @Test
    public void isLoggedIn_test() {
        login(ACCOUNT_NUMBER, PIN);

        Assert.that(currentAccount != null, "Current account does not have a value after logging in.");
    }


}