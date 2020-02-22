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
public class Bisection {

    // Array for Bisection, BN - number of elements
    private static int BN = 0;
    private static Result bArray[] = new Result[500];

    private static void addToBArray(Double x, Double f, Double fPrime) {
        Result result = new Result(x, f, fPrime);
        bArray[BN] = result;
        BN++;
    }

    private static Double calculateDecimals(int n) {
        double result = 1;
        for (int i = 0; i < n; i++) {
            result = result / 10;
        }
        return result;
    }

    public static Double Bisection(double xlower, double xupper, IFunction f, int nrDecimals, int itMax) {

        double xnew, fxlower, fxupper, fxnew, diff;
        Double calculatedDecimals = calculateDecimals(nrDecimals);
        int iteration;

        fxlower = f.f(xlower);
        fxupper = f.f(xupper);

        iteration = 0;

        BN = 0;

        do {

            iteration = iteration + 1;

            xnew = (xlower + xupper) / 2.0;

            addToBArray(xnew, f.f(xnew), 0.0);

            fxnew = f.f(xnew);

            diff = Math.abs(xupper - xlower) / 2;

            if (fxlower * fxnew > 0) {
                xlower = xnew;
                fxlower = fxnew;
            } else if (fxupper * fxnew > 0) {
                xupper = xnew;
                fxupper = fxnew;
            }
        } while (diff > calculatedDecimals.doubleValue() && iteration < itMax);

        return xnew;
    }

    public static int getBN() {
        return BN;
    }

    public static Result[] getbArray() {
        return bArray;
    }
    
}
