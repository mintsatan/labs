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
import java.util.concurrent.ExecutorService;

public class ServerPart implements Runnable{
    private final ServerMaker clientInteractive;
    private PriorityQueue<Product> production;
    private final Date initDate;
    private ByteArrayOutputStream bytearrayoutputstream;
    private ObjectOutputStream objectoutputstream;
    private String envvariable;
    private Thread t;
    private int countOfWorkingClients;
    private int countOfRunningThreads;
    private ExecutorService executor;

    class Message extends Thread {

        public void run() {
            System.out.println("Пока");
        }
    }

    public ServerPart(ServerMaker clientInteractive, String envvariable, PriorityQueue<Product> production, int count1, int count2, ExecutorService executor) {
        this.clientInteractive = clientInteractive;
        this.envvariable = envvariable;
        this.production = production;
        this.initDate = new Date();
        this.executor = executor;
        this.countOfWorkingClients = count1;
        this.countOfRunningThreads = count2;
        this.bytearrayoutputstream = new ByteArrayOutputStream();
        try {
            this.objectoutputstream = new ObjectOutputStream(bytearrayoutputstream);
        } catch (IOException e) {
            System.err.println("Упс");
        }
    }

    protected void save() {
        StringWriter sw = new StringWriter();
        try {
            Products prod = new Products();
            prod.setProducts(production);
            JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            jaxbMarshaller.marshal(prod, new File(envvariable));

            jaxbMarshaller.marshal(prod, sw);
//            System.out.println(sw.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            Runtime.getRuntime().addShutdownHook(new Message());
        } catch (Exception e) {
            e.printStackTrace();
        }
        do {
            try {
                byte[] b = clientInteractive.receiving();
//                System.out.println(Arrays.toString(b));
                if (b[0] == -1) {
                    countOfWorkingClients++;
                    if (countOfRunningThreads < countOfWorkingClients) {
                        executor.execute(new ServerPart(clientInteractive, envvariable, production, countOfWorkingClients, countOfRunningThreads, executor));
                        countOfRunningThreads++;
                    }
                    this.clientInteractive.sendData(b);
                } else {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(b);
                    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    Command command = (Command) objectInputStream.readObject();
//            System.out.println(command.getClass());
                    if (command.getClass() != Exit.class) {
                        this.clientInteractive.sendData(this.performance(command));
                    } else {
                        this.save();
                    }
                }
            } catch (IOException e) {
                System.err.println("Что-то пошло не так");
            } catch (ClassNotFoundException e) {
                System.err.println("Это не команда..");
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
