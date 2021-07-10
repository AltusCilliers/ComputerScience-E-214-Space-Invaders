import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
//testsave

public class Game {
    public static final double XSCALE = 0;
    public static final double YSCALE = 0;
    /** Time when last shot was fired **/
    private long lastFire = 150;
    /** Time allowed between shots (in ms) **/
    private long fireTimer = 600;

    /** Time for enemy when last shot was fired **/
    private long elastFire = 150;
    /** Time allowed between shots for enemies (in ms) **/
    private long efireTimer = 1500;

    private int score;
    private int lives;
    private int level;
    private int speedUp;
    private boolean ecanshoot;
    private int prevLives;
    private boolean respawn;
    private int temp;

    private ArrayList<Enemies> enemy = new ArrayList<Enemies>();
    private ArrayList<Missile> missiles = new ArrayList<Missile>();
    private ArrayList<Missile> enemymissiles = new ArrayList<Missile>();
    private ArrayList<Bunker> bunkers = new ArrayList<Bunker>();


    private Missile rMissile = new Missile();


    private static Player player = new Player();
    private static Missile missile = new Missile();
    private static Enemies enemies = new Enemies();
    private Bunker bunker = new Bunker();


    //private static Player player;
    //construcor for Game
    public Game() {


        level = 1;
        if (level == 1) {
            setspeedUp(0);
            createEnemies(speedUp);
        }
        createBunkers();
        score = 0;
        lives = 3;
        prevLives = 3;
        respawn = false;

        // System.out.println(enemy.size());
    }

    // consutructor for new level to take arguments score,lives and level
    public Game(int score, int lives, int level, ArrayList<Bunker> bunkers) {
        // this() calls default constructor for Game, ie without arguments
        //this();
        this.score = score;
        this.lives = lives;
        this.level = level;
        this.bunkers = bunkers;

        prevLives = lives;
        respawn = false;

        if (this.level != 1) {
            setspeedUp(level);
            createEnemies(speedUp);

        }

    }


    public void gameloop() {
        collisionTest();
        inputs();
        Update();
        //draw();
        // System.out.println("test1");
        //System.out.println(enemy.size());
    }

    public void createEnemies(int l) {
        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 8; j++) {
                System.out.println(l);
                enemy.add(
                        new Enemies(-1.5 + j * ((1.6 / 10) + 0.1), 0.975 - i * ((1.2 / 10) + 0.05),
                                    (0.01 + (l * 0.01)), -90));
            }
        }

    }

    public void createBunkers() {
        //bunkers.add(new Bunker(-1, -0.74, "BunkerA.png"));
        //bunkers.add(new Bunker(-1.06, -0.74, "BunkerA.png"));
        //bunkers.add(new Bunker(-1.06, -0.68, "BunkerA.png"));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                if (i == 0) {
                    if (j == 0) {
                        System.out.println("test");
                        bunkers.add(
                                new Bunker(-1.06 + j * (0.06), -0.5 - i * (0.06), "BunkerB.png"));
                        bunkers.add(
                                new Bunker(-0.15 + j * (0.06), -0.5 - i * (0.06), "BunkerB.png"));
                        bunkers.add(
                                new Bunker(0.76 + j * (0.06), -0.5 - i * (0.06), "BunkerB.png"));
                    }
                    if (j == 5) {

                        bunkers.add(
                                new Bunker(-1.06 + j * (0.06), -0.5 - i * (0.06), "BunkerC.png"));
                        bunkers.add(
                                new Bunker(-0.15 + j * (0.06), -0.5 - i * (0.06), "BunkerC.png"));
                        bunkers.add(
                                new Bunker(0.76 + j * (0.06), -0.5 - i * (0.06), "BunkerC.png"));
                    }
                    else if ((j == 1) || (j == 2) || (j == 3) || (j == 4)) {
                        //System.out.println(bunkers.get(j).getPosX());

                        bunkers.add(
                                new Bunker(-1.06 + j * (0.06), -0.5 - i * (0.06), "BunkerA.png"));
                        bunkers.add(
                                new Bunker(-0.15 + j * (0.06), -0.5 - i * (0.06), "BunkerA.png"));
                        bunkers.add(
                                new Bunker(0.76 + j * (0.06), -0.5 - i * (0.06), "BunkerA.png"));
                    }

                }
                else {
                    if ((j != 2) && (j != 3) || (i == 1)) {

                        bunkers.add(
                                new Bunker(-1.06 + j * (0.06), -0.5 - i * (0.06), "BunkerA.png"));
                        bunkers.add(
                                new Bunker(-0.15 + j * (0.06), -0.5 - i * (0.06), "BunkerA.png"));
                        bunkers.add(
                                new Bunker(0.76 + j * (0.06), -0.5 - i * (0.06), "BunkerA.png"));
                    }
                    // System.out.println(bunkers.get(j).getPosX());

                }

            }
        }

    }


    private void draw() {
        //StdDraw.enableDoubleBuffering();

        for (int i = 0; i < enemy.size(); i++) {
            enemy.get(i).draw();

        }

        //draws User Interface
        UI(score, lives, level);

        //draws player
        if (respawn == true) {
            System.out.println("true");
            StdDraw.picture(player.getPosX(), player.getPosY(), "explosion.png");
            StdDraw.show();
            StdDraw.pause(1000);
            respawn = false;
        }
        else {
            StdDraw.picture(player.getPosX(), -1, "shooter1.png", player.getDeg());
        }

        //dtaws player missiles
        for (int i = 0; i < missiles.size(); i++) {
            // System.out.println("testdrawforloop");
            missiles.get(i).drawM();
        }

        //draws enemy missiles
        for (int i = 0; i < enemymissiles.size(); i++) {
            // System.out.println("testdrawforloop");
            enemymissiles.get(i).edrawM();
        }

        //draws bunkers
        for (int i = 0; i < bunkers.size(); i++) {
            // System.out.println("testdrawforloop");
            bunkers.get(i).drawB();
        }


        StdDraw.show();
        StdDraw.pause(5);


    }

    /*public void createMissiles() {

        missiles.add(new Missile(player.getPosX(), player.getPosY(), 0.1, player.getDeg(),
                                 "bluelaser.png"));
    }*/

    private void inputs() {


        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {

        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {

        }

        if (StdDraw.isKeyPressed(KeyEvent.VK_Q)) {

        }

        if (StdDraw.isKeyPressed(KeyEvent.VK_E)) {

        }

        if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {

            tryToFire();

        }

    }


    private static void launchGame() {
        //StdDraw.setCanvasSize(800, 600);
        //StdDraw.setXscale(-1.6, 1.6);
        //StdDraw.setYscale(-1.2, 1.2);

    }

    private void Update() {
        // move player
        player.move();

        // move all missiles
        for (int i = 0; i < missiles.size(); i++) {
            missiles.get(i).move();
        }

        // move enemy missiles
        for (int i = 0; i < enemymissiles.size(); i++) {
            enemymissiles.get(i).move();
        }

        // move all enemies
        for (int i = 0; i < enemy.size(); i++) {
            enemy.get(i).move();
        }

        // clears screen
        clear();
        // draws objects
        draw();

        // enemies shoot back code
        if (enemy.size() != 0) {
            Enemies rEnemy = enemy.get((int) (Math.random() * enemy.size()));
            rMissile = new Missile(rEnemy.getPosX(), rEnemy.getPosY() - 0.176, 0.1, 180,
                                   "redlaser.png");
            etryToFire();


            if (ecanshoot) {
                enemymissiles.add(rMissile);
            }
        }


        //enemymissiles.add(new Missile(0.5, 0.5, 0.1, 180, "redlaser.png"));

        //enemymissiles.add(new Missile(rEnemy.getPosX(), rEnemy.getPosY(), 0.1, 180, "red laser.png"));


        //drawPlayer();

        // drawMissile();
        // drawEnemies();

    }

    private void collisionTest() {
        // tests if missile hits the edges of screen and removes entity if it does so
        for (int i = 0; i < missiles.size(); i++) {

            if ((missiles.size() != 0) && ((missiles.get(i).getPosX() <= -1.6) || (
                    missiles.get(i).getPosX() >= 1.6) || (missiles.get(i).getPosY() <= -1.2) || (
                    missiles.get(i).getPosY() >= 1.2))) {
                missiles.remove(i);
            }
        }

        // tests if collision happens between missile and enemy
        // removes both missile and enemy if true
        for (int i = 0; i < missiles.size(); i++) {
            for (int j = 0; j < enemy.size(); j++) {
                if ((enemy.size() != 0) && (missiles.size() != 0) && (
                        ((missiles.get(i).getPosX() - 0.1) <= (enemy.get(j).getPosX())
                                && (missiles.get(i).getPosX() + 0.1) >= (enemy.get(j).getPosX())
                                && ((missiles.get(i).getPosY() - 0.1) <= (enemy.get(j).getPosY())
                                && (missiles.get(i).getPosY() + 0.1) >= (enemy.get(j)
                                                                              .getPosY()))))) {
                    missiles.remove(i);
                    enemy.remove(j);
                    StdAudio.play("hit.wav");
                    score += 10;
                    break;


                }
            }
        }

        //tests if enemy missile hits player
        if (enemymissiles.size() != 0) {
            for (int i = 0; i < enemymissiles.size(); i++) {

                if ((enemymissiles.get(i).getPosX() - 0.03) <= (player.getPosX())
                        && (enemymissiles.get(i).getPosX() + 0.03) >= (player.getPosX()) && (
                        (enemymissiles.get(i).getPosY() - 0.1) <= (player.getPosY())
                                && (enemymissiles.get(i).getPosY() + 0.1) >= (player.getPosY()))) {
                    enemymissiles.remove(i);
                    lives--;
                    respawn(lives);
                    StdAudio.play("lifelost.wav");
                    break;


                }

            }
        }

        // tests if enemy missile hits bunker
        if ((enemymissiles.size() != 0) && (bunkers.size() != 0)) {

            for (int i = 0; i < enemymissiles.size(); i++) {
                for (int j = 0; j < bunkers.size(); j++) {

                    if ((enemymissiles.get(i).getPosX() - 0.031) <= (bunkers.get(j).getPosX())
                            && (enemymissiles.get(i).getPosX() + 0.031) >= (bunkers.get(j)
                                                                                   .getPosX())
                            && (
                            (enemymissiles.get(i).getPosY() - 0.1) <= (bunkers.get(j).getPosY())
                                    && (enemymissiles.get(i).getPosY() + 0.1) >= (bunkers.get(j)
                                                                                         .getPosY()))) {
                        enemymissiles.remove(i);
                        //bunkers.add(new Bunker(bunkers.get(j).getPosX(), bunkers.get(j).getPosY(),
                        //                       "BunkerA1.png"));
                        //bunkers.get(j).drawB();
                        bunkers.remove(j);

                        //StdAudio.play("lifelost.wav");
                        break;


                    }


                }
            }

        }

        // tests if player missile hits bunker
        if ((missiles.size() != 0) && (bunkers.size() != 0)) {

            for (int i = 0; i < missiles.size(); i++) {
                for (int j = bunkers.size() - 1; j >= 0; j--) {
                    System.out.println(bunkers.size());
                    if ((missiles.get(i).getPosX() - 0.031) <= (bunkers.get(j).getPosX())
                            && (missiles.get(i).getPosX() + 0.031) >= (bunkers.get(j).getPosX())
                            && ((missiles.get(i).getPosY() + 0.031) >= (bunkers.get(j).getPosY())
                            && (missiles.get(i).getPosY() - 0.031) <= (bunkers.get(j).getPosY()))) {

                        bunkers.remove(j);
                        missiles.remove(i);


                        /*if (bunker.bunkerHitBefore(j)) {
                            temp = j;
                            bunkers.remove(j);
                        }
                        bunkers.add(new Bunker(bunkers.get(j).getPosX(), bunkers.get(j).getPosY(),
                                               "BunkerA1.png"));
                        bunkers.get(j).drawB();
                        //bunkers.remove(j);
                        //StdAudio.play("lifelost.wav");*/
                        break;


                    }


                }
            }

        }


        // tests if enemies get near edges of screen
        for (int i = 0; i < enemy.size(); i++) {
            for (int j = 0; j < enemy.size(); j++) {
                if (enemy.get(i).getPosX() + 0.02 >= 1.55) {
                    enemy.get(j).setAngle(90);
                    enemy.get(j).setY(enemy.get(j).getPosY() - 0.04);
                }

                if (enemy.get(i).getPosX() - 0.02 <= -1.55) {
                    enemy.get(j).setAngle(-90);
                    enemy.get(j).setY(enemy.get(j).getPosY() - 0.04);
                }
            }
        }

        // tests if enemies touch the player or the floor
        for (int i = 0; i < enemy.size(); i++) {
            for (int j = 0; j < enemy.size(); j++) {
                if ((enemy.get(i).getPosY() - 0.1) <= (player.getPosY())
                        && (enemy.get(i).getPosY() + 0.1) >= (player.getPosY()) || (
                        enemy.get(i).getPosY() <= -1.3)) {
                    lives = -1;
                }
            }
        }

        // tests if enemy bullet can be fired ie if no enemies are below it
        /*ecanshoot = true;
        for (int i = 0; i < enemy.size(); i++) {
            for (int j = 0; j < enemy.size(); j++) {
                if ((rMissile.getPosY() - 0.175) <= (enemy.get(i).getPosY())
                        && ((rMissile.getPosY() + 0.175)) >= (enemy.get(i).getPosY())) {

                    ecanshoot = false;
                    break;
                }

            }
        }*/


    }

    //Got timer idea from http://www.cokeandcode.com/info/tut2d.html
    public void tryToFire() {
        long currentfireTimer = System.currentTimeMillis() - lastFire;

        // if we are trying to shoot again in less time than allowed, do nothing
        if (currentfireTimer < fireTimer) {
            return;
        }

        // if we're OK to fire, update last fire time to current time
        lastFire = System.currentTimeMillis();

        // create shot entity and add it to entity list
        missiles.add(new Missile(player.getPosX(), player.getPosY(), 0.075, player.getDeg(),
                                 "bluelaser.png"));
        StdAudio.play("shoot.wav");

    }

    public void etryToFire() {
        long ecurrentfireTimer = System.currentTimeMillis() - elastFire;

        // if we are trying to shoot again in less time than allowed, do nothing
        if (ecurrentfireTimer < efireTimer) {
            ecanshoot = false;
            return;
        }

        // if we're OK to fire, update last fire time to current time
        elastFire = System.currentTimeMillis();

        // create shot entity and add it to entity list
        ecanshoot = true;


    }





   /* private static void drawPlayer() {
        //display player: this.player.getPosX();


        StdDraw.enableDoubleBuffering();

        StdDraw.picture(player.getPosX(), -1, "shooter1.png", player.getDeg());

        StdDraw.show();
        StdDraw.pause(5);


    }*/


    public boolean checkAlive() {
        if (lives >= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void gameOver() {
        Font font = new Font("Times New Roman", Font.BOLD, 50);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.text(XSCALE, YSCALE, "GAME OVER");
        StdDraw.picture(player.getPosX(), player.getPosY(), "explosion.png");
        StdAudio.play("gameover.wav");
        StdDraw.show();
        StdDraw.pause(5000);

    }

    public boolean nextLevel() {
        if (enemy.size() == 0) {
            level++;
            speedUp++;
            return true;
        }
        else {
            return false;
        }
    }

    public void respawn(int life) {

        if (life < prevLives) {
            prevLives = life;
            System.out.println("respawn");
            respawn = true;

        }

    }

    public void UI(int score, int lives, int level) {
        Font font = new Font("Times New Roman", Font.BOLD, 20);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.text(XSCALE, YSCALE + 1.1, "SCORE " + score + "                   LIVES " + lives
                + "                 LEVEL " + level);

    }

    private static void clear() {

        StdDraw.picture(XSCALE, YSCALE, "cosmos.png");
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public int getLevel() {
        return level;
    }

    public ArrayList<Bunker> getBunkers() {
        return bunkers;
    }

    public int setspeedUp(int v) {
        this.speedUp = v;
        return this.speedUp;
    }


}



