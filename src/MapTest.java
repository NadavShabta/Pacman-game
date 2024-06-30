import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class MapTest {  //This JUnit class represents a JUnit for Map class



    @Test
    void init1() { // Test the new array size and compares to the expected array size

        int w = 2;
        int h = 4;
        int v = 0;
        Map map = new Map(w, h, v);
        int[][] result = map.getMap();
        int[][] expected = new int[2][4];
        assertArrayEquals(result, expected);
    }

    @Test
    void init2() { // Test the new array size, the initialized values and compares to the expected array

        int w = 2;
        int h = 4;
        int v = 1;
        Map map = new Map(w, h, v);
        int[][] result = map.getMap();
        int[][] expected = new int[2][4];
        for (int i = 0; i < w; i++) { //  initializes its values according to v
            for (int j = 0; j < h; j++) {
                expected[i][j] = v;
            }
        }
        assertArrayEquals(result, expected);
    }

    @Test
    void initArr1() {    // Test the case when the array == null a RuntimeException will appear
        int[][] arr = null;
        int[][] arr1 = new int[10][10];
        Map map = new Map(arr1);
        assertThrows(RuntimeException.class, () -> {
            map.init(arr);
        });
    }

    @Test
    void initArr2() {    // Test the case when the array is empty a RuntimeException will appear
        int[][] arr = new int[0][0];
        int[][] arr1 = new int[10][10];
        Map map = new Map(arr1);
        assertThrows(RuntimeException.class, () -> {
            map.init(arr);
        });
    }

    @Test
    void initArr3() {    // Test the case when the array is ragged a RuntimeException will appear
        int[][] arr1 = new int[10][10];
        int[][] arr = new int[3][];
        arr[0] = new int[4];
        arr[1] = new int[5];
        arr[2] = new int[2];

        Map map = new Map(arr1);
        assertThrows(RuntimeException.class, () -> {
            map.init(arr);
        });
    }

    @Test
    void initArr4() {    // Test that the copy matches the original array
        int[][] arr = new int[10][10];
        Map map = new Map(arr);
        for (int i = 0; i < 10; i++) { //  initializes its values according to 0
            for (int j = 0; j < 10; j++) {
                arr[i][j] = 0;
            }
            assertArrayEquals(map.getMap(), arr);
        }
    }

    @Test
    void getMap1() {    // Test that the deep copy matches the original array
        int[][] arr = new int[10][10];
        for (int i = 0; i < 10; i++) { //  initializes its values according to 2
            for (int j = 0; j < 10; j++) {
                arr[i][j] = 2;
            }
            Map map = new Map(arr);
            assertArrayEquals(map.getMap(), arr);
        }

    }

    @Test
    void getWidth1() {    // Test for correct width acceptance according to the given array
     int w =10 ;
     int h = 10;
        int[][] arr = new int[w][h];

        Map map = new Map(arr);
        assertEquals(w,map.getWidth());

    }

    @Test
    void getHeight1() {    // Test for correct height acceptance according to the given array
        int w =10 ;
        int h = 9;
        int[][] arr = new int[w][h];

        Map map = new Map(arr);
        assertEquals(h,map.getHeight());

    }

    @Test
    void getPixel1() {    // Test for correct int value acceptance according to the given integers
        int x =10 ;
        int y = 9;
        int[][] arr = new int[x][y];
        for (int i = 0; i < 10; i++) { //  initializes its values according to 0
            for (int j = 0; j < 9; j++) {
                arr[i][j] = 0;
            }
        Map map = new Map(arr);
        assertEquals(0,map.getPixel(4,5));

        }
    }

    @Test
    void getPixel2() {    // Test for correct int value acceptance according to the given Pixel
        Pixel2D p = new Index2D(10,9);
        int[][] arr = new int[p.getX() + 1 ][p.getY() + 1];
        for (int i = 0; i < p.getX(); i++) { //  initializes its values according to 0
            for (int j = 0; j < p.getY(); j++) {
                arr[i][j] = 0;
            }
            Map map = new Map(arr);
            assertEquals(0,map.getPixel(p));

        }
    }

    @Test
    void setPixel1() {    // Test for correct initialization for the given value at the given x,y
        int[][] arr = new int[5 ][5];
            Map map = new Map(arr);
            int x = 2;
            int y = 3;
            map.setPixel(x,y,2);
            assertEquals(2,map.getPixel(x,y));

    }

    @Test
    void setPixel2() {   // Test for correct initialization for the given value at the given Pixel
        int[][] arr = new int[5][5];
        Map map = new Map(arr);
        Pixel2D p = new Index2D(2,3);
        map.setPixel(p,2);
        assertEquals(2,map.getPixel(p));

    }

    @Test
    void isInside1 () {  // Test that with given Pixel coordinates that in the map it will return true
    Pixel2D p = new Index2D(2,5);
    int [][] arr = new int[10][10];
    Map map = new Map(arr);
    assertTrue(map.isInside(p));

    }

    @Test
    void isCyclic1 () {  // Test if function return true accordingly to the isCyclic flag
        int [][] arr = new int[1][1];
        Map map = new Map(arr);
        assertTrue(map.isCyclic());

    }

    @Test
    void setCyclic1 () {  // Test that the function update the iscycling flag accordingly to the given boolean
      boolean cy = false;
        int [][] arr = new int[1][1];
        Map map = new Map(arr);
        map.setCyclic(cy);
        assertFalse(map.isCyclic());

    }

    @Test
    void fill1() { // Test the case when the received pixel is not inside
        Pixel2D s = new Index2D(5,5);
        int[][] arr = new int [3][4];
        Map map = new Map(arr);
        assertTrue(map.fill(s,-1)==0);

    }
    @Test
    void fill2() { // Test the case when the received pixels color is equal to the new color
        Pixel2D s = new Index2D(2,3);
        int[][] arr = new int [5][5];
        Map map = new Map(arr);
        assertTrue(map.fill(s,0)==0);

    }

    @Test
    void fill3() { // Test a basic case of filling a map
        Pixel2D s = new Index2D(2,2);
        int[][] arr = new int [5][5];
        Map map = new Map(arr);
        assertTrue(map.fill(s,2)==25);

    }

    @Test
    void fill4() { // Test _CYCLIC flag on
        Pixel2D s = new Index2D(0,4);
        int[][] arr = new int [5][5];
        Map map = new Map(arr);
        assertTrue(map.fill(s,2)==25);

    }



    @Test
    void allDistance1() { // Test a basic case of using the allDistance function and verifies that the returned map is appropriate
        Pixel2D s = new Index2D(0,0);
        int[][] arr = new int [4][4];
        int[][] array = {
                {0, 1, 2, 1},
                {1, 2, 3, 2},
                {2, 3, 4, 3},
                {1, 2, 3, 2}
        };
        Map map = new Map(arr);
        Map2D allD = map.allDistance(s, -1);
   arraysEqual(array,allD.getMap());

    }

    @Test
    void allDistance2() { // Test a case(including obstacles) of using the allDistance function and verifies that the returned map is appropriate
        Pixel2D s = new Index2D(0,0);
        int[][] arr = {
                {0, 1, 2, 3, -1},
                {1, 2, -1, 4, -1},
                {2, -1, 5, 4, 3},
                {-1, 3, 4, 5, 4},
                {-1, 2, 3, 4, 5}
        };
        int[][] array = {
                {0, 0, 0, 0,-1},
                {0, 0, -1, 0,-1},
                {0, -1, 0, 0,0},
                {-1, 0, 0, 0,0},
                {-1, 0, 0, 0,0}
        };
        Map map = new Map(array);
        Map2D allD = map.allDistance(s, -1);
        arraysEqual(arr,allD.getMap());

    }


    @Test
    void allDistance3() { //  Test a case when none accessible entries should be marked -1.
        Pixel2D s = new Index2D(0,0);
        int[][] arr = {
                {0, 1, 2, 3, -1},
                {1, 2, -1, 0, -1},
                {2, -1, -1, -1, 3},
                {-1, 3, -1, 5, 4},
                {-1, 2, 3, 4, 5}
        };
        int[][] array = {
                {0, 0, 0, 0, -1},
                {0, 0, -1, 0, -1},
                {0, -1, 0, -1, 0},
                {-1, 0, -1, 0, 0},
                {-1, 0, 0, 0, 0}
        };
        Map map = new Map(array);
        Map2D allD = map.allDistance(s, -1);
        arraysEqual(arr,allD.getMap());

    }

    @Test
    void shortestPath1() { // Test a case (with obstacles) of using the allDistance function and verifies that the returned map is appropriate
        int[][] array = {
                {-1, 0, 0, 0},
                {0, -1, 0, 0},
                {0, -1, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, -1}
        };
        Pixel2D p1 = new Index2D(1, 2);
        Pixel2D p2 = new Index2D(3, 0);
        Map map = new Map(array);
        Pixel2D a = new Index2D(1,2);
        Pixel2D b = new Index2D(2,2);
        Pixel2D c = new Index2D(3,2);
        Pixel2D d = new Index2D(3,1);
        Pixel2D e = new Index2D(3,0);
        Pixel2D ans  [] = {a,b,c,d,e };

        printPixelArray(map.shortestPath(p1,p2,-1));
        printPixelArray(ans);
        comparePixelArrays(ans, map.shortestPath(p1,p2,-1));
    }
    @Test
    void shortestPath2() { // Test a basic case of using the shortestPath algorithm and verifies that the returned array is appropriate
        int[][] array = {
                {0, 1, 2, 1},
                {1, 2, 3, 2},
                {2, 3, 4, 3},
                {1, 2, 3, 2}
        };
        Pixel2D p1 = new Index2D(0, 0);
        Pixel2D p2 = new Index2D(3, 3);
        Map map = new Map(array);
        Pixel2D a = new Index2D(0,0);
        Pixel2D b = new Index2D(3,0);
        Pixel2D c = new Index2D(3,3);

        Pixel2D ans  [] = {a,b,c};
        comparePixelArrays(ans, map.shortestPath(p1,p2,-1));
        printPixelArray(map.shortestPath(p1,p2,-1));
        printPixelArray(ans);
    }



    @Test
    void shortestPath4() { // Test a case when 2 given pixels are equals
        int[][] array = {
                {-1, 0, 0, 0},
                {0, -1, 0, 0},
                {0, -1, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, -1}
        };
        Pixel2D p1 = new Index2D(0, 2);
        Pixel2D p2 = new Index2D(0, 2);
        Map map = new Map(array);
        Pixel2D ans  [] = {p1};
        comparePixelArrays(ans, map.shortestPath(p1,p2,-1));
    }





/*Auxiliary functions*/

    public boolean comparePixelArrays(Pixel2D[] array1, Pixel2D[] array2) {
        if (array1.length != array2.length) {
            return false; // Arrays have different lengths, they are not equal
        }

        for (int i = 0; i < array1.length; i++) {
            if (array1[i].getX() != array2[i].getX()){
                return false; // Pixel2D objects at index i are not equal
            }
        }

        return true; // All Pixel2D objects in the arrays are equal
    }

    public void printPixelArray(Pixel2D[] pixels) {
        if (pixels == null) {
            System.out.println("Array is null");
            return;
        }

        if (pixels.length == 0) {
            System.out.println("Array is empty");
            return;
        }

        for (int i = 0; i < pixels.length; i++) {
            System.out.println("Pixel " + i + ": " + pixels[i]);
        }
    }


    public static boolean arraysEqual(int[][] arr1, int[][] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i].length != arr2[i].length) {
                return false;
            }

            for (int j = 0; j < arr1[i].length; j++) {
                if (arr1[i][j] != arr2[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }









}



