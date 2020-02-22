/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework_pc3876q;


/**
 *
 * @author paul
 */
public class Newton_Raphson {

    public static MyLinkedList list = new MyLinkedList();

    // Calculates the error epsilon
    // Returns 0.0000..1 with number of 0 + 1 decimals 
    //------------------------------------------------------------------------------
    private static Double calculateDecimals(int n) {
        double result = 1;
        for (int i = 0; i < n; i++) {
            result = result / 10;
        }
        return result;
    }


    //------------------------------------------------------------------------------
    public static Double Newton_Raphson(double xold, IFunction f, IFunction fPrime,
            int nrDecimals, int itMax) {

        
        double xnew, fxold, fdashxold, diff;
        int iteration;
        Double calculatedDecimals = calculateDecimals(nrDecimals);

        iteration = 0;

        list.clear();
        do {

            iteration = iteration + 1;
            list.add(new Result(xold, f.f(xold), fPrime.f(xold)));

            fxold = f.f(xold);
            fdashxold = fPrime.f(xold);
            xnew = xold - (fxold / fdashxold);
            diff = Math.abs(xnew - xold);
            xold = xnew;
            
        } while (diff > calculatedDecimals.doubleValue() && iteration < itMax);

        return xnew;
    }
}
