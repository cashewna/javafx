package com.pckoala.javafx.chapter32;

import com.pckoala.javafx.chapter32.TaskThreadDemo.PrintChar;
import com.pckoala.javafx.chapter32.TaskThreadDemo.PrintNum;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Optimise TaskThreadDemo operations using a thread pool which manages the number of tasks
 * concurrently.
 */
public class ExecutorDemo {
  public static void main(String[] args) {
    // Create a fixed thread pool with a maximum of three threads
    ExecutorService executor = Executors.newFixedThreadPool(3);

    // Submit runnable tasks to the executor
    executor.execute(new PrintChar('a', 100));
    executor.execute(new PrintChar('b', 100));
    executor.execute(new PrintNum(100));

    // Shut down the executor
    executor.shutdown();
  }
}
