package kgmyshin.sample;

import org.junit.Test;

import java.util.Arrays;

import kgmyshin.marray.Marray;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class MarrayTest {

    @Test
    public void testConstructor() {
        Marray<String> marray = new Marray(Arrays.asList("A", "B", "C"));

        assertThat("A", is(marray.get(0)));
        assertThat("B", is(marray.get(1)));
        assertThat("C", is(marray.get(2)));
    }

    @Test
    public void testAny() {
        Marray<String> marray = new Marray(Arrays.asList("ABC", "DDDD", "EEEEE"));

        boolean correct = marray.any(new Marray.CondOpe<String>() {
            @Override
            public boolean cond(String s) {
                return s.length() > 4;
            }
        });
        assertThat(true, is(correct));

        boolean wrong = marray.any(new Marray.CondOpe<String>() {
            @Override
            public boolean cond(String s) {
                return s.length() > 5;
            }
        });
        assertThat(false, is(wrong));
    }

    @Test
    public void testAll() {
        Marray<String> marray = new Marray(Arrays.asList("ABC", "DDDD", "EEEEE"));

        boolean correct = marray.all(new Marray.CondOpe<String>() {
            @Override
            public boolean cond(String s) {
                return s.length() > 2;
            }
        });
        assertThat(true, is(correct));

        boolean wrong = marray.all(new Marray.CondOpe<String>() {
            @Override
            public boolean cond(String s) {
                return s.length() > 3;
            }
        });
        assertThat(false, is(wrong));
    }

    @Test
    public void testMap() {
        Marray<Integer> marray = new Marray(Arrays.asList(1, 2, 5));

        Marray<Integer> mapped = marray.map(new Marray.MapOpe<Integer, Integer>() {
            @Override
            public Integer func(Integer integer) {
                return integer * 2;
            }
        });

        assertThat(2, is(mapped.get(0)));
        assertThat(4, is(mapped.get(1)));
        assertThat(10, is(mapped.get(2)));
    }

    @Test
    public void testFilter() {
        Marray<Integer> marray = new Marray(Arrays.asList(15, 9, 15, 30, 2));

        Marray<Integer> filtered = marray.filter(new Marray.CondOpe<Integer>() {
            @Override
            public boolean cond(Integer integer) {
                return integer > 10;
            }
        });

        assertThat(15, is(filtered.get(0)));
        assertThat(15, is(filtered.get(1)));
        assertThat(30, is(filtered.get(2)));
    }

    @Test
    public void testJoin() {
        Marray<Integer> marray = new Marray(Arrays.asList(15, 9, 15, 30, 2));

        assertThat("15,9,15,30,2", is(marray.join(",")));
    }

    @Test
    public void testReduce() {
        Marray<Integer> marray = new Marray(Arrays.asList(1, 2, 3, 4, 5));

        int result = marray.reduce(new Marray.ReduceOpe<Integer>() {
            @Override
            public Integer func(Integer x, Integer y) {
                return x + y;
            }
        });

        assertThat(15, is(result));
    }

    @Test
    public void testNumbers() {
        Marray<Integer> marray = new Marray<>();
        for (int i = 0; i < 50; i++) {
            marray.add(i * 3);
        }

        assertThat(0, is(marray.zero()));
        assertThat(3, is(marray.one()));
        assertThat(6, is(marray.two()));
        assertThat(9, is(marray.three()));
        assertThat(12, is(marray.four()));
        assertThat(15, is(marray.five()));
        assertThat(126, is(marray.fortyTwo()));
    }

}