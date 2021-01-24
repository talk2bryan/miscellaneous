/**
* @file HTree.java
* @brief A H-Tree is a fractal geometric shape shape that consists of repeating
* patterns that resembles the letter H.
* See the Wikipedia page: https://en.wikipedia.org/wiki/H_tree
*
* @author Bryan Wodi <talk2kamp@gmail.com>
* @version 1.0
* @date 2018-11-26
*/

import java.util.*;

public class HTree {
  static public void main( String args[] ) {

    if (args.length > 0) {
        try {
            double depth = Double.parseDouble(args[0]);
            if (depth >= 1 && depth <= 10) {
                drawHTree(0.5, 0.5, 0.5, depth);
            } else {
                earlyExit();
            }
        }catch (NumberFormatException nfe) {
            System.out.println("Cannot convert input to number: " + args[0]);
        }
    } else {
        earlyExit();
    }
  }

  static public void earlyExit() {
    System.out.println("Invalid parameter. You have to run the program in the format:");
    System.out.println("java HTree <DEPTH> \nWhere 1 <= DEPTH <= 10");
    System.exit(0);
  }


  /* --------------------------------------------------------------------------*/
  /**
  * @brief This method constructs a H-tree, given its center (x and y 
  * coordinates), a starting length, and depth. Assume that the starting line is 
  * parallel to the X-axis
  *
  * @param x
  * @param y
  * @param length. Length of whole line where (x,y) is its centre.
  * @param depth. (1 <= depth <= 10)
  */
  /* --------------------------------------------------------------------------*/
  static void drawHTree(double x, double y, double length, double depth) {
     if(depth == 0) {
       return;
     }
     // Get the start and end points for the centre.
     /*
              y1
              |
              |
      x0 ---(x,y)--- x1
              |
              |
              y0
      */
    double x0 = x - (length/2);
    double x1 = x + (length/2);
    double y0 = y - (length/2);
    double y1 = y + (length/2);

    // Draw the 3 H lines
    drawLine(x0, y1, x0, y0);
    drawLine(x1, y1, x1, y0);
    drawLine(x0, y, x1, y);  

    // Divide into 4 quadrants and find their midpoints    
    drawHTree(x0, y1, length/2, depth-1);
    drawHTree(x1, y1, length/2, depth-1);
    drawHTree(x0, y0, length/2, depth-1);
    drawHTree(x1, y0, length/2, depth-1); 
  }

  static void drawLine(double startX, double endX, double startY, double endY) {
  	StdDraw.line(startX, endX, startY, endY);
  }
}
