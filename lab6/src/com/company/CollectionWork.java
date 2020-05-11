package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.PriorityQueue;

public class CollectionWork {
    private PriorityQueue<Product> production;
    private Date initDate;
    Comparator<Product> comparator;
    private File zerocollection;
    private boolean logging;

    {
        comparator = Comparator.comparing(Product::getId);
        production = new PriorityQueue(comparator);
    }

    CollectionWork(String envvariable) {
        this.initDate = new Date();
        this.logging = true;

        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Document document;

        try {
            if (envvariable == null) throw new FileNotFoundException();
        } catch (FileNotFoundException ex) {
            System.err.println("Неверный путь. Укажите переменную окружения - 'envvar'");
            System.exit(1);
        }
        try {
            zerocollection = new File(envvariable);
            String format = zerocollection.getAbsolutePath().substring(zerocollection.getAbsolutePath().lastIndexOf(".") + 1);
            if (!zerocollection.exists() | format.equals("xml")) throw new FileNotFoundException();
            if (!zerocollection.canRead() || !zerocollection.canWrite()) throw new SecurityException();

            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            document = builder.parse(zerocollection);
            document.getDocumentElement().normalize();

            NodeList Products = document.getElementsByTagName("Product");
            for (int i = 0; i < Products.getLength(); i++) {
                Element node = (Element) Products.item(i);
                Element coords = (Element) node.getElementsByTagName("coordinates").item(0);
                Element person = (Element) node.getElementsByTagName("owner").item(0);
                Product product = new Product(
                        node.getElementsByTagName("name").item(0).getTextContent(),
                        new Coordinates(
                                Integer.parseInt(coords.getElementsByTagName("x").item(0).getTextContent()),
                                Float.parseFloat(coords.getElementsByTagName("y").item(0).getTextContent())
                        ),
                        Float.parseFloat(node.getElementsByTagName("price").item(0).getTextContent()),
                        Double.parseDouble(node.getElementsByTagName("manufactureCost").item(0).getTextContent()),
                        UnitOfMeasure.valueOf(node.getElementsByTagName("unitOfMeasure").item(0).getTextContent()),
                        node.getElementsByTagName("owner").getLength() == 0 ? null :
                                new Person(
                                        person.getElementsByTagName("name").item(0).getTextContent(),
                                        Integer.parseInt(person.getElementsByTagName("weight").item(0).getTextContent()),
                                        Color.valueOf(person.getElementsByTagName("eyeColor").item(0).getTextContent()),
                                        Color0.valueOf(person.getElementsByTagName("hairColor").item(0).getTextContent()),
                                        Country.valueOf(person.getElementsByTagName("nationality").item(0).getTextContent())
                                )
                );
                product.setId(Integer.parseInt(node.getElementsByTagName("id").item(0).getTextContent()));
                product.setCreationDate(ZonedDateTime.parse(node.getElementsByTagName("creationDate").item(0).getTextContent()));
                production.add(product);
            }
        } catch (ParserConfigurationException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (FileNotFoundException ex) {
            System.err.println("Файла по указанному пути не существует.");
            System.exit(1);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        } catch (SecurityException ex) {
            if (!this.logging) {
                System.err.println("Не хватает прав доступа для работы с файлом.");
            }
            System.exit(1);
        }
    }

    public void turnOffLogging() {
        this.logging = false;
    }

    public File getZerocollection() {
        return zerocollection;
    }

    public Date getInitDate() {
        return initDate;
    }

    public PriorityQueue<Product> getProduction() {
        return production;
    }

    public void setLogging(boolean logging) {
        this.logging = logging;
    }

    public boolean isLogging() {
        return logging;
    }

    /**
     * Сериализует коллекцию в xml файл
     */
    public void save() {
        StringWriter sw = new StringWriter();
        try {
            Products prod = new Products();
            prod.setProducts(production);
            JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            jaxbMarshaller.marshal(prod, new File(System.getenv("envvar")));

            jaxbMarshaller.marshal(prod, sw);
//            System.out.println(sw.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Тип коллекции: " + production.getClass() +
                "\nДата инициализации: " + initDate +
                "\nКоличество элементов: " + production.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CollectionWork)) return false;
        CollectionWork that = (CollectionWork) o;
        return Objects.equals(production, that.production) && Objects.equals(zerocollection, that.zerocollection);
    }

    public void safe_exit() {
        save();
        System.exit(0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(production, zerocollection);
    }
}
