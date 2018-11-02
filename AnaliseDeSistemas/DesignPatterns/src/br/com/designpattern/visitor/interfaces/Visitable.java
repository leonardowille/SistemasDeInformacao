package br.com.designpattern.visitor.interfaces;

public interface Visitable {
    abstract void visit(CalculatorVisitor visitor);
}
