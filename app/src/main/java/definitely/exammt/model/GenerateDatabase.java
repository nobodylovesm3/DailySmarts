package definitely.exammt.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateDatabase {
    private static List<Raid> raids;
    private static ArrayList<Raid> attendingRaids;

    public static List<Raid> getDatabase() {
        if (raids == null) {
            raids = generateDatabase();
            attendingRaids = new ArrayList<>();
        }
        return raids;
    }

    private static List<Raid> generateDatabase() {
        return Arrays.asList(
                new Raid("Враца", "Hello this is description", "https://i.pinimg.com/originals/60/12/e7/6012e72ad4548100daaf8b5bfbc456d8.jpg"),
                new Raid("София", "description, dude", "http://www.dronestagr.am/wp-content/uploads/2014/10/sofia-2_2.jpg")
        );
    }

    public static void addAttending(Raid raid) {
        attendingRaids.add(raid);
    }

    public static void removeAttending(Raid raid) {
        attendingRaids.remove(raid);
    }

    public static List<Raid> getAttendingRaids() {
        return attendingRaids;
    }

    public static void setAttendingRaids(List<Raid> raids) {
        attendingRaids = (ArrayList<Raid>) raids;
    }

    public static boolean isAttendingRaid(String name) {
        for (int i = 0; i < attendingRaids.size(); i++) {
            if (attendingRaids.get(i).getRaidTitle().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
