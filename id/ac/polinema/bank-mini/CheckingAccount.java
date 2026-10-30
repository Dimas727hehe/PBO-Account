package id.ac.polinema;

public class CheckingAccount extends Account implements Auditable {
    private double overdraftLimit;
    private static final double MONTHLY_FEE = 15000;

    public CheckingAccount(String accountNumber, Customer owner, double balance, double overdraftLimit) {
        super(accountNumber, owner, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public double monthlyFee() {
        return MONTHLY_FEE;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    @Override
    protected boolean canWithdraw(double amount) {
        return amount > 0 && amount <= getBalance() + overdraftLimit;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Account type: Checking, overdraft limit: " + overdraftLimit);
    }

    @Override
    public String auditLog() {
        return getAccountNumber() + " - " + getOwner().getName() + " - balance: " + getBalance() + " - overdraft limit: " + overdraftLimit;
    }
}
