package Main;

import PerfectHashing.PerfectHashMap;

import java.security.SecureRandom;
import java.util.*;

public class Main {

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";

    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        Set<Schedule> set = new HashSet<>();

        String searchKey = null;
        String answer = null;

        for (int i = 0; i < 20; i++) {
            String key = generateRandomString(4);

            List<String> value = new ArrayList<>();
            value.add(generateRandomString(4));

            Schedule schedule = new Schedule(key);
            schedule.setLessons(value);
            set.add(schedule);

            if (i == 11) {
                searchKey = key;
                answer = value.get(0);
            }
        }

        List<Schedule> list = new ArrayList<>(set);

//        List<Main.Schedule> buffer = Main.GettingSchedule.getSchedule();
//
//        for (Main.Schedule schedule : buffer) {
//            list.add(new Pair<>(schedule.getDay(), schedule.getLessons()));
//        }

        PerfectHashMap perfectHashMap = new PerfectHashMap(list, searchKey);

        perfectHashMap.getHashTableSize();
        perfectHashMap.printHashTable();

        assert searchKey != null;
        System.out.println(answer);
        System.out.println(perfectHashMap.get(searchKey).getLessons());
    }

    public static String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char randomChar = DATA_FOR_RANDOM_STRING.charAt(randomCharAt);

            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }
}