package Trees;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class RedBlackTreeTest {

    private RedBlackTree<Integer, Integer> testRedBlackTree;
    private RedBlackTreeTestingUtils<Integer, Integer> testingUtils;
    private static Random random;

    @BeforeClass
    public static void setUpRedBlackTreeTest() {
        random = new Random();
    }

    @Before
    public void setUp() {
        testRedBlackTree = new RedBlackTree<>();
        testingUtils = new RedBlackTreeTestingUtils<>(testRedBlackTree);
    }

    @Test
    public void insertTest() {
        for (int i = 0; i < 1000; i++) {
            testRedBlackTree.insert(random.nextInt(), random.nextInt());
            assertTrue(testingUtils.checkTreeProperties((RedBlackTree<Integer, Integer>.RBNode) testRedBlackTree.root));
            assertTrue(testingUtils.checkTreeCorrectness((RedBlackTree<Integer, Integer>.RBNode) testRedBlackTree.root));
        }
    }

    @Test
    public void insertGrowingSequenceTest() {
        for (int i = 0; i < 1000; i++) {
            testRedBlackTree.insert(i, null);
            assertTrue(testingUtils.checkTreeProperties((RedBlackTree<Integer, Integer>.RBNode) testRedBlackTree.root));
            assertTrue(testingUtils.checkTreeCorrectness((RedBlackTree<Integer, Integer>.RBNode) testRedBlackTree.root));
        }
    }

    @Test
    public void insertFallingSequenceTest() {
        for (int i = 1000; i >= 0; i--) {
            testRedBlackTree.insert(i, null);
            assertTrue(testingUtils.checkTreeProperties((RedBlackTree<Integer, Integer>.RBNode) testRedBlackTree.root));
            assertTrue(testingUtils.checkTreeCorrectness((RedBlackTree<Integer, Integer>.RBNode) testRedBlackTree.root));
        }
    }

    @Test
    public void deleteTest() {
        for (int i = 0; i < 1000; i++) {
            testRedBlackTree.insert(random.nextInt(1000), null);
        }

        for (int i = 0; i < 500; i++) {
            Integer key = random.nextInt(1000);
            Integer valueByKey = testRedBlackTree.search(key);
            Integer buffer = testRedBlackTree.delete(key);

            assertEquals(valueByKey, buffer);

            assertTrue(testingUtils.checkTreeProperties((RedBlackTree<Integer, Integer>.RBNode) testRedBlackTree.root));
            assertTrue(testingUtils.checkTreeCorrectness((RedBlackTree<Integer, Integer>.RBNode) testRedBlackTree.root));
        }
    }

    @Test
    public void deleteInGrowingOrderTest() {
        for (int i = 0; i < 1000; i++) {
            testRedBlackTree.insert(i, null);
        }

        for (int i = 0; i < 1000; i++) {
            Integer key = i;
            Integer valueByKey = testRedBlackTree.search(key);
            Integer buffer = testRedBlackTree.delete(key);

            assertEquals(valueByKey, buffer);

            assertTrue(testingUtils.checkTreeProperties((RedBlackTree<Integer, Integer>.RBNode) testRedBlackTree.root));
            assertTrue(testingUtils.checkTreeCorrectness((RedBlackTree<Integer, Integer>.RBNode) testRedBlackTree.root));
        }
    }

    @Test
    public void deleteInFallingOrderTest() {
        for (int i = 0; i < 1000; i++) {
            testRedBlackTree.insert(i, null);
        }

        for (int i = 1000; i >= 0; i--) {
            Integer key = i;
            Integer valueByKey = testRedBlackTree.search(key);
            Integer buffer = testRedBlackTree.delete(key);

            assertEquals(valueByKey, buffer);

            assertTrue(testingUtils.checkTreeProperties((RedBlackTree<Integer, Integer>.RBNode) testRedBlackTree.root));
            assertTrue(testingUtils.checkTreeCorrectness((RedBlackTree<Integer, Integer>.RBNode) testRedBlackTree.root));
        }
    }

    @Test
    public void bigDataTest() {
        int i;

        for (i = 0; i < 100000; i++) {
            testRedBlackTree.insert(random.nextInt(10000), null);
        }

        for (int j = i; j >= 0; j--) {
            Integer key = random.nextInt(10000);
            Integer valueByKey = testRedBlackTree.search(key);
            Integer buffer = testRedBlackTree.delete(key);

            assertEquals(valueByKey, buffer);
        }

        try {
            assertTrue(testingUtils.checkTreeProperties((RedBlackTree<Integer, Integer>.RBNode) testRedBlackTree.root));
            assertTrue(testingUtils.checkTreeCorrectness((RedBlackTree<Integer, Integer>.RBNode) testRedBlackTree.root));
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
            testRedBlackTree.insert(i, value);

            if (i == key) {
                valueBuffer = value;
            }
        }

        assertEquals((Integer) valueBuffer, testRedBlackTree.search(key));
    }

    @Test
    public void searchNotExistKey() {
        for (int i = 0; i < 100; i++) {
            testRedBlackTree.insert(i, random.nextInt());
        }

        Integer key = 101;
        assertNull(testRedBlackTree.search(key));
    }

    @Test
    public void toStringTest() {
        RedBlackTree<Integer, ?> redBlackTree = new RedBlackTree<>();

        for (int i = 0; i < 50; i++) {
            redBlackTree.insert(i, null);
        }

        assertEquals("\n" +
                "                                                                                          null=true\n" +
                "                                                                                49=false\n" +
                "                                                                                          null=true\n" +
                "                                                                      48=true\n" +
                "                                                                                null=true\n" +
                "                                                            47=false\n" +
                "                                                                                null=true\n" +
                "                                                                      46=true\n" +
                "                                                                                null=true\n" +
                "                                                  45=true\n" +
                "                                                                      null=true\n" +
                "                                                            44=true\n" +
                "                                                                      null=true\n" +
                "                                        43=false\n" +
                "                                                                      null=true\n" +
                "                                                            42=true\n" +
                "                                                                      null=true\n" +
                "                                                  41=true\n" +
                "                                                                      null=true\n" +
                "                                                            40=true\n" +
                "                                                                      null=true\n" +
                "                              39=true\n" +
                "                                                                      null=true\n" +
                "                                                            38=true\n" +
                "                                                                      null=true\n" +
                "                                                  37=true\n" +
                "                                                                      null=true\n" +
                "                                                            36=true\n" +
                "                                                                      null=true\n" +
                "                                        35=false\n" +
                "                                                                      null=true\n" +
                "                                                            34=true\n" +
                "                                                                      null=true\n" +
                "                                                  33=true\n" +
                "                                                                      null=true\n" +
                "                                                            32=true\n" +
                "                                                                      null=true\n" +
                "                    31=false\n" +
                "                                                            null=true\n" +
                "                                                  30=true\n" +
                "                                                            null=true\n" +
                "                                        29=true\n" +
                "                                                            null=true\n" +
                "                                                  28=true\n" +
                "                                                            null=true\n" +
                "                              27=true\n" +
                "                                                            null=true\n" +
                "                                                  26=true\n" +
                "                                                            null=true\n" +
                "                                        25=true\n" +
                "                                                            null=true\n" +
                "                                                  24=true\n" +
                "                                                            null=true\n" +
                "          23=true\n" +
                "                                                  null=true\n" +
                "                                        22=true\n" +
                "                                                  null=true\n" +
                "                              21=true\n" +
                "                                                  null=true\n" +
                "                                        20=true\n" +
                "                                                  null=true\n" +
                "                    19=true\n" +
                "                                                  null=true\n" +
                "                                        18=true\n" +
                "                                                  null=true\n" +
                "                              17=true\n" +
                "                                                  null=true\n" +
                "                                        16=true\n" +
                "                                                  null=true\n" +
                "15=true\n" +
                "                                                  null=true\n" +
                "                                        14=true\n" +
                "                                                  null=true\n" +
                "                              13=true\n" +
                "                                                  null=true\n" +
                "                                        12=true\n" +
                "                                                  null=true\n" +
                "                    11=true\n" +
                "                                                  null=true\n" +
                "                                        10=true\n" +
                "                                                  null=true\n" +
                "                              9=true\n" +
                "                                                  null=true\n" +
                "                                        8=true\n" +
                "                                                  null=true\n" +
                "          7=true\n" +
                "                                                  null=true\n" +
                "                                        6=true\n" +
                "                                                  null=true\n" +
                "                              5=true\n" +
                "                                                  null=true\n" +
                "                                        4=true\n" +
                "                                                  null=true\n" +
                "                    3=true\n" +
                "                                                  null=true\n" +
                "                                        2=true\n" +
                "                                                  null=true\n" +
                "                              1=true\n" +
                "                                                  null=true\n" +
                "                                        0=true\n" +
                "                                                  null=true", redBlackTree.toString());
    }
}