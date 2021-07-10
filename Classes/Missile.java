//public class Missile extends Player {
//testsave
public class Missile {
    private double x;
    private double y;
    private double v;
    private double deg;
    private String f;


    public Missile() {

    }

    public Missile(double x, double y, double v, double theta, String f) {
        this.x = x;
        this.y = y;
        this.v = v;
        this.deg = theta;
        //this.f=
    }

    public void drawM() {
        StdDraw.picture(x, y, "bluelaser.png", deg);
        //StdDraw.show();
    }

    public void edrawM() {
        StdDraw.picture(x, y, "redlaser.png", deg);
        //StdDraw.show();
    }

    public void move() {
        //deg = theta;

        //tryToFire();
        x = x + -v * Math.sin(Math.toRadians(deg));
        y = y + v * Math.cos(Math.toRadians(deg));

    }


    public double getPosX() {
        return x;
    }

    public double getPosY() {

        return y;
    }

}
