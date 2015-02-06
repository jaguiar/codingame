import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
public class ThorPlayer {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int LX = in.nextInt(); // the X position of the light of power
        int LY = in.nextInt(); // the Y position of the light of power
        int TX = in.nextInt(); // Thor's starting X position
        int TY = in.nextInt(); // Thor's starting Y position

        int nbXmoves = LX - TX;
        int nbYmoves = LY - TY;
        String Xdirection = nbXmoves > 0 ? "E" : "W";
        String Ydirection = nbYmoves > 0 ? "S" : "N";
        
        nbXmoves = Math.abs(nbXmoves);
        nbYmoves = Math.abs(nbYmoves);
        
        
        // game loop
        while (true) {
            int E = in.nextInt(); // The level of Thor's remaining energy, representing the number of moves he can still make.

            
            if(nbXmoves == 0){
              Xdirection = "";
            } else {
              nbXmoves --;
            }

            if(nbYmoves == 0){
              Ydirection = "";
            } else {
              nbYmoves --;
            }
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            System.out.println(Ydirection+Xdirection); // A single line providing the move to be made: N NE E SE S SW W or NW
            
        }
    }
}