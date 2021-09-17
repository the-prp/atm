public class Account {

    private final int accountNumber;

    private final int pin;

    private final String name;

    private double balance = 0;

    public Account(int accountNumber, int pin, String name) {

        this.accountNumber = accountNumber;
        this.pin = pin;
        this.name = name;
    }

    public int getAccountNumber(){
        return accountNumber;
    }

    public String getName(){
        return name;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws Exception {

        if (balance - amount < 0){
            throw new Exception("Cannot withdraw more than the account holds.");
        }
        balance -= amount;
    }

    /**
     * Checks that the given PIN number matches this account's PIN
     * @param pin An integer representing the PIN number given
     * @return true if the PIN matches, false otherwise
     */
    public boolean verifyPIN(int pin) {
        return this.pin == pin;
    }

    @Override
    public String toString() {
        return String.format("The account balance is $%.2f", balance);
    }
}
