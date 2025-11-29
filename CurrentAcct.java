public class CurrentAcct extends Account{
    private double overdraftLt;// Overdraft limit

    public CurrentAcct(double stBalance, String owner, double overdraftLt) {
        super(stBalance, owner);
        this.overdraftLt = overdraftLt;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) { // Check for positive deposit amount
            double nwBalance = getBalance() + amount;
            setBalance(nwBalance);
            System.out.println("Deposited: " + amount + ". Your new balance: " + getBalance());
        }else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > getBalance() + overdraftLt) { // Check against overdraft limit
            System.out.println("Withdrawal exceeds overdraft limit. Withdrawal denied.");
            return;
        }else if (amount <= 0) { // Check for positive withdrawal amount
            System.out.println("Withdrawal amount must be positive.");
            return;
        } else if (amount > getBalance()) { // Check if using overdraft
            System.out.println("Warning: You are using your overdraft limit.");
        }
        double nwBalance = getBalance() - amount;
        System.out.println( "Withdrew: " + amount + ". Your new balance: " + getBalance());
    }

    public double getOverdraftLt() {
        return overdraftLt;
    }

    public void setOverdraftLt(double overdraftLt) {
        this.overdraftLt = overdraftLt;
    }
}
