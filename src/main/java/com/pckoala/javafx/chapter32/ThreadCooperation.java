package com.pckoala.javafx.chapter32;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadCooperation {
  private static final Account account = new Account();

  public static void main(String[] args) {
    // Create a thread pool with two threads
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    executorService.execute(new DepositTask());
    executorService.execute(new WithdrawTask());
    executorService.shutdown();

    System.out.println("Thread 1\t\t\t\t\tThread 2\t\t\t\t\tBalance");
  }

  private static class Account {
    // Create a new lock
    private static final Lock lock = new ReentrantLock();

    // Create a condition
    private static final Condition newDeposit = lock.newCondition();

    private int balance = 0;

    public int getBalance() {
      return balance;
    }

    public void withdraw(int amount) {
      lock.lock(); // Acquire the lock
      try {
        while (balance < amount) {
          System.out.println("\t\t\t\t\t\t\tFailed to withdraw " + amount + "\t\t" + getBalance());
          newDeposit.await();
        }

        balance -= amount;
        System.out.println("\t\t\t\t\t\t\tWithdraw " + amount + "\t\t\t\t\t\t  " + getBalance());
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.unlock(); // Release the lock
      }
    }

    public void deposit(int amount) {
      lock.lock(); // Acquire the lock
      try {
        balance += amount;
        String padding = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        if (amount >= 10) {
          padding = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        }
        System.out.println("Deposit " + amount + padding + getBalance());

        // Signal thread waiting on the condition
        newDeposit.signalAll();
      } catch (Exception e) {
        throw new RuntimeException(e);
      } finally {
        lock.unlock();
      }
    }
  }

  private static class DepositTask implements Runnable {
    // Keep adding a random amount to the account
    @Override
    public void run() {
      try { // Purposely delay it to let the withdraw() method proceed
        while (true) {
          account.deposit((int) (Math.random() * 10) + 1);
          Thread.sleep(1000);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private static class WithdrawTask implements Runnable {
    // Keep subtracting a random amount from the account
    @Override
    public void run() {
      while (true) {
        account.withdraw((int) (Math.random() * 10) + 1);
      }
    }
  }
}
