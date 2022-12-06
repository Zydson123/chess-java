import java.util.Scanner;

public class Main {
    public static void fill_board(Piece board[][]){
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                board[i][j] = new Piece('l',false,i,j,true);
            }
        }
    }
    public static void print_board(Piece board[][]){
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
    }
    /*public int convertXCharToInt(char posX){
        int posXint=0;
        if(posX=='A'){
            posXint=0;
        }
        else if(posX=='B'){
            posXint=1;
        }
        else if(posX=='C'){
            posXint=2;
        }
        else if(posX=='D'){
            posXint=3;
        }
        else if(posX=='E'){
            posXint=4;
        }
        else if(posX=='F'){
            posXint=5;
        }
        else if(posX=='G'){
            posXint=6;
        }
        else if(posX=='H'){
            posXint=7;
        }
        return posXint;
    }*/
    public static void add_pieces(Piece board[][]){
        //white pieces
        board[7][7] = new Piece('R',true,7,7,false);
        board[7][0] = new Piece('R',true,7,0,false);
        board[7][3] = new Piece('Q',true,7,3,false);
        board[7][4] = new Piece('K',true,7,3,false);
        board[7][1] = new Piece('N',true,7,1,false);
        board[7][6] = new Piece('N',true,7,6,false);
        board[7][2] = new Piece('B',true,7,2,false);
        board[7][5] = new Piece('B',true,7,5,false);
        //black pieces
        board[0][0] = new Piece('R',false,0,0,false);
        board[0][7] = new Piece('R',false,0,7,false);
        board[0][3] = new Piece('Q',false,0,3,false);
        board[0][4] = new Piece('K',false,0,4,false);
        board[0][1] = new Piece('N',false,0,1,false);
        board[0][6] = new Piece('N',false,0,6,false);
        board[0][2] = new Piece('B',false,0,2,false);
        board[0][5] = new Piece('B',false,0,5,false);

        for(int i = 0; i< board.length; i++){
            //black pawns
            board[1][i] = new Piece('P',false,1,i,false);
            //white pawns
            board[6][i] = new Piece('P',true,6,i,false);
        }

    }
    public static void main(String[] args) {
        //white - true
        //black - false
        boolean whoseTurn = true;
        Piece[][] board = new Piece[8][8];
        fill_board(board);
        add_pieces(board);
        //pies
        System.out.println("(white - true, black - false)");
        System.out.println("Its the turn of: " + whoseTurn);
        print_board(board);
        while (true){

            System.out.println("Choose the X coordinates of your piece");
            Scanner input2 = new Scanner(System.in);
            int coordsX = input2.nextInt();

            System.out.println("Choose the Y coordinates of your piece");
            Scanner input3 = new Scanner(System.in);
            int coordsY = input3.nextInt();
            Piece piece = board[coordsY-1][coordsX-1];
            System.out.println(piece);
            System.out.println(piece.getColor());

            System.out.println("Choose the X coordinates of your move");
            Scanner input4 = new Scanner(System.in);
            coordsX = input4.nextInt();

            System.out.println("Choose the Y coordinates of your move");
            Scanner input5 = new Scanner(System.in);
            coordsY = input5.nextInt();
            piece.move_piece(piece,coordsX-1,coordsY-1,whoseTurn,board);
            if(whoseTurn==true){
                whoseTurn=false;
            }
            else{
                whoseTurn=true;
            }
            System.out.println("(white - true, black - false)");
            System.out.println("Its the turn of: " + whoseTurn);
            print_board(board);

        }
    }
}