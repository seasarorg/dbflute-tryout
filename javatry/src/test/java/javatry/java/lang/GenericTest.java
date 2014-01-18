package javatry.java.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class GenericTest extends UnitTryTestCase {

    public void test_EffectiveJava_Item25_Prefer_lists_to_arrays_1() {
        Object[] objectArray = new Long[1];
        try {
            objectArray[0] = "I don't fit in";
        } catch (ArrayStoreException e) {
            // OK
            Class<? extends ArrayStoreException> clazz = e.getClass();
            System.out.println(clazz.getSimpleName() + ": " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    public void test_EffectiveJava_Item25_Prefer_lists_to_arrays_2() {
        // [1] Compile Error
        // List<String>[] stringLists = new List<String>[1];
        List<String>[] stringLists = null;

        // [2]
        List<Integer> intList = Arrays.asList(42);

        // [3]
        Object[] objects = stringLists;

        try {
            // [4]
            objects[0] = intList;

            // [5]
            // Oops! Integer nanoni String!?
            String s = stringLists[0].get(0);
            assertNotNull(s);
        } catch (NullPointerException ignored) {
            // OK
        }
    }

    @SuppressWarnings("unchecked")
    public void test_EffectiveJava_Item25_Prefer_lists_to_arrays_3() {
        @SuppressWarnings("rawtypes")
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        Object result1 = reduce1(list, new FunctionA() {
            public Object apply(Object arg1, Object arg2) {
                System.out.println("result=" + arg1 + " o=" + arg2);
                return (Integer) arg1 + (Integer) arg2;
            }
        }, 42);
        System.out.println("result1=" + result1);
        Object result2 = reduce2(list, new FunctionA() {
            public Object apply(Object arg1, Object arg2) {
                System.out.println("result=" + arg1 + " o=" + arg2);
                return (Integer) arg1 + (Integer) arg2;
            }
        }, 42);
        System.out.println("result2=" + result2);
    }

    static Object reduce1(@SuppressWarnings("rawtypes") List list, FunctionA f, Object initVal) {
        synchronized (list) {
            Object result = initVal;
            for (Object o : list)
                result = f.apply(result, o);
            return result;
        }
    }

    static Object reduce2(@SuppressWarnings("rawtypes") List list, FunctionA f, Object initVal) {
        Object[] snapshot = list.toArray(); // Locks list internally
        Object result = initVal;
        for (Object o : snapshot)
            result = f.apply(result, o);
        return result;
    }

    interface FunctionA {
        Object apply(Object arg1, Object arg2);
    }

    public void test_EffectiveJava_Item25_Prefer_lists_to_arrays_4() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        Object result3 = reduce3(list, new FunctionB<Integer>() {
            public Integer apply(Integer arg1, Integer arg2) {
                System.out.println("result=" + arg1 + " o=" + arg2);
                return arg1 + arg2;
            }
        }, 42);
        System.out.println("result3=" + result3);
        Object result4 = reduce4(list, new FunctionB<Integer>() {
            public Integer apply(Integer arg1, Integer arg2) {
                System.out.println("result=" + arg1 + " o=" + arg2);
                return arg1 + arg2;
            }
        }, 42);
        System.out.println("result4=" + result4);
    }

    static <E> E reduce3(List<E> list, FunctionB<E> f, E initVal) {
        @SuppressWarnings("unchecked")
        E[] snapshot = (E[]) list.toArray(); // Locks list internally
        E result = initVal;
        for (E o : snapshot)
            result = f.apply(result, o);
        return result;
    }

    static <E> E reduce4(List<E> list, FunctionB<E> f, E initVal) {
        List<E> snapshot;
        synchronized (list) {
            snapshot = new ArrayList<E>(list); // Locks list internally
        }
        E result = initVal;
        for (E o : snapshot)
            result = f.apply(result, o);
        return result;
    }

    interface FunctionB<T> {
        T apply(T arg1, T arg2);
    }
}
