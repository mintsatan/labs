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
            last_command = in.next();
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
                    boolean control = in.hasNextInt();
                    if (!control) {
                        if (!this.standartStream) {
                            System.out.println("Введите id элемента");
                        }
                        in.next();
                    } else {
                        int id = in.nextInt();
                        helper.update(id);
                    }
                    break;
                case "remove_by_id":
                    boolean control_2 = in.hasNextInt();
                    if (!control_2) {
                        if (!this.standartStream) {
                            System.out.println("Введите id элемента");
                        }
                        in.next();
                    } else {
                        int id = in.nextInt();
                        helper.remove_by_id(id);
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
                    boolean control_3 = in.hasNext();
                    if (!control_3) {
                        if (!this.standartStream) {
                            System.out.println("Введите имя файла (переменная окружения - 'readscript')");
                        }
                        in.next();
                    } else {
                        String filename = in.next();
                        helper.execute_script(filename);
                    }
                    break;
                default:
                    if (!this.standartStream) {
                        System.out.println("Неизвестная комманда. Вы можете посмотреть список комманд с помощью 'help'\n");
            }       }
        } while(!last_command.equals("exit"));
    }

}
