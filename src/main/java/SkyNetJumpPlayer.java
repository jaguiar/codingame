import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
public class SkyNetJumpPlayer {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt(); // the length of the road before the gap.
        int G = in.nextInt(); // the length of the gap.
        int L = in.nextInt(); // the length of the landing platform.
        
        // game loop
        int distanceToPlatform = R + G;
        int nextPosition;
        int brakingDistance;
        boolean jump = false;
        while (true) {
            int S = in.nextInt(); // the motorbike's speed.
            int X = in.nextInt(); // the position on the road of the motorbike.
            nextPosition = S + X; // without accelerating or slowing
            brakingDistance = S*(S+1)/2;
            if(jump){
              // we have already jumped
              System.out.println("SLOW");
            } else if( distanceToPlatform <= nextPosition){
              jump = true;
              System.out.println("JUMP");
            } else if( brakingDistance <= L){
              System.out.println("SPEED");
            } else if (brakingDistance > L+1){
                System.out.println("SLOW");
            } else {
              System.out.println("WAIT");
            }
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            //System.out.println("SPEED"); // A single line containing one of 4 keywords: SPEED, SLOW, JUMP, WAIT.
        }
    }
}