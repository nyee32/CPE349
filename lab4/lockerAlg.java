import java.io.*;
import java.util.*;

public class lockerAlg {

  public ArrayList<locker> list = new ArrayList<locker>();
  private ArrayList<Integer> keys = new ArrayList<Integer>();
  private ArrayList<Integer> tennisBalls = new ArrayList<Integer>();
  private int[][] table;
  private int lockerCnt;
  private int keyCnt;
  private int tennisBCnt;


  public static void main(String[] args) throws FileNotFoundException {
    lockerAlg a = new lockerAlg();
    a.readFile(args[0]);
    HashSet<Integer> tmp = new HashSet<Integer>();
    tmp.add(3);
    tmp.add(4);
    tmp.add(2);
    tmp.add(5);



    // Change so that arraylist index for lockers match the locker number



    //System.out.println("left most ball is = " + a.getLeftLockerWithBall(2));

    //System.out.println("valid = " + a.isValidPath(tmp, 5));

    //System.out.println(a.getPath(1,3) + " " + a.getPath(1,3).size());
    System.out.println("Shortest = " + a.getOpt());
    //a.printTable();

  }

  private void readFile (String file) throws FileNotFoundException {
    int lockers = 0;
    int numOfKeys = 0;
    int tBCnt = 0;
    int ltb = 0; //tennis ball in locker counter
    ArrayList<Integer> tb = new ArrayList<Integer>();
    Scanner sc = new Scanner(new File(file));

    lockers = sc.nextInt();
    numOfKeys = sc.nextInt();
    tBCnt = sc.nextInt();

    for (int j = 0; j < numOfKeys; j++) {
      keys.add(sc.nextInt());
    }
    Collections.sort(keys);

    //System.out.println("keys = " +keys);

    for (int k = 0; k < tBCnt; k++) {
      tb.add(sc.nextInt());
    }

    Collections.sort(tb);
    //System.out.println("Tennisballs = " + tb);

    //Setup table with locker count
    for (int i = 1; i <= lockers; i++) {

      if (ltb < tb.size() && i == tb.get(ltb)) {
	list.add(new locker(true, i));
	ltb++;
      }
      else {
	list.add(new locker(false, i));
      }

    }

    //Set Table
    table = new int[numOfKeys][lockers];
    lockerCnt = lockers;
    keyCnt = numOfKeys;
    tennisBCnt = tBCnt;
    tennisBalls = tb;

    //System.out.println(list);

    System.out.println("l = " + lockers + " K = " + numOfKeys +
		       " tb = " + tBCnt);
  }

  private HashSet<Integer> getPath(int key, int i) {
    int a; //start
    int b; //finish
    HashSet<Integer> path = new HashSet<Integer>();

    if (key == i) {
      System.out.println("same " + key + " "+ i);
      path.add(list.get(i - 1).num);
      return path;
    }


    if (key > i) {
      a = i;
      b = key;
    }
    else {
      b = i;
      a = key;
    }

    while (b >= a) {
      path.add(list.get(b - 1).num);
      b--;
    }

    //list.get(i + 1).path = path;

    return path;
  }

  private boolean isValidPath(HashSet<Integer> p, int max) {
    boolean valid = false;
    HashSet<Integer> tb = new HashSet<Integer>();

    for (int i = 0; i < tennisBalls.size(); i++) {
      if (tennisBalls.get(i) <= (max + 1)) {
	//System.out.println("ADDING... " + tennisBalls.get(i));
	tb.add(tennisBalls.get(i));
      }
    }

    //System.out.println("Valid tennis balls in list p " + tb);
    return p.containsAll(tb);

  }

  // locker return is base 1. first locker is 1
  private int getLeftLockerWithBall (int l) {
    int end = l - 2;

    while (end >= 0) {
      // System.out.println("getting locker = " + list.get(end).num);
      if (list.get(end).ball) {
	return list.get(end).num;
      }
      end--;
    }
    //System.out.println("end = " + end);
    return -1;

  }

  private int getKeyShortestPath(int locker) {
    int shortest = 9999999;
    int smallestKey = 0;
    int tmpLocker = locker - 1;

    for (int i = 0; i < keyCnt; i++) {
      //System.out.println("table val = " + table[i][tmpLocker]);
      if (table[i][tmpLocker] < shortest && table[i][tmpLocker] > 0) {
	shortest = table[i][tmpLocker];
	smallestKey = i;
	//System.out.println("value = " + shortest);
      }
    }

    return smallestKey;

  }

  private int smallestValAtLocker (int locker) {
    int shortest = 9999999;
    int tmpLocker = locker - 1;

    for (int i = 0; i < keyCnt; i++) {
      if (table[i][tmpLocker] < shortest && table[i][tmpLocker] > 0) {
	shortest = table[i][tmpLocker];
      }
    }

    return shortest;


  }


  private int minDist(HashSet<Integer> path, int locker,
		      int prev, int leftKey, int leftLocker) {

    int retval = 0;
    int useLeftVal = 0;
    HashSet<Integer> tmpPath = new HashSet<Integer>(path);

    if (leftLocker != -1) {
      System.out.println("tmpPath = " + tmpPath);
      useLeftVal = leftLockerShortestPath(leftKey,
					  leftLocker - 1,
					  locker,
					  tmpPath);
    }

    System.out.println("Locker: " + (locker+1) + " {" + path.size() + " ,"
		       + prev + " ," + useLeftVal + "}");


    // Path does not contain all tennis balls
    if (!isValidPath(path, list.get(locker).num)) {

      if (prev > useLeftVal && useLeftVal > 0) {
	System.out.println("Path = " + path);
	System.out.println("tmpPath = " + tmpPath);

	retval = useLeftVal;
	list.get(locker).path = tmpPath;
      }
      else {
	retval = prev;
	list.get(locker).path = list.get(locker - 1).path;
      }


      retval = Math.min(prev, useLeftVal);
    }
    else { // Path contains all tennis balls
      if (prev > useLeftVal && useLeftVal > 0) {
	if (path.size() > useLeftVal && useLeftVal > 0) {
	  retval = useLeftVal;
	  list.get(locker).path = tmpPath;
	}
	else {
	  retval = path.size();
	  list.get(locker).path = path;
	}

      }
      else {

	if (path.size() > prev && prev > 0) {
	  retval = prev;
	  list.get(locker).path = list.get(locker - 1).path;
	}
	else {
	  retval = path.size();
	  list.get(locker).path = path;
	}

      }
    }

    return retval;

  }

  // leftLocker must be locker based in the list (0 based)
  private int leftLockerShortestPath (int leftKey, int leftLocker,
				      int locker, HashSet<Integer> path) {

    HashSet<Integer> leftLockerPath = new HashSet<Integer>(list.get(leftLocker).path);
    System.out.println("B4 Getting left locker math = " + leftLockerPath + " @left locker = " + (leftLocker+1));
    leftLockerPath.addAll(path);

    System.out.println("leng = " + (table[leftKey][leftLocker] + path.size() - Math.abs(keys.get(leftKey) - list.get(locker).num)));

    return table[leftKey][leftLocker] + path.size();

  }



  private void resetPath() {

    for (int i = 1; i < list.size(); i++) {
      list.get(i).path.clear();
    }

  }

  private int getOpt() {
    // locker cnt is off by one!!!
    HashSet<Integer> tmpPath = new HashSet<Integer>();
    int leftLocker = 0;
    int leftLockerKey = 0;

    for (int key = 0; key < keyCnt; key++) {

      for (int locker = 0; locker < lockerCnt; locker++) {

	System.out.println("locker = "+ (locker+1) + " " + "key = "
			   + keys.get(key));

	//System.out.println("Key = " +keys.get(key));
	// if key is in front of locker i
	if (keys.get(key) < list.get(locker).num) {

	  tmpPath = getPath(keys.get(key), list.get(locker).num);

	  //System.out.println("locker = "+ (locker+1) + " " + "key = "
	  //		     + keys.get(key) + " path = " + tmpPath);

	  if (isValidPath(tmpPath, list.get(locker).num)) {
	    table[key][locker] = tmpPath.size();
	  }
	  else {
	    table[key][locker] = table[key][locker - 1] + 1;
	  }


	  list.get(locker).path = tmpPath;
	}
	else if (keys.get(key) >= list.get(locker).num) {
	  //System.out.println("Key is larger");

	  if (locker != 0 && list.get(locker - 1).ball) {
	    //System.out.println("Next to ball");
	    table[key][locker] = table[key][locker - 1];
	    list.get(locker).path = list.get(locker - 1).path;
	  }
	  else {
	    tmpPath = getPath(keys.get(key), list.get(locker).num);

	    leftLocker = getLeftLockerWithBall(list.get(locker).num);
	    System.out.println(leftLocker);

	    if (locker == 0) {
	      table[key][locker] = tmpPath.size();
	      list.get(locker).path = tmpPath;
	    }
	    else if (leftLocker == -1) {
	      System.out.println("HERE");
	      table[key][locker] = minDist(tmpPath, locker, table[key][locker - 1],
					   leftLockerKey, leftLocker);
	    }
	    else {
	      System.out.println("HERE2");
	      //System.out.println("Key: " + keys.get(key));
	      leftLockerKey = getKeyShortestPath(leftLocker);
	      table[key][locker] = minDist(tmpPath, locker, table[key][locker - 1],
					   leftLockerKey, leftLocker);
	    }

	  }

	}

      }
      //System.out.println(list);
      resetPath();

    }

    Collections.sort(tennisBalls);
    System.out.println("right most = " + tennisBalls.get(tennisBalls.size() - 1));



    return smallestValAtLocker(tennisBalls.get(tennisBalls.size() - 1));

  }

  public void printTable() {

    for (int key = 0; key < keyCnt; key++) {
      System.out.print(keys.get(key) + "= ");
      for (int locker = 0; locker < lockerCnt; locker++) {

	//if (locker == 138) {
	//System.out.print((locker+1)+ ":" + table[key][locker] + " ");
	System.out.print(table[key][locker] + " ");
	//}
      }
      System.out.println("");
    }

  }



}
