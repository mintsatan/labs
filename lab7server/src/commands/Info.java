package commands;

public class Info extends Command {
    public static String indication = "выводит информацию о коллекции";
    public static String name = "info";

    public Info() {
    }

    public static String help() {
        return Info.name + ": " + Info.indication + '\n';
    }

}
