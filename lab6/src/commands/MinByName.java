package commands;

import com.company.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MinByName extends Command {

    public MinByName() {
        indication = "выводит любой объект из коллекции, значение поля name которого является минимальным";
        name = "min_by_name";
    }

    @Override
    public synchronized String execute(PriorityQueue<Product> production) {
        if (production.size() != 0) {
            List<Product> coll2 = new ArrayList<>(production);
            Collections.sort(coll2, (o1, o2) -> o1.getName().compareTo(o2.getName()));
            return coll2.get(0).toString();
        } else {
            return "Коллекция не содержит элементов";
        }
    }
}
