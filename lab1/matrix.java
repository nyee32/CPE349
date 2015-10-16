/**
 *
 * @author Nathan Yee
 */
public class matrix {
  /**
   * $params args the command line arguments
   */
  public static void main (String[] args) {
    int[][] a = {{1,2}, {4,3}};
    int[][] b = {{1,2}, {4,3}};

    multiply(a, b);
  }

  private static int[][] multiply(int[][]m1, int[][] m2) {
    int[][] sum = new int[m1.length][m1.length];

    System.out.println(m1.length);

    for (int i = 0; i < m1[0].length; i++) {
      for (int j = 0; j < m1[0].length; i++) {
	System.out.println("i = " + i + "j = " + j);
	sum[i][j]+= m1[j][i] * m2[i][j];
      }
    }

    System.out.println(sum);

    return m1;

  }
}
