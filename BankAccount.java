public class BankAccount {
    public enum AccountType { CURRENT, SAVINGS }

    // Member variables
    private AccountType acctType;
    private String acctID;
    private double balance;
    private int numWithdrawals;
    private boolean inTheRed;
    private double minBalance;
    private double interestRate;
    private double maintenanceFee;
    private int withdrawalLimit;

    // Constants for savings and current accounts
    public static final double CURRENT_ACCT_MIN_BALANCE = 1000.0;
    public static final double SAVINGS_ACCT_MIN_BALANCE = 1500.0;

    // Constants for interest rates, maintenance fees, and withdrawal limits
    public static final double SAVINGS_ACCT_INTEREST_RATE = 0.03;
    public static final double CURRENT_ACCT_MAINTENANCE_FEE = 10.0;
    public static final int SAVINGS_WITHDRAWAL_LIMIT = 2;

    //First Constructor 
    public BankAccount(AccountType type, String id) {
        this.acctType = type;
        this.acctID = id;
        this.balance = 0.0;
        this.numWithdrawals = 0;

        if (type == AccountType.CURRENT) {
            this.minBalance = CURRENT_ACCT_MIN_BALANCE;
            this.interestRate = 0.0;
            this.maintenanceFee = CURRENT_ACCT_MAINTENANCE_FEE;
            this.withdrawalLimit = -1; 
        } else {
            this.minBalance = SAVINGS_ACCT_MIN_BALANCE;
            this.interestRate = SAVINGS_ACCT_INTEREST_RATE;
            this.maintenanceFee = 0.0;
            this.withdrawalLimit = SAVINGS_WITHDRAWAL_LIMIT;
        }

        this.inTheRed = this.balance < this.minBalance;
    }

    // Second Constructor 
    public BankAccount(AccountType type, String id, double openingBalance) {
        this.acctType = type;
        this.acctID = id;
        this.balance = openingBalance;
        this.numWithdrawals = 0;

        if (type == AccountType.CURRENT) {
            this.minBalance = CURRENT_ACCT_MIN_BALANCE;
            this.interestRate = 0.0;
            this.maintenanceFee = CURRENT_ACCT_MAINTENANCE_FEE;
            this.withdrawalLimit = -1; 
        } else {
            this.minBalance = SAVINGS_ACCT_MIN_BALANCE;
            this.interestRate = SAVINGS_ACCT_INTEREST_RATE;
            this.maintenanceFee = 0.0;
            this.withdrawalLimit = SAVINGS_WITHDRAWAL_LIMIT;
        }

        this.inTheRed = this.balance < this.minBalance;

    }
        // getters
    public double getBalance(){
        return balance; //get the account balance and return it
    }
    public AccountType getAccountType(){
        return acctType; //get the accountType balance and return it
    }
    public String getAccountID(){
        return acctID; //get the accountID balance and return it
    }
    public double getMinBalance(){
        return minBalance; //get the account min balance and return it
    }

    public boolean withdraw(double amount){

        if (inTheRed){
            System.out.println("Sorry, could not perform withdrawal: Your account is in the red zone");
            return false;
        }
        if (withdrawalLimit != -1 && numWithdrawals >= withdrawalLimit){
            System.out.println("Sorry, could not perform withdrawal: Withdrawal limit reached");
            return false;
        }
        if (minBalance > balance-amount){
            System.out.println("Sorry, could not perform withdrawal: Insufficient balance.");
            return false;
        }

        balance -= amount;
        numWithdrawals++;
        inTheRed = (balance < minBalance);

        return true;
    }

    public void deposit(double amount){
        balance += amount;
        inTheRed = (balance < minBalance);
        System.out.println("Deposit of " + amount+" successful.");
    }

    public void performMonthlyMaintenance() {

    double earnedInterest = balance * (interestRate / 12);

    balance += earnedInterest;

    balance -= maintenanceFee;

    inTheRed = (balance < minBalance);

    numWithdrawals = 0;

    // Print monthly summary
    System.out.println("Earned interest: " + earnedInterest);
    System.out.println("Maintenance fee: " + maintenanceFee);
    System.out.println("Updated balance: " + balance);

    if (inTheRed) {
        System.out.println("WARNING: This account is in the red!");
    }
}

public boolean transfer(boolean transferTo, BankAccount otherAccount, double amount) {
    if (transferTo) {
        boolean success = this.withdraw(amount);
        if (success) {
            otherAccount.deposit(amount);
            System.out.println("Transfer of " + amount + " to Account ID " + otherAccount.acctID + " successful.");
            return true;
        } else {
            System.out.println("Transfer failed: Could not withdraw from source account.");
            return false;
        }
    }else {
        boolean success = otherAccount.withdraw(amount);
        if (success) {
            this.deposit(amount);
            System.out.println("Transfer of " + amount + " from Account ID " + otherAccount.acctID + " successful.");
            return true;
        } else {
            System.out.println("Transfer failed: Could not withdraw from source account.");
            return false;
        }
    }
}


}
