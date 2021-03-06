package br.com.designpattern.visitor.visitors;

import br.com.designpattern.visitor.interfaces.CalculatorVisitor;
import br.com.designpattern.visitor.models.Calculator;

public class Plus implements CalculatorVisitor {

    @Override
    public void visit(Calculator calculator) {
        calculator.setResultValue(calculator.getFirstValue() + calculator.getSecondValue());
    }
}
