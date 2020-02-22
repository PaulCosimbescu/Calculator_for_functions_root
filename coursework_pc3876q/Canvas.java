/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework_pc3876q;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author paul
 */
public class Canvas extends JPanel {

    // offset to draw the chart rectangle
    int offset = 10;
    boolean isList;
    double x1,x2;
    // f is the first function to draw the chart
    IFunction f;
    // fp is the second function to draw the chart
    IFunction fp;

    List<Double> X = new LinkedList<>();
    // method to pass the function from outside
    // the Canvas class
    
    public void setValues(double x1,double x2){
    	this.x1 = x1;
    	this.x2 = x2;
    }
    public void setFunction(IFunction f) {
        this.f = f;
    }
    // method to pass the function from outside
    // the Canvas class

    public void setFunctionPrime(IFunction f) {
        this.fp = f;
    }

    public void setXAsList(MyLinkedList X) {
        this.X.clear();
        X.reset();
        Result r;
        while ((r = X.get())!=null) {
            this.X.add(r.getX());
        }
    }

    public void setXAsArray(Result X[], int N) {
        this.X.clear();
        for (int i = 0; i < N; i++) {
            this.X.add(X[i].getX());
        }
        isList = false;
    }
    // method that paints the component
    // g is the graphic context

    @Override
    public void paintComponent(Graphics g) {

        // we call superclass method
        super.paintComponent(g);

        //we want to display the chart between -10 and 10
        double p1 = -100;
        double p2 = 100;

        // 
        double stepx = 10;

        // we set a white background color
        g.setColor(Color.WHITE);

        // we draw the background
        g.fillRect(offset + 0, offset + 0, this.getWidth(), this.getHeight());

        // we store in square 1 unit size
        double square = this.getWidth() / 20;

        // we draw the grid starting with p1
        double k = p1;

        // we store parity of current line in 
        int parity = 0;

        // for each square we draw horizontal lines
        // and we add numbers with drawString method
        for (float i = 0; i < this.getWidth(); i += square) {
            // we set color gray for grid
            g.setColor(Color.GRAY);

            // we draw current line of grid
            g.drawLine(offset + (int) i, offset + 0, offset + (int) i, offset + this.getHeight());

            // se set the color and font for text
            g.setColor(new Color(112, 176, 45));
            g.setFont(new Font("Arial", Font.BOLD, 12));

            // if odd line we draw the string
            if (parity % 2 == 0) {
                g.drawString("" + (Math.round(k * 100) / 100.0), offset + (int) i + 5, offset + 10);
            }

            // we increment string to be drawn
            k = k + stepx;
            // we increment parity varible
            parity++;
        }

        // we set color gray for vertical grid
        g.setColor(Color.GRAY);

        // se set stepy for Oy axis
        double stepy = 10;
        // we set first parity line to 1 
        parity = 1;

        // we restart the numbering form p1
        k = p1;

        // we draw the grid starting with p1
        for (float i = 0; i < this.getHeight(); i += square) {
            // we set color gray for grid
            g.setColor(Color.GRAY);

            g.drawLine(offset + 0, offset + (int) i, offset + this.getWidth(), offset + (int) i);
            g.setColor(new Color(112, 176, 45));
            g.setFont(new Font("Arial", Font.BOLD, 12));
            if (parity % 2 == 0) {
                g.drawString("" + (-Math.round((k * 100)) / 100.0), offset + 0 + 5, offset + (int) i + 10);
            }
            k = k + stepy;
            parity++;
        }
        g.drawString("X", 40, 7);
        g.drawString("Y", 7, 40);
        g.drawString("O", 7, 7);

        // draw graphic
        g.setColor(Color.BLACK);
        double ival, fval;
        // Y = (X-A)/(B-A) * (D-C) + C
        double ipval, fpval;
        if (p1 != 0 && p2 != 0 && f != null) {
            double step = 0.001;
            if (Math.abs(p2 - p1) < 3) {
                step = 0.0001;
            }
            if (Math.abs(p2 - p1) >= 3 && Math.abs(p2 - p1) <= 50) {
                step = 0.001;
            }
            if (Math.abs(p2 - p1) > 50) {
                step = 0.01;
            }

            for (double i = p1; i <= p2; i += step) {
                ival = map(i, -100, 100, 0, 20);
                //fval = map(f.f(i),f.f(p1),f.f(p2),0,20);	
                fval = map(f.f(i), -100, 100, 0, 20);

                ipval = map(i, -100, 100, 0, 20);
                //fpval = map(fp.f(i),fp.f(p1),fp.f(p2),0,20);		
                fpval = map(fp.f(i), -100, 100, 0, 20);
                if (this.getHeight() - (10 + (int) (fval * square))<10){
                	continue;
                }
                g.setColor(Color.BLACK);
                g.fillOval(10 + (int) (ival * square), this.getHeight() - (10 + (int) (fval * square)), 2, 2);
                g.setColor(Color.BLUE);
                g.fillOval(10 + (int) (ipval * square), this.getHeight() - (10 + (int) (fpval * square)), 2, 2);
                g.setColor(Color.BLACK);
            }

            for (Double x : X) {
                ival = map(x.doubleValue(), p1, p2, 0, 20);
                g.setColor(Color.RED);
                g.fillOval(7 + (int) (ival * square), this.getHeight() - (7 + (int) (10 * square)), 6, 6);
            }

            ival = (map(0, -100, 100, 0, 20));

            //fval = map(fp.f(i),fp.f(p1),fp.f(p2),0,20);				
            fval = map(0, -100, 100, 0, 20);

            g.setColor(Color.BLACK);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2));
            g2.drawLine(10 + 0, 10 + (int) (fval * square), 10 + (int) (20 * square), 10 + (int) (fval * square));
            g2.drawLine(10 + (int) (ival * square), 10 + 0, 10 + (int) (ival * square), 10 + (int) (20 * square));
        }
    }

    public static double map(double val,
            double sc1, double ec1,
            double sc2, double ec2) {
        double offset = sc2;
        double r = (ec2 - sc2) / (ec1 - sc1);
        return r * (val - sc1) + offset;
    }

}
