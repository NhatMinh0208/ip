package sphene.component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sphene.exception.SaveException;
import sphene.exception.LoadException;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<String> load() throws LoadException {
        try {
            Files.createDirectories(Paths.get("data"));
        } catch (IOException e) {
            throw new LoadException(filePath);
        }

        try {
            Files.createFile(Paths.get(filePath));
        } catch (IOException e) {

        }

        try {
            File taskListFile = new File(filePath);
            List<String> serializedStrings = new ArrayList<>();
            Scanner taskListScanner = new Scanner(taskListFile);
            while (taskListScanner.hasNext()) {
                serializedStrings.add(taskListScanner.nextLine());
            }
            return serializedStrings;
        } catch (Exception e) {
            throw new LoadException(filePath);
        }
    }

    public void store(List<String> serializedStrings) throws SaveException {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (String s : serializedStrings) {
                writer.write(s + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new SaveException(filePath);
        }
    }
}
