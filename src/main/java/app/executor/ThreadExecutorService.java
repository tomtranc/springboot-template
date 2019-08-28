package app.executor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadExecutorService<V> {

  private int threadPoolSize = 2;

  private ThreadPoolExecutor executor;
  private List<ThreadFuture> currTasks;

  public ThreadExecutorService() {
    this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadPoolSize);
    this.currTasks =  Collections.synchronizedList(new ArrayList<ThreadFuture>());
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

  public List<ThreadFuture> getCurrTasks() {
    return currTasks;
  }
}
