package ait.bank;

import ait.bank.model.Account;
import ait.bank.servis.Transfer;

public class BankDeadlockAppl {
    public static void main(String[] args) throws InterruptedException {
        Account father = new Account(1000);
        Account son = new Account(20_000);
        father.debit(1000);
        son.debit(1000);
        Transfer transfer1 = new Transfer(father, son, 900);
        Transfer transfer2 = new Transfer(son, father, 900);
        Thread thread1 = new Thread(transfer1);
        Thread thread2 = new Thread(transfer2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("Balance of acc: " + father.getAccNumber() + " = " + father.getBalance());
        System.out.println("Balance of acc: " + son.getAccNumber() + " = " + son.getBalance());
    }
}
