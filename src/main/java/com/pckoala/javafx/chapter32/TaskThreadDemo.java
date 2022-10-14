package com.pckoala.javafx.chapter32;

public class TaskThreadDemo {
  public static void main(String[] args) {
    // Create tasks
    Runnable printA = new PrintChar('a', 1000);
    Runnable printB = new PrintChar('b', 100);
    Runnable print100 = new PrintNum(100);

    // Create threads
    Thread thread1 = new Thread(printA);
    Thread thread2 = new Thread(printB);
    Thread thread3 = new Thread(print100);

    // Start threads
    thread1.start();
    thread2.start();
    thread3.start();
  }

  private static class PrintChar implements Runnable {
    private final char charToPrint; // The character to print
    private final int times; // The number of times to repeat

    /**
     * Construct a task with a specified character and number of times to print the character.
     *
     * @param c The character to print
     * @param t The number of times to repeat
     */
    public PrintChar(char c, int t) {
      charToPrint = c;
      times = t;
    }

    @Override
    public void run() {
      for (int i = 0; i < times; i++) {
        System.out.print(charToPrint);
      }
    }
  }

  // The task class for printing numbers from 1 to n for a given n
  private static class PrintNum implements Runnable {
    private final int lastNum;

    /**
     * Construct a task for printing 1, 2, ..., n
     *
     * @param n The
     */
    public PrintNum(int n) {
      lastNum = n;
    }

    @Override
    public void run() {
      for (int i = 1; i <= lastNum; i++) {
        System.out.print(" " + i);
      }
    }
  }
}
