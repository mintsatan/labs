package com.company;

import commands.*;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ClientPart {
    private String last_string;
    private InputStream inputStream;
    private Scanner in;
    private final ServerConnect serverDeliver;
    private List<Product> collect = new ArrayList<>();

    public ClientPart(ServerConnect serverDeliver, InputStream inputStream) {
        this.serverDeliver = serverDeliver;
        this.inputStream = inputStream;
        this.in = new Scanner(this.inputStream);

        this.understanding();
    }

    private byte[] serialize(Object obj) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
//            System.out.println(Arrays.toString(byteArrayOutputStream.toByteArray()));
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            System.err.println("Айй");
        }
        return null;
    }

    private String[] safeRead(String field) {
        if (this.inputStream == System.in) {
            System.out.println(field);
        }
        do {
            try {
                last_string = in.nextLine().trim();
            } catch (NoSuchElementException ex) {
                in.close();
                System.exit(0);
            }

            if (last_string.isEmpty()) {
                System.err.println("Вы ничего не ввели :(");
                continue;
            }
            return last_string.split(" ");
        } while (true);
    }

    public void understanding() {
        String support;
        String now;
        String log;
        String pass;
        Charset charset = StandardCharsets.UTF_8;
        String ans = this.safeRead("У вас уже есть аккаунт? Yes/another ans")[0];
        if (ans.equals("Yes")) {
            log = this.safeRead("Введите логин: ")[0];
            pass = this.safeRead("Введите пароль: ")[0];
            this.serverDeliver.sendData(this.serialize(new Authenticate(log, pass)));
        } else {
            log = this.safeRead("Тогда давайте зарегистрируемся!\nПридумайте логин: ")[0];
            pass = this.safeRead("Придумайте пароль: ")[0];
            this.serverDeliver.sendData(this.serialize(new Registr(log, pass)));
        }
        do {
            String[] hope = this.safeRead("Введите команду: ");
            now = hope[0];
//            System.out.println(now);
            switch (now) {
                case "add":
                    this.serverDeliver.sendData(this.serialize(new Add(getProduct())));
                    System.out.println(charset.decode(ByteBuffer.wrap(this.serverDeliver.receiving()))
                            .toString());
                    break;
                case "add_if_min":
                    this.serverDeliver.sendData(this.serialize(new AddIfMin(getProduct())));
                    System.out.println(charset.decode(ByteBuffer.wrap(this.serverDeliver.receiving()))
                            .toString());
                    break;
                case "clear":
                    this.serverDeliver.sendData(this.serialize(new Clear()));
                    System.out.println(charset.decode(ByteBuffer.wrap(this.serverDeliver.receiving()))
                            .toString());
                    break;
                case "execute_script":
                    try {
                        support = hope[1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Имя файла не было введено");
                        break;
                    }

                        try {
                            File file = new File(support);
                             if (!file.exists()) throw new FileNotFoundException();
                             if (!file.canRead()) throw new SecurityException();
                             String format = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1);
                             InputStream fileInputStream = new FileInputStream(file);
                             ClientPart client = new ClientPart(this.serverDeliver, fileInputStream);
                            if (!format.equals("txt")) {
                                System.err.println("Неверный файл");
                                break;
                            }
                        } catch (FileNotFoundException e) {
                            System.err.println("Файла по указанному пути не существует");
                            break;
                        } catch (SecurityException ex) {
                            System.err.println("Не хватает прав доступа для работы с файлом.");
                            break;
                        }
                    break;
                case "info":
                    this.serverDeliver.sendData(this.serialize(new Info()));
                    System.out.println(charset.decode(ByteBuffer.wrap(this.serverDeliver.receiving()))
                            .toString());
                    break;
                case "min_by_name":
                    this.serverDeliver.sendData(this.serialize(new MinByName()));
                    System.out.println(charset.decode(ByteBuffer.wrap(this.serverDeliver.receiving()))
                            .toString());
                    break;
                case "print_field_descending_unit_of_measure":
                    this.serverDeliver.sendData(this.serialize(new PrintFieldDescendingUnitOfMeasure()));
                    System.out.println(charset.decode(ByteBuffer.wrap(this.serverDeliver.receiving()))
                            .toString());
                    break;
                case "print_field_descending_manufacture_cost":
                    this.serverDeliver.sendData(this.serialize(new PrintFieldDescendingManufactureCost()));
                    System.out.println(charset.decode(ByteBuffer.wrap(this.serverDeliver.receiving()))
                            .toString());
                    break;
                case "remove_by_id":
                    int id;
                    try {
                        support = hope[1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("id не был введен");
                        break;
                    }

                    try {
                        id = Integer.parseInt(support);
                    } catch (NumberFormatException e) {
                        System.err.println("id указан неверно");
                        break;
                    }
                    this.serverDeliver.sendData(this.serialize(new RemoveById(id)));
                    System.out.println(charset.decode(ByteBuffer.wrap(this.serverDeliver.receiving()))
                            .toString());
                    break;
                case "remove_first":
                    this.serverDeliver.sendData(this.serialize(new RemoveFirst()));
                    System.out.println(charset.decode(ByteBuffer.wrap(this.serverDeliver.receiving()))
                            .toString());
                    break;
                case "remove_greater":
                    this.serverDeliver.sendData(this.serialize(new RemoveGreater(getProduct())));
                    System.out.println(charset.decode(ByteBuffer.wrap(this.serverDeliver.receiving()))
                            .toString());
                    break;
                case "show":
                    this.serverDeliver.sendData(this.serialize(new Show()));
                    System.out.println(charset.decode(ByteBuffer.wrap(this.serverDeliver.receiving()))
                            .toString());
                    break;
                case "update":
                    int id1;
                    try {
                        support = hope[1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("id не был введен");
                        break;
                    }

                    try {
                        id1 = Integer.parseInt(support);
                    } catch (NumberFormatException e) {
                        System.err.println("id указан неверно");
                        break;
                    }
                    this.serverDeliver.sendData(this.serialize(new Update(getProduct(), id1)));
                    System.out.println(charset.decode(ByteBuffer.wrap(this.serverDeliver.receiving()))
                            .toString());
                    break;
                case "help":
                    this.serverDeliver.sendData(this.serialize(new Help()));
                    System.out.println(charset.decode(ByteBuffer.wrap(this.serverDeliver.receiving()))
                            .toString());
                    break;
                case "exit":
                    this.serverDeliver.sendData(this.serialize(new Exit()));
                    break;
                default:
                    System.err.println("Неизвестная команда. Вы можете посмотреть список команд с помощью 'help'\n");
            }
        } while (!now.equals("exit"));
    }

    public Product getProduct() {
        Product thing;
        Person owner;
        String name;
        int x;
        float y;
        float price;
        double manufactureCost;
        UnitOfMeasure unitOfMeasure;
        String nameow;
        int weight;
        Color eyecolor;
        Color0 haircolor;
        Country nationality;
        String support;
        int number;
        boolean control;

        name = safeRead("Введите название продукта: ")[0];
        do {
            support = safeRead("Введите координату x: ")[0];
            try {
                x = Integer.parseInt(support);
                break;
            } catch (NumberFormatException e) {
                continue;
            }
        } while (true);
        do {
            support = safeRead("Введите координату y: ")[0];
            try {
                y = Float.parseFloat(support);
                break;
            } catch (NumberFormatException e) {
                continue;
            }
        } while (true);
        do {
            support = safeRead("Введите ценy: ")[0];
            try {
                price = Float.parseFloat(support);
                break;
            } catch (NumberFormatException e) {
                continue;
            }
        } while (true);
        do {
            support = safeRead("Введите стоимость производства: ")[0];
            try {
                manufactureCost = Double.parseDouble(support);
                break;
            } catch (NumberFormatException e) {
                continue;
            }
        } while (true);
        do {
            support = safeRead("Выберете единицу измерения 1-4 (" + Arrays.toString(UnitOfMeasure.values()) + "): ")[0];
            try {
                number = Integer.parseInt(support);
            } catch (NumberFormatException e) {
                continue;
            }
            if (number >= 1 && number <= 4) {
                unitOfMeasure = UnitOfMeasure.values()[number - 1];
                break;
            }
        } while (true);
        do {
            support = safeRead("Вы хотите добавить владельца продукта? Yes/No")[0];
            if (support.equals("Yes") || support.equals("No")) {
                control = support.equals("Yes");
                break;
            }
        } while (true);
        if (!control) {
            owner = null;
        } else {
            nameow = safeRead("Введите имя владельца: ")[0];
            do {
                support = safeRead("Введите вес владельца: ")[0];
                try {
                    number = Integer.parseInt(support);
                } catch (NumberFormatException e) {
                    continue;
                }
                if (number > 0) {
                    weight = number;
                    break;
                }
            } while (true);
            do {
                support = safeRead("Выберете цвет глаз 1-4 (" + Arrays.toString(Color.values()) + "): ")[0];
                try {
                    number = Integer.parseInt(support);
                } catch (NumberFormatException e) {
                    continue;
                }
                if (number >= 1 && number <= 4) {
                    eyecolor = Color.values()[number - 1];
                    break;
                }
            } while (true);
            do {
                support = safeRead("Выберете цвет волос 1-5 (" + Arrays.toString(Color0.values()) + "): ")[0];
                try {
                    number = Integer.parseInt(support);
                } catch (NumberFormatException e) {
                    continue;
                }
                if (number >= 1 && number <= 5) {
                    haircolor = Color0.values()[number - 1];
                    break;
                }
            } while (true);
            do {
                support = safeRead("Выберете национальность 1-5 (" + Arrays.toString(Country.values()) + "): ")[0];
                try {
                    number = Integer.parseInt(support);
                } catch (NumberFormatException e) {
                    continue;
                }
                if (number >= 1 && number <= 5) {
                    nationality = Country.values()[number - 1];
                    break;
                }
            } while (true);
            owner = new Person(nameow, weight, eyecolor, haircolor, nationality);
        }
        thing = new Product(name, new Coordinates(x, y), price, manufactureCost, unitOfMeasure, owner);
        return thing;
    }

}
