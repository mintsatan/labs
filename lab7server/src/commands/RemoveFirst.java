package commands;

import com.company.Product;

import java.util.PriorityQueue;

public class RemoveFirst extends Command {
    public static String indication = "удаляет первый элемент из коллекции";
    public static String name = "remove_first";

    public RemoveFirst() {
    }

    public static String help() {
        return RemoveFirst.name + ": " + RemoveFirst.indication + '\n';
    }

    @Override
    public synchronized String execute(PriorityQueue<Product> production) {
        if (production.size() != 0) {
            Product e = production.poll();
            return "Элемент удален";
        } else {
            return "Коллекция не содержит элементов";
        }
    }
}
