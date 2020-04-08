package com.company;

import javax.xml.bind.annotation.*;
import java.time.ZonedDateTime;

/**
 * Класс продуктов, хранящихся в коллекции
 */
@XmlRootElement(name = "Product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null

    @XmlElement(name = "creationDate")
    private String dateTimeString;
    @XmlTransient
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    private Float price; //Поле не может быть null, Значение поля должно быть больше 0
    private Double manufactureCost; //Поле не может быть null
    private UnitOfMeasure unitOfMeasure; //Поле не может быть null
    private Person owner; //Поле может быть null

    Product() {}

    Product(String name, Coordinates coordinates, Float price, Double manufactureCost, UnitOfMeasure unitOfMeasure, Person owner) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
        this.dateTimeString = creationDate.toString();
        this.price = price;
        this.manufactureCost = manufactureCost;
        this.unitOfMeasure = unitOfMeasure;
        this.owner = owner;
        this.id = getRandNumber(100000, 999999);
    }

    private int getRandNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
        this.dateTimeString = creationDate.toString();
    }

    public Float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Double getManufactureCost() {
        return manufactureCost;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    @Override
    public String toString() {
        return "id: " + getId() + " название: " + getName() + " " + getCreationDate() + " " + coordinates.toString() + " цена: " + getPrice() + " стоимость производства: " + getManufactureCost() + " единица измерия: " + getUnitOfMeasure() + " " + ((owner == null) ? "" : owner.toString());
    }
}
