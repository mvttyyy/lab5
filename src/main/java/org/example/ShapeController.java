package org.example;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shapes")
public class ShapeController {
    private final ShapeService shapeService;

    public ShapeController(ShapeService shapeService) {
        this.shapeService = shapeService;
    }

    @GetMapping
    public List<? extends Shape> getAllShapes() {
        return shapeService.getAllShapes();
    }

    @GetMapping("/{id}")
    public Shape getShapeById(@PathVariable Long id) {
        return shapeService.getShapeById(id);
    }

    @PostMapping
    public Shape createShape(@RequestBody Shape shape) {
        return shapeService.saveShape(shape);
    }
}