package commands;

public class Exit extends Command {
    public static String indication = "завершает работу";
    public static String name = "exit";

    public Exit() {
    }

    public static String help() {
        return Exit.name + ": " + Exit.indication + '\n';
    }

}
