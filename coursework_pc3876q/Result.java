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
class Result {

    private Double x;
    private Double f;
    private Double fPrime;

    public Result(Double x, Double f, Double fPrime) {
        this.x = x;
        this.f = f;
        this.fPrime = fPrime;
    }

    public Double getX() {
        return x;
    }

    public Double getF() {
        return f;
    }

    public Double getfPrime() {
        return fPrime;
    }

}
