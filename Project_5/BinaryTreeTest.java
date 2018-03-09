import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;

public class BinaryTreeTest {
    /*
          40
      20      60
    10  30  50  70
     */
    BinaryTree<Integer, String> p1Tree = new BinaryTree<Integer, String>(new Node<Integer, String>(40, "forty",
            new Node<Integer, String>(20, "twenty",
                    new Node<Integer, String>(10, "ten"), new Node<Integer, String>(30, "thirty")),
            new Node<Integer, String>(60, "sixty",
                    new Node<Integer, String>(50, "fifty"), new Node<Integer, String>(70, "seventy"))));

    BinaryTree<Integer, String> p1Tree1 = new BinaryTree<Integer, String>(new Node<Integer, String>(40, "forty",
            new Node<Integer, String>(20, "twenty",
                    new Node<Integer, String>(10, "ten", new Node<Integer, String>(5, "five"), null), new Node<Integer, String>(30, "thirty")),
            new Node<Integer, String>(60, "sixty",
                    new Node<Integer, String>(50, "fifty"), new Node<Integer, String>(70, "seventy"))));

    BinaryTree<Integer, String> p1Tree2 = new BinaryTree<Integer, String>(new Node<Integer, String>(40, "forty",
            new Node<Integer, String>(20, "twenty",
                    new Node<Integer, String>(10, "ten"), new Node<Integer, String>(30, "thirty")),
            new Node<Integer, String>(60, "sixty",
                    new Node<Integer, String>(50, "fifty"), new Node<Integer, String>(70, "seventy", null, new Node<Integer, String>(75, "seventy-five")))));

    BinaryTree<Integer, String> p1Tree3 = new BinaryTree<Integer, String>(new Node<Integer, String>(40, "forty",
            new Node<Integer, String>(20, "twenty",
                    new Node<Integer, String>(10, "ten"), new Node<Integer, String>(30, "thirty", new Node<Integer, String>(25, "twenty-five"), null)),
            new Node<Integer, String>(60, "sixty",
                    new Node<Integer, String>(50, "fifty"), new Node<Integer, String>(70, "seventy"))));

    BinaryTree<Integer, String> p1Tree4 = new BinaryTree<Integer, String>(new Node<Integer, String>(55, "fifty-five"));

    BinaryTree<Integer, String> p1Tree5 = new BinaryTree<Integer, String>(new Node<Integer, String>(40, "forty",
            new Node<Integer, String>(20, "twenty",
                    new Node<Integer, String>(10, "ten"), new Node<Integer, String>(30, "thirty")),
            new Node<Integer, String>(60, "sixty",
                    new Node<Integer, String>(50, "updated"), new Node<Integer, String>(70, "seventy"))));

    @Test
    public void test1_1() throws Exception {
        //checks for the case where 5 is added
        p1Tree.add(5, "five");
        assert p1Tree.getRoot().equals(p1Tree1.getRoot());
    }

    @Test
    public void test1_2() throws Exception {
        //checks for the case where 75 is added
        p1Tree.add(75, "seventy-five");
        assert p1Tree.getRoot().equals(p1Tree2.getRoot());
    }

    @Test
    public void test1_3() throws Exception {
        //checks for case where 25 is added
        p1Tree.add(25, "twenty-five");
        assert p1Tree.getRoot().equals(p1Tree3.getRoot());
    }

    @Test
    public void test1_4() throws Exception {
        //checks for case where root is empty
        BinaryTree<Integer, String> tree = new BinaryTree<Integer, String>(null);
        tree.add(55, "fifty-five");
        assert tree.getRoot().equals(p1Tree4.getRoot());
    }

    @Test
    public void test1_5() throws Exception {
        //checks for case where 50 is updated
        p1Tree.add(50, "updated");
        assert p1Tree.getRoot().equals(p1Tree5.getRoot());
    }

    @Test
    public void test2_1() throws Exception {
        assert p1Tree.find(10).equals("ten");
    }

    @Test
    public void test2_2() throws Exception {
        assert p1Tree.find(5) == null;
    }

    @Test
    public void test2_3() throws Exception {
        assert p1Tree.find(70).equals("seventy");
    }

    @Test
    public void test2_4() throws Exception {
        assert p1Tree.find(35) == null;
    }

    @Test
    public void test2_5() throws Exception {
        assert p1Tree.find(40).equals("forty");
    }

    BinaryTree<Integer, Boolean> prob3_1 = new BinaryTree<Integer, Boolean>(null);
    BinaryTree<Integer, Boolean> prob3_2 = new BinaryTree<Integer, Boolean>(new Node<Integer, Boolean>(15, true));
    BinaryTree<Integer, Boolean> prob3_3 = new BinaryTree<Integer, Boolean>(new Node<Integer, Boolean>(1, true, null, new Node<Integer, Boolean>(2, true, null, new Node<Integer, Boolean>(3, true, null, new Node<Integer, Boolean>(4, true, null, new Node<Integer, Boolean>(5, false, null, null))))));

    @Test
    public void test3_1() throws Exception {
        assert Arrays.equals(prob3_1.flatten(), new Boolean[0]);
    }

    @Test
    public void test3_2() throws Exception {
        assert Arrays.equals(prob3_2.flatten(), new Boolean[] {true});
    }

    @Test
    public void test3_3() throws Exception {
        assert Arrays.equals(prob3_3.flatten(), new Boolean[] {true, true, true, true, false});
    }

    @Test
    public void test3_4() throws Exception {
        assert Arrays.equals(p1Tree5.flatten(), new String[] {"ten", "twenty", "thirty", "forty", "updated", "sixty", "seventy"});
    }

    @Test
    public void test3_5() throws Exception {
        assert Arrays.equals(p1Tree.flatten(), new String[] {"ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy"});
    }

    /*
           5
        2     8
      1   3     9
    0
     */

    BinaryTree<Integer, Character> p4Tree = new BinaryTree<Integer, Character>(new Node<Integer, Character>(5, 'a',
            new Node<Integer, Character>(2, 'd',
                    new Node<Integer, Character>(1, 'e', new Node<>(0, 'g'), null),
                    new Node<Integer, Character>(3, 'f', null, null)),
            new Node<Integer, Character>(8, 'b', null, new Node<Integer, Character>(9, 'c', null, null))));

    @Test
    public void test4_1() throws Exception {
        p4Tree.remove(-1);
        assert Arrays.equals(p4Tree.flatten(), new Character[] {'g', 'e', 'd', 'f', 'a', 'b', 'c'});
    }

    @Test
    public void test4_2() throws Exception {
        p4Tree.remove(0);
        assert Arrays.equals(p4Tree.flatten(), new Character[] {'e', 'd', 'f', 'a', 'b', 'c'});
    }

    @Test
    public void test4_3() throws Exception {
        p4Tree.remove(8);
        assert Arrays.equals(p4Tree.flatten(), new Character[] {'g', 'e', 'd', 'f', 'a', 'c'});
    }

    @Test
    public void test4_4() throws Exception {
        p4Tree.remove(1);
        assert Arrays.equals(p4Tree.flatten(), new Character[] {'g', 'd', 'f', 'a', 'b', 'c'});
    }

    @Test
    public void test4_5() throws Exception {
        p4Tree.remove(5);
        assert Arrays.equals(p4Tree.flatten(), new Character[] {'g', 'e', 'd', 'f', 'b', 'c'});
    }

    BinaryTree<Integer, String> p5Tree = new BinaryTree<Integer, String>(new Node<Integer, String>(40, "forty",
            new Node<Integer, String>(20, "twenty",
                    new Node<Integer, String>(10, "ten"), new Node<Integer, String>(30, "thirty")),
            new Node<Integer, String>(60, "sixty",
                    new Node<Integer, String>(50, "fifty"), new Node<Integer, String>(70, "seventy"))));

    BinaryTree<Integer, String> p5Tree1 = new BinaryTree<Integer, String>(new Node<Integer, String>(40, "forty",
            new Node<Integer, String>(20, "twenty",
                    new Node<Integer, String>(10, "ten"), new Node<Integer, String>(30, "thirty")),
            new Node<Integer, String>(60, "sixty",
                    new Node<Integer, String>(50, "fifty"), new Node<Integer, String>(70, "seventy"))));

    BinaryTree<Integer, String> p5Tree2 = new BinaryTree<Integer, String>(new Node<Integer, String>(40, "forty",
            new Node<Integer, String>(20, "twenty",
                    new Node<Integer, String>(60, "ten"), new Node<Integer, String>(30, "thirty")),
            new Node<Integer, String>(60, "sixty",
                    new Node<Integer, String>(50, "fifty"), new Node<Integer, String>(70, "seventy"))));

    BinaryTree<Integer, String> p5Tree3 = new BinaryTree<Integer, String>(new Node<Integer, String>(60, "sixty",
            new Node<Integer, String>(50, "fifty"), new Node<Integer, String>(70, "seventy")));

    BinaryTree<Integer, String> p5Tree4 = new BinaryTree<Integer, String>(new Node<Integer, String>(40, "forty",
            new Node<Integer, String>(20, "twenty",
                    new Node<Integer, String>(10, "fifty"), new Node<Integer, String>(30, "thirty")),
            new Node<Integer, String>(60, "sixty",
                    new Node<Integer, String>(50, "fifty"), new Node<Integer, String>(70, "seventy"))));

    BinaryTree<Integer, String> p5Tree5 = new BinaryTree<Integer, String>(null);


    @Test
    public void test5_1() throws Exception {
        assert p5Tree.containsSubtree(p5Tree1);
    }

    @Test
    public void test5_2() throws Exception {
        assert !(p5Tree.containsSubtree(p5Tree2));
    }

    @Test
    public void test5_3() throws Exception {
        assert p5Tree.containsSubtree(p5Tree3);
    }

    @Test
    public void test5_4() throws Exception {
        assert !(p5Tree.containsSubtree(p5Tree4));
    }

    @Test
    public void test5_5() throws Exception {
        assert p5Tree.containsSubtree(p5Tree5);
    }
}