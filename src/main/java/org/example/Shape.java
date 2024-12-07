package org.example;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Rectangle.class, name = "rectangle"),
        @JsonSubTypes.Type(value = Triangle.class, name = "triangle"),
})
abstract public class Shape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Embedded
    public Color color;

    public Shape() {
        this.color = new Color(255, 255, 255, 0);
    }

    public Shape(Color color) {
        this.color = color;
    }

    public void print() {
        System.out.print(this.getClass().getSimpleName());
    }

    public void getColorDescription() {
        System.out.print(color.toString());
    }

    public abstract double getArea();

    public abstract double getPerimeter();
}