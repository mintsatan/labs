package commands;

import com.company.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MinByName extends Command {
    public static String indication = "выводит любой объект из коллекции, значение поля name которого является минимальным";
    public static String name = "min_by_name";

    public MinByName() {

    }

    public static String help() {
        return MinByName.name + ": " + MinByName.indication + '\n';
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
