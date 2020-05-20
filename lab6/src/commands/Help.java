package commands;

import com.company.Product;

import java.util.PriorityQueue;

public class Help extends Command {

    public Help() {
        name = "help";
        indication = "дает справку по командам";
    }

    @Override
    public synchronized String execute(PriorityQueue<Product> production) {
        return Add.help() + AddIfMin.help() + Clear.help() + ExecuteScript.help()
                + Help.help() + Info.help() + MinByName.help() + PrintFieldDescendingManufactureCost.help()
                + PrintFieldDescendingUnitOfMeasure.help() + RemoveById.help() + RemoveFirst.help()
                + RemoveGreater.help() + Show.help() + Update.help();
    }
}
