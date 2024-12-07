package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShapeService {
    private ShapeRepository shapeRepository;

    @Autowired
    public void ShapeSerivce(ShapeRepository shapeRepository){
        this.shapeRepository = shapeRepository;
    }

    public Shape saveShape(Shape shape){
        return shapeRepository.save(shape);
    }

    public List<? extends Shape> getAllShapes(){
        return shapeRepository.findAll();
    }

    public Shape getShapeById(Long id) {
        return shapeRepository.findById(id).orElse(null);
    }
}