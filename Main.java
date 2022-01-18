/*
    This is tic tac toe 3x3 for now, working to n x n
    Develop Logic.java for n x n
*/

import java.util.*;

public class Main {

    //Print current game table
    static void table(String[][] table){
        for(String[] row : table){
            System.out.println(Arrays.toString(row).replace(",",""));
            System.out.println();
        }
    }

    //Insert player token
    static int changeTable(char x, char y, char token, String[][]table, int[][] data){
        //Convert char to location on game table
        int X = (int) x - 64;
        int Y = (int) y - 48;

        if(table[Y][X] == "[ ]"){
            table[Y][X] = " " + token + " ";
            return data[Y-1][X-1];
        }
        //If the coordinate is taken already
        else 
            System.out.println("Coordinate taken, retry");
        return 0;
    }

    //Take user input
    static String Input(Scanner read){
        
        String input = read.nextLine();

        while(input.charAt(0) > 'C' || input.charAt(0) < 'A' || input.charAt(2) < '1' || input.charAt(2) > '3' || input.charAt(2) == '\n' || input.charAt(1) != ','){
            System.out.println("Invalid input, please try again");
            input = read.nextLine();
        }
        return input;
    }

    //Game logic
    
    //Under consideration to be replaced by Logic.java for n x n grid because current is n^3
    static Boolean logic (ArrayList<Integer> tokenData){
        if(tokenData.size() < 3)
            return false;

        Collections.sort(tokenData);
        for(int i = 0; i < tokenData.size(); i++){
            for(int j = i + 1; j < tokenData.size(); j++){
                for(int k = j + 1; k < tokenData.size(); k++){
                    if(tokenData.get(i) + tokenData.get(j) + tokenData.get(k) == 15)
                        return true;
                }}}

        return false;
    }

    //Driver code
    public static void main(String[] args){
        //Game logic table
        int[][] Data = {
                        {2,7,6},
                        {9,5,1},
                        {4,3,8}
                        };
        ArrayList<Integer> xData = new ArrayList<Integer>();
        ArrayList<Integer> oData = new ArrayList<Integer>();
        int tempData;

        //Game table
        String[][] Game = {{"   A ", " B ", " C "},
                            {"1","[ ]","[ ]","[ ]"},
                            {"2","[ ]","[ ]","[ ]"},
                            {"3","[ ]","[ ]","[ ]"}};

        //Player state
        String cross = "Lose", circle = "Lose";
        //Take user input
        Scanner read = new Scanner(System.in);
        String input;

        //Game loop
        while(cross == "Lose"  && circle == "Lose"){

            //X turn
            table(Game);
            System.out.println("X turn, enter coordinate: letter,number");
            do{
                input = Input(read);
                tempData = changeTable(input.charAt(0), input.charAt(2), 'X', Game, Data);
            } while(tempData == 0);
            xData.add(tempData);

            //Check if X wins
            if(logic(xData) == true){
                table(Game);
                System.out.println("X wins!");
                break;
            }

            //O turn
            table(Game);
            System.out.println("O turn, enter coordinate: letter,number");
            do{
                input = Input(read);
                tempData = changeTable(input.charAt(0), input.charAt(2), 'O', Game, Data);
            } while(tempData == 0);
            oData.add(tempData);

            //Check if O wins
            if(logic(oData) == true){
                table(Game);
                System.out.println("O wins!");
                break;
            }
        }

        //Clean up
        read.close();
    }
}