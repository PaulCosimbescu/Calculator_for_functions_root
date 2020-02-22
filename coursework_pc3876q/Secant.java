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
public class Secant {
    // Array for Secant, SN - number of elements

    public static int SN = 0;
    public static Result sArray[] = new Result[500];

    private static void addToSArray(Double x, Double f, Double fPrime) {
        Result result = new Result(x, f, fPrime);
        sArray[SN] = result;
        SN++;
    }

    private static Double calculateDecimals(int n) {
        double result = 1;
        for (int i = 0; i < n; i++) {
            result = result / 10;
        }
        return result;
    }

    public static Double Secant(double xold1, double xold2, IFunction f, int nrDecimals, int itMax) {
        
        double xnew, fxold1, fxold2, diff;
        int iteration;
        Double calculatedDecimals = calculateDecimals(nrDecimals);

        iteration = 0;
        SN = 0;
        do {
            iteration = iteration + 1;
            // determine f(xold1) and f(xold2)
            addToSArray(xold1, f.f(xold1), 0.0);
            fxold1 = f.f(xold1);
            fxold2 = f.f(xold2);
            xnew = xold1 - (fxold1 * (xold1 - xold2)) / (fxold1 - fxold2);
            diff = Math.abs(xnew - xold1);
            xold2 = xold1;
            xold1 = xnew;

        } while (diff > calculatedDecimals.doubleValue() && iteration < itMax);
        return xnew;
    }

}
