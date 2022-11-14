package org.example;

public class Column {
    String type;
    String name;

    public Column(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Column{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
