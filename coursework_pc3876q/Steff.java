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
class Steff {

    public static MyLinkedList list = new MyLinkedList();

    private static Double calculateDecimals(int n) {
        
        double result = 1;
        for (int i = 0; i < n; i++) {
            result = result / 10;
        }
        return result;
    }

    public static double Steffensen(double x, IFunction f, int nrDecimals, int itMax) {
        
        double gx;
        int iterations = 0;
        list.clear();
        Double calculatedDecimals = calculateDecimals(nrDecimals);
        
        do {
 
            gx = (f.f(x + f.f(x)) / f.f(x)) - 1;
            list.add(new Result(x, f.f(x), gx));
            x = x - f.f(x) / gx;
            
            iterations++;
            
        } while (Math.abs(gx) > 0 && Math.abs(gx) > calculatedDecimals.doubleValue() && iterations < itMax);

        return x;

    }
}
