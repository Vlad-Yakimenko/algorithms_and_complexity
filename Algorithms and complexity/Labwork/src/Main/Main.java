package Main;

import Trees.RedBlackTree;

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
////        List<Main.Schedule> buffer = Main.GettingSchedule.getSchedule();
////
////        for (Main.Schedule schedule : buffer) {
////            list.add(new Pair<>(schedule.getDay(), schedule.getLessons()));
////        }
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

    public static void main(String[] args) {
//        AbstractTree<Integer, String>.Node root;
//
//        root = abstractTree.new Node(7, null);
//        root.right = abstractTree.new Node(11, null);
//        root.right.parent = root;, null
//        root.right.left = abstractTree.new Node(9, null);
//        root.right.left.parent = root.right;
//        root.right.right = abstractTree.new Node(18, null);
//        root.right.right.parent = root.right;
//        root.right.right.left = abstractTree.new Node(14, null);
//        root.right.right.left.parent = root.right.right;
//        root.right.right.right = abstractTree.new Node(19, null);
//        root.right.right.right.parent = root.right.right;
//        abstractTree.leftRotate(root.right);
//        abstractTree.rightRotate(root.right);
//
//        print(root, 0);

        RedBlackTree<Integer, String> redBlackTree = new RedBlackTree<>();
        redBlackTree.insert(87, null);
        redBlackTree.insert(3, null);
        redBlackTree.insert(67, null);
        redBlackTree.insert(11, null);
        redBlackTree.insert(56, "Hello");
        redBlackTree.insert(12, null);
        redBlackTree.insert(7, null);
        redBlackTree.insert(5, "Lol");
        redBlackTree.insert(1, null);
        redBlackTree.insert(77, null);
        redBlackTree.insert(9, null);
        redBlackTree.insert(45, null);

        redBlackTree.print();
        System.out.println("______________________");

        redBlackTree.delete(87);
        redBlackTree.delete(9);
        redBlackTree.delete(5);
        redBlackTree.delete(45);
        redBlackTree.print();

        System.out.println(redBlackTree.search(56));
    }
}