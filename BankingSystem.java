import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private static int idCounter = 1000;
    private static int accNumberCounter = 5000;

    private int accountId;
    private int accountNumber;
    private String holderName;
    private String email;
    private String phone;
    private String bankName;
    private double balance;

    public Account(String holderName, String email, String phone, String bankName) {
        this.accountId = ++idCounter;
        this.accountNumber = ++accNumberCounter;
        this.holderName = holderName;
        this.email = email;
        this.phone = phone;
        this.bankName = bankName;
        this.balance = 0.0;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getBankName() {
        return bankName;
    }

    public double getBalance() {
        return balance;
    }
    public void deposit(double amount) {
        if(amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if(amount <= 0) {
            System.out.println("Withdraw amount must be positive.");
        } else if(amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
            System.out.println("Withdrawn $" + amount);
        }
    }

    public void displayAccountInfo() {
        System.out.println("Account ID: " + accountId);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + holderName);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Bank Name: " + bankName);
        System.out.println("Balance: $" + balance);
    }
}

public class BankingSystem {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Banking System");

        boolean running = true;
        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Create New Bank Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Bank Balance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositToAccount();
                    break;
                case 3:
                    withdrawFromAccount();
                    break;
                case 4:
                    viewBalance();
                    break;
                case 5:
                    System.out.println("Thanku for using the Banking System. Good day!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
        sc.close();
    }

    private static void createAccount() {
        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = sc.nextLine();
        System.out.print("Enter Bank Name: ");
        String bank = sc.nextLine();

        Account newAccount = new Account(name, email, phone, bank);
        accounts.add(newAccount);
        System.out.println("Account created successfully!");
        newAccount.displayAccountInfo();
    }

    private static Account findAccountByNumber(int accNum) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNum) {
                return acc;
            }
        }
        return null;
    }

    private static void depositToAccount() {
        System.out.print("Enter Account Number: ");
        int accNum = sc.nextInt();
        sc.nextLine();

        Account acc = findAccountByNumber(accNum);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        acc.deposit(amount);
    }

    private static void withdrawFromAccount() {
        System.out.print("Enter Account Number: ");
        int accNum = sc.nextInt();
        sc.nextLine();

        Account acc = findAccountByNumber(accNum);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        acc.withdraw(amount);
    }

    private static void viewBalance() {
        System.out.print("Enter Account Number: ");
        int accNum = sc.nextInt();
        sc.nextLine();

        Account acc = findAccountByNumber(accNum);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }

        acc.displayAccountInfo();
    }
}
