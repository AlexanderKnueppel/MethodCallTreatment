package java.io;

public class PrintStream {
  /*@ public normal_behavior
    @ ensures true;
    @*/
  public void /*@ strictly_pure @*/ println(int x);
}