package com.company;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.PriorityQueue;

@XmlRootElement(name = "Collection")
@XmlAccessorType(XmlAccessType.FIELD)
public class Products {
    @XmlElement(name = "Product")
    private PriorityQueue<Product> production = null;

    public PriorityQueue<Product> getProducts() {
        return production;
    }

    public void setProducts(PriorityQueue<Product> newProduction) {
        this.production = newProduction;
    }

}
