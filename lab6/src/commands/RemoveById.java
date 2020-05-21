package commands;

import com.company.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class RemoveById extends Command {
    Integer identificator;
    public static String indication = "удаляет элемент из коллекции по его id";
    public static String name = "remove_by_id";

    public RemoveById(int identificator) {
        this.identificator = identificator;
    }

    public static String help() {
        return RemoveById.name + ": " + RemoveById.indication + '\n';
    }

    @Override
    public synchronized String execute(PriorityQueue<Product> production) {
        List<Product> coll2 = new ArrayList<Product>(production);
        boolean flag = false;
        for (int j = 0; j < coll2.size(); j++) {
            Product a = coll2.get(j);
            if (a.getId() == identificator) {
                flag = production.remove(a);
            }
        }
        if (!flag) {
            return "Записи с таким id не существует";
        } else {
            return "Элементы успешно удалены";
        }
    }
}
