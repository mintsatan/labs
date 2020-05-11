package commands;

import com.company.Product;

import java.util.PriorityQueue;

public class RemoveFirst extends Command {

    public RemoveFirst() {
        indication = "удаляет первый элемент из коллекции";
        name = "remove_first";
    }

    @Override
    public synchronized String execute(PriorityQueue<Product> production) {
        Product e = production.poll();
        return "Элемент удален";
    }
}
