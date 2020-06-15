package commands;

import com.company.Product;

import java.util.PriorityQueue;

public class Clear extends Command {
    public static String indication = "очищает коллекцию";
    public static String name = "clear";

    public Clear() {
    }

    public static String help() {
        return Clear.name + ": " + Clear.indication + '\n';
    }

    @Override
    public synchronized String execute(PriorityQueue<Product> production) {
        if (production.size() != 0) {
            production.clear();
            return "Коллекция очищена";
        } else {
            return "Коллекция не содержит элементов";
        }
    }
}
