package com.pckoala.javafx.chapter32;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerProducer {
  private static final Buffer buffer = new Buffer();

  public static void main(String[] args) {
    // Create a thread pool with two threads
    ExecutorService executor = Executors.newFixedThreadPool(2);
    executor.execute(new ProducerTask());
    executor.execute(new ConsumerTask());
    executor.shutdown();
  }

  private static class Buffer {
    private static final int CAPACITY = 1; // buffer size
    private final LinkedList<Integer> queue = new LinkedList<>();

    // Create a new lock
    private static final Lock lock = new ReentrantLock();

    // Create two conditions
    private static final Condition notEmpty = lock.newCondition();
    private static final Condition notFull = lock.newCondition();

    public void write(int value) {
      lock.lock(); // Acquire the lock
      try {
        while (queue.size() == CAPACITY) {
          System.out.println("Wait for notFull condition.");
          notFull.await();
        }
        queue.offer(value);
        notEmpty.signal();
      } catch (InterruptedException e) {
        throw new RuntimeException("Program was interrupted.", e);
      } finally {
        lock.unlock();
      }
    }

    public int read() {
      int value;
      lock.lock(); // Acquire the lock
      try {
        while (queue.isEmpty()) {
          System.out.println("\t\tWait for notEmpty condition");
          notEmpty.await();
        }
        value = queue.remove();
        notFull.signal();
      } catch (InterruptedException e) {
        throw new RuntimeException("Program was interrupted.", e);
      } finally {
        lock.unlock();
      }
      return value;
    }

  }

  private static class ProducerTask implements Runnable {


    @Override
    public void run() {
      try {
        int i = 1;
        while (true) {
          System.out.println("Producer writes " + i);
          buffer.write(i++); // Add a value to the buffer
          // Put the thread to sleep
          Thread.sleep((int) (Math.random() * 10000));
        }
      } catch (InterruptedException e) {
        throw new RuntimeException("Program interrupted", e);
      }
    }
  }

  private static class ConsumerTask implements Runnable {

    @Override
    public void run() {
      try {
        while (true) {
          System.out.println("\t\t\tConsumer reads " + buffer.read());
          // Put the thread to sleep
          Thread.sleep((int)(Math.random() * 10000));
        }
      } catch (InterruptedException e) {
        throw new RuntimeException("Program interrupted", e);
      }
    }
  }
}
