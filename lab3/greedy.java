import java.util.*;
import java.io.*;
/**
 *
 * @author Nathan Yee
 */
public class greedy {
  /**
   * $params args the command line arguments
   */
  public static void main (String[] args) throws FileNotFoundException {
    Scanner in = new Scanner(new File(args[0]));
    int time = 0;
    int tmp = 0;
    int player = 0;
    ArrayList<ArrayList<Integer>> camper = new ArrayList<ArrayList<Integer>>();
    in.useDelimiter("[(,)\\s:]+");

    while (in.hasNextInt()) {
      player = in.nextInt();
      System.out.println("player: " + player);
      ArrayList<Integer> times = new ArrayList<Integer>();
      for (int i = 0; i < 3; i++) {
	if (in.hasNextInt()) {
	  tmp = in.nextInt();
	  time +=tmp;
	  times.add(tmp);
	}
	//System.out.println(times);
      }
      times.add(player);
      camper.add(times);
    }

    getShortestSeq(camper);

    // System.out.println(camper.get(0));
    // System.out.println(camper.get(1));
    // System.out.println(camper.get(2));
    System.out.println("Completion time: " + time);
  }

  private void getShortestTime() {

  }

  static private void getShortestSeq(ArrayList<ArrayList<Integer>> campers) {
    int slowestP = -1;
    int largestVal = 0;
    int remove = -1;
    int campersSize = campers.size();
    ArrayList<Integer> sequence = new ArrayList<Integer>();

    while (sequence.size() < campersSize) {
      System.out.println("size = " + sequence.size());
      System.out.println("sizec = " + campers.size());

      for (int i = 0; i < campers.size(); i++) {
	System.out.println("got = " + campers.get(i) + " " + (campers.get(i).get(1) + campers.get(i).get(2)));
	if ((campers.get(i).get(1) + campers.get(i).get(2)) > largestVal) {
	  largestVal = campers.get(i).get(1) + campers.get(i).get(2);
	  slowestP = campers.get(i).get(3);
	  remove = i;
	}
      }

      if (slowestP != -1) {
	System.out.println("Adding... " + slowestP);
	sequence.add(slowestP);
	if (remove != -1) {
	  campers.remove(remove);
	}
	slowestP = -1;
	largestVal = 0;
      }

    }

    System.out.println("Sequence: " + sequence);

  }

  //completeion time is the shotest time to complete the tri
  //keep track of where the start time; start time is the sum of all the swim 
  //times then add the complete time to the starttime; the longest time is the
  //completion time
  // order is based off non swim times


}
