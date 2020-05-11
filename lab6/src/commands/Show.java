package commands;

import com.company.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Show extends Command {

    public Show() {
        indication = "выводит все элементы коллекции в строковом представлении";
        name = "show";
    }

    @Override
    public synchronized String execute(PriorityQueue<Product> production) {
        List<Product> coll2 = new ArrayList<Product>(production);
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < coll2.size(); j++) {
            Product a = coll2.get(j);
            result.append(a.toString()).append("\n    ");
        }
        return result.toString();
    }
}
