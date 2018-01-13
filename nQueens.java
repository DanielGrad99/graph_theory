import java.util.*;

public class nQueens {

    static Scanner in = new Scanner(System.in);

    static int n;

    public static void main(String[] args){

        n = in.nextInt();

        Queen[] InitialState = new Queen[n];

        Queen[] Solved = Solve(InitialState);

        if (Solved == null) System.out.println("No solutions");

        else
            for (int count = 0; count < Solved.length; count++)
                System.out.println(Solved[count].xpos + "," + Solved[count].ypos);
    }

    private static ArrayList<Queen[]> PossibleMoves(Queen[] Queens){

        int xpos = 0;

        for (int count = 0; count < n; count++){
            if (Queens[count] == null){
                xpos = count +1;
                break;
            }
        }

        ArrayList<Queen[]> AllMoves = new ArrayList<Queen[]>();

        for (int count = 0; count < n; count++){

            Queen NextMove = new Queen(xpos, count+1);

            if (LegalMove(Queens, NextMove)){

                AllMoves.add(new Queen[n]);

                for (int c1 = 0; c1 < xpos -1; c1++) AllMoves.get(AllMoves.size()-1)[c1] = Queens[c1];
                AllMoves.get(AllMoves.size()-1)[xpos-1] = NextMove;
            }
        }

        return AllMoves;
    }


    private static boolean LegalMove(Queen[] Queens, Queen NextMove){

        for (int count = 0; count < n; count++){
            if (Queens[count] == null) break;

            if (Queens[count].ypos == NextMove.ypos){
                return false;
            }

            if (NextMove.xpos - Queens[count].xpos
                    == Math.abs(NextMove.ypos - Queens[count].ypos)){
                return false;
            }
        }

        return true;
    }


    private static Queen[] Solve(Queen[] Queens){

        if (Queens[n-1] == null) return Solvel(Queens);

        return Queens;
    }


    private static Queen[] Solvel(Queen[] Queens){

        ArrayList<Queen[]> AllMoves = PossibleMoves(Queens);


        for (int count = 0; count < AllMoves.size(); count++){

            Queen[] NextMove = Solve(AllMoves.get(count));

            if (NextMove != null) return NextMove;

        }

        return null;
    }
}

class Queen {

    public int xpos;
    public int ypos;

    public Queen(int xpos, int ypos){
        this.xpos = xpos;
        this.ypos = ypos;
    }

}