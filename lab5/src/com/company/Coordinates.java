package com.company;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Класс для координат продуктов - объектов коллекции
 */
@XmlRootElement(name = "coordinates")
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates {
    private Integer x; //Поле не может быть null
    private float y;

    Coordinates() {}
    Coordinates(Integer x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getY() {
        return y;
    }

    public Integer getX() {
        return x;
    }

    @Override
    public String toString() {
        return "coordinates: " + getX() + " " + getY();
    }

    @Override
    public int hashCode() {
        return getX() + (int) getY();
    }
}
