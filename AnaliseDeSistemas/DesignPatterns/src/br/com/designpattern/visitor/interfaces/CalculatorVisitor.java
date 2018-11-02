package br.com.designpattern.visitor.interfaces;

import br.com.designpattern.visitor.models.Calculator;

public interface CalculatorVisitor {
    void visit(Calculator calculator);
}
