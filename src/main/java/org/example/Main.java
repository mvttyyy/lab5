package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main (String[]args){
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        ShapeDAO shapeDAO = new ShapeDAO(sessionFactory);

        try {
            Rectangle rectangle1 = new Rectangle(4.0, 8.0);
            Rectangle rectangle2 = new Rectangle(6.0, 12.0);
            shapeDAO.saveShape(rectangle1);
            shapeDAO.saveShape(rectangle2);

            Triangle triangle1 = new Triangle(6.0, 8.0, 10.0);
            Triangle triangle2 = new Triangle(7.0, 24.0, 25.0);
            shapeDAO.saveShape(triangle1);
            shapeDAO.saveShape(triangle2);


            List<Rectangle> rectangles = shapeDAO.getAllShapes(Rectangle.class);
            List<Triangle> triangles = shapeDAO.getAllShapes(Triangle.class);

            System.out.println("Rectangles:");
            rectangles.forEach(Rectangle::print);
            System.out.println("Triangles:");
            triangles.forEach(Triangle::print);
        } catch (Exception e) {
            logger.error("Error during database operations", e);
        } finally {
            shapeDAO.close();
        }
    }
}