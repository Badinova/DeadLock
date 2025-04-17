package ait.bank.servis;

import ait.bank.model.Account;

public class Transfer implements Runnable{
    private Account accForm;
    private Account accTo;
    private int sum;

    public Transfer(Account accForm, Account accTo, int sum) {
        this.accForm = accForm;
        this.accTo = accTo;
        this.sum = sum;
    }

    @Override
    public void run() {
        synchronized (accForm) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (accTo) {
                if (accForm.getBalance() >= sum) {
                    accForm.credit(sum);
                    accTo.debit(sum);
                }
            }
        }
    }
}
