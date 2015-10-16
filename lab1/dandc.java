import java.util.*;
import java.io.*;

/**
 *
 * @author Nathan Yee
 */
public class dandc {
  /**
   * $params args the command line arguments
   */
  public static void main (String[] args) throws IOException{
    ArrayList<Integer> slist = new ArrayList<Integer>();
    ArrayList<Integer> mlist = new ArrayList<Integer>();
    ArrayList<Integer> testList = testArraylist();
    ArrayList<Integer> testList2 = testArraylist();
    Random rand = new Random();
    int limit = 6;
    int tmp = 0;

    FileWriter sw = new FileWriter("Selection.txt");
    FileWriter mw = new FileWriter("merge.txt");


    for (int cnt = 0; cnt < 1; cnt++) {
      Integer[] fini = new Integer[limit];

      for (int i = 0; i < limit; i++) {
	if (rand.nextInt(2) == 1) {
	  tmp = rand.nextInt(10000);
	  slist.add(tmp);
	  mlist.add(tmp);
	} else {
	  tmp = rand.nextInt(10000) * -1;
	  slist.add(tmp);
	  mlist.add(tmp);
	}
      }

      //System.out.println("list size = " + mylist.size() + "last val = " + mylist.get(mylist.size() - 1) + " tmp = " + tmp);

      long startTime = System.currentTimeMillis();
      //sel_sort(slist);
      sel_sort(testList);
      long endTime = System.currentTimeMillis();

      long duration = (endTime - startTime);


      //sw.write(Long.toString(duration) + "\t" + limit + "\n");

      for(int i = 0; i < testList.size(); i++) {
	System.out.print(testList.get(i) + " ");
	sw.write(testList.get(i) + " ");
      }

      //System.out.println("List = " + testList);
      System.out.println("\nSel Time = " + duration + "ms");


      // System.out.println("==================================");
      // System.out.println("MERGE SORT  " + testList2.size()/2 + " " + fini.length);

      startTime = System.currentTimeMillis();
      //mergeSort(mlist, fini);
      fini = new Integer[testList2.size()];

      mergeSort(new ArrayList<Integer>(testList2), fini);
      endTime = System.currentTimeMillis();
      duration = (endTime - startTime);
      //mw.write(Long.toString(duration) + "\t" + limit + "\n");

      for(int i = 0; i < fini.length; i++) {
	  System.out.print(fini[i] + " ");
	  mw.write(fini[i] + " ");
      }


      System.out.println("\nMerge Time = " + duration + "ms");
      limit++;
      slist.clear();
      mlist.clear();
    }

    //System.out.println("limit = " + limit);
    sw.close();
    mw.close();


  }

  private static ArrayList<Integer> testArraylist() throws FileNotFoundException{

    //Integer[] array = {45, -16, 6708, 450, 451, -76, 12, 53};
    //Integer[] array = {1,56,3,90,32,-18};
    //Integer[] array = {1,56,3,90,32,-18,0,14,-32,45,102,-43,2,4,7,65,12,34,123,4,-6,-12,65,64,-98};
    File f = new File("tmp3.txt");

    Scanner sc = new Scanner(f);
    ArrayList<Integer> testList = new ArrayList<Integer>();

    while(sc.hasNextInt()) {
      testList.add(sc.nextInt());
    }


    return testList;
  }

  private static void sel_sort(ArrayList<Integer> list) {
    int saved = 0;
    int tmp = 0;
    int j = 0;

    //System.out.println("here");

    for (int i = 0; i < list.size(); i++) {
      saved = i;
      //System.out.println("getting = " + list.get(i));
      //loop through and compare
      for (j = i + 1; j < list.size(); j++) {
	if (list.get(saved) > list.get(j)) {
	  saved = j;
	}
      }
      // swap
      tmp = list.get(i);
      list.set(i, list.get(saved));
      list.set(saved, tmp);

    }
  }

  private static void mergeSort(ArrayList<Integer> list1,
				Integer[] list2) {

    mergeSplit(list1, 0, list1.size(), list2);


  }

  private static void mergeSplit(ArrayList<Integer> list1, int start, int end,
				 Integer[] listFini) {

    int mid = 0;
    if (end - start < 2)
      return;

    mid = (start + end)/2;
    mergeSplit(list1, start, mid, listFini);
    mergeSplit(list1, mid, end, listFini);
    merge(list1, start, mid, end, listFini);
    copyList(listFini, start, end, list1);
  }

  private static void merge(ArrayList<Integer> list1, int start, int mid,
			    int end, Integer[] listFini) {

    int bInx = start;
    int mInx = mid;

    for (int i = start; i < end; i++) {

      if (bInx < mid && (mInx >= end || (list1.get(bInx) <= list1.get(mInx)))) {
	listFini[i] = list1.get(bInx);
	bInx++;

      } else {
	//System.out.println("i = " + i + "length = " + listFini.length);
	listFini[i] = list1.get(mInx);
	mInx++;
      }
    }
  }

  private static void copyList (Integer[] listFini, int start,
				int end,  ArrayList<Integer> list1) {

    for (int i = start; i < end; i++) {
      //System.out.println("Adding = " + list1.get(i));
      list1.set(i, listFini[i]);
    }

  }

}
