package org.example;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

public class ShapeDeserializer extends JsonDeserializer<Shape> {

    @Override
    public Shape deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);

        Color color = new Color(255, 255, 255, 0);
        if (node.has("color")) {
            JsonNode colorNode = node.get("color");
            color = new Color(
                    colorNode.get("red").asInt(),
                    colorNode.get("green").asInt(),
                    colorNode.get("blue").asInt(),
                    (float) colorNode.get("alpha").asDouble()
            );
        }

        if (!node.has("type")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "type required");
        }

        String type = node.get("type").asText();

        switch (type.toLowerCase()) {
            case "rectangle":
                if (!node.has("width") || !node.has("height")) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "missing fields");
                }
                double width = node.get("width").asDouble();
                double height = node.get("height").asDouble();
                return new Rectangle(width, height, color);
            case "triangle":
                if (!node.has("a") || !node.has("b") || !node.has("c")) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "missing sides");
                }
                double a = node.get("a").asDouble();
                double b = node.get("b").asDouble();
                double c = node.get("c").asDouble();
                return new Triangle(a, b, c, color);
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "unknown type");
        }
    }
}
