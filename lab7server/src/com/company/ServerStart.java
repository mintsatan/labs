package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerStart {
    public static void main(String[] args) {
        Comparator<Product> comparator = Comparator.comparing(Product::getId);
        PriorityQueue<Product> production = new PriorityQueue<>(comparator);;
        File zerocollection;
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Document document;
        String envvariable = System.getenv("envvar");

        try {
            if (envvariable == null) throw new FileNotFoundException();
        } catch (FileNotFoundException ex) {
            System.err.println("Неверный путь. Укажите переменную окружения - 'envvar'");
            System.exit(1);
        }
        try {
            zerocollection = new File(envvariable);
            String format = zerocollection.getAbsolutePath().substring(zerocollection.getAbsolutePath().lastIndexOf("."));
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
            System.err.println("Не хватает прав доступа для работы с файлом.");
            System.exit(1);
        }
        ServerMaker serverMaker = new ServerMaker(3562);
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new ServerPart(serverMaker, envvariable, production, 0, 0, executor));
    }
}
