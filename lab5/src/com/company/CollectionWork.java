package com.company;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.time.ZonedDateTime;
import java.util.Scanner;
import java.util.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Класс, реализующий методы для управления коллекцией
 */
public class CollectionWork {
    private PriorityQueue<Product> production;
    private Comparator<Product> comparator;
    private Date initDate;
    private File zerocollection;
    private boolean logging;
    protected static Map<String, String> forhelp = new HashMap<>();
    Iterator<Product> i;

    {
        comparator = Comparator.comparing(Product::getId);
        production = new PriorityQueue(comparator);
        i = production.iterator();
        forhelp.put("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        forhelp.put("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        forhelp.put("add {element}", "добавить новый элемент в коллекцию");
        forhelp.put("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        forhelp.put("remove_by_id id", "удалить элемент из коллекции по его id");
        forhelp.put("clear", "очистить коллекцию");
        forhelp.put("save", "сохранить коллекцию в файл");
        forhelp.put("execute_script file_name", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
        forhelp.put("exit", "завершить программу (без сохранения в файл)");
        forhelp.put("remove_first", "удалить первый элемент из коллекции");
        forhelp.put("add_if_min {element}", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        forhelp.put("remove_greater {element}", "удалить из коллекции все элементы, превышающие заданный");
        forhelp.put("min_by_name", "вывести любой объект из коллекции, значение поля name которого является минимальным");
        forhelp.put("print_field_descending_unit_of_measure unitOfMeasure", "вывести значения поля unitOfMeasure в порядке убывания");
        forhelp.put("print_field_descending_manufacture_cost manufactureCost", "вывести значения поля manufactureCost в порядке убывания");
    }

    CollectionWork(String envvariable) {
        try {
            if (envvariable == null) throw new FileNotFoundException();
        } catch (FileNotFoundException ex) {
            System.out.println("Неверный путь. Укажите переменную окружения - 'env_variable_for_my_5_laba'");
            System.exit(1);
        }

        try {
            this.zerocollection = new File(envvariable);
            if (!zerocollection.exists()) throw new FileNotFoundException();
        } catch (FileNotFoundException ex) {
            System.out.println("Файла по указанному пути не существует.");
            System.exit(1);
        }

        this.initDate = new Date();
        this.fillup();
        this.logging = true;
    }

    public void turnOffLogging() {
        this.logging = false;
    }

    /**
     * Десериализует коллекцию из файла xml
     */
    public void fillup() {
        try {
            if (!zerocollection.canRead() || !zerocollection.canWrite()) throw new SecurityException();
        } catch (SecurityException ex) {
            if (!this.logging) {
                System.out.println("Не хватает прав доступа для работы с файлом.");
            }
            System.exit(1);
        }

        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Document document;

        try {
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
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Осуществляет пользовательский ввод для параметров объекта класса Product
     * @return возвращает объект класса Product
     */
    public Product getProduct() {
        Product thing;
        Person owner;
        Scanner input = new Scanner(System.in);
        boolean control = false;
        while (!control) {
            if (!this.logging) {
                System.out.println("Введите название продукта: ");
            }
            control = input.hasNext();
            if (!control) {
                input.next();
            } else {
                break;
            }
        }
        String name = input.next();
        control = false;
        while (!control) {
            if (!this.logging) {
                System.out.println("Введите координату x: ");
            }
            control = input.hasNextInt();
            if (!control) {
                input.next();
            } else {
                break;
            }
        }
        Integer x = input.nextInt();
        control = false;
        while (!control) {
            if (!this.logging) {
                System.out.println("Введите координату y: ");
            }
            control = input.hasNextFloat();
            if (!control) {
                input.next();
            } else {
                break;
            }
        }
        float y = input.nextFloat();
        control = false;
        while (!control) {
            if (!this.logging) {
                System.out.println("Введите цену: ");
            }
            control = input.hasNextFloat();
            if (!control) {
                input.next();
            } else {
                break;
            }
        }
        Float price = input.nextFloat();
        control = false;
        while (!control) {
            if (!this.logging) {
                System.out.println("Введите стоимость производства: ");
            }
            control = input.hasNextDouble();
            if (!control) {
                input.next();
            } else {
                break;
            }
        }
        Double manufactureCost = input.nextDouble();
        control = false;
        int number = 0;
        while (!control) {
            if (!this.logging) {
                System.out.println("Выберете единицу измерения 1-4 (" + UnitOfMeasure.values().toString() + "): ");
            }
            control = input.hasNextInt();
            if (!control) {
                input.next();
            } else {
                number = input.nextInt();
                control = (number >= 1 && number <= 3);
            }
        }
        UnitOfMeasure unitOfMeasure = UnitOfMeasure.values()[number - 1];
        control = false;
        boolean control_2 = false;
        String change = "";
        while (!control) {
            if (!this.logging) {
                System.out.println("Вы хотите добавить владельца продукта? Да/Нет");
            }
            control = input.hasNext();
            if (!control) {
                input.next();
            } else {
                change = input.next();
                control = (change.equals("Да") || change.equals("Нет"));
            }
        }
        control_2 = change.equals("Да");
        if (!control_2) {
            owner = null;
        } else {
            control_2 = false;
            while (!control_2) {
                if (!this.logging) {
                    System.out.println("Введите имя владельца: ");
                }
                control_2 = input.hasNext();
                if (!control_2) {
                    input.next();
                } else {
                    break;
                }
            }
            String nameow = input.next();
            control_2 = false;
            int wei = 1;
            while (!control_2) {
                if (!this.logging) {
                    System.out.println("Введите вес владельца: ");
                }
                control_2 = input.hasNext();
                if (!control_2) {
                    input.next();
                } else {
                    wei = input.nextInt();
                    control_2 = (wei > 0);
                }
            }
            int weight = wei;
            int num = 0;
            control_2 = false;
            while (!control_2) {
                if (!this.logging) {
                    System.out.println("Выберете цвет глаз 1-4 (" + Color.values().toString() + "): ");
                }
                control_2 = input.hasNextInt();
                if (!control_2) {
                    input.next();
                } else {
                    num = input.nextInt();
                    control_2 = (num >= 1 && num <= 4);
                }
            }
            Color eyecolor = Color.values()[num - 1];
            num = 0;
            control_2 = false;
            while (!control_2) {
                if (!this.logging) {
                    System.out.println("Выберете цвет волос 1-5 (" + Color0.values().toString() + "): ");
                }
                control_2 = input.hasNextInt();
                if (!control_2) {
                    input.next();
                } else {
                    num = input.nextInt();
                    control_2 = (num >= 1 && num <= 5);
                }
            }
            Color0 haircolor = Color0.values()[num - 1];
            num = 0;
            control_2 = false;
            while (!control_2) {
                {
                    if (!this.logging) {
                        System.out.println("Выберете национальность 1-5 (" + Country.values().toString() + "): ");
                    }
                }
                control_2 = input.hasNextInt();
                if (!control_2) {
                    input.next();
                } else {
                    num = input.nextInt();
                    control_2 = (num >= 1 && num <= 5);
                }
            }
            Country nationality = Country.values()[num - 1];
            owner = new Person(nameow, weight, eyecolor, haircolor, nationality);
        }
        thing = new Product(name, new Coordinates(x, y), price, manufactureCost, unitOfMeasure, owner);
        return thing;
    }

    /**
     * Добавляет новый элемент в коллекцию
     */
    public void add() {
        production.add(getProduct());
        if (!this.logging) {
            System.out.println("Элемент успешно добавлен");
        }
    }

    /**
     * Обновляет значение элемента коллекции по заданному id
     * @param identificator - id объекта
     */
    public void update(int identificator) {
        List<Product> coll2 = new ArrayList<Product>(production);
        for (int j = 0; j < coll2.size(); j++) {
            Product a = coll2.get(j);
            if (coll2.get(j).getId() == identificator) {
                production.remove(a);
                Product c = getProduct();
                c.setId(identificator);
                return;
            }
        }
        if (!this.logging) {
            System.out.println("Записи с таким id не существует");
        }
    }

    /**
     * Выводит объект из коллекции, значение поля name которого является минимальным
     */
    public void min_by_name() {
        if (production.size() != 0) {
            List<Product> coll2 = new ArrayList<Product>(production);
            Collections.sort(coll2, (o1, o2) -> o1.getName().compareTo(o2.getName()));
            System.out.println(coll2.get(0));
        } else {
            if (!this.logging) {
                System.out.println("Коллекция не содержит элементов");
            }
        }
    }

    /**
     * Выводит значения поля unitOfMeasure всех элементов в порядке убывания
     */
    public void print_field_descending_unit_of_measure() {
        if (production.size() != 0) {
            List<Product> coll2 = new ArrayList<Product>(production);
            Collections.sort(coll2, (o1, o2) -> o1.getUnitOfMeasure().compareTo(o2.getUnitOfMeasure()));
            Collections.reverse(coll2);
            for (int i = 0; i < production.size(); i++) {
                System.out.println(coll2.get(i).getUnitOfMeasure());
            }
        } else {
            if (!this.logging) {
                System.out.println("Коллекция не содержит элементов");
            }
        }
    }

    /**
     * Выводит значения поля manufactureCost всех элементов в порядке убывания
     */
    public void print_field_descending_manufacture_cost() {
        if (production.size() != 0) {
            List<Product> coll2 = new ArrayList<Product>(production);
            Collections.sort(coll2, (o1, o2) -> o1.getManufactureCost().compareTo(o2.getManufactureCost()));
            Collections.reverse(coll2);
            for (int i = 0; i < production.size(); i++) {
                System.out.println(coll2.get(i).getManufactureCost());
            }
        } else {
            if (!this.logging) {
                System.out.println("Коллекция не содержит элементов");
            }
        }
    }

    /**
     * Добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
     */
    public void add_if_min() {
        Product new_one = getProduct();
        if (new_one.getId() < production.peek().getId()) {
            production.add(new_one);
        } else {
            if (!this.logging) {
                System.out.println("id данного элемента больше, чем у наименьшего в коллекции");
            }
        }
    }

    /**
     * Очищает коллекцию
     */
    public void clean() {
        production.clear();
        if (!this.logging) {
            System.out.println("Коллекция очищена");
        }
    }

    /**
     * Выводит в стандартный поток вывода все элементы коллекции в строковом представлении
     */
    public void show() {
        List<Product> coll2 = new ArrayList<Product>(production);
        for (int j = 0; j < coll2.size(); j++) {
            Product a = coll2.get(j);
            System.out.println(a.toString());
        }
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

            jaxbMarshaller.marshal(prod, new File(System.getenv("env_variable_for_my_5_laba")));

            jaxbMarshaller.marshal(prod, sw);
            System.out.println(sw.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Удаляет первый элемент из коллекции
     */
    public void remove_first() {
        Product e = production.poll();
        if (!this.logging) {
            System.out.println("Элемент удален");
        }
    }

    /**
     * Считать и исполняет скрипт из указанного файла
     * @param filename - файл с заданными командами
     * @throws FileNotFoundException
     */
    public void execute_script(String filename) throws FileNotFoundException {
        filename = "C:\\Users\\Yana\\IdeaProjects\\lab5\\script.txt";
        try {
//            InputStream inputStream = new FileInputStream(System.getenv(filename));
            InputStream inputStream = new FileInputStream(filename);

            Interactiv4ik my = new Interactiv4ik(new CollectionWork(System.getenv("env_variable_for_my_5_laba")), inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("Файла по указанному пути не существует. Введите переменную окружения -'readscript'");
        }
    }

    /**
     * Удаляет из коллекции все элементы, превышающие заданный
     */
    public void remove_greater() {
        int b = getProduct().getId();
        boolean flag = false;
        List<Product> coll2 = new ArrayList<Product>(production);
        for (int j = 0; j < coll2.size(); j++) {
            Product a = coll2.get(j);
            if (a.getId() > b) {
                flag = production.remove(a);
            }
        }
        if (!flag) {
            if (!this.logging) {
                System.out.println("В коллекции нет элементов, превыщающих заданный");
            }
        } else {
            if (!this.logging) {
                System.out.println("Элементы успешно удалены");
            }
        }
    }

    /**
     * Удаляет элемент из коллекции по его id
     * @param identificator - id заданного элемента
     */
    public void remove_by_id(int identificator) {
        List<Product> coll2 = new ArrayList<Product>(production);
        for (int j = 0; j < coll2.size(); j++) {
            Product a = coll2.get(j);
            if (a.getId() == identificator) {
                production.remove(a);
                return;
                }
            }
        if (!this.logging) {
            System.out.println("Записи с таким id не существует");
        }
    }


    /**
     * Выводит справку по доступным командам
     */
    public void help() {
        for (Map.Entry entry : forhelp.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /**
     *  Выводит информацию о коллекции
     * @return возвращает тип, дату и размер коллекции в сроковом представлении
     */
    @Override
    public String toString() {
        return "Тип коллекции: " + production.getClass() +
                "\nДата инициализации: " + initDate +
                "\nКоличество элементов: " + production.size();
    }

}
