package commands;

import com.company.Product;

import java.util.PriorityQueue;

public class AddIfMin extends Command {
    Product new_one;

    public AddIfMin(Product new_one) {
        this.new_one = new_one;

        indication = "добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
        name = "add_if_min";
    }

    @Override
    public synchronized String execute(PriorityQueue<Product> production) {
        if (new_one.getId() < production.peek().getId()) {
            production.add(new_one);
            return "Элемент успешно добавлен";
        } else {
            return "id данного элемента больше, чем у наименьшего в коллекции";
        }
    }
}
