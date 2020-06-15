package commands;

import com.company.Product;

import java.util.PriorityQueue;

public class Add extends Command {
    Product new_one;
    public static String indication = "добавляет новый элемент в коллекцию";
    public static String name = "add";

    public Add(Product new_one) {
        this.new_one = new_one;
    }

    public static String help() {
        return Add.name + ": " + Add.indication + '\n';
    }

    @Override
    public synchronized String execute(PriorityQueue<Product> production) {
        production.add(new_one);
        return "Элемент успешно добавлен";
    }
}
