public class SavingsAcct extends Account{
    private double Interest; // Interest rate as a percentage

    public SavingsAcct(double stBalance, String owner, double Interest) {
        super(stBalance, owner);
        this.Interest = Interest;
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
        if (amount > getBalance()) { // Check for sufficient funds
            System.out.println("Insufficient funds. Withdrawal denied.");
            return;
        } else if (amount <= 0) { // Check for positive withdrawal amount
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        double nwBalance = getBalance() - amount;
        setBalance(nwBalance);
        System.out.println( "Withdrew: " + amount + ". Your new balance: " + getBalance());
    }

    public void applyInterest() {
        double nwBalance = getBalance() + (getBalance() * Interest / 100); // Calculate new balance with interest
        setBalance(nwBalance);
        System.out.println("Interest applied. New balance: " + getBalance());
    }
    // Getter for Interest rate
    public double getInterest() {
        return Interest;
    }
}
