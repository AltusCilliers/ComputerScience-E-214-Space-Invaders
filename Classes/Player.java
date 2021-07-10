import java.awt.event.KeyEvent;
//testsave

public class Player {
    private double speed = 0.04;
    private double speedb = 0.1;
    private double size = 0.2;
    private double posX = 0;
    private double posY = -1;
    public double deg = 0;
    //private double turretdeg = 0;
    // private double shotdeg = 0;
    private double theta = 5;
    public double velY = 0.1;
    public boolean shot = false;
    public double xshot;
    public double yshot;
    public double shotdeg;


    //private Missile missile = new Missile();
    //private Game game = new Game();

    //array which stores all bullet entities
    // public ArrayList<Missile> missiles = new ArrayList<Missile>();


    //public static Player(){
    //
    //}

    public double getPosX() {
        return posX;
    }

    public double getPosY() {

        return posY;
    }

    public double getSize() {
        return this.size;
    }

    public double getDeg() {

        return deg;
    }

    //public double getShotTimer() {
    //  return shottimer;
    // }

    // public void setShotTimer(int t) {
    //   this.shottimer = t;
    //}

    public void setX(double x) {
        this.posX = x;
    }

    public void setY(double y) {
        this.posY = y;
    }

    public void setAngle(double theta) {
        this.deg = theta;
    }


    public void move() {
        // change posX when key is pressed, needs to handle collision with boundaries

        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
            if (posX >= (-1.6 + size)) {
                posX = posX - speed;
                //setAngle(90);
            }
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {

            if (posX <= (1.6 - size)) {
                posX = posX + speed;
                //setAngle(-90);
            }
        }

        if (StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
            if (deg <= 90) {
                //deg = 0;
                deg = deg + theta;

            }
        }

        if (StdDraw.isKeyPressed(KeyEvent.VK_E)) {
            if (deg >= -90) {
                //deg = 0;
                deg = deg - theta;

            }


        }
    }


}





