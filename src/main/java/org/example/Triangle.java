package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="triangles")
public class Triangle extends Shape {
    private double a;
    private double b;
    private double c;

    public Triangle() {}

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Triangle(double a, double b, double c, Color color) {
        super(color);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getPerimeter() {
        return this.a + this.b + this.c;
    }

    public void print() {
        System.out.println("Triangle: " + this.a + ", " + this.b + ", " + this.c + this.color.toString());
    }

    public double getArea() {
        double var1 = this.getPerimeter() / 2.0;
        return Math.sqrt(var1 * (var1 - this.a) * (var1 - this.b) * (var1 - this.c));
    }
    public double getA() {
        return this.a;
    }
    public double getB() {
        return this.b;
    }
    public double getC() {
        return this.c;
    }
}