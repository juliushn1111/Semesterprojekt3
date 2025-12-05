package persistence;

import model.Institution;
import java.io.*;

public class ModelManager {

    private String fileName;

    public ModelManager(String fileName) {
        this.fileName = fileName;
    }

    public void save(Institution institution) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(institution);
            System.out.println("Institution gemt.");
        } catch (Exception e) {
            System.out.println("Fejl ved gemning: " + e.getMessage());
        }
    }

    public Institution load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Institution) in.readObject();
        } catch (Exception e) {
            System.out.println("Ingen gemt fil — laver ny institution");
            return null;
        }
    }
}
