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
        String name;
        Integer x;
        float y;
        Float price;
        Double manufactureCost;
        UnitOfMeasure unitOfMeasure;
        String nameow;
        int weight;
        Color eyecolor;
        Color0 haircolor;
        Country nationality;
        Scanner support;
        Scanner input = new Scanner(System.in);
        String inputString = "";
        boolean control;
        do {
            if (!this.logging) {
                System.out.println("Введите название продукта: ");
            }
            inputString = input.nextLine();

            if (!inputString.isEmpty()) {
                name = inputString.split(" ")[0];
                break;
            }
        } while (true);
        do {
            if (!this.logging) {
                System.out.println("Введите координату x: ");
            }
            inputString = input.nextLine();
            if (!inputString.isEmpty()) {
                support = new Scanner(inputString);
                if (support.hasNextInt()) {
                    x = support.nextInt();
                    break;
                }
            }
        } while (true);
        do {
            if (!this.logging) {
                System.out.println("Введите координату y: ");
            }
            inputString = input.nextLine();
            if (!inputString.isEmpty()) {
                support = new Scanner(inputString);
                if (support.hasNextFloat()) {
                    y = support.nextFloat();
                    break;
                }
            }
        } while (true);
        do {
            if (!this.logging) {
                System.out.println("Введите цену: ");
            }
            inputString = input.nextLine();
            if (!inputString.isEmpty()) {
                support = new Scanner(inputString);
                if (support.hasNextFloat()) {
                    price = support.nextFloat();
                    break;
                }
            }
        } while (true);
            do {
                if (!this.logging) {
                    System.out.println("Введите стоимость производства: ");
                }
                inputString = input.nextLine();
                if (!inputString.isEmpty()) {
                    support = new Scanner(inputString);
                    if (support.hasNextDouble()) {
                        manufactureCost = support.nextDouble();
                        break;
                    }
                }
            } while (true);
        int number = 0;
        do {
            if (!this.logging) {
                System.out.println("Выберете единицу измерения 1-4 (" + Arrays.toString(UnitOfMeasure.values()) + "): ");
            }
            inputString = input.nextLine();

            if (!inputString.isEmpty()) {
                support = new Scanner(inputString);
                if (support.hasNextInt()) {
                    number = support.nextInt();
                    if (number >= 1 && number <= 4) {
                        unitOfMeasure = UnitOfMeasure.values()[number - 1];
                        break;
                    }
                }
            }
        } while (true);
        do {
            if (!this.logging) {
                System.out.println("Вы хотите добавить владельца продукта? Yes/No");
            }
            inputString = input.nextLine();

            if (!inputString.isEmpty()) {
                String change = inputString.split(" ")[0];
                if (change.equals("Yes") || change.equals("No")) {
                    control = change.equals("Yes");
                    break;
                }
            }
        } while (true);
        if (!control) {
            owner = null;
        } else {
            do {
                if (!this.logging) {
                    System.out.println("Введите имя владельца: ");
                }
                inputString = input.nextLine();

                if (!inputString.isEmpty()) {
                    nameow = inputString.split(" ")[0];
                    break;
                }
            } while (true);
            do {
                if (!this.logging) {
                    System.out.println("Введите вес владельца: ");
                }
                inputString = input.nextLine();

                if (!inputString.isEmpty()) {
                    support = new Scanner(inputString);
                    if (support.hasNextInt()) {
                        number = support.nextInt();
                        if (number > 0) {
                            weight = number;
                            break;
                        }
                    }
                }
            } while (true);
            do {
                if (!this.logging) {
                    System.out.println("Выберете цвет глаз 1-4 (" + Arrays.toString(Color.values()) + "): ");
                }
                inputString = input.nextLine();

                if (!inputString.isEmpty()) {
                    support = new Scanner(inputString);
                    if (support.hasNextInt()) {
                        number = support.nextInt();
                        if (number >= 1 && number <= 4) {
                            eyecolor = Color.values()[number - 1];
                            break;
                        }
                    }
                }
            } while (true);
            do {
                if (!this.logging) {
                    System.out.println("Выберете цвет волос 1-5 (" + Arrays.toString(Color0.values()) + "): ");
                }
                inputString = input.nextLine();

                if (!inputString.isEmpty()) {
                    support = new Scanner(inputString);
                    if (support.hasNextInt()) {
                        number = support.nextInt();
                        if (number >= 1 && number <= 5) {
                            haircolor = Color0.values()[number - 1];
                            break;
                        }
                    }
                }
            } while (true);
            do {
                {
                    if (!this.logging) {
                        System.out.println("Выберете национальность 1-5 (" + Arrays.toString(Country.values()) + "): ");
                    }
                }
                inputString = input.nextLine();

                if (!inputString.isEmpty()) {
                    support = new Scanner(inputString);
                    if (support.hasNextInt()) {
                        number = support.nextInt();
                        if (number >= 1 && number <= 5) {
                            nationality = Country.values()[number - 1];
                            break;
                        }
                    }
                }
            } while (true);
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
//            System.out.println(sw.toString());
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
        try {
//            InputStream inputStream = new FileInputStream(System.getenv(filename));
            InputStream inputStream = new FileInputStream(filename);

            Interactiv4ik my = new Interactiv4ik(new CollectionWork(System.getenv("env_variable_for_my_5_laba")), inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("Файла по указанному пути не существует");
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
