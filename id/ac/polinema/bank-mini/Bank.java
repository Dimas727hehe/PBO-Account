package id.ac.polinema;

public class Bank {
    private Account[] accounts;
    private int count;

    public Bank(int capacity) {
        accounts = new Account[capacity];
        count = 0;
    }

    public void printMonthlyFees() {
        for (int i = 0; i < count; i++) {
            System.out.println(accounts[i].getAccountNumber() + " fee: " + accounts[i].monthlyFee());
        }
    }

    public boolean addAccount(Account account) {
        if (count >= accounts.length) {
            return false;
        }
        accounts[count] = account;
        count++;
        return true;
    }

    public void findAccount(String accountNumber) throws AccountNotFoundException {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                accounts[i].printInfo();
                found = true;
            }
        }
        if (!found) {
            throw new AccountNotFoundException("Account with number " + accountNumber + " not found");
        }
    }

    public void printAllAccounts() {
        for (int i = 0; i < count; i++) {
            accounts[i].printInfo();
        }
    }

    public void processMonthEnd() {
        for (int i = 0; i < count; i++) {
            Account acc = accounts[i];
            if (accounts[i] instanceof InterestBearing bearing) {
                bearing.applyInterest();
                System.out.println(acc.getAccountNumber() + " interest applied, new balance: " + acc.getBalance());
            }
            System.out.println(acc.getAccountNumber() + " monthly fee: " + acc.monthlyFee());
        }
    }
    
    public void printAuditLog(){
        for (Account acc : accounts) {
            if (acc instanceof Auditable audit) {
                System.out.println(audit.auditLog());
            }
        }
    }
}
