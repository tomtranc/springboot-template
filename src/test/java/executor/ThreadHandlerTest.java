package executor;

import app.executor.ResponseObj;
import app.executor.TaskSleep;
import app.executor.ThreadExecutorService;
import app.executor.ThreadFuture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.concurrent.*;

import static app.utils.Helpers.print;
import static org.junit.Assert.assertEquals;

public class ThreadHandlerTest {

  private ThreadExecutorService executorService1 = ThreadExecutorService.getInstance();
  private ObjectMapper mapper = new ObjectMapper();

//  @Test
  public void threadPoolExecutorShortTest() throws InterruptedException, ExecutionException {
    ThreadPoolExecutor executor =
            (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
    Future<String> future1 = executor.submit(() -> {
      String taskName = "future1";
      print("Thread %s started task: %s", Thread.currentThread().getName(), taskName);
      Thread.sleep(1000);
      print("Thread %s ended task: %s", Thread.currentThread().getName(), taskName);
      return String.format("%s ran", taskName);
    });
    Future<String> future2 = executor.submit(() -> {
      String taskName = "future2";
      print("Thread %s started task: %s", Thread.currentThread().getName(), taskName);
      Thread.sleep(1000);
      print("Thread %s ended task: %s", Thread.currentThread().getName(), taskName);
      return String.format("%s ran", taskName);
    });
    Future<String> future3 = executor.submit(() -> {
      String taskName = "future3";
      print("Thread %s started task: %s", Thread.currentThread().getName(), taskName);
      Thread.sleep(1000);
      print("Thread %s ended task: %s", Thread.currentThread().getName(), taskName);
      return String.format("%s ran", taskName);
    });

    assertEquals(2, executor.getPoolSize());
    assertEquals(1, executor.getQueue().size());

    // calling Future#get() is a blocking call, it will block until thread finishes execution
    print("Extracted value: %s", future1.get());
    print("Extracted value: %s", future2.get());
    print("Extracted value: %s", future3.get());
  }

  @Test
  public void threadExecutorServiceTest() throws InterruptedException, ExecutionException, JsonProcessingException {
    ResponseObj task1 = new ResponseObj("task1");
    ResponseObj task2 = new ResponseObj("task2");
    ResponseObj task3 = new ResponseObj("task3");

    ThreadFuture<ResponseObj> future1 = executorService1.submit(new TaskSleep(task1));
    ThreadFuture<ResponseObj> future2 = executorService1.submit(new TaskSleep(task2));
    ThreadFuture<ResponseObj> future3 = executorService1.submit(new TaskSleep(task3));

    print("Executor1 pool: %s queue: %s", executorService1.getExecutor().getPoolSize(),
            executorService1.getExecutor().getQueue().size());

    assertEquals(2, executorService1.getExecutor().getPoolSize());
    assertEquals(1, executorService1.getExecutor().getQueue().size());

    print("currTasks: %s", mapper.writeValueAsString(executorService1.getCurrTasks()));

    // calling Future#get() is a blocking call, it will block until thread finishes execution
    print("Extracted value: %s ", future1.getSubject().getResult());
    print("Extracted value: %s", future2.getSubject().getResult());
    print("Extracted value: %s", future3.getSubject().getResult());
  }
}
