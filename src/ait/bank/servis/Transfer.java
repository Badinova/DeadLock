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
        Account firstLock;
        Account secondLock;

        if (accForm.getAccNumber() < accTo.getAccNumber()) {
            firstLock = accForm;
            secondLock = accTo;
        } else {
            firstLock = accTo;
            secondLock = accForm;
        }
        synchronized (firstLock) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (secondLock) {
                if (accForm.getBalance() >= sum) {
                    accForm.credit(sum);
                    accTo.debit(sum);
                }
            }
        }
    }
}
