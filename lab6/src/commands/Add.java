package commands;

import com.company.Product;

import java.util.PriorityQueue;

public class Add extends Command {
    Product new_one;

    public Add(Product new_one) {
        this.new_one = new_one;

        indication = "добавляет новый элемент в коллекцию";
        name = "add";
    }

    @Override
    public synchronized String execute(PriorityQueue<Product> production) {
        production.add(new_one);
        return "Элемент успешно добавлен";
    }
}
