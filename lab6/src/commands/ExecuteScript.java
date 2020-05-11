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
    
}
