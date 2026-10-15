package id.ac.polinema;

public class Main {
    public static void main(String[] args) {
        Customer nadia = new Customer("Nadia", "0812-0000-0001");
        Customer sari = new Customer("Sari", "0812-0000-0002");

        Account acc1 = new Account("A001", nadia, 500000);
        Account acc2 = new Account("A002", nadia, 250000);
        Account acc3 = new Account("A003", sari, 200000);

        Bank bank = new Bank(10);
        bank.addAccount(acc1);
        bank.addAccount(acc2);
        bank.addAccount(acc3);

        Account[] hasil = bank.findAccountsByOwnerName("Nadia");
        System.out.println("Jumlah rekening Nadia: " + hasil.length);
        for (Account acc : hasil) {
            acc.printInfo();
        }
    }
}
