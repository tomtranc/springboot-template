package app.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;

import static app.utils.Helpers.print;

public class ThreadExecutorService<V> {

  private static ThreadExecutorService executorService;

  private int threadPoolSize = 2;
  private ThreadPoolExecutor executor;
  private Map<String, ThreadFuture> currTasks;

  private final Logger LOG = LoggerFactory.getLogger(getClass());

  public static ThreadExecutorService getInstance() {
    if (executorService == null) {
      synchronized (ThreadExecutorService.class) {
        executorService = new ThreadExecutorService();
        executorService.setExecutor((ThreadPoolExecutor)
                Executors.newFixedThreadPool(executorService.getThreadPoolSize()));
        executorService.setCurrTasks(Collections.synchronizedMap(new HashMap<String, ThreadFuture>()));
      }
    }

    return executorService;
  }

  public ThreadFuture submit(TaskBase<V> task) {
    Future<V> future = executor.submit(task);

    // wrap future
    ThreadFuture futureWrapper = new ThreadFuture(task, future);
    currTasks.put(task.getId(), futureWrapper);

    return futureWrapper;
  }

  public void done(TaskBase task) {
    ThreadFuture future = currTasks.get(task.getId());
    currTasks.remove(task.getId());
//    try {
//      future.getSubject();
//    } catch (InterruptedException e) {
//      print("Task %s description \"%s\" has been interrupted", //$NON-NLS-1$
//              task.getId(), task.getDescription());
//    } catch (CancellationException e) {
//      print("Task %s description \"%s\" has been cancelled", //$NON-NLS-1$
//              task.getId(), task.getDescription());
//    } catch (ExecutionException e) {
//      print("Task %s description \"%s\" execution failed", //$NON-NLS-1$
//              task.getId(), task.getDescription());
//      e.printStackTrace();
//    }
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

  public Map<String, ThreadFuture> getCurrTasks() {
    return currTasks;
  }

  public void setCurrTasks(Map<String, ThreadFuture> currTasks) {
    this.currTasks = currTasks;
  }
}
