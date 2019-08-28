package app.utils;

public class Helpers {

  public static <T> void print(String str, T...inputs) {
    System.out.println(String.format(str, inputs));
  }
}
