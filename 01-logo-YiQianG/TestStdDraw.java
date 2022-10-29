import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
/**
 * Write a description of class TestStdDraw here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestStdDraw
{  
    public static void rotate(double[]x, double[]y, double theta)
    {
        double rotatedx = 0.0;
        double rotatedy = 0.0;
        theta = (theta/180)*Math.PI;
        for(int i = 1; i<x.length; i++)
        {
            rotatedx = ((x[i]-x[0])*Math.cos(theta)) -((y[i]-y[0])*Math.sin(theta));
            rotatedy = ((y[i]-y[0])*Math.cos(theta)) -((x[i]-x[0])*Math.sin(theta));

            x[i] = rotatedx + x[0];
            y[i] = rotatedy + y[0];
        }
    }

    public static void main (String []args )
    {
        // Write your code here ;) 
        //set size of canvas
        StdDraw.setCanvasSize(600,600);

        //set the scale
        StdDraw.setXscale(-10,10);
        StdDraw.setYscale(-10,10);

        //setColor
        StdDraw.setPenColor( new Color(39, 44, 130));

        //thickness
        StdDraw.setPenRadius (0.125);

        //draw shape
        StdDraw.circle(0.0,0.0, 7.5);

        StdDraw.setPenColor( new Color(237, 9, 9));
        StdDraw.filledSquare(0,0,3.5);

        StdDraw.setPenColor( new Color(255, 255, 255));
        StdDraw.filledCircle(0,0, 3);

        StdDraw.setPenColor( new Color(39, 44, 130));
        StdDraw.filledRectangle(0,0,0.8,2.5);
        StdDraw.filledRectangle(0,0,2.5,0.8);

        //Text, font, size
        Font font = new Font("Franklin Gothic Demi", Font.BOLD, 40);
        StdDraw.setFont(font);
        StdDraw.setPenColor( new Color(255,255,255));
        StdDraw.text(-5.5,-5.5, "M", -43.0);
        StdDraw.text(-3.5,-6.8, "E", -28.0);
        StdDraw.text(-1.2, -7.6 , "X", -10.0);
        StdDraw.text(1.2, -7.6, "I", 10);
        StdDraw.text(3.5, -6.8, "C", 28);
        StdDraw.text(5.5, -5.5, "O", 43);

        StdDraw.setPenColor( new Color(39, 44, 130));
        StdDraw.text(0,4,"DEPORTIVO");
        StdDraw.text(0,-4.4,"CRUZ AZUL");

        Font f = new Font("Franklin Gothic Demi", Font.BOLD, 6);
        StdDraw.setFont(f);
        StdDraw.text(3.4,-3.65, "MR");

        //8 stars
        //Isosceles triangle with an indent at the base
        StdDraw.setPenColor( new Color(255,255,255));
        double [] x1  = {7.106,7.606,7.106,6.706};
        double [] y1 = {1.652,1.252,2.652,1.552};
        rotate(x1,y1,-10);
        StdDraw.filledPolygon(x1, y1);
        //upside down obtuse triangle
        double [] x2 = {7.106,7.806,6.406};
        double [] y2 = {1.652,1.952,2.4};
        rotate(x2,y2,-10);
        StdDraw.filledPolygon(x2,y2);        
        double [] x3  = {-7.106,-7.606,-7.106,-6.706};
        double [] y3 = {1.652,1.252,2.652,1.552};
        rotate(x3,y3,10);
        StdDraw.filledPolygon(x3, y3); 
        double [] x4 = {-7.106,-7.806,-6.406};
        double [] y4 = {1.652,1.952,2.4};
        rotate(x4,y4,10);
        StdDraw.filledPolygon(x4,y4);

        
        double[] w1 = {6.074,6.574,6.074,5.674};
        double[] z1 = {4.156,4.125,5.156,3.656};
        rotate(w1,z1,15);
        StdDraw.filledPolygon(w1,z1);
        double[] w2 = {6.074,6.474,5.2};
        double[] z2 = {4.156,4.756,4.656};
        rotate(w2,z2,-15);
        StdDraw.filledPolygon(w2,z2);
        double[] w3 = {-6.074,-6.574,-6.074,-5.674};
        double[] z3 = {4.156,4.125,5.156,3.656};
        rotate(w3,z3,-15);
        StdDraw.filledPolygon(w3,z3);
        double[] w4 = {-6.074,-6.474,-5.2};
        double[] z4 = {4.156,4.756,4.656};
        rotate(w4,z4,15);
        StdDraw.filledPolygon(w4,z4);
        
        
        double[] a1 = {4.258,5.158,3.958,3.558};
        double[] b1 = {6.104,6.604,6.904,5.104};
        rotate(a1,b1,30);
        StdDraw.filledPolygon(a1,b1);
        double[] a2 = {4.258,3.758,2.759};
        double[] b2 = {6.104,7.304,7.004};
        rotate(a2,b2,-30);
        StdDraw.filledPolygon(a2,b2);
        double[] a3 = {-4.258,-5.158,-3.958,-3.558};
        double[] b3 = {6.104,6.604,6.904,5.104};
        rotate(a3,b3,-30);
        StdDraw.filledPolygon(a3,b3);
        double[] a4 = {-4.258,-3.758,-2.759};
        double[] b4 = {6.104,7.304,7.004};
        rotate(a4,b4,30);
        StdDraw.filledPolygon(a4,b4);
        
                
        double [] c1  = {1.461,1.961,1.461,0.961};
        double [] d1 = {7.026,6.426,8.026,6.826};
        rotate(c1,d1,-10);
        StdDraw.filledPolygon(c1, d1);
        double [] c2 = {1.461,2.361,0.861};
        double [] d2 = {7.026,7.426,7.526};
        rotate(c2,d2,10);
        StdDraw.filledPolygon(c2,d2);        
        double [] c3  = {-1.461,-1.961,-1.461,-0.961};
        double [] d3 = {7.026,6.426,8.026,6.826};
        rotate(c3,d3,10);
        StdDraw.filledPolygon(c3, d3); 
        double [] c4 = {-1.461,-2.361,-0.861};
        double [] d4 = {7.026,7.426,7.526};
        rotate(c4,d4,-10);
        StdDraw.filledPolygon(c4,d4);
    }
}
