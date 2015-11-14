import java.io.*;
import java.util.*;

public class locker {

  public boolean ball;
  public int num;
  public boolean open;
  public HashSet<Integer> path;

  public locker (boolean ball, int lockNum) {
    this.ball = ball;
    num = lockNum;
    open = false;
    path = new HashSet<Integer>();
  }

  public String toString() {
    return ball + " " + num + " " + path;
  }

}
