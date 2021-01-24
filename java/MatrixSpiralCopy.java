/**
 * @file MatrixSpiralCopy.java
 * @brief Given a 2D matrix of integers, the function spiralCopy copies its values into a 1D array 
 * in a spiral order, clockwise.
 *
 * @run: java -ea MatrixSpiralCopy // To enable assertions on runtime
 *
 * @author Bryan Wodi <talk2kamp@gmail.com>
 * @version 2.0
 * @date 2018-09-02
 */
import java.util.Arrays;

public class MatrixSpiralCopy {
  public static void main (String [] args) {
    int [][] matrix = new int [][] {
      {1, 2, 3, 4, 5},
      {6, 7, 8, 9, 10},
      {11, 12, 13, 14, 15},
      {16, 17, 18, 19, 20}
    };
    int [] expectedOutput = new int[] 
    {1, 2, 3, 4, 5, 10, 15, 20, 19, 18, 17, 16, 11, 6, 7, 8, 9, 14, 13, 12};
    
    int [] methodOutput = spiralCopy(matrix);
    
    assert(Arrays.equals(expectedOutput, methodOutput) == true) : "output != expectedOutput";
  }
  
  static int[] spiralCopy(int[][] inputMatrix) {
    assert(inputMatrix != null) : "uninitialized array"; 
    assert(inputMatrix.length != 0) : "empty array";
    // early exit condition...
    if (inputMatrix.length == 1) return inputMatrix[0];
    
    final int MAX_SIZE = 100;
    int [] buffer = new int[MAX_SIZE*MAX_SIZE];
    int bufferCount = 0;
    
    int numRows = inputMatrix.length;
    int numCols = inputMatrix[0].length;
    int topRow = 0; // 0 -> numRows -1 
    int btmRow = numRows-1; // numRows -1 -> 0
    int leftCol = 0; // 0 -> numCols -1 
    int rightCol = numCols -1; // numCols -1 -> 0 
    
    while (topRow <= btmRow && leftCol <= rightCol) {
      // copy top row left to right:
      for (int i = leftCol; i <= rightCol; i++)
        buffer[bufferCount++] = inputMatrix[topRow][i];
      topRow++;
      // copy right col up to down:
      for (int i = topRow; i <= btmRow; i++)
        buffer[bufferCount++] = inputMatrix[i][rightCol];
      rightCol--;
      // copy btm row from right to left:
      if (topRow <= btmRow) {
        for (int i = rightCol; i >= leftCol; i--)
          buffer[bufferCount++] = inputMatrix[btmRow][i];
        btmRow--;
      }
      // copy left col from down to up
      if (leftCol <= rightCol) {
        for (int i = btmRow; i >= topRow; i--)
          buffer[bufferCount++] = inputMatrix[i][leftCol];
        leftCol++;
      }
    } // while
    int [] result = new int[bufferCount];
    System.arraycopy(buffer, 0, result, 0, bufferCount);
    return result;
  }
}
