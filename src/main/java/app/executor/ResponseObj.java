package app.executor;

import java.util.Date;

public class ResponseObj {

  public ResponseObj(String taskName) {
    this.taskName = taskName;
  }

  String taskName;
  String result;

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }
}
