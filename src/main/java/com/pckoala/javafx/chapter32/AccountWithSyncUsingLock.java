package com.pckoala.javafx.chapter32;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountWithSyncUsingLock {
  private static final Account account = new Account();

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();

    // Create and launch 100 threads
    for (int i = 0; i < 100; i++) {
      executorService.execute(new AddAPennyTask());
    }

    executorService.shutdown();

    // Wait until all tasks are finished
    while (!executorService.isTerminated()) {

    }

    System.out.println("Balance = " + account.getBalance());
  }

  private static class Account {
    private static final Lock lock = new ReentrantLock(); // Create a lock
    private int balance = 0;

    public int getBalance() {
      return balance;
    }

    public void deposit(int amount) {
      lock.lock(); // Acquire the lock

      try {
        int newBalance = balance + amount;

        // This delay is deliberately added to magnify the
        // data-corruption problem and make it easy to see.
        Thread.sleep(5);

        balance = newBalance;
      } catch (InterruptedException ex) {
        throw new RuntimeException("InterruptedException triggered", ex);
      } finally {
        lock.unlock(); // Release the lock
      }
    }
  }

  private static class AddAPennyTask implements Runnable {

    @Override
    public void run() {
      account.deposit(1);
    }
  }
}
