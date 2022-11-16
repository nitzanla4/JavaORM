package org.example.entities;

import java.lang.annotation.Annotation;

public class Column {
    String type;
    String name;
    String annotation;

    public Column(String type, String name) {
        this.type = type;
        this.name = name;
    }
    public Column(String type, String name, String annotation) {
        this.type = type;
        this.name = name;
        this.annotation=annotation;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getAnnotation() {
        return annotation;
    }

    @Override
    public String toString() {
        return "Column{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", annotation='" + annotation + '\'' +
                '}';
    }
}
