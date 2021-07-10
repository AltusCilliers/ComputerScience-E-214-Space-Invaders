public class Enemies {
    //testsave
    private double x;
    private double y;
    private double v;
    private double deg;

    //public ArrayList<Enemies> enemy = new ArrayList<Enemies>();


    public Enemies() {

    }

    public Enemies(double x, double y, double v, double theta) {
        this.x = x;
        this.y = y;
        this.v = v;
        System.out.println(this.v);
        this.deg = theta;

    }

    public void draw() {
        //StdDraw.setPenColor(StdDraw.BLUE);
        //StdDraw.filledCircle(x, y, v);
        StdDraw.picture(x, y, "ufo.png", 1.6 / 10, 1.2 / 10);
        //System.out.println("test2");
    }

    public void move() {
        double xMove = -v * Math.sin(Math.toRadians(deg));
        //double yMove = v * Math.sin(Math.toRadians(-90));

        x = x + xMove;


    }

    public void setAngle(double theta) {
        this.deg = theta;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getPosX() {
        return x;
    }

    public double getPosY() {

        return y;
    }


}
