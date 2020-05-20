package commands;

public class ExecuteScript extends Command {
    String filename;

    public ExecuteScript(String filename) {
        this.filename = filename;

        name = "execute_script";
        indication = "исполняет скрипт из указанного файла";
    }
    
}
