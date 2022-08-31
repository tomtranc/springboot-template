package executor;

import app.executor.ResponseObj;
import app.executor.task.SleepTask;
import app.executor.ThreadExecutorService;
import app.executor.ThreadFuture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static app.utils.Helpers.print;
import static org.assertj.core.api.Assertions.assertThat;

public class ThreadHandlerTest {

  private static final ThreadExecutorService executorService1 = ThreadExecutorService.getInstance();
  private ObjectMapper mapper = new ObjectMapper();

  @AfterAll
  public static void afterClass() throws InterruptedException {
    executorService1.waitAllTasksCompletion();
  }

  @Test
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

    assertThat(executor.getPoolSize()).isEqualTo(2);
    assertThat(executor.getQueue().size()).isEqualTo(1);

    // calling Future#get() is a blocking call, it will block until thread finishes execution
    print("Extracted value: %s", future1.get());
    print("Extracted value: %s", future2.get());
    print("Extracted value: %s", future3.get());
  }

  @Test
  public void threadExecutorServiceTest() throws InterruptedException, ExecutionException, TimeoutException, JsonProcessingException {
    ResponseObj task1 = new ResponseObj("task1");
    ResponseObj task2 = new ResponseObj("task2");
    ResponseObj task3 = new ResponseObj("task3");

    ThreadFuture<ResponseObj> future1 = executorService1.submit(new SleepTask(task1));
    ThreadFuture<ResponseObj> future2 = executorService1.submit(new SleepTask(task2));
    ThreadFuture<ResponseObj> future3 = executorService1.submit(new SleepTask(task3));

//    print("Executor1 pool: %s queue: %s", executorService1.getExecutor().getPoolSize(), executorService1.getExecutor().getQueue().size());
    assertThat(executorService1.getExecutor().getPoolSize()).isEqualTo(executorService1.getThreadPoolSize());
    assertThat(executorService1.getExecutor().getQueue().size()).isGreaterThan(0);

    assertThat(executorService1.getExecutor().getPoolSize()).isEqualTo(executorService1.getThreadPoolSize());
    assertThat(executorService1.getExecutor().getQueue().size()).isEqualTo(1);

//    print("currTasks: %s", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(executorService1.getExecutor().getCurrTasks()));

    // calling Future#get() is a blocking call, it will block until thread finishes execution
    print("Extracted value: %s ", future1.getSubject(5).getResult());
    print("Extracted value: %s", future2.getSubject(5).getResult());
    print("Extracted value: %s", future3.getSubject(5).getResult());
  }
}
