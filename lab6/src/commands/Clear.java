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
        production.clear();
        return "Коллекция очищена";
    }
}
