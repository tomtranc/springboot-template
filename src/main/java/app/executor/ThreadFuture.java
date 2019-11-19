package app.executor;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ThreadFuture<V> {

  private Future<V> future;
  private TaskBase<V> task;
  private V subject;


  public ThreadFuture(TaskBase<V> task, Future<V> future) {
    this.task = task;
    this.future = future;
  }

  @JsonIgnore
  public V getSubject() throws ExecutionException, InterruptedException {
    if (this.subject == null) {
      this.subject = future.get();
    }
    return this.subject;
  }

  public Future<V> getFuture() {
    return future;
  }

  public TaskBase<V> getTask() {
    return task;
  }
}
