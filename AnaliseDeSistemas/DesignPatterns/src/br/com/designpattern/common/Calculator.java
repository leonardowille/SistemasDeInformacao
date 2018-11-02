package br.com.designpattern.common;

public class Calculator {

    private Double firstValue;
    private Double secondValue;
    private Double resultValue;

    public Calculator(Double firstValue, Double secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public Double getResultValue() {
        return resultValue;
    }

    public void plus(){
        this.resultValue = this.firstValue + this.secondValue;
    }

    public void minus(){
        this.resultValue = this.firstValue - this.secondValue;
    }

    public void multiply(){
        this.resultValue = this.firstValue * this.secondValue;
    }

    public void divide(){
        this.resultValue = this.firstValue / this.secondValue;
    }
}
