package br.com.designpattern.common;

public class Client {

    public static void main(String[] args){
        System.out.println("************** Calculator **************");

        Calculator calculator = new Calculator(10.0, 2.0);

        calculator.minus();
        System.out.println("Minus: " + calculator.getResultValue());
        calculator.plus();
        System.out.println("Plus: " + calculator.getResultValue());
        calculator.multiply();
        System.out.println("Multiply: " + calculator.getResultValue());
        calculator.divide();
        System.out.println("Divide: " + calculator.getResultValue());
    }
}
