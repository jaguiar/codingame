import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class DescentePlayer {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        String action;
        // game loop
        while (true) {
            int SX = in.nextInt();
            int SY = in.nextInt();
            int maxHeight = 0;
            int maxSX = 0;
            for (int i = 0; i < 8; i++) {
                int MH = in.nextInt(); // represents the height of one mountain, from 9 to 0. Mountain heights are provided from left to right.
                if( MH > maxHeight ){
                    maxHeight = MH;
                    maxSX = i;
                }
            }
            
            action = maxSX == SX ? "FIRE" : "HOLD";
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            System.out.println(action); // either:  FIRE (ship is firing its phase cannons) or HOLD (ship is not firing).
        }
    }
}