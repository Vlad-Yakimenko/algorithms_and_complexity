package Trees;

import Utils.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class AbstractBinaryTreeTest {

    private AbstractBinaryTree<Integer, Integer> testTree;
    private static Factory treeFactory;
    private static Random random;

    @BeforeClass
    public static void setUpUtils() {
        treeFactory = new SplayTreeFactory();
        random = new Random();
    }

    @Before
    public void setUp() {
        testTree = treeFactory.createBinaryTree();
    }

    @Test
    public void searchOnEmptyTree() {
        assertNull(testTree.search(1));
    }

    @Test
    public void insertTest() {
        assertTrue(testTree.isEmpty());
        testTree.insert(1, 1);
        assertFalse(testTree.isEmpty());
    }

    @Test
    public void deleteTest() {
        List<Integer> keys = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Integer key = random.nextInt();
            keys.add(key);
            testTree.insert(key, i);
        }

        assertFalse(testTree.isEmpty());

        IntStream.range(0, keys.size()).map(i -> keys.size() - 1 - i).mapToObj(keys::get).forEach(testTree::delete);

        assertTrue(testTree.isEmpty());
    }

    @Test
    public void deleteNonExistKey() {
        testTree.insert(1, 1);
        assertFalse(testTree.isEmpty());
        assertNull(testTree.delete(2));
        assertFalse(testTree.isEmpty());
    }

    @Test
    public void deleteFromEmptyTree() {
        assertTrue(testTree.isEmpty());
        assertNull(testTree.delete(1));
        assertTrue(testTree.isEmpty());
    }

    @Test
    public void deleteOneElementMultipleTimes() {
        for (int i = 0; i < 5; i++) {
            testTree.insert(i, i);
        }
        assertFalse(testTree.isEmpty());
        assertNotNull(testTree.delete(1));
        assertFalse(testTree.isEmpty());
        assertNull(testTree.delete(1));
        assertNull(testTree.delete(1));
        assertNull(testTree.delete(1));
        assertNull(testTree.delete(1));
        assertNull(testTree.delete(1));
        assertFalse(testTree.isEmpty());
    }

    @Test
    public void bigDataTest() {
        for (int i = 0; i < 1000; i++) {
            testTree.insert(i, random.nextInt());
        }

        for (int i = 0; i < 100; i++) {
            assertNotNull(testTree.search(random.nextInt(1000)));
        }

        for (int i = 0; i < 1000; i++) {
            testTree.delete(random.nextInt(1000));
        }
    }

    @Test
    public void searchInEmptyTree() {
        assertTrue(testTree.isEmpty());
        assertNull(testTree.search(1));
        assertTrue(testTree.isEmpty());
    }

    @Test
    public void searchTest() {
        int i;
        for (i = 0; i < 100; i++) {
            testTree.insert(i, 100 - i);
        }
        i -= 25;

        assertEquals(Integer.valueOf(100 - i), testTree.search(i));
    }

    @Test
    public void searchNotExistKey() {
        int i;
        for (i = 0; i < 100; i++) {
            testTree.insert(i, 100 - i);
        }

        assertNull(testTree.search(101));
    }
}