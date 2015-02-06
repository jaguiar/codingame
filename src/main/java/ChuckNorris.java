import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class ChuckNorris {

    private static final char ZERO = '0';
    private static final char SPACE = ' ';
    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String MESSAGE = in.nextLine();
        char[] chars = MESSAGE.toCharArray();
        StringBuilder answer = new StringBuilder();
        int last = 2;
        for (int i = 0; i < chars.length; i++){
            int c = chars[i];
            for(int j = 64; j >= 1; j /= 2){
                int bit = (c & j) > 0 ? 1 : 0; //"bitmask"
                if(last != bit){// new "block"
                    answer.append(SPACE).append(ZERO); //0 or 1 at least one "0"
                    if(bit == 0){ //a second "0" if bit is a zero
                        answer.append(ZERO);
                    }
                    answer.append(SPACE);
                } // else continuation of the same "block"
                answer.append(ZERO);
                last = bit;
            }
        }
        System.out.println(answer.deleteCharAt(0).toString());//delete the first "space"
    }
}