package app.executor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Wrapper to ThreadPoolExecutor maintain a list of currentTasks
 */
public class ThreadPoolExecutorCustom extends ThreadPoolExecutor {
  private Map<String, ThreadFuture> currTasks;

  public ThreadPoolExecutorCustom(int nThreads) {
    super(nThreads, nThreads, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    this.currTasks = Collections.synchronizedMap(new HashMap<>());
  }

  protected void beforeExecute(Thread t, Runnable r) {
    super.beforeExecute(t, r);
    // add before task execution if needed
  }

  protected void afterExecute(Runnable r, Throwable t) {
    super.afterExecute(r, t);
    currTasks.remove(r);
  }

  /**
   * get the current task synchronizedMap
   * @return currTasks - be sure to synchronize on this returned map when iterating over any of its collection view
   */
  public Map<String, ThreadFuture> getCurrTasks() {
    return currTasks;
  }

  public void addToCurrTasks(String key, ThreadFuture value) {
    currTasks.put(key, value);
  }
}
