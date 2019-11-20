package app.executor;

import app.executor.task.BaseTask;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThreadFuture<V> {

  private Future<V> future;
  private BaseTask<V> task;
  private V subject;


  public ThreadFuture(BaseTask<V> task, Future<V> future) {
    this.task = task;
    this.future = future;
  }

  @JsonIgnore
  public V getSubject(long timeout) throws ExecutionException, InterruptedException, TimeoutException {
    if (this.subject == null) {
      this.subject = future.get(timeout, TimeUnit.SECONDS);
    }
    return this.subject;
  }

  public Future<V> getFuture() {
    return future;
  }

  public BaseTask<V> getTask() {
    return task;
  }
}
