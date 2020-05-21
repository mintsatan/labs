package commands;

public class ExecuteScript extends Command {
    String filename;
    public static String name = "execute_script";
    public static String indication = "исполняет скрипт из указанного файла";

    public ExecuteScript(String filename) {
        this.filename = filename;
    }

    public static String help() {
        return ExecuteScript.name + ": " + ExecuteScript.indication + '\n';
    }
}
