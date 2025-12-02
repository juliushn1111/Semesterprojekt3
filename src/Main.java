import model.Institution;
import model.Room;
import model.Child;

public class Main {
    public static void main(String[] args) {
        System.out.println("Backend test kører12345");

        Institution institution = new Institution("Børnehuset Solstrålen");
        Room room = new Room("Rød stue");
        Child child = new Child("Oliver", "Hansen", 3);

        room.addChild(child);  // ← Kun dette kalder den nye metode

        System.out.println(institution);
        System.out.println(room);
        System.out.println(child);
    }
}
