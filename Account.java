public abstract class Account {
    // The template for the other account classes
    private double balance;
    private String owner;

    public Account(double stBalance, String owner) {
        this.balance = stBalance;
        this.owner = owner;
    }
    public abstract void deposit(double amount); //Abstract method for deposit
    public abstract void withdraw(double amount);//Abstract method for withdraw

    //Getters to obtain the balance and owner
    public double getBalance() {
        return balance;
    }
    public String getOwner() {
        return owner;
    }
    //Setter to update the balance and the owner
    protected void setBalance(double nwBalance) {
        this.balance = nwBalance;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
