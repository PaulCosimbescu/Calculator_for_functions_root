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
public interface Function {

    public double f(double x);
}

//------------------------------------------------------------------------------
// first function to check

class Function1 implements Function {

    public double f(double x) {
        return x - x * x;
    }
}


class Function2 implements Function {

    public double f(double x) {
        
        return Math.log(x + 1) + 1;
    }

}


class Function3 implements Function {

    public double f(double x) {
        return Math.pow(Math.E, x) - 3 * x;
    }

}

//------------------------------------------------------------------------------

class Function1Prime implements Function {

    public double f(double x) {
        return 1 - 2 * x;
    }
}


class Function2Prime implements Function {

    public double f(double x) {
        return 1 / (x + 1);
    }
}


class Function3Prime implements Function {

    public double f(double x) {
        return Math.pow(Math.E, x) - 3;
    }

}
