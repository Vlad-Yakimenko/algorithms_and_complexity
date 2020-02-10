package main.Main;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GettingSchedule {

    static List<Schedule> getSchedule() {
        try (FileReader fileReader = new FileReader("src/ScheduleForTest.txt")) {
            Scanner scanner = new Scanner(fileReader);

            List<Schedule> buffer = new ArrayList<>();

            while (scanner.hasNextLine()) {
                Schedule schedule = new Schedule(scanner.nextLine());

                schedule.setLessons(Arrays.asList(scanner.nextLine().split(", ")));

                buffer.add(schedule);
            }

            return buffer;
        } catch (IOException e) {
            System.out.println("This file doesn't exists!");
        }

        return null;
    }
}