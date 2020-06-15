package commands;

import com.company.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class RemoveGreater extends Command {
    Product new_one;
    public static String indication = "удаляет из коллекции все элементы, превышающие заданный";
    public static String name = "remove_greater";

    public RemoveGreater(Product new_one) {
        this.new_one = new_one;
    }

    public static String help() {
        return RemoveGreater.name + ": " + RemoveGreater.indication + '\n';
    }

    @Override
    public synchronized String execute(PriorityQueue<Product> production) {
        int b = new_one.getId();
        boolean flag = false;
        List<Product> coll2 = new ArrayList<>(production);
        for (int j = 0; j < coll2.size(); j++) {
            Product a = coll2.get(j);
            if (a.getId() > b) {
                flag = production.remove(a);
            }
        }
        if (!flag) {
            return "В коллекции нет элементов, превыщающих заданный";
        } else {
            return "Элементы успешно удалены";
        }
    }
}
