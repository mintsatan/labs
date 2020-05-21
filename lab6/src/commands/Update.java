package commands;

import com.company.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Update extends Command {
    Product new_one;
    Integer identificator;
    public static String indication = "обновляет значение элемента коллекции, id которого равен заданному";
    public static String name = "update";

    public Update(Product new_one, int identificator) {
        this.new_one = new_one;
        this.identificator = identificator;
    }

    public static String help() {
        return Update.name + ": " + Update.indication + '\n';
    }

    @Override
    public synchronized String execute(PriorityQueue<Product> production) {
        List<Product> coll2 = new ArrayList<Product>(production);
        for (int j = 0; j < coll2.size(); j++) {
            Product a = coll2.get(j);
            if (coll2.get(j).getId().equals(identificator)) {
                production.remove(a);
                new_one.setId(identificator);
                return "Элемент удален";
            }
        }
        return "Записи с таким id не существует";
    }
}
