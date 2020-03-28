package Main;

import Heaps.AbstractHeap;
import Heaps.BinomialHeap;
import Heaps.FibonacciHeap;
import Trees.BTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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
////        DatabaseManager databaseManager = new JDBCDatabaseManager();
////        try {
////            databaseManager.connect("postgres", "password");
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////
////        Random random = new Random();
////        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
////        String[] disciplines = {"Programming", "Math", "English", "Ecology", "WEB", "Algorithms"};
////        int counter = 0;
////
////        for (int i = 0; i < 50; i++) {
////            if (days[i % 5].equals("Monday")) counter++;
////
////            List<String> lessons = new ArrayList<>();
////            int amountOfLessons = random.nextInt(5);
////            for (int j = 0; j < amountOfLessons; j++) {
////                lessons.add(disciplines[random.nextInt(6)]);
////            }
////
////            databaseManager.addDayToSchedule(days[i % 5] + counter, lessons.toString());
////        }
//
//        OrderStatisticTree<String, List<String>> orderStatisticTree = new OrderStatisticTree<>();
//
//        List<Schedule> scheduleList = GettingSchedule.getSchedule();
//        for (Schedule schedule : scheduleList) {
//            orderStatisticTree.insert(schedule.getDay(), schedule.getLessons());
//        }
//
//        orderStatisticTree.print();
//
//        System.out.println(orderStatisticTree.getRank("Friday5"));
//        System.out.println(orderStatisticTree.selectOST(6));
//    }

//    public static void main(String[] args) {
//        SplayTree<Integer, String> splayTree = new SplayTree<>();
//
//        splayTree.insert(5, null);
//        splayTree.insert(6, null);
//        splayTree.insert(7, "lol");
//        splayTree.insert(8, null);
//        splayTree.print();
//        System.out.println("----------------------------------");
//        splayTree.insert(1, null);
//        splayTree.insert(45, "something");
//        splayTree.insert(65, null);
//        splayTree.print();
//        System.out.println("----------------------------------");
//        splayTree.insert(14, null);
//        splayTree.insert(25, "hello");
//        splayTree.insert(76, null);
//        splayTree.insert(88, null);
//
//        String string = splayTree.search(25);
//        splayTree.print();
//        System.out.println("----------------------------------");
//        splayTree.search(7);
//        splayTree.print();
//        System.out.println("----------------------------------");
//        splayTree.search(14);
//        splayTree.print();
//        System.out.println("----------------------------------");
//
//
//        splayTree.delete(25);
//        splayTree.print();
//        System.out.println("----------------------------------");
//        splayTree.delete(5);
//        splayTree.print();
//        System.out.println("----------------------------------");
//        splayTree.delete(6);
//        splayTree.print();
//    }

//    public static void main(String[] args) {
//        List<Pair<Integer, Integer>> list = new ArrayList<>();
//        list.add(new Pair<>(1, null));
//        list.add(new Pair<>(2, null));
//        list.add(new Pair<>(3, null));
//        list.add(new Pair<>(4, null));
//        list.add(new Pair<>(5, 123));
//
//        double[] p = {0.15, 0.1, 0.05, 0.1, 0.2};
//        double[] q = {0.05, 0.1, 0.05, 0.05, 0.05, 0.1};
//
//        OptimalBinarySearchTree<Integer, Integer> optimalBinarySearchTree = new OptimalBinarySearchTree<>(list, p, q);
//
//        optimalBinarySearchTree.print();
//    }

//    public static void main(String[] args) {
//        AbstractHeap<Integer, String> heap = new FibonacciHeap<>(Integer.MIN_VALUE);
//
//        FibonacciHeap<Integer, String>.Node node1 = heap.insert(45, "lol");
//        FibonacciHeap<Integer, String>.Node node2 = heap.insert(43, "lol");
//        FibonacciHeap<Integer, String>.Node node3 = heap.insert(56, "lol");
//        FibonacciHeap<Integer, String>.Node node4 = heap.insert(12, "lol");
//        FibonacciHeap<Integer, String>.Node node5 = heap.insert(90, "lol");
//        FibonacciHeap<Integer, String>.Node node6 = heap.insert(1, "lol");
//        FibonacciHeap<Integer, String>.Node node7 = heap.insert(5, "lol");
//        FibonacciHeap<Integer, String>.Node node8 = heap.insert(435, "lol");
//        FibonacciHeap<Integer, String>.Node node9 = heap.insert(87, "lol");
//        FibonacciHeap<Integer, String>.Node node10 = heap.insert(58, "lol");
//        FibonacciHeap<Integer, String>.Node node11 = heap.insert(2, "lol");
//        FibonacciHeap<Integer, String>.Node node12 = heap.insert(7, "lol");
//        FibonacciHeap<Integer, String>.Node node13 = heap.insert(123, "lol");
//        FibonacciHeap<Integer, String>.Node node14 = heap.insert(212, "lol");
//        FibonacciHeap<Integer, String>.Node node15 = heap.insert(122, "lol");
//        FibonacciHeap<Integer, String>.Node node16 = heap.insert(87, "lol");
//        FibonacciHeap<Integer, String>.Node node17 = heap.insert(92, "lol");
//        FibonacciHeap<Integer, String>.Node node18 = heap.insert(78, "lol");
//
////        heap.delete(node1);
////        heap.delete(node2);
////        heap.delete(node3);
////        heap.delete(node4);
////        heap.delete(node5);
////        heap.delete(node6);
////        heap.delete(node7);
////        heap.delete(node8);
////        heap.delete(node9);
////        heap.delete(node10);
////        heap.delete(node11);
////        heap.delete(node12);
////        heap.delete(node13);
////        heap.delete(node14);
////        heap.delete(node15);
////        heap.delete(node16);
////        heap.delete(node17);
////        heap.delete(node18);
//        System.out.println(heap.extractMin());
//    }

    public static void main(String[] args) {
        BTree<Integer, String> bTree = new BTree<>(3);

        for (int i = 50; i >= 0; i--) {
            bTree.insert(i, null);
        }

//        for (int i = 0; i < 51; i++) {
//            bTree.delete(i);
//            System.out.println(bTree);
//        }
        bTree.delete(33);
        bTree.delete(24);
        bTree.delete(44);

//        bTree.insert(12, null);
//        bTree.insert(1, null);
//        bTree.insert(67, null);
//        bTree.insert(87, null);
//        bTree.insert(45, null);
//        bTree.insert(54, null);
//        bTree.insert(2, null);
//        bTree.insert(5, null);
//        bTree.insert(65, null);
//        bTree.insert(13, null);
//        bTree.insert(11, null);
//        bTree.insert(65, null);
//        bTree.insert(65, null);
//        bTree.insert(65, null);
//        bTree.insert(65, null);
//        bTree.insert(65, null);
//        bTree.insert(65, null);
//        bTree.insert(65, null);
//        bTree.insert(65, null);

//        bTree.delete(65);
//        bTree.delete(65);
//        bTree.delete(12);
//        bTree.delete(13);

        System.out.println(bTree);
    }
}