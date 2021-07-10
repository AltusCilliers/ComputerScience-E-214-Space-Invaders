import java.awt.Font;
import java.awt.event.KeyEvent;
//testsave

public class SpaceInvaders {
    public static final double XSCALE = 0;
    public static final double YSCALE = 0;

    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        Game game = new Game();


        StdDraw.setCanvasSize(800, 600);
        StdDraw.setXscale(-1.6, 1.6);
        StdDraw.setYscale(-1.2, 1.2);


        //set background
        //launch Game once space bar is pressed

        while ((!StdDraw.isKeyPressed(KeyEvent.VK_ENTER)) && (!StdDraw
                .isKeyPressed(KeyEvent.VK_S))) {
            // Wait for enter
            background();
        }

        // launchGame();

        while (!StdDraw.isKeyPressed(KeyEvent.VK_S)) {
            //Update();
            game.gameloop();
            if (!game.checkAlive()) {
                game.gameOver();
                game = new Game();
            }

            if (game.nextLevel()) {
                game = new Game(game.getScore(), game.getLives(), game.getLevel(),
                                game.getBunkers());
            }
        }
        System.exit(0);

    }

    private static void background() {
        //StdDraw.setCanvasSize(800, 600);
        //StdDraw.picture(XSCALE, YSCALE, "backround.png");
        StdDraw.picture(XSCALE, YSCALE, "cosmos.png");

        Font font = new Font("Times New Roman", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.text(XSCALE, YSCALE + 0.9, "SPACE CONQUISTADORS");
        font = new Font("Times New Roman", Font.BOLD, 25);
        StdDraw.setFont(font);
        StdDraw.text(XSCALE, YSCALE + 0.6, "Press Enter  to Continue");
        font = new Font("Times New Roman", Font.BOLD, 20);
        StdDraw.setFont(font);
        StdDraw.text(XSCALE, YSCALE + 0, "Shoot(SPACE)");
        StdDraw.text(XSCALE, YSCALE + -0.2, "Move: Right(Right Arrowkey) Left(Left Arrowkey");
        StdDraw.text(XSCALE, YSCALE + -0.4, "Rotate: Right(E) Left(Q)");
        StdDraw.show();
        StdDraw.pause(20);

    }


}
