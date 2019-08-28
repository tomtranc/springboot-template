package app.executor;

import java.sql.Timestamp;
import java.util.concurrent.Callable;

public abstract class TaskBase<V> implements Callable<V> {

  private String id;
  private String description;
  private V subject;
  private Timestamp startTs, finishTs;
  private long taskDurationTs;

  public TaskBase(V subject) {
    this(subject, null);
  }

  public TaskBase(V subject, String description) {
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
