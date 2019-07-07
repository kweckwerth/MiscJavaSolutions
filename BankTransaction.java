package com.test;


import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;


/*
 * Create the Account and Transaction classes here.
 */
class TransactionRunnable implements Runnable {
    private static final SecureRandom RANDOM_GENERATOR = new SecureRandom();
    private final Transaction transaction;
    private final int transactionsCount;

    public TransactionRunnable(Transaction transaction, int transactionsCount) {
        this.transaction = transaction;
        this.transactionsCount = transactionsCount;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.transactionsCount; i++) {
            int transactionType = RANDOM_GENERATOR.nextInt() % 2;
            int money = RANDOM_GENERATOR.nextInt(100) + 1;

            if (transactionType == 0) {
                this.transaction.deposit(money);
            } else {
                this.transaction.withdraw(money);
            }
        }
    }
}

class Account {
    //private final Object lock = new Object();

    int balance = 0;

    public int getBalance() {
        return balance;
    }

    public String deposit(int money) {
        //synchronized (lock)
        //{
        balance += money;
        return "Depositing $" + money;
        //}
    }

    public String withdraw(int money) {
        //	synchronized (lock)
        //	{
        int tempBal = balance - money;

        if (tempBal < 0) {
            //do nothing
            return "Withdrawing $" + money + " (Insufficient Balance)";
        } else {
            balance = tempBal;
            return "Withdrawing $" + money;
        }
        //}
    }
}

class Transaction {
    private Account account;
    List<String> transactions = new ArrayList<String>();

    public Transaction(Account account) {
        this.account = account;
    }

    public void deposit(int money) {
        synchronized (account) {
            transactions.add(account.deposit(money));
        }
    }

    public void withdraw(int money) {
        synchronized (account) {
            transactions.add(account.withdraw(money));
        }
    }

    public List<String> getTransaction() {
        return this.transactions;
    }

}
