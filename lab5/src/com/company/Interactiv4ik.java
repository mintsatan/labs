package com.company;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

/**
 * Класс, реализующий взаимодействие с пользователем
 */
public class Interactiv4ik {

    CollectionWork helper;
    private String last_command;
    private InputStream inputStream;
    private boolean standartStream;
    private String last_string;

    Interactiv4ik (CollectionWork helper, InputStream inputStream) throws FileNotFoundException {
        this.helper = helper;
        last_command = "";
        this.standartStream = this.inputStream == System.in;
        if (!this.standartStream) {
            this.helper.turnOffLogging();
        }
        this.inputStream = inputStream;
        this.init();
    }

    public void init() throws FileNotFoundException {
        Scanner in = new Scanner(this.inputStream);
        do {
            try {
                last_string = in.nextLine().trim();
            } catch (NoSuchElementException ex) {
                in.close();
                helper.safe_exit();
            }

            if (last_string.isEmpty()) {
                System.out.println("Введите команду");
                last_command = "";
                continue;
            }
            Scanner stringScanner = new Scanner(last_string);
            last_command = stringScanner.next();
                switch (last_command) {
                    case "help":
                        helper.help();
                        break;
                    case "add":
                        helper.add();
                    case "exit":
                        break;
                    case "show":
                        helper.show();
                        break;
                    case "min_by_name":
                        helper.min_by_name();
                        break;
                    case "update":
                        boolean control = stringScanner.hasNextInt();
                        if (control) {
                            int id = stringScanner.nextInt();
                            helper.update(id);
                        } else {
                            if (!this.standartStream) {
                                System.out.println("Введите id элемента");
                            }
                            do {
                                try {
                                    last_string = in.nextLine().trim();
                                } catch (NoSuchElementException ex) {
                                    in.close();
                                    helper.safe_exit();
                                }
                                if (last_string.isEmpty()) {
                                    if (!this.standartStream) {
                                        System.out.println("Введите id элемента");
                                    }
                                } else {
                                    stringScanner = new Scanner(last_string);
                                    if (stringScanner.hasNextInt()) {
                                        int id = stringScanner.nextInt();
                                        helper.update(id);
                                        break;
                                    } else {
                                        if (!this.standartStream) {
                                            System.out.println("Введите id элемента");
                                        }
                                    }
                                }
                            } while (true);
                        }
                        break;
                    case "remove_by_id":
                        control = stringScanner.hasNextInt();
                        if (control) {
                            int id = stringScanner.nextInt();
                            helper.remove_by_id(id);
                        } else {
                            if (!this.standartStream) {
                                System.out.println("Введите id элемента");
                            }
                            do {
                                try {
                                    last_string = in.nextLine().trim();
                                } catch (NoSuchElementException ex) {
                                    in.close();
                                    helper.safe_exit();
                                }
                                if (last_string.isEmpty()) {
                                    if (!this.standartStream) {
                                        System.out.println("Введите id элемента");
                                    }
                                } else {
                                    stringScanner = new Scanner(last_string);
                                    if (stringScanner.hasNextInt()) {
                                        int id = stringScanner.nextInt();
                                        helper.remove_by_id(id);
                                        break;
                                    } else {
                                        if (!this.standartStream) {
                                            System.out.println("Введите id элемента");
                                        }
                                    }
                                }
                            } while (true);
                        }
                        break;
                    case "save":
                        helper.save();
                        break;
                    case "remove_greater":
                        helper.remove_greater();
                        break;
                    case "print_field_descending_unit_of_measure":
                        helper.print_field_descending_unit_of_measure();
                        break;
                    case "print_field_descending_manufacture_cost":
                        helper.print_field_descending_manufacture_cost();
                        break;
                    case "remove_first":
                        helper.remove_first();
                        break;
                    case "add_if_min":
                        helper.add_if_min();
                        break;
                    case "clear":
                        helper.clean();
                        break;
                    case "info":
                        if (!this.standartStream) {
                            System.out.println(helper.toString());
                        }
                        break;
                    case "execute_script":
                        control = stringScanner.hasNext();
                        if (control) {
                            String filename = stringScanner.next();
                            helper.execute_script(filename);
                        } else {
                                if (!this.standartStream) {
                                    System.out.println("Введите имя файла");
                                }
                                do {
                                    try {
                                        last_string = in.nextLine().trim();
                                    } catch (NoSuchElementException ex) {
                                        in.close();
                                        helper.safe_exit();
                                    }
                                    if (last_string.isEmpty()) {
                                        if (!this.standartStream) {
                                            System.out.println("Введите имя файла");
                                        }
                                    } else {
                                        stringScanner = new Scanner(last_string);
                                        if (stringScanner.hasNext()) {
                                            String filename = stringScanner.next();
                                            helper.execute_script(filename);
                                            break;
                                        } else {
                                            if (!this.standartStream) {
                                                System.out.println("Введите имя файла");
                                            }
                                        }
                                    }
                                } while (true);
                            }
                        break;
                    default:
                        if (!this.standartStream) {
                            System.out.println("Неизвестная команда. Вы можете посмотреть список команд с помощью 'help'\n");
                        }
                }
        } while (!last_command.equals("exit")) ;
    }
}
