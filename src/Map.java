import java.util.ArrayList;

/**
 * This class represents a 2D map as a "screen" or a raster matrix or maze over integers.
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D {   //  N detailed documentation for all methods here
	private int[][] _map;
	private boolean _cyclicFlag = true;


	/**
	 * Constructs a w*h 2D raster map with an init value v.
	 *
	 * @param w
	 * @param h
	 * @param v
	 */
	public Map(int w, int h, int v) {
		init(w, h, v);
	}

	/**
	 * Constructs a square map (size*size).
	 *
	 * @param size
	 */
	public Map(int size) {
		this(size, size, 0);
	}

	/**
	 * Constructs a map from a given 2D array.
	 *
	 * @param data
	 */
	public Map(int[][] data) {
		init(data);
	}

	@Override
	public void init(int w, int h, int v) {
		// Declares a new variable of type _map, defines the size of the array according to the parameters w,v
		_map = new int[w][h];
		for (int i = 0; i < w; i++) { //  initializes its values according to v
			for (int j = 0; j < h; j++) {
				_map[i][j] = v;
			}
		}
	}

	@Override
	public void init(int[][] arr) {

		if (arr == null || arr.length == 0) throw new RuntimeException();

		int firstRow = arr[0].length;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i].length != firstRow) throw new RuntimeException();
		}

		_map = new int[arr.length][arr[0].length];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {    // Copy the values from the original array to the copy
				_map[i][j] = arr[i][j];
			}
		}

	}


	@Override
	public int[][] getMap() {

		int[][] deepCopy = new int[_map.length][];

		for (int i = 0; i < _map.length; i++) {
			deepCopy[i] = new int[_map[i].length];        // Create a new subarray for each row

			for (int j = 0; j < _map[i].length; j++) {    // Copy the values from the original array to the copy
				deepCopy[i][j] = _map[i][j];
			}
		}

		return _map;
	}

	@Override

	public int getWidth() {
		return _map.length;
	}

	@Override

	public int getHeight() {

		return _map[0].length;

	}

	@Override
	public int getPixel(int x, int y) { // Returns the int value from the x,y coordinate of the map

		return _map[x][y];
	}

	@Override

	public int getPixel(Pixel2D p) { // Returns the int value from the Pixel2D p coordinate of the map

		return _map[p.getX()][p.getY()];
	}

	@Override
	public void setPixel(int x, int y, int v) {  // Set the [x][y] coordinate of the map to v.

		_map[x][y] = v;
	}

	@Override

	public void setPixel(Pixel2D p, int v) { // Set the [x][y] coordinate of the map to v

		this.setPixel(p.getX(), p.getY(), v);
	}

	@Override
	/**
	 * Fills this map with the new color (new_v) starting from p.
	 * https://en.wikipedia.org/wiki/Flood_fill
	 */
	public int fill(Pixel2D xy, int new_v) {  // this function implements a flood fill algorithm to fill a region of pixels with a new color.

		int ans = 0;

		if (isInside(xy) == false) {
			return 0;
		}
		;
		if (this.getPixel(xy) == new_v) {
			return 0;
		}
		int color = this.getPixel(xy);
		Index2D s = new Index2D(xy);
		ArrayList<Index2D> list = new ArrayList<Index2D>();
		list.add(s);
		this.setPixel(s, new_v);

		while (list.isEmpty() == false) {// Starts from a given pixel and explores its neighbors to find all the connected pixels with the same original color.
			// It sets their color to the new color and keeps track of the number of filled pixels.
			ans++;
			s = list.remove(0);

			if (_cyclicFlag == true && s.getY() == _map[0].length - 1) {
				Index2D t = new Index2D((s.getX()), 0);
				if (isInside(t)) {
					if (this.getPixel(t) == color)
						list.add(t);
					this.setPixel(t, new_v);
				}
			} else {
				Index2D t = new Index2D((s.getX()), (s.getY() + 1));
				if (isInside(t)) {
					if (this.getPixel(t) == color)
						list.add(t);
					this.setPixel(t, new_v);
				}
			}

			if (_cyclicFlag == true && s.getY() == 0) {
				Index2D t = new Index2D((s.getX()), _map[0].length - 1);
				if (isInside(t)) {
					if (this.getPixel(t) == color)
						list.add(t);
					this.setPixel(t, new_v);
				}
			} else {
				Index2D t = new Index2D(s.getX(), s.getY() - 1);
				if (isInside(t)) {
					if (this.getPixel(t) == color)
						list.add(t);
					this.setPixel(t, new_v);
				}
			}

			if (_cyclicFlag == true && s.getX() == _map.length - 1) {
				Index2D t = new Index2D(0, s.getY());
				if (isInside(t)) {
					if (this.getPixel(t) == color)
						list.add(t);
					this.setPixel(t, new_v);
				}
			} else {
				Index2D t = new Index2D(s.getX() + 1, s.getY());
				if (isInside(t)) {
					if (this.getPixel(t) == color)
						list.add(t);
					this.setPixel(t, new_v);
				}
			}


			if (_cyclicFlag == false && s.getX() == 0) {
				Index2D t = new Index2D(_map.length - 1, s.getY());
				if (isInside(t)) {
					if (this.getPixel(t) == color)
						list.add(t);
					this.setPixel(t, new_v);
				}
			} else {
				Index2D t = new Index2D(s.getX() - 1, s.getY());
				if (isInside(t)) {
					if (this.getPixel(t) == color)
						list.add(t);
					this.setPixel(t, new_v);

				}
			}
		}
		return ans;
	}


	@Override
	/**
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor) { // computes the shortestPath between 2 given pixels

	Map2D map = this.allDistance(p1, obsColor);

	Pixel2D[] ans = new Pixel2D[map.getPixel(p2) + 1];

	Pixel2D tom = p2;
	int i = tom.getX();

	int j = tom.getY();
	int ind = map.getPixel(i, j);

	int height = map.getHeight();
	int width = map.getWidth();

	int d;

	if (map.isCyclic()) {
		while (0 <= ind) {
			d = map.getPixel(i, j);
			ans[ind--] = new Index2D(i, j);

			if (map.getPixel(i, (j + 1) % getHeight()) == d - 1) {
				j = (j + 1) % getHeight();
			} else if (map.getPixel((i + 1) % getWidth(), j) == d - 1) {
				i = (i + 1) % getWidth();
			} else if (map.getPixel(i, (j + getHeight() - 1) % getHeight()) == d - 1) {
				j = (j - 1) % getHeight();
			} else if (map.getPixel((i + getWidth() - 1) % getWidth(), j) == d - 1) {
				i = (i - 1) % getWidth();
			}
		}
	} else {
		while (0 <= ind) {
			d = map.getPixel(i, j);
			ans[ind--] = new Index2D(i, j);

			if ((j < height - 1) && (map.getPixel(i, j + 1) == d - 1)) {
				j = (j + 1);
			} else if ((i < width - 1) && (map.getPixel(i + 1, j) == d - 1)) {
				i = (i + 1);
			} else if ((j != 0) && (map.getPixel(i, j - 1) == d - 1)) {
				j = (j - 1);
			} else if ((i != 0) && (map.getPixel(i - 1, j) == d - 1)) {
				i = (i - 1);
			}
		}
	}
	return ans;
}

	@Override
	public boolean isInside(Pixel2D p) { // check if a given Pixel is inside the map
		if (p.getX() < _map.length && p.getY() < this.getHeight() && p.getX() >= 0 && p.getY() >= 0)
			return true;
		else return false;
	}

	@Override
	public boolean isCyclic() {

		return _cyclicFlag;
	}

	@Override
	public void setCyclic(boolean cy) { // sets the cyclic flag according to the given boolean

		_cyclicFlag = cy;
	}

	@Override
	public Map2D allDistance(Pixel2D start, int obsColor) {

		Map map = new Map(getMap()) ;
		for (int i = 0; i < _map.length; i++) {
			for (int j = 0; j < getHeight(); j++) {
				if (obsColor != map.getPixel(i,j)) {
					map.setPixel(i,j,-2);
				} else {
					map.setPixel(i,j,-1);
				}
			}
		}
		Index2D s = new Index2D(start);
		map.setPixel(s,0);
		ArrayList<Index2D> list = new ArrayList<Index2D>();
		list.add(s);

		while (list.isEmpty() == false) {// Starts from a given pixel and explores its neighbors to find all the connected pixels with the same original integer.
			// It sets their value to the new value according to the number of pixels from the start.
			s = list.remove(0);

			if (_cyclicFlag == true && s.getY() == _map[0].length-1) {
				Index2D t = new Index2D((s.getX()), 0);
				if (isInside(t)) {
					if (map.getPixel(t) == -2){
						list.add(t);
						map.setPixel(t.getX(),t.getY(), map.getPixel(s)+1);
					}
				}
			} else {
				Index2D t = new Index2D((s.getX()), (s.getY() + 1));
				if (isInside(t)) {
					if (map.getPixel(t) == -2){
						list.add(t);
						map.setPixel(t.getX(),t.getY(), map.getPixel(s)+1);
					}
				}
			}

			if (_cyclicFlag == true && s.getY() == 0) {
				Index2D t = new Index2D((s.getX()), map.getHeight() - 1);
				if (isInside(t)) {
					if (map.getPixel(t) == -2){
						list.add(t);
						map.setPixel(t.getX(),t.getY(), map.getPixel(s)+1);
					}}
			} else {
				Index2D t = new Index2D(s.getX(), s.getY() - 1);
				if (isInside(t)) {
					if (map.getPixel(t) == -2) {
						list.add(t);
						map.setPixel(t.getX(), t.getY(), map.getPixel(s)+1);
					}
				}
			}

			if (_cyclicFlag == true && s.getX() == _map.length - 1) {
				Index2D t = new Index2D(0, s.getY());
				if (isInside(t)) {
					if (map.getPixel(t) == -2){
						list.add(t);
						map.setPixel(t.getX(),t.getY(), map.getPixel(s)+1);
					}
				}
			} else {
				Index2D t = new Index2D(s.getX() + 1, s.getY());
				if (isInside(t)) {
					if (map.getPixel(t) == -2){
						list.add(t);
						map.setPixel(t.getX(),t.getY(), map.getPixel(s)+1);
					}
				}
			}

			if (_cyclicFlag == true && s.getX() == 0) {
				Index2D t = new Index2D(_map.length - 1, s.getY());
				if (isInside(t)) {
					if (map.getPixel(t) == -2){
						list.add(t);
						map.setPixel(t.getX(),t.getY(), map.getPixel(s)+1);
					}}
			} else {
				Index2D t = new Index2D(s.getX() - 1, s.getY());
				if (isInside(t)) {
					if (map.getPixel(t) == -2){
						list.add(t);
						map.setPixel(t.getX(),t.getY(), map.getPixel(s)+1);
					}
				}
			}
		}
		for (int i = 0; i < map.getWidth(); i++) { // Verify that none accessible entries will be marked as -1.
			for (int j = 0; j < map.getHeight(); j++) {
				if (-2 == map.getPixel(i,j))
					map.setPixel(i,j,-1) ;

			}
		}
		return map;
	}

	public Pixel2D eat(Pixel2D start, int obsColor) {
		Map2D ans = new Map(this.getMap());  // the result.


		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				if (getPixel(i, j) == obsColor) {
					ans.setPixel(i, j, -1); // obstacle
				} else if (ans.getPixel(i, j) == 3 || ans.getPixel(i, j) == 5) {
					ans.setPixel(i, j, -5); // Pink or Green point
				} else {
					ans.setPixel(i, j, -2); // path
				}
			}
		}

		ans.setPixel(start, 0);
		ArrayList<Pixel2D> list = new ArrayList<Pixel2D>();
		list.add(start);
		while (!list.isEmpty()) {
			Pixel2D current = list.remove(0);
			Pixel2D check;

			// Check neighboring cells
			check = new Index2D(current.getX(), current.getY() + 1);
			if (isInside(check) && ans.getPixel(check) < -1) {
				if (ans.getPixel(check) == -5) {
					return check;
				}
				ans.setPixel(check, ans.getPixel(current) + 1);
				list.add(check);
			}

			check = new Index2D(current.getX(), current.getY() - 1);
			if (isInside(check) && ans.getPixel(check) < -1) {
				if (ans.getPixel(check) == -5) {
					return check;
				}
				ans.setPixel(check, ans.getPixel(current) + 1);
				list.add(check);
			}

			check = new Index2D(current.getX() + 1, current.getY());
			if (isInside(check) && ans.getPixel(check) < -1) {
				if (ans.getPixel(check) == -5) {
					return check;
				}
				ans.setPixel(check, ans.getPixel(current) + 1);
				list.add(check);
			}

			check = new Index2D(current.getX() - 1, current.getY());
			if (isInside(check) && ans.getPixel(check) < -1) {
				if (ans.getPixel(check) == -5) {
					return check;
				}
				ans.setPixel(check, ans.getPixel(current) + 1);
				list.add(check);
			}
		}


		return eatHelper(start,obsColor);
	}

	private Pixel2D eatHelper (Pixel2D start , int obs) {
	Pixel2D right = new Index2D(start.getX()+1 , start.getY());
		Pixel2D left = new Index2D(start.getX()-1 , start.getY());
		Pixel2D down = new Index2D(start.getX() , start.getY()-1);
		Pixel2D up = new Index2D(start.getX() , start.getY()+1);
		if(getPixel(right) != obs){return right;}
		else if(getPixel(left) != obs){return left;}
		else if(getPixel(down) != obs){return down;}
		else {return up;}
	}


}
