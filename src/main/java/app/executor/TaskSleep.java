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
  public void executeTask() throws Exception {
    Thread.sleep(2000);;
  }

}
