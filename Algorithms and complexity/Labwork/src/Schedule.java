import java.util.ArrayList;
import java.util.List;

public class Schedule {

    private String day;
    private List<String> lessons;

    public Schedule(String day) {
        this.day = day;
        lessons = new ArrayList<>();
    }

    public void addLesson(String lesson) {
        lessons.add(lesson);
    }

    public void  setLessons(List<String> lessons) {
        this.lessons = lessons;
    }

    public String getDay() {
        return day;
    }

    public List<String> getLessons() {
        return lessons;
    }

    public void clearSchedule() {
        lessons.clear();
    }
}