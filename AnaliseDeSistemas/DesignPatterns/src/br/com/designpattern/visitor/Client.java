package br.com.designpattern.visitor;

import br.com.designpattern.visitor.models.Calculator;
import br.com.designpattern.visitor.visitors.Divide;
import br.com.designpattern.visitor.visitors.Minus;
import br.com.designpattern.visitor.visitors.Multiply;
import br.com.designpattern.visitor.visitors.Plus;

public class Client {

    public static void main(String[] args){
        System.out.println("************** Calculator **************");
        System.out.println("**************   Visitor   **************");

        Calculator calculator = new Calculator(10.0, 2.0);

        calculator.visit(new Minus());
        System.out.println("Minus: " + calculator.getResultValue());
        calculator.visit(new Plus());
        System.out.println("Plus: " + calculator.getResultValue());
        calculator.visit(new Multiply());
        System.out.println("Multiply: " + calculator.getResultValue());
        calculator.visit(new Divide());
        System.out.println("Divide: " + calculator.getResultValue());
    }
}
