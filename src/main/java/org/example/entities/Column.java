package org.example.entities;

public class Column {
    String type;
    String name;
    String annotation;

    public Column(String type, String name) {
        this.type = type;
        this.name = name;
    }
    public Column(String type, String name,String annotation) {
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
}
