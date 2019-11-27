package app.executor;

import app.executor.task.BaseTask;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.*;

@Slf4j
public class ThreadExecutorService<V> {

  private static ThreadExecutorService executorService;

  private static final long THREAD_AWAIT_TERMINATION_TIMEOUT = 60;
  private static final int threadPoolSize = 2;
  private static final long taskFutureTimeout = 15L;
  private ThreadPoolExecutorCustom executor;

  public static ThreadExecutorService getInstance() {
    if (executorService == null) {
      synchronized (ThreadExecutorService.class) {
        executorService = new ThreadExecutorService();
        executorService.setExecutor(new ThreadPoolExecutorCustom(executorService.getThreadPoolSize()));
      }
    }

    return executorService;
  }

  public ThreadFuture submit(BaseTask<V> task) {
    Future<V> future = executor.submit(task);

    // wrap future
    ThreadFuture futureWrapper = new ThreadFuture(task, future);
    executor.addToCurrTasks(task.getId(), futureWrapper);

    return futureWrapper;
  }

  /**
   * This method is used to forcefully synchronize all threads
   * It will perform blocking calls Future#getSubject() and wait for existing background tasks to finish
   * FIXME this method causes a deadlock if one thread's task is finishing up at the same time this method gets call.
   * This is due to the fact that ThreadPoolExecutor#afterExecute() gets call after task is completed and it needs the
   * lock to ThreadPoolExecutor.currTasks which this method is currently holding on to.
   * Use {@link #waitAllTasksCompletion()} for now
   */
  public void forceAllTasksCompletion() {

    Map<String, ThreadFuture> currTasksMap = executor.getCurrTasks();
    synchronized (currTasksMap) {
      for (ThreadFuture future : currTasksMap.values()) {
        try {
          log.warn("Awaiting completion of the remaining {} tasks with {} tasks queued. Currently waiting taskId {} desc {}",
                  executor.getActiveCount(), executor.getQueue().size(), future.getTask().getId(), future.getTask().getDescription());
          future.getSubject(taskFutureTimeout);
        } catch (InterruptedException e) {
          log.warn("TaskId {} description {} has been interrupted",
                  future.getTask().getId(), future.getTask().getDescription());
        } catch (CancellationException e) {
          log.warn("TaskId {} description {} has been cancelled",
                  future.getTask().getId(), future.getTask().getDescription());
        } catch (TimeoutException e) {
          log.error(String.format("TaskId %s description '%s' timed out after %d seconds. Cancelling task to prevent blocking.",
                  future.getTask().getId(), future.getTask().getDescription(), taskFutureTimeout), e);
          future.getFuture().cancel(true);
        } catch (ExecutionException e) {
          log.error(String.format("TaskId %s description '%s' has thrown an exception during execution",
                  future.getTask().getId(), future.getTask().getDescription()), e);
        }
      }
    }
  }

  /**
   * This method is used to check and wait for all asynchronous tasks (active and queued) to be completed
   * @throws InterruptedException
   */
  public void waitAllTasksCompletion() throws InterruptedException {
    long startTs = System.currentTimeMillis();
    long timeoutMs = THREAD_AWAIT_TERMINATION_TIMEOUT * 1000;

    while ((executor.getActiveCount() > 0 || executor.getQueue().size() > 0) &&
            (System.currentTimeMillis() - startTs < timeoutMs)) {
      log.info("Waiting asynchronous tasks to complete - currently handling {} tasks with {} tasks queued.",
              executor.getActiveCount(), executor.getQueue().size());
      Thread.sleep(3000);
    }
  }

  public int getThreadPoolSize() {
    return threadPoolSize;
  }

  public ThreadPoolExecutorCustom getExecutor() {
    return executor;
  }

  private void setExecutor(ThreadPoolExecutorCustom executor) {
    this.executor = executor;
  }
}
