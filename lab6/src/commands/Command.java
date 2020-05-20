package commands;

import com.company.Product;

import java.io.Serializable;
import java.util.Objects;
import java.util.PriorityQueue;

public abstract class Command implements Serializable {
    public static String name;
    public static String indication;

    public synchronized String execute(PriorityQueue<Product> production) {
        return null;
    }

    public static String help() {
        return name + ": " + indication + '\n';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, indication);
    }

    @Override
    public String toString() {
        return "Command{" +
                "name='" + name +
                ", indication='" + indication;
    }
}
