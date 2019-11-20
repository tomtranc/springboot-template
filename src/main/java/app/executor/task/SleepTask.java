package app.executor.task;

import app.executor.ResponseObj;

public class SleepTask extends BaseTask<ResponseObj> {

  public SleepTask(ResponseObj subject) {
    this(subject, null);
  }

  public SleepTask(ResponseObj subject, String description) {
    super(subject, description);
  }

  @Override
  public void executeTask() throws Exception {
    Thread.sleep(2000);
    getSubject().setResult(String.format("Task Id '%s' with name '%s' was executed", getId(), getSubject().getTaskName()));
  }

}
