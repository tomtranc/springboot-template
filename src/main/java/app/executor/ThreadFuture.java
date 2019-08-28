package app.executor;

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
