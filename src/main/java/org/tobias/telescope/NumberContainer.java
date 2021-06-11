package org.tobias.telescope;

/*Class to contain the numbers from the .csv file.*/
public class NumberContainer {
  // Contains 2 doubles and one int. The doubles are for the numerical data
  // And the int is for the common factor(e.g time)
  private double a;
  private double b;
  private int c;

  // Constructs a numberContainer from 2 doubles
  public NumberContainer(double a, double b, int c) {
    this.a = a;
    this.b = b;
    this.c = 0;
  }

  public NumberContainer(int a, int b, int c) {
    this.a = (double) a;
    this.b = (double) b;
    this.c = c;
  }

  // Constructs a numberContainer from a double and an int
  public NumberContainer(double a, int c) {
    this.a = a;
    this.b = 0.0;
    this.c = c;
  }

  // Method to create a numberContainer from 2 ins, with the
  public NumberContainer(int a, int c) {
    this.a = (double) a;
    this.b = 0.0;
    this.c = c;
  }

  // Constructs a numberContainer with doubles set to 0 and int set to null
  public NumberContainer() {
    this.a = 0.0;
    this.b = 0.0;
    this.c = 0;
  }

  // Below methods return the numbers in a numberContainer
  public double getA() {
    return a;
  }

  public double getB() {
    return b;
  }

  public int getC() {
    return c;
  }

  // Below methods mutate the numbers in a numberContainer
  public void setA(double newA) {
    this.a = newA;
  }

  public void setB(double newB) {
    this.b = newB;
  }

  public void setC(int newC) {
    this.c = newC;
  }
}