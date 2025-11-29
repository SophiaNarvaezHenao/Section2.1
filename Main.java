import java.util.Scanner;
public class Main {
    //Admin Account
    private static String adminUser = "Admin_001";
    private static  String adminKey = "Admin_2025";
    private static SavingsAcct savingsAcct;
    private static CurrentAcct currentAcct;
    public static void createAccount(String accType){ //Only for admins
        Scanner sc = new Scanner(System.in);
        switch (accType){
            case "1":
                System.out.println("Creating a Savings Account...");
                System.out.println("Enter initial balance:");
                double stBalance = Double.parseDouble(sc.nextLine());
                System.out.println("Enter owner's name:");
                String owner = sc.nextLine();
                System.out.println("Enter interest rate (enter it without the '%'):");
                double interest = Double.parseDouble(sc.nextLine());
                savingsAcct = new SavingsAcct(stBalance, owner, interest);
                System.out.println("Savings Account created successfully for " + savingsAcct.getOwner() + " with balance: " + savingsAcct.getBalance());
                break;
            case "2":
                System.out.println("Creating a Current Account...");
                System.out.println("Enter initial balance:");
                double initBalance = Double.parseDouble(sc.nextLine());
                System.out.println("Enter owner's name:");
                String accOwner = sc.nextLine();
                System.out.println("Enter overdraft limit:");
                double overdraftLimit = Double.parseDouble(sc.nextLine());
                currentAcct = new CurrentAcct(initBalance, accOwner, overdraftLimit);
                System.out.println("Current Account created successfully for " + currentAcct.getOwner() + " with balance: " + currentAcct.getBalance());
                break;
            default:
                System.out.println("Invalid account type selected.");
        }

    }
    public static void accessAccount(String accType){
        Scanner sc = new Scanner(System.in);
        int times = 0;
        String ownerName = "";
        switch (accType){
            case "1":
                if (savingsAcct == null) {
                    System.out.println("No savings account created yet.");
                    return;
                }
                System.out.println("Accessing Savings Account...");
                System.out.println("Enter the Owner Name");
                ownerName = sc.nextLine();
                if (ownerName.isEmpty()){
                    System.out.println("Owner name cannot be empty.");
                    return;
                } else if (ownerName.equals(savingsAcct.getOwner())) {
                    System.out.println("Welcome back, " + ownerName + "!");
                    System.out.println("What do you want to do? (1. Deposit / 2. Withdraw / 3. See your balance / 4. Apply Interest)");
                    String action = sc.nextLine();
                    switch (action){
                        case "1":
                            System.out.println("Enter amount to deposit:");
                            double depositAmount = Double.parseDouble(sc.nextLine());
                            savingsAcct.deposit(depositAmount);
                            break;
                        case "2":
                            System.out.println("Enter amount to withdraw:");
                            double withdrawAmount = Double.parseDouble(sc.nextLine());
                            savingsAcct.withdraw(withdrawAmount);
                            break;
                        case "3":
                            System.out.println("Your current balance is: " + savingsAcct.getBalance());
                            break;
                        case "4":
                            if (times >= 1) {
                                System.out.println("Interest can only be applied once per session.");
                                break;
                            }
                            savingsAcct.applyInterest();
                            times++;
                            break;
                        default:
                            System.out.println("Invalid action selected.");
                    }
                } else {
                    System.out.println("No account found for the given owner name.");
                }
                break;
            case "2":
                if (currentAcct == null) {
                    System.out.println("No savings account created yet.");
                    return;
                }
                System.out.println("Accessing Current Account...");
                System.out.println("Enter the Owner Name");
                ownerName = sc.nextLine();
                if (ownerName.isEmpty()){
                    System.out.println("Owner name cannot be empty.");
                    return;
                } else if (ownerName.equals(currentAcct.getOwner())) {
                    System.out.println("Welcome back, " + ownerName + "!");
                    System.out.println("What do you want to do? (1. Deposit / 2. Withdraw / 3. See your balance )");
                    String action = sc.nextLine();
                    switch (action){
                        case "1":
                            System.out.println("Enter amount to deposit:");
                            double depositAmount = Double.parseDouble(sc.nextLine());
                            currentAcct.deposit(depositAmount);
                            break;
                        case "2":
                            System.out.println("Enter amount to withdraw:");
                            double withdrawAmount = Double.parseDouble(sc.nextLine());
                            currentAcct.withdraw(withdrawAmount);
                            break;
                        case "3":
                            System.out.println("Your current balance is: " + currentAcct.getBalance());
                            break;
                        default:
                            System.out.println("Invalid action selected.");
                    }
                } else {
                    System.out.println("No account found for the given owner name.");
                }
                break;
            default:
                System.out.println("Invalid account type selected.");
        }

    }
    public static void main(String[] args) {
        System.out.println("Welcome to Sophie Bank :D");
        Scanner sc = new Scanner(System.in);
        while(true){//repeats until user exits
            System.out.println("What do you want to do?");
            System.out.println("0. Create an account (Only for Admins) \n1. Access to your account\n2. Exit");
            String choice = sc.nextLine();
            try {
                switch (choice) {
                    case "0":
                        System.out.println("Enter Admin Username:");
                        String username = sc.nextLine();
                        System.out.println("Enter Admin Key:");
                        String key = sc.nextLine();
                        if (username.equals(adminUser) && key.equals(adminKey)) {
                            System.out.println("Access Granted. You can create an account.");
                            System.out.println("What type of account do you want to create? (1. Savings Account / 2. Current Account)");
                            String accType = sc.nextLine();
                            createAccount(accType);
                        } else {
                            System.out.println("Access Denied. Invalid credentials.");
                        }
                        break;
                    case "1":
                        System.out.println("What type of account do you have? (1. Savings Account / 2. Current Account)");
                        String accType = sc.nextLine();
                        accessAccount(accType);
                        break;
                    case "2":
                        System.out.println("Exiting the system. Goodbye!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("try again");//catch the exceptions
            }
        }
    }
}