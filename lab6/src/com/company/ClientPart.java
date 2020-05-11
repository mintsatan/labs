package com.company;

import commands.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientPart {
    private String last_string;
    private InputStream inputStream;

    private String[] safeRead(String field) {
        Scanner in = new Scanner(this.inputStream);
        System.out.println(field);
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

    public Command understanding(String[] hope) {
        String support;
        String now = hope[0];
        do {
            switch (now) {
                case "add":
                    return new Add(getProduct());
                case "add_if_min":
                    return new AddIfMin(getProduct());
                case "clear":
                    return new Clear();
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
                    return new ExecuteScript(support);
                case "info":
                    return new Info();
                case "min_by_name":
                    return new MinByName();
                case "print_field_descending_unit_of_measure":
                    return new PrintFieldDescendingUnitOfMeasure();
                case "print_field_descending_manufacture_cost":
                    return new PrintFieldDescendingManufactureCost();
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
                    return new RemoveById(id);
                case "remove_first":
                    return new RemoveFirst();
                case "remove_greater":
                    return new RemoveGreater(getProduct());
                case "show":
                    return new Show();
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
                    return new Update(getProduct(), id1);
                case "save":
                    return new Save();
                case "help":
                    return new Help();
                default:
                    System.err.println("Неизвестная команда. Вы можете посмотреть список команд с помощью 'help'\n");
            }
        } while (!now.equals("exit"));
        return null;
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
