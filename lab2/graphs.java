import java.util.*;
import java.io.*;

/**
 *
 * @author Nathan Yee
 */
public class graphs {

  private static Set<Integer> visited_x = new TreeSet<Integer>();
  private static Set<Integer> visited_y = new TreeSet<Integer>();
  // private static boolean colorable = false;
  /**
   * $params args the command line arguments
   */
  public static void main (String[] args) throws FileNotFoundException {
    int[][] matrix;
    ArrayList<Integer> list = new ArrayList<Integer>();
    int max = 0;
    int min = 0;
    int size = 0;
    int offset = 0;
    Scanner in = new Scanner(new File(args[0]));

    while (in.hasNextInt()) {
      list.add(in.nextInt());
    }

    max = Collections.max(list);
    min = Collections.min(list);

    if (min == 0) {
      matrix = new int[max + 1][max + 1];
    }
    else {
      matrix = new int[max][max];
      offset = 1;
    }

    for (int i = 0; i < list.size(); i+=2) {
      //System.out.println(list.get(i) + " " + list.get(i + 1));
      matrix[list.get(i) - offset][list.get(i+1) - offset] = 1;
      matrix[list.get(i + 1) - offset][list.get(i) - offset] = 1;
    }

    for (int j = 0; j < max; j++) {
      for (int k = 0; k < max; k++) {
	System.out.print(matrix[j][k] + " ");
      }
      System.out.println();
    }

    //explore(matrix, min - 1, 0);
    DFS(matrix, offset);

    //System.out.println("max = " + max + "min = " + min);

  }


  private static boolean explore(int[][] graph, int startVert, int swap, int offset) {

    int newSwap = 0;
    boolean colorable = false;

    if (swap == 0) {
      //System.out.println((startVert + 1) + " has color x");
      visited_x.add(startVert);
      newSwap = 1;
    }
    else {
      //System.out.println((startVert + 1) + " has color y");
      visited_y.add(startVert);
    }

    for (int i = 0; i < graph[startVert].length; i++) {

      if (graph[startVert][i] == 1) {
	if (visited_x.contains(i) && swap == 0) {
	  //System.out.println("not 2 colorable x " + (startVert +offset) + " " + (i + offset));
	  //System.out.println("x: " + visited_x + " y:  " + visited_y);
	  colorable = true;
	}
	else if (visited_y.contains(i) && swap == 1) {
	  //System.out.println("not 2 colorable y " + " " +(startVert +offset) + " " + (i + offset));
	  //System.out.println("x: " + visited_x + " y:  " + visited_y);
	  colorable = true;
	}
	else if (!visited_x.contains(i) && !visited_y.contains(i)) {
	 colorable =  explore(graph, i, newSwap, offset);
	}
      }

    }

    // if (colorable == false) {
    //   System.out.println("2 colorable");
    // }
    // else {
    //   System.out.println("not 2 colorable");
    // }


    return colorable;

  }


  private static void DFS(int[][] graph, int offset) {

    Set<Integer> old_visited = new TreeSet<Integer>();
    Set<Integer> new_visited = new TreeSet<Integer>();
    int[] testArr = new int[graph[0].length];
    boolean disconnected = false;
    boolean finish = false;
    int components = 0;
    int grphnum = 1;

    for (int i = 0; i < graph.length; i++) {

      if (!disconnected) {
	old_visited.addAll(visited_x);
	old_visited.addAll(visited_y);
      }
      visited_x.clear();
      visited_y.clear();

      //System.out.println("\nStarting Search at Vertex " + (i+offset));
      if (!old_visited.contains(i)) {

	System.out.println("Graph #: " + grphnum);
	if (explore(graph, i, 0, offset)) {
	  System.out.println("not 2 colorable");
	}
	else {
	  System.out.println("2 colorable");
	}

	components++;
	grphnum++;
      }

      if (components > 1) {
	new_visited.addAll(visited_x);
	new_visited.addAll(visited_y);
      }

    }

    if (components > 1) {
      System.out.println("Graph is disconnected");
      //System.out.println(new_visited + "\t" + old_visited);
    }
    else {
      System.out.println("Graph is connected");
    }

  }


}
