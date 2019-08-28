package app.executor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadExecutorService<V> {

  private static ThreadExecutorService executorService;

  private int threadPoolSize = 2;
  private ThreadPoolExecutor executor;
  private List<ThreadFuture> currTasks;

  public static ThreadExecutorService getInstance() {
    if (executorService == null) {
      synchronized (ThreadExecutorService.class) {
        executorService = new ThreadExecutorService();
        executorService.setExecutor((ThreadPoolExecutor)
                Executors.newFixedThreadPool(executorService.getThreadPoolSize()));
        executorService.setCurrTasks(Collections.synchronizedList(new ArrayList<>()));
      }
    }

    return executorService;
  }

  public ThreadFuture submit(TaskBase<V> task) {
    Future<V> future = executor.submit(task);

    // wrap future
    ThreadFuture futureWrapper = new ThreadFuture(task, future);
    currTasks.add(futureWrapper);

    return futureWrapper;
  }

  public int getThreadPoolSize() {
    return threadPoolSize;
  }

  public ThreadPoolExecutor getExecutor() {
    return executor;
  }

  private void setExecutor(ThreadPoolExecutor executor) {
    this.executor = executor;
  }

  public List<ThreadFuture> getCurrTasks() {
    return currTasks;
  }

  private void setCurrTasks(List<ThreadFuture> currTasks) {
    this.currTasks = currTasks;
  }
}
