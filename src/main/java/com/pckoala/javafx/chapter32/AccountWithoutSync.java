package com.pckoala.javafx.chapter32;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** Demonstrate data corruption of an account with multiple threads accessing one resource. */
public class AccountWithoutSync {
  private static final Account account = new Account();

  public static void main(String[] args) {
    ExecutorService executor = Executors.newCachedThreadPool();

    // Create and launch 100 threads
    for (int i = 0; i < 100; i++) {
      executor.execute(new AddAPennyTask());
    }
    executor.shutdown();

    // Wait until all tasks are finished.
    while (!executor.isTerminated()) {

    }

    System.out.println("Balance = " + account.getBalance());
  }

  private static class Account {
    private int balance = 0;

    public int getBalance() {
      return balance;
    }

    // Adding synchronized locks the method to one thread at a time
    public synchronized void deposit(int amount) {
      int newBalance = balance + amount;
      // This delay is deliberately added to magnify the
      // data corruption problem and make it easy to see.
      try {
        Thread.sleep(5);
      } catch (InterruptedException ex) {
        throw new RuntimeException("InterruptedException", ex);
      }

      balance = newBalance;
    }
  }

  private static class AddAPennyTask implements Runnable {
    public void run() {
      // Enables you to synchronize part of the code instead of the entire method, increasing
      // concurrency.
      synchronized (account) {
        account.deposit(1);
      }
    }
  }
}
