package commands;

import com.company.Product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.PriorityQueue;

public class ExecuteScript extends Command {
    String filename;

    public ExecuteScript(String filename) {
        this.filename = filename;

        name = "execute_script";
        indication = "исполняет скрипт из указанного файла";
    }

    @Override
    public synchronized String execute(PriorityQueue<Product> production) {
        try {
            File file = new File(filename);
            String format = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1);
            if (!format.equals("txt")) {
                InputStream inputStream = new FileInputStream(file);
//                Interactiv4ik my = new Interactiv4ik(new CollectionWork(System.getenv("env_variable_for_my_5_laba")), inputStream);
            } else {
                return "Неверный файл";
            }
        } catch (FileNotFoundException e) {
            return "Файла по указанному пути не существует";
        }
        return null;
    }
}
