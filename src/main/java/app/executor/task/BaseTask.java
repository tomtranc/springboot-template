package app.executor.task;

import app.executor.ThreadExecutorService;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.util.concurrent.Callable;

@Slf4j
public abstract class BaseTask<V> implements Callable<V> {

  private String id;
  private String description;
  private V subject;
  private Timestamp startTs, finishTs;
  private long taskDurationTs;

  private ThreadExecutorService executorService = ThreadExecutorService.getInstance();

  // actual implementation of the call() execution
  public abstract void executeTask() throws Exception;

  @Override
  public V call() throws Exception {

    // log start timestamp
    Long start = System.currentTimeMillis();
    this.startTs = new Timestamp(start);

    log.debug("Thread %s started taskId \"{}\" description: \"%s\"", Thread.currentThread().getName(), id, description);
    try {
      executeTask();
    } finally {
      // log execution duration
      this.finishTs = new Timestamp(System.currentTimeMillis());
      this.taskDurationTs = System.currentTimeMillis() - start;

      log.debug("Thread {} finished taskId {} description: {}", Thread.currentThread().getName(), id, description);
    }

    return subject;
  }

  public BaseTask(V subject) {
    this(subject, null);
  }

  public BaseTask(V subject, String description) {
    setSubject(subject);
    setId(Long.toString(System.nanoTime(), 10));
    setDescription(description);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Timestamp getStartTs() {
    return startTs;
  }

  public void setStartTs(Timestamp startTs) {
    this.startTs = startTs;
  }

  public Timestamp getFinishTs() {
    return finishTs;
  }

  public void setFinishTs(Timestamp finishTs) {
    this.finishTs = finishTs;
  }

  public long getTaskDurationTs() {
    return taskDurationTs;
  }

  public void setTaskDurationTs(long taskDurationTs) {
    this.taskDurationTs = taskDurationTs;
  }

  public V getSubject() {
    return subject;
  }

  public void setSubject(V subject) {
    this.subject = subject;
  }
}
