package commands;

import com.company.Product;

import java.util.*;

public class PrintFieldDescendingUnitOfMeasure extends Command {

    public PrintFieldDescendingUnitOfMeasure() {
        indication = "выводит значения поля unitOfMeasure в порядке убывания";
        name = "print_field_descending_unit_of_measure";
    }

    @Override
    public synchronized String execute(PriorityQueue<Product> production) {
        if (production.size() != 0) {
            List<Product> coll2 = new ArrayList<Product>(production);
            Collections.sort(coll2, Comparator.comparing(Product::getUnitOfMeasure));
            Collections.reverse(coll2);
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < production.size(); i++) {
                result.append(coll2.get(i).getUnitOfMeasure()).append("\n    ");
            }
            return result.toString();
        } else {
            return "Коллекция не содержит элементов";
        }
    }
}
