package com.company;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

/**
 * Класс для координат продуктов - объектов коллекции
 */
@XmlRootElement(name = "coordinates")
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates implements Serializable {
    private Integer x; //Поле не может быть null
    private float y;

    Coordinates() {}
    public Coordinates(Integer x, float y) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Float.compare(that.y, y) == 0 &&
                x.equals(that.x);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
