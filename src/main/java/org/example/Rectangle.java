package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="rectangles")
public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(){}


    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle(double width, double height, Color color) {
        super(color);
        this.width = width;
        this.height = height;
    }

    public void print() {
        System.out.println("Rectangle: " + this.width + ", " + this.height + this.color.toString());
    }

    public double getPerimeter() {
        return 2.0 * (this.width + this.height);
    }

    public double getArea() {
        return this.width * this.height;
    }

    public double getWidth() {
        return this.width;
    }
    public double getHeight() {
        return this.height;
    }
}