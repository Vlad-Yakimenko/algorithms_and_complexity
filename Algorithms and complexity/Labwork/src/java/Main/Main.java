package Main;

import PerfectHashing.PerfectHashMap;
import Trees.AbstractTree;
import Trees.OrderStatisticTree;
import Trees.RedBlackTree;
import javafx.util.Pair;

import java.security.SecureRandom;
import java.util.*;

public class Main {

//    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
//    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
//    private static final String NUMBER = "0123456789";
//
//    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
//    private static SecureRandom random = new SecureRandom();
//
//    public static void main(String[] args) {
//        Set<Schedule> set = new HashSet<>();
//
//        String searchKey = null;
//        String answer = null;
//
//        for (int i = 0; i < 20; i++) {
//            String key = generateRandomString(4);
//
//            List<String> value = new ArrayList<>();
//            value.add(generateRandomString(4));
//
//            Schedule schedule = new Schedule(key);
//            schedule.setLessons(value);
//            set.add(schedule);
//
//            if (i == 11) {
//                searchKey = key;
//                answer = value.get(0);
//            }
//        }
//
//        List<Schedule> list = new ArrayList<>(set);
//
//        List<Schedule> buffer = GettingSchedule.getSchedule();
//
//        for (Schedule schedule : buffer) {
//            list.add(new Pair<>(schedule.getDay(), schedule.getLessons()));
//        }
//
//        PerfectHashMap perfectHashMap = new PerfectHashMap(list, searchKey);
//
//        perfectHashMap.getHashTableSize();
//        perfectHashMap.printHashTable();
//
//        assert searchKey != null;
//        System.out.println(answer);
//        System.out.println(perfectHashMap.get(searchKey).getLessons());
//    }
//
//    public static String generateRandomString(int length) {
//        if (length < 1) throw new IllegalArgumentException();
//
//        StringBuilder stringBuilder = new StringBuilder(length);
//        for (int i = 0; i < length; i++) {
//            int randomCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
//            char randomChar = DATA_FOR_RANDOM_STRING.charAt(randomCharAt);
//
//            stringBuilder.append(randomChar);
//        }
//
//        return stringBuilder.toString();
//    }

//    public static void main(String[] args) {
//        RedBlackTree<Integer, String> redBlackTree = new RedBlackTree<>();
//        redBlackTree.insert(87, null);
//        redBlackTree.insert(3, null);
//        redBlackTree.insert(67, null);
//        redBlackTree.insert(11, null);
//        redBlackTree.insert(56, "Hello");
//        redBlackTree.insert(12, null);
//        redBlackTree.insert(7, null);
//        redBlackTree.insert(5, "Lol");
//        redBlackTree.insert(1, null);
//        redBlackTree.insert(77, null);
//        redBlackTree.insert(9, null);
//        redBlackTree.insert(45, null);
//
//        redBlackTree.print();
//        System.out.println("______________________");
//
//        redBlackTree.delete(87);
//        redBlackTree.delete(9);
//        redBlackTree.delete(5);
//        redBlackTree.delete(45);
//        redBlackTree.print();
//
//        System.out.println(redBlackTree.search(56));
//
//        redBlackTree.insert(11, null);
//        redBlackTree.insert(7, null);
//        redBlackTree.insert(13, null);
//        redBlackTree.insert(6, null);
//        redBlackTree.insert(4, null);
//        redBlackTree.print();
//    }

//    public static void main(String[] args) {
//        OrderStatisticTree<Integer, String> orderStatisticTree = new OrderStatisticTree<>();
//        orderStatisticTree.insert(87, null);
//        orderStatisticTree.insert(3, null);
//        orderStatisticTree.insert(67, null);
//        orderStatisticTree.insert(11, null);
//        orderStatisticTree.insert(56, "Hello");
//        orderStatisticTree.insert(12, "Gotcha!");
//        orderStatisticTree.insert(7, null);
//        orderStatisticTree.insert(5, "Lol");
//        orderStatisticTree.insert(1, null);
//        orderStatisticTree.insert(77, null);
//        orderStatisticTree.insert(9, null);
//        orderStatisticTree.insert(45, null);
//
//        orderStatisticTree.print();
//        System.out.println("______________________");
//
//        orderStatisticTree.delete(87);
//        orderStatisticTree.delete(9);
//        orderStatisticTree.delete(5);
//        orderStatisticTree.delete(45);
//        orderStatisticTree.print();
//
//        System.out.println("______________________");
//
//        System.out.println(orderStatisticTree.selectOST(orderStatisticTree.getRank(3)));
//        System.out.println(orderStatisticTree.search(56));
//    }

    public static void main(String[] args) {
//        Random random = new Random();
//        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
//        String[] disciplines = {"Programming", "Math", "English", "Ecology", "WEB", "Algorithms"};
//        int counter = 0;
//
//        for (int i = 0; i < 50; i++) {
//            if (days[i % 5].equals("Monday")) counter++;
//
//            List<String> lessons = new ArrayList<>();
//            int amountOfLessons = random.nextInt(5);
//            for (int j = 0; j < amountOfLessons; j++) {
//                lessons.add(disciplines[random.nextInt(6)]);
//            }
//
//            databaseManager.addDayToSchedule(days[i % 5] + counter, lessons.toString());
//        }

        OrderStatisticTree<String, List<String>> orderStatisticTree = new OrderStatisticTree<>();

        List<Schedule> scheduleList = GettingSchedule.getSchedule();
        for (Schedule schedule : scheduleList) {
            orderStatisticTree.insert(schedule.getDay(), schedule.getLessons());
        }

        orderStatisticTree.print();

        System.out.println(orderStatisticTree.getRank("Friday5"));
        System.out.println(orderStatisticTree.selectOST(6));
    }
}