package Trees;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class OrderStatisticTreeTest {

    private OrderStatisticTree<Integer, Integer> testOrderStatisticTree;
    private RedBlackTreeTestingUtils<Integer, Integer> testingUtils;
    private static Random random;

    @BeforeClass
    public static void setUpRedBlackTreeTest() {
        random = new Random();
    }

    @Before
    public void setUp() {
        testOrderStatisticTree = new OrderStatisticTree<>();
        testingUtils = new RedBlackTreeTestingUtils<>(testOrderStatisticTree);
    }

    @Test
    public void insertTest() {
        for (int i = 0; i < 1000; i++) {
            testOrderStatisticTree.insert(random.nextInt(), random.nextInt());
            assertTrue(testingUtils.checkTreeProperties((RedBlackTree<Integer, Integer>.RBNode) testOrderStatisticTree.root));
            assertTrue(testingUtils.checkTreeCorrectness((RedBlackTree<Integer, Integer>.RBNode) testOrderStatisticTree.root));
        }
    }

    @Test
    public void insertGrowingSequenceTest() {
        for (int i = 0; i < 1000; i++) {
            testOrderStatisticTree.insert(i, null);
            assertTrue(testingUtils.checkTreeProperties((RedBlackTree<Integer, Integer>.RBNode) testOrderStatisticTree.root));
            assertTrue(testingUtils.checkTreeCorrectness((RedBlackTree<Integer, Integer>.RBNode) testOrderStatisticTree.root));
        }
    }

    @Test
    public void insertFallingSequenceTest() {
        for (int i = 1000; i >= 0; i--) {
            testOrderStatisticTree.insert(i, null);
            assertTrue(testingUtils.checkTreeProperties((RedBlackTree<Integer, Integer>.RBNode) testOrderStatisticTree.root));
            assertTrue(testingUtils.checkTreeCorrectness((RedBlackTree<Integer, Integer>.RBNode) testOrderStatisticTree.root));
        }
    }

    @Test
    public void deleteTest() {
        for (int i = 0; i < 1000; i++) {
            testOrderStatisticTree.insert(random.nextInt(1000), i);
        }

        for (int i = 0; i < 500; i++) {
            Integer key = random.nextInt(1000);
            Integer valueByKey = testOrderStatisticTree.search(key);
            Integer buffer = testOrderStatisticTree.delete(key);

            assertEquals(valueByKey, buffer);

            assertTrue(testingUtils.checkTreeProperties((RedBlackTree<Integer, Integer>.RBNode) testOrderStatisticTree.root));
            assertTrue(testingUtils.checkTreeCorrectness((RedBlackTree<Integer, Integer>.RBNode) testOrderStatisticTree.root));
        }
    }

    @Test
    public void deleteInGrowingOrderTest() {
        for (int i = 0; i < 1000; i++) {
            testOrderStatisticTree.insert(i, null);
        }

        for (int i = 0; i < 1000; i++) {
            Integer key = i;
            Integer valueByKey = testOrderStatisticTree.search(key);
            Integer buffer = testOrderStatisticTree.delete(key);

            assertEquals(valueByKey, buffer);

            assertTrue(testingUtils.checkTreeProperties((RedBlackTree<Integer, Integer>.RBNode) testOrderStatisticTree.root));
            assertTrue(testingUtils.checkTreeCorrectness((RedBlackTree<Integer, Integer>.RBNode) testOrderStatisticTree.root));
        }
    }

    @Test
    public void deleteInFallingOrderTest() {
        for (int i = 0; i < 1000; i++) {
            testOrderStatisticTree.insert(i, null);
        }

        for (int i = 1000; i >= 0; i--) {
            Integer key = i;
            Integer valueByKey = testOrderStatisticTree.search(key);
            Integer buffer = testOrderStatisticTree.delete(key);

            assertEquals(valueByKey, buffer);

            assertTrue(testingUtils.checkTreeProperties((RedBlackTree<Integer, Integer>.RBNode) testOrderStatisticTree.root));
            assertTrue(testingUtils.checkTreeCorrectness((RedBlackTree<Integer, Integer>.RBNode) testOrderStatisticTree.root));
        }
    }

    @Test
    public void bigDataTest() {
        int i;

        for (i = 0; i < 100000; i++) {
            testOrderStatisticTree.insert(random.nextInt(10000), null);
        }

        for (int j = i; j >= 0; j--) {
            Integer key = random.nextInt(10000);
            Integer valueByKey = testOrderStatisticTree.search(key);
            Integer buffer = testOrderStatisticTree.delete(key);

            assertEquals(valueByKey, buffer);
        }

        try {
            assertTrue(testingUtils.checkTreeProperties((RedBlackTree<Integer, Integer>.RBNode) testOrderStatisticTree.root));
            assertTrue(testingUtils.checkTreeCorrectness((RedBlackTree<Integer, Integer>.RBNode) testOrderStatisticTree.root));
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void searchTest() {
        int key = random.nextInt(100);
        int valueBuffer = 0;

        for (int i = 0; i < 100; i++) {
            int value = random.nextInt();
            testOrderStatisticTree.insert(i, value);

            if (i == key) {
                valueBuffer = value;
            }
        }

        assertEquals((Integer) valueBuffer, testOrderStatisticTree.search(key));
    }

    @Test
    public void getRankTest() {
        for (int i = 1; i <= 100; i++) {
            testOrderStatisticTree.insert(i, null);
        }

        assertEquals(25, testOrderStatisticTree.getRank(25));
    }

    @Test
    public void getRankIncorrectKeyTest() {
        for (int i = 1; i <= 100; i++) {
            testOrderStatisticTree.insert(i, null);
        }

        assertEquals(-1, testOrderStatisticTree.getRank(1000));
    }

    @Test
    public void selectOSTTest() {
        for (int i = 1; i <= 100; i++) {
            testOrderStatisticTree.insert(i, null);
        }

        assertEquals(100, (int) testOrderStatisticTree.selectOST(100).getKey());
    }

    @Test(expected = IllegalArgumentException.class)
    public void selectOSTNegativeRankTest() {
        for (int i = 1; i <= 100; i++) {
            testOrderStatisticTree.insert(i, null);
        }

        testOrderStatisticTree.selectOST(-1);
    }

    @Test
    public void selectOSTBiggerRankTest() {
        for (int i = 1; i <= 100; i++) {
            testOrderStatisticTree.insert(i, null);
        }

        assertNull(testOrderStatisticTree.selectOST(101));
    }

    @Test
    public void equalityOfGetRankAndSelectOSTMethods() {
        for (int i = 1; i <= 100; i++) {
            testOrderStatisticTree.insert(i, null);
        }

        assertEquals(50,
                (int) testOrderStatisticTree.selectOST(testOrderStatisticTree.getRank(50)).getKey());
    }

    @Test
    public void searchNotExistKey() {
        for (int i = 0; i < 100; i++) {
            testOrderStatisticTree.insert(i, random.nextInt());
        }

        Integer key = 101;
        assertNull(testOrderStatisticTree.search(key));
    }

    @Test
    public void toStringTest() {
        OrderStatisticTree<Integer, ?> orderStatisticTree = new OrderStatisticTree<>();

        for (int i = 0; i < 50; i++) {
            orderStatisticTree.insert(i, null);
        }

        assertEquals("\n" +
                "                                                                                49=size:1\n" +
                "                                                                      48=size:2\n" +
                "                                                            47=size:4\n" +
                "                                                                      46=size:1\n" +
                "                                                  45=size:6\n" +
                "                                                            44=size:1\n" +
                "                                        43=size:10\n" +
                "                                                            42=size:1\n" +
                "                                                  41=size:3\n" +
                "                                                            40=size:1\n" +
                "                              39=size:18\n" +
                "                                                            38=size:1\n" +
                "                                                  37=size:3\n" +
                "                                                            36=size:1\n" +
                "                                        35=size:7\n" +
                "                                                            34=size:1\n" +
                "                                                  33=size:3\n" +
                "                                                            32=size:1\n" +
                "                    31=size:26\n" +
                "                                                  30=size:1\n" +
                "                                        29=size:3\n" +
                "                                                  28=size:1\n" +
                "                              27=size:7\n" +
                "                                                  26=size:1\n" +
                "                                        25=size:3\n" +
                "                                                  24=size:1\n" +
                "          23=size:34\n" +
                "                                        22=size:1\n" +
                "                              21=size:3\n" +
                "                                        20=size:1\n" +
                "                    19=size:7\n" +
                "                                        18=size:1\n" +
                "                              17=size:3\n" +
                "                                        16=size:1\n" +
                "15=size:50\n" +
                "                                        14=size:1\n" +
                "                              13=size:3\n" +
                "                                        12=size:1\n" +
                "                    11=size:7\n" +
                "                                        10=size:1\n" +
                "                              9=size:3\n" +
                "                                        8=size:1\n" +
                "          7=size:15\n" +
                "                                        6=size:1\n" +
                "                              5=size:3\n" +
                "                                        4=size:1\n" +
                "                    3=size:7\n" +
                "                                        2=size:1\n" +
                "                              1=size:3\n" +
                "                                        0=size:1", orderStatisticTree.toString());
    }
}