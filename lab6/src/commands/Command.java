package commands;

import com.company.Product;

import java.util.Objects;
import java.util.PriorityQueue;

public abstract class Command {
    protected static String name;
    protected static String indication;
    protected PriorityQueue<Product> production;

    public Command() {
    }

    public synchronized String execute(PriorityQueue<Product> production) {
        return null;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public PriorityQueue<Product> getProduction() {
        return production;
    }

    public String getIndication() {
        return indication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return name.equals(command.name) &&
                indication.equals(command.indication) &&
                production.equals(command.production);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, indication, production);
    }

    @Override
    public String toString() {
        return "Command{" +
                "name='" + name + '\'' +
                ", indication='" + indication + '\'' +
                ", production=" + production +
                '}';
    }
}
