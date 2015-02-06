import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class ASCIIArt {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        in.nextLine();
        int H = in.nextInt();
        in.nextLine();
        String T = in.nextLine();
        String[] rows = new String[H]; 
        for (int i = 0; i < H; i++) {
            rows[i] = in.nextLine();
        }
        
        char[] textCharArrays = T.toLowerCase().toCharArray();
        String toDisplay;
        int pos;
        for (int i = 0; i < H; i++) {
            toDisplay = "";
            for (int j = 0; j< textCharArrays.length ; j++ ){
                pos = textCharArrays[j] - 'a';
                if(pos > 25 || pos < 0){
                    toDisplay += (rows[i]).substring(26*L, (26+1)*L);
                } else {
                    toDisplay += (rows[i]).substring(pos*L, (pos+1)*L);
                }
            }
            System.out.println(toDisplay);
        } 

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        
    }
}