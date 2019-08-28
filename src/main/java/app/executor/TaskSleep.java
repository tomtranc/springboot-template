package app.executor;

import java.sql.Timestamp;

import static app.utils.Helpers.print;

public class TaskSleep extends TaskBase<ResponseObj> {

  public TaskSleep(ResponseObj subject) {
    this(subject, null);
  }

  public TaskSleep(ResponseObj subject, String description) {
    super(subject, description);
  }

  @Override
  public ResponseObj call() throws Exception {
    long start = System.currentTimeMillis();

    print("Thread %s started task: %s", Thread.currentThread().getName(), getSubject().getTaskName());
    Thread.sleep(2000);
    print("Thread %s ended task: %s", Thread.currentThread().getName(), getSubject().getTaskName());

    // log time duration
    setStartTs(new Timestamp(start));
    setFinishTs(new Timestamp(System.currentTimeMillis()));
    setTaskDurationTs(System.currentTimeMillis() - start);

    getSubject().setResult(String.format("%s ran for %s ms", getSubject().getTaskName(), getTaskDurationTs()));

    return getSubject();
  }

}
