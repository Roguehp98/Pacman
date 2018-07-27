import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static String map[][] = new String[4][4];
    public static int playerX = 1;
    public static int playerY = 1;
    public static int food_1X = 0;
    public static int food_1Y = 0;
    public static int food_2X = 0;
    public static int food_2Y = 1;
    public static int food_3X = 1;
    public static int food_3Y = 0;
    public static int ghost_1X = 0;
    public static int ghost_1Y = 3;
    public static int ghost_2X = 3;
    public static int ghost_2Y = 3;
    public static String player = " P ";
    public static String ghost = " G ";
    public static String food = " F ";
    public static String empty = " - ";

    public static Boolean check = true;

    public static int eatFood = 3;

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                map[i][j] = " - ";

        map[playerX][playerY] = player;
        map[food_1X][food_1Y] = food;
        map[food_2X][food_2Y] = food;
        map[food_3X][food_3Y] = food;
        map[ghost_1X][ghost_1Y] = ghost;
        map[ghost_2X][ghost_2Y] = ghost;
        showMap();
        Point point_ghost_1;
        Point point_ghost_2;
        do {
            playerMove();
            map[ghost_1X][ghost_1Y] = empty;
            map[ghost_2X][ghost_2Y] = empty;
            point_ghost_1 = ghostMove(ghost_1X,ghost_1Y);
            ghost_1X = point_ghost_1.x;
            ghost_1Y = point_ghost_1.y;
            map[ghost_1X][ghost_1Y] = ghost;
            point_ghost_2 = ghostMove(ghost_2X,ghost_2Y);
            ghost_2X = point_ghost_2.x ;
            ghost_2Y = point_ghost_2.y;
            map[ghost_2X][ghost_2Y] = ghost;
            eatFood();
            if(eatFood == 0) {
                System.out.println(" YOU WON");
                check =false;
            }
            checkLost();
            showMap();
        } while (check);

    }

    public static void playerMove() {
        String move = scanner.nextLine();
        switch (move) {
            case "A":
                map[playerX][playerY] = " - ";
                playerY -= 1;
                map[playerX][playerY] = player;
                break;
            case "W":
                map[playerX][playerY] = " - ";
                playerX -= 1;
                map[playerX][playerY] = player;
                break;
            case "D":
                map[playerX][playerY] = " - ";
                playerY += 1;
                map[playerX][playerY] = player;
                break;
            case "S":
                map[playerX][playerY] = " - ";
                playerX += 1;
                map[playerX][playerY] = player;
                break;
        }
    }

    public static Point ghostMove(int x, int y) {
        Random random = new Random();
        int randomMove;
        randomMove = random.nextInt(4);
        map[x][y] = empty;
        switch (randomMove) {
            case 0:
                if (x - 1 >= 0)
                    x -= 1;
                else x += 1;
                break;
            case 1:
                if (x + 1 < 4)
                    x += 1;
                else x -= 1;
                break;
            case 2:
                if (y - 1 >= 0)
                    y -= 1;
                else y += 1;
                break;
            case 3:
                if (y + 1 < 4)
                    y += 1;
                else y -= 1;
                break;
        }
        map[x][y] = ghost;
        return new Point(x,y);
    }

    public static void checkLost(){
        if((playerX == ghost_1X && playerY == ghost_1Y)|| (playerX == ghost_2X && playerY == ghost_2Y)){
            System.out.println("YOU LOST");
            check = false;
        }
    }
    public static void eatFood(){
        if((playerX == food_1X && playerY == food_1Y)
                ||(playerX == food_2X && playerY == food_2Y)
                ||(playerX == food_3X && playerY == food_3Y))
            eatFood --;
    }

    public static void showMap() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
