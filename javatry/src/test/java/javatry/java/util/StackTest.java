package javatry.java.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javatry.unit.UnitTryTestCase;

public class StackTest extends UnitTryTestCase {

    public void test_iterator() {
        // ## Arrange ##
        Stack<String> stack = new Stack<String>();
        stack.add("aaa");
        stack.add("bbb");
        stack.add("ccc");

        // ## Act ##
        List<String> list = new ArrayList<String>();
        for (String element : stack) {
            list.add(element);
        }

        // ## Assert ##
        assertEquals("aaa", list.get(0));
        assertEquals("bbb", list.get(1));
        assertEquals("ccc", list.get(2));
        assertEquals(3, list.size());
    }

    public void test_asList() {
        // ## Arrange ##
        Stack<String> stack = new Stack<String>();
        stack.add("aaa");
        stack.add("bbb");
        stack.add("ccc");

        // ## Act ##
        List<String> list = stack;

        // ## Assert ##
        assertEquals("aaa", list.get(0));
        assertEquals("bbb", list.get(1));
        assertEquals("ccc", list.get(2));
        assertEquals(3, stack.size());
    }
}
