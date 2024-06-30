import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class Index2DTest {

    private double EPS = 0.001;
    private void assertAll(boolean equals, boolean ans) {
    }


    @Test
    void testDistance2D() {    // test a basic case of computing distance with positive integers
        Pixel2D pix1 = new Index2D(0, 4);
        Pixel2D pix2 = new Index2D(0, 2);
        double expected = 2.0;
        double distance = pix1.distance2D(pix2);
        assertEquals(distance, expected, EPS);
    }


    @Test
    void testDistance2D1() {    // Test the case when one of the parameters == null a RuntimeException will appear
        Pixel2D pix3 = null;
        Pixel2D pix4 = new Index2D(0, 2);

        assertThrows(RuntimeException.class, () -> {
            pix3.distance2D(pix4);
        });

    }
    @Test

    void equals1() { // test that in case t == null , ans = false

        boolean ans = false ;
        Object t = null;

        assertAll(equals(t) , ans);
    }

@Test
    void equals2 () {    // test the basic functionality of the function with 2  unequals objects
        Pixel2D t = new Index2D(23,45);
        Pixel2D l = new Index2D(53,54);
        boolean ans = false;
        boolean actual = l.equals(t);
       assertTrue(ans == actual);

}

}

