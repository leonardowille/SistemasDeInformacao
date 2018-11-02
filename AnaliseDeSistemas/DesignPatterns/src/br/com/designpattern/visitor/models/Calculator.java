package br.com.designpattern.visitor.models;

import br.com.designpattern.visitor.interfaces.CalculatorVisitor;
import br.com.designpattern.visitor.interfaces.Visitable;

public class Calculator implements Visitable {

    private Double firstValue;
    private Double secondValue;
    private Double resultValue;

    public Calculator(Double firstValue, Double secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public Double getFirstValue() {
        return firstValue;
    }

    public Double getSecondValue() {
        return secondValue;
    }

    public Double getResultValue() {
        return resultValue;
    }

    public void setResultValue(Double resultValue) {
        this.resultValue = resultValue;
    }

    @Override
    public void visit(CalculatorVisitor visitor) {
        visitor.visit(this);
    }
}
