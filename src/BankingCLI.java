import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents the CLI interface that is displayed for ATM Banking
 */
public class BankingCLI {

    /**
     * The number of digits that a PIN number must be
     */
    private static final int PIN_LENGTH = 4;

    /**
     * The cache of accounts at the bank
     */
    protected final Map<Integer, Account> accounts = new HashMap<>();

    /**
     * Incremental value for account number. It is a security no-no,
     * but for the purpose of this simple app, we'll increment
     */
    private int accountNumber = 1;

    /**
     * The current logged in account
     */
    protected Account currentAccount;

    public void startATMInterface(){
        int selection = showMenu();

        while (selection != 7){

            try {
                switch (selection) {
                    case 1: {
                        System.out.println("To create an account please enter your name:");

                        Scanner input = new Scanner(System.in);

                        String name = input.next();

                        System.out.println("Please enter a 4 Digit PIN:");

                        int pin = input.nextInt();

                        String pinVerifier = Integer.toString(pin);
                        boolean validPin = pinVerifier.length() == PIN_LENGTH;

                        // making sure that the pin length is valid
                        while(!validPin){
                            System.out.println("Please enter a valid 4 digit PIN number:");
                            pin = input.nextInt();
                            pinVerifier = Integer.toString(pin);
                            validPin = pinVerifier.length() == PIN_LENGTH;
                        }

                        Account account = new Account(accountNumber, pin, name);
                        accounts.put(account.getAccountNumber(), account);

                        System.out.printf("Account created. Your account number is %d\n", accountNumber);

                        // increment the account number
                        accountNumber++;

                        break;
                    }

                    case 2: {
                        if (!isLoggedIn()) {
                            System.out.print("Please enter the account number:");
                            Scanner input = new Scanner(System.in);

                            int accountNumber = input.nextInt();

                            System.out.println("Please enter the PIN number for the account:");

                            int pin = input.nextInt();

                            if (login(accountNumber, pin)) {
                                System.out.printf("Welcome %s! Please select a menu option to operate on this account\n", currentAccount.getName());
                            }
                        } else {
                            System.out.println("You are already logged in");
                        }
                        break;
                    }

                    case 3: {
                        if (isLoggedIn()) {
                            System.out.println(currentAccount.toString());
                        }
                        break;
                    }

                    case 4: {
                        if (isLoggedIn()) {
                            System.out.print("Please enter an amount to withdraw:");
                            Scanner input = new Scanner(System.in);

                            double amountToWithdraw = input.nextInt();

                            currentAccount.withdraw(amountToWithdraw);

                            break;
                        }
                    }

                    case 5: {
                        if (isLoggedIn()) {
                            System.out.print("Please enter an amount to deposit:");
                            Scanner input = new Scanner(System.in);

                            double amountToDeposit = input.nextInt();

                            currentAccount.deposit(amountToDeposit);

                            break;
                        }
                    }

                    case 6: {
                        if (isLoggedIn()){
                            currentAccount = null;
                            System.out.println("Logging out");
                            break;
                        }
                    }

                    default: {
                        System.out.println("Invalid Selection");
                        break;
                    }
                }
            } catch (Exception e){
                System.out.printf("The following error occurred:\n %s\n.", e.getMessage());
            }

            selection = showMenu();
        }
    }

    protected int showMenu(){
        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("======= ATM =======");
        System.out.println("Welcome! Please select an operation:");
        System.out.println("1. Create an account");
        System.out.println("2. Login");
        System.out.println("3. Show Balance");
        System.out.println("4. Withdraw");
        System.out.println("5. Deposit");
        System.out.println("6. Logout");
        System.out.println("7. Exit");

        selection = input.nextInt();
        return selection;
    }

    /**
     * Logs in to the account with the given account number using a
     * 4 digit PIN to verify
     * @param accountNumber Number of the account
     * @param pin PIN number for the account
     * @return true if the login was successful, false otherwise
     */
    protected boolean login(int accountNumber, int pin) {

        Account account = accounts.get(accountNumber);

        if (account == null){
            System.out.println("Account does not exist.");
        } else {
            boolean authenticated = account.verifyPIN(pin);

            if (authenticated){
                currentAccount = account;
                return true;
            } else {
                System.out.println("Incorrect PIN");
            }
        }
        return false;
    }



    /**
     * Checks if a user is logged in
     * @return true if the user is 'logged in', false otherwise
     */
    protected boolean isLoggedIn(){

        if (currentAccount == null){
            System.out.println("Please login to your account");
            return false;
        } else {
            return true;
        }
    }
}
