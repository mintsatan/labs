package com.company;

import commands.*;
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
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.*;

public class ServerPart {
    private final ServerMaker clientInteractive;
    private PriorityQueue<Product> production;
    private final Date initDate;
    Comparator<Product> comparator;
    private File zerocollection;
    private ByteArrayOutputStream bytearrayoutputstream;
    private ObjectOutputStream objectoutputstream;

    class Message extends Thread {

        public void run() {
            System.out.println("Пока");
        }
    }

    public ServerPart(int PORT, String envvariable) {
        this.clientInteractive = new ServerMaker(PORT);

        this.initDate = new Date();
        this.comparator = Comparator.comparing(Product::getId);
        this.production = new PriorityQueue<>(comparator);
        this.bytearrayoutputstream = new ByteArrayOutputStream();
        try {
            this.objectoutputstream = new ObjectOutputStream(bytearrayoutputstream);
        } catch (IOException e) {
            System.err.println("Упс");
        }

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
            this.zerocollection = new File(envvariable);
            String format = this.zerocollection.getAbsolutePath().substring(this.zerocollection.getAbsolutePath().lastIndexOf("."));
            if (!this.zerocollection.exists() | format.equals("xml")) throw new FileNotFoundException();
            if (!this.zerocollection.canRead() || !this.zerocollection.canWrite()) throw new SecurityException();

            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            document = builder.parse(this.zerocollection);
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
                this.production.add(product);
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

        this.init();
    }

    protected void save() {
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

    protected void init() {
        try {
            Runtime.getRuntime().addShutdownHook(new Message());
        } catch (Exception e) {
            e.printStackTrace();
        }
        do {
            Command command = this.clientInteractive.receiving();
//            System.out.println(command.getClass());
            if (command.getClass() != Exit.class) {
                this.clientInteractive.sendData(this.performance(command));
            } else {
                this.save();
            }

        } while (true);
    }

    protected byte[] performance(Command command) {
        String a;
        Charset charset = StandardCharsets.UTF_8;
        if (command.getClass() == Info.class) {
            a = getInfo();
        } else {
            a = command.execute(this.production);
        }
        return charset.encode(a).array();
    }

    public String getInfo() {
        return "Тип коллекции: " + production.getClass() +
                "\nДата инициализации: " + initDate +
                "\nКоличесвто элементов: " + production.size();
    }

}
