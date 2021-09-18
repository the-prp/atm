# atm


#### Building

The application is a simple Java app. Import the source code into your IDE of choice and run the Main method to start the application. 

#### Testing

The tests were created with JUnit 5. Run the BankingCLITest class to perform tests. Runtime tests can also be performed by starting the application. 


#### Interface

The interface has 7 options

```
======= ATM =======
Welcome! Please select an operation:
1. Create an account
2. Login
3. Show Balance
4. Withdraw
5. Deposit
6. Logout
7. Exit
```


1. Create an account: Create an account using a name and a PIN number. An account number will be generated for you.
1. Login: You cannot do any operations on the account unless you are logged in to the account. You need to provide the account number and a PIN that gives you access to the account.
1. Show balance: Displays the logged in account's balance.
1. Withdraw: Withdraw a given amount from the logged in account. The number given should be a numeric value.
1. Deposit: Deposit a given amount from the logged in account. The number given should be a numeric value.
1. Logout: Logs out of account.
1. Exit: Quit the program.
