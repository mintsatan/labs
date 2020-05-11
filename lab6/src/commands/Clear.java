package commands;

import com.company.Product;

import java.util.PriorityQueue;

public class Clear extends Command {

    public Clear() {
        indication = "очищает коллекцию";
        name = "clear";
    }

    @Override
    public synchronized String execute(PriorityQueue<Product> production) {
        production.clear();
        return "Коллекция очищена";
    }
}
