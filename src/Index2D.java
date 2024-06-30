public class Index2D implements Pixel2D{
    private int _x, _y;
    public Index2D() {this(0,0);}
    public Index2D(int x, int y) {_x=x;_y=y;}
    public Index2D(Pixel2D t) {this(t.getX(), t.getY());}
    @Override
    public int getX() {
        return _x;
    }
    @Override
    public int getY() {
        return _y;
    }

    public double distance2D(Pixel2D t) {     // This function  computes the distance between 2 pixels
        double rx = Math.abs(this._x - t.getX());
        double ry = Math.abs(this._y - t.getY());
        double ans = Math.sqrt((rx * rx) + (ry * ry));  // distance formula

        boolean b = t == null;
        if (b == true) {
            throw new RuntimeException();

        }

        return ans;
    }


    @Override
    public String toString() {   //   converts the pixels to string
        return getX() + "," + getY();
    }


    @Override
    public boolean equals(Object t) { // this function compares two objects , considering them equal if the distance is zero
                                      // assuming the second object is of type Pixel2D or its subclass.
        boolean ans = false;
        if(t != null && t instanceof Pixel2D){
            Pixel2D p = (Pixel2D)  t ;
            if (this.distance2D(p) == 0) ans = true;
            else ans = false;
        }
        return ans;
    }
}
