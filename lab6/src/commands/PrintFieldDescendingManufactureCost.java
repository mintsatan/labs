package commands;

import com.company.Product;

import java.util.*;

public class PrintFieldDescendingManufactureCost extends Command {

    public PrintFieldDescendingManufactureCost() {
        indication = "выводит значения поля manufactureCost в порядке убывания";
        name = "print_field_descending_manufacture_cost";
    }

    @Override
    public synchronized String execute(PriorityQueue<Product> production) {
        if (production.size() != 0) {
            List<Product> coll2 = new ArrayList<Product>(production);
            Collections.sort(coll2, Comparator.comparing(Product::getManufactureCost));
            Collections.reverse(coll2);
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < production.size(); i++) {
                result.append(coll2.get(i).getManufactureCost()).append("\n    ");
            }
            return result.toString();
        } else {
            return "Коллекция не содержит элементов";
        }
    }
}
