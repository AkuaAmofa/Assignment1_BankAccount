public class TestBankAccount {
    public static void main(String[] args) {

        // creating two bank accounts
        BankAccount savingsAcc = new BankAccount(BankAccount.AccountType.SAVINGS, "SAVING001", 10000);
        BankAccount currentAcc = new BankAccount(BankAccount.AccountType.CURRENT, "CURRENT001", 7000);

        System.out.println("Initial Account Details");
        System.out.println("Savings Account ID: " + savingsAcc.getAccountID());
        System.out.println("Current Account ID: " + currentAcc.getAccountID());
        System.out.println("Savings Balance: " + savingsAcc.getBalance());
        System.out.println("Current Balance: " + currentAcc.getBalance());
        System.out.println();

        // testing deposit
        System.out.println("Deposit Test");
        savingsAcc.deposit(600);
        System.out.println("Savings Balance now: " + savingsAcc.getBalance());
        System.out.println();

        // testing withdrawal
        System.out.println("Withdrawal Test");
        boolean withdrawResult = savingsAcc.withdraw(700);
        System.out.println("Withdrawal status: " + withdrawResult);
        System.out.println("Savings Balance now: " + savingsAcc.getBalance());
        System.out.println();

        // testing withdrawal that should fail
        System.out.println("Withdrawal Failure Test");
        boolean failWithdraw = savingsAcc.withdraw(10000); 
        System.out.println("Withdrawal status: " + failWithdraw);
        System.out.println();

        // transfer from savings to current
        System.out.println("Transfer Test");
        boolean transferResult = savingsAcc.transfer(true, currentAcc, 300);
        System.out.println("Transfer status: " + transferResult);
        System.out.println("Savings Balance: " + savingsAcc.getBalance());
        System.out.println("Current Balance: " + currentAcc.getBalance());
        System.out.println();

        // transfer that should fail
        System.out.println("Transfer Failure Test");
        boolean failTransfer = savingsAcc.transfer(true, currentAcc, 8500); 
        System.out.println("Transfer status: " + failTransfer);
        System.out.println();

        // perform monthly maintenance for both accounts
        System.out.println("Monthly Maintenance");
        savingsAcc.performMonthlyMaintenance();
        currentAcc.performMonthlyMaintenance();
        System.out.println();

        // check final balances
        System.out.println("Final Account Balances");
        System.out.println("Savings Account Final Balance: " + savingsAcc.getBalance());
        System.out.println("Current Account Final Balance: " + currentAcc.getBalance());
    }
}
