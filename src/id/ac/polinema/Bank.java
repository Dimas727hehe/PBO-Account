package id.ac.polinema;

public class Bank {
    private AccountRepository repository;

    public Bank(AccountRepository repository) {
        this.repository = repository;
    }

    public boolean addAccount(Account account) {
        if (repository.findByNumber(account.getAccountNumber()) != null) {
            return false;
        }
        repository.save(account);
        return true;
    }

    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        Account acc = repository.findByNumber(accountNumber);
        if (acc == null) {
            throw new AccountNotFoundException("Account with number " + accountNumber + " not found");
        }
        return acc;
    }

    public void printAllAccounts() {
        for (Account acc : repository.findAll()) {
            acc.printInfo();
        }
    }

    public void printMonthlyFees() {
        for (Account acc : repository.findAll()) {
            System.out.println(acc.getAccountNumber() + " fee: " + acc.monthlyFee());
        }
    }

    public void processMonthEnd() {
        for (Account acc : repository.findAll()) {
            if (acc instanceof InterestBearing bearing) {
                bearing.applyInterest();
                System.out.println(acc.getAccountNumber() + " interest applied, new balance: " + acc.getBalance());
            }
            System.out.println(acc.getAccountNumber() + " monthly fee: " + acc.monthlyFee());
        }
    }

    public void printAuditLog() {
        for (Account acc : repository.findAll()) {
            if (acc instanceof Auditable audit) {
                System.out.println(audit.auditLog());
            }
        }
    }

    public void printHistory(String accountNumber) {
        try {
            Account acc = findAccount(accountNumber);
            for (Transaction t : acc.getHistory()) {
                System.out.println(accountNumber + " " + t);
            }
        } catch (AccountNotFoundException e) {
            System.out.println("Account not found: " + accountNumber);
        }
    }
}
