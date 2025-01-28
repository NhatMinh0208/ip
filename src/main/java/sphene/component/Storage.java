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

/**
 * Storage object for a specified file.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a new storage object for the specified file.
     * @param filePath Path of the file to create storage object for.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load all strings from the file.
     * @return The strings loaded from the file.
     * @throws LoadException If file cannot be loaded.
     */
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

    /**
     * Saves a list of strings to the file, overwriting the previous content in the file.
     * @param serializedStrings Strings to be saved to the file.
     * @throws SaveException If the strings cannot be saved to the file.
     */
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
