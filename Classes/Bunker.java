import java.util.ArrayList;

public class Bunker {
    private double x;
    private double y;
    private String picture;

    private ArrayList<Integer> hitcount = new ArrayList<Integer>();

    public Bunker() {

    }

    public Bunker(double x, double y, String picture) {
        this.x = x;
        this.y = y;
        this.picture = picture;

        //this.f=
    }

    public boolean bunkerHitBefore(int j) {
        System.out.println(hitcount);
        if (hitcount.contains(j)) {
            return true;
        }
        else {
            hitcount.add(j);
            return false;
        }
    }

    public void drawB() {
        StdDraw.picture(x, y, picture);
        //StdDraw.show();
    }

    public void drawB(String dmgPicture) {
        StdDraw.picture(x, y, dmgPicture);
        //StdDraw.show();
    }

    public double getPosX() {
        return x;
    }

    public double getPosY() {

        return y;
    }

}
