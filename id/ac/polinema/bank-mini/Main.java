package id.ac.polinema;

public class Main {
    public static void main(String[] args) {
        Account limited = new Account("A005", "Fajar", 10000000, 200000);
        System.out.println("Widthdraw 300000 allowed? " + limited.withdraw(300000));
        System.out.println("Widthdraw 150000 allowed? " + limited.withdraw(150000));
        limited.printInfo();
    }
}