import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Piece {
    private char type;
    private boolean color;
    private int posX;
    private int posY;

    private int id;
    private boolean isFiller;
    private boolean didMove;
    public Piece lastTaken;
    private boolean isUsed;
    private ArrayList<Piece> blockades = new ArrayList<Piece>();
    private ArrayList<Piece> blockades2 = new ArrayList<Piece>();
    private ArrayList<Piece> blockades3 = new ArrayList<Piece>();
    private ArrayList<Piece> blockades4 = new ArrayList<Piece>();
    private ArrayList<Piece> blockades5 = new ArrayList<Piece>();
    private ArrayList<Piece> blockades6 = new ArrayList<Piece>();

    public Piece(char type, boolean color, int posX, int posY, boolean isFiller) {
        this.id++;
        this.type = type;
        this.color = color;
        this.posX = posX;
        this.posY = posY;
        this.isFiller = isFiller;
        this.didMove=false;
    }
    public boolean check_legal(Piece piece, Piece board[][],int posXofMove,int posYofMove){
        boolean isLegal=false;
        piece.setUsed(true);
        this.blockades.clear();
        this.blockades2.clear();
        this.blockades3.clear();
        this.blockades4.clear();
        this.blockades5.clear();
        this.blockades6.clear();
        /*
        System.out.println("Pos X of piece: " + piece.getPosY());
        System.out.println("Pos Y of piece: " + piece.getPosX());
        System.out.println("pos X of move: " + posXofMove);
        System.out.println("pos Y of move: " + posYofMove);
        System.out.println("Type of target square: " + board[posYofMove][posXofMove]);
        */
        if(piece.getType()=='P' && piece.getColor()==true){
            System.out.println(piece.getPosY());
            System.out.println(posXofMove);
            System.out.println(posYofMove);
            System.out.println(piece.getPosX());
            //System.out.println(board[piece.getPosY()][posYofMove].getType());
            //pies
            System.out.println(board[posYofMove][piece.getPosY()].getType());
            System.out.println(board[posYofMove][posXofMove].getType());
            //go forward by two
            if(piece.getPosX()==6 && 6-posYofMove==2 && piece.getPosY()==posXofMove && board[posYofMove][posXofMove].getType()=='l'){
                isLegal = true;
            }
            //go forward by one
            else if(piece.getPosX()-posYofMove==1 && piece.getPosY()==posXofMove && board[posYofMove][posXofMove].getType()=='l'){
                isLegal = true;
            }
            //takes
            else if(piece.getPosX()-posYofMove==1 && board[posYofMove][posXofMove].getType()!='l' && posXofMove!=piece.getPosY() && board[posYofMove][posXofMove].getColor()!=piece.getColor()){
                isLegal = true;
                board[0][0].lastTaken = board[posYofMove][posXofMove];
            }
            else{
                isLegal=false;
            }
        }
        else if(piece.getType()=='P' && piece.getColor()==false){
            System.out.println(piece.getPosY());
            System.out.println(posXofMove);
            System.out.println(posYofMove);
            System.out.println(piece.getPosX());
            //System.out.println(board[piece.getPosY()][posYofMove].getType());
            System.out.println(board[posYofMove][piece.getPosY()].getType());
            System.out.println(board[posYofMove][posXofMove].getType());
            //go forward by two
            if(piece.getPosX()==1 && piece.getPosX()+posYofMove==4 && piece.getPosY()==posXofMove && board[posYofMove][posXofMove].getType()=='l'){
                isLegal = true;
            }
            //go forward by one
            else if(posYofMove-piece.getPosX()==1 && piece.getPosY()==posXofMove && board[posYofMove][posXofMove].getType()=='l'){
                isLegal = true;
            }
            //takes
            else if(posYofMove-piece.getPosX()==1 && board[posYofMove][posXofMove].getType()!='l' && board[posYofMove][posXofMove].getColor()!=piece.getColor()){
                isLegal = true;
                board[0][0].lastTaken = board[posYofMove][posXofMove];
            }
            else{
                isLegal=false;
            }
        }
        else if(piece.getType()=='N'){
            /*System.out.println("X of piece: " + piece.getPosY()); //X
            System.out.println("Y of piece: " + piece.getPosX()); //Y
            //for some reason X and Y ^^ are inverted here, god knows why
            System.out.println("pos x of move: " + posXofMove); //X of where you want to go
            System.out.println("pos y of move: " + posYofMove); //Y of where you want to go
            System.out.println(posXofMove-piece.getPosY());
            System.out.println(piece.getPosX()-posYofMove);
            System.out.println(board[posYofMove][posXofMove].getType()); //the type of piece on the place you want to go*/
            //if its filler and goes forward and is on the right side of the board
            if(board[posYofMove][posXofMove].getType()=='l' && piece.getPosX()-posYofMove==2 && piece.getPosY()-posXofMove==1){
                isLegal=true;
            }
            //if its filler and goes right
            else if(board[posYofMove][posXofMove].getType()=='l' && posXofMove-piece.getPosY()==2 && piece.getPosX()-posYofMove==1){
                isLegal=true;
            }
            //if its filler and goes left
            else if(board[posYofMove][posXofMove].getType()=='l' && posXofMove-piece.getPosY()==-2 && piece.getPosX()-posYofMove==1){
                isLegal=true;
            }
            //if its filler and goes right back
            else if(board[posYofMove][posXofMove].getType()=='l' && posXofMove-piece.getPosY()==2 && piece.getPosX()-posYofMove==-1){
                isLegal=true;
            }
            //if takes and goes right back
            else if(board[posYofMove][posXofMove].getType()!='l' && board[posYofMove][posXofMove].getColor()!=piece.getColor() && posXofMove-piece.getPosY()==2 && piece.getPosX()-posYofMove==-1){
                isLegal=true;
                board[0][0].lastTaken = board[posYofMove][posXofMove];
            }
            //if its filler and goes left back
            else if(board[posYofMove][posXofMove].getType()=='l' && posXofMove-piece.getPosY()==-2 && piece.getPosX()-posYofMove==-1){
                isLegal=true;
            }
            //if takes and goes left back
            else if(board[posYofMove][posXofMove].getType()!='l' && board[posYofMove][posXofMove].getColor()!=piece.getColor() && posXofMove-piece.getPosY()==-2 && piece.getPosX()-posYofMove==-1){
                isLegal=true;
                board[0][0].lastTaken = board[posYofMove][posXofMove];
            }
            //if takes and goes left
            else if(board[posYofMove][posXofMove].getType()!='l' && board[posYofMove][posXofMove].getColor()!=piece.getColor() && posXofMove-piece.getPosY()==-2 && piece.getPosX()-posYofMove==1){
                isLegal=true;
                board[0][0].lastTaken = board[posYofMove][posXofMove];
            }
            //if takes and goes right
            else if(board[posYofMove][posXofMove].getType()!='l' && board[posYofMove][posXofMove].getColor()!=piece.getColor() && posXofMove-piece.getPosY()==2 && piece.getPosX()-posYofMove==1){
                isLegal=true;
                board[0][0].lastTaken = board[posYofMove][posXofMove];
            }
            //if its filler and goes forward and is on the left side of the board
            else if(board[posYofMove][posXofMove].getType()=='l' && piece.getPosX()-posYofMove==2 && posXofMove-piece.getPosY()==1){
                isLegal=true;
            }
            //if its filler and goes backwards and knight is on left side of the board
            else if(board[posYofMove][posXofMove].getType()=='l' && piece.getPosY()-posXofMove==1 && posYofMove-piece.getPosX()==2){
                isLegal=true;
            }
            //if its filler and goes backwards and knight is on left side of the board
            else if(board[posYofMove][posXofMove].getType()=='l' && posXofMove-piece.getPosY()==1 && posYofMove-piece.getPosX()==2){
                isLegal=true;
            }
            //if takes and forward and knight is on right side of board
            else if(board[posYofMove][posXofMove].getType()!='l' && board[posYofMove][posXofMove].getColor()!=piece.getColor() && piece.getPosX()-posYofMove==2 && piece.getPosY()-posXofMove==1){
                isLegal=true;
                board[0][0].lastTaken = board[posYofMove][posXofMove];
            }
            //if takes and forward and knight is on left side of board
            else if(board[posYofMove][posXofMove].getType()!='l' && board[posYofMove][posXofMove].getColor()!=piece.getColor() && piece.getPosX()-posYofMove==2 && posXofMove-piece.getPosY()==1){
                isLegal=true;
                board[0][0].lastTaken = board[posYofMove][posXofMove];
            }
            //if takes and backwards and knight is on right side of the board
            else if(board[posYofMove][posXofMove].getType()!='l' && board[posYofMove][posXofMove].getColor()!=piece.getColor() && piece.getPosY()-posXofMove==1 && piece.getPosX()-posYofMove==2){
                isLegal=true;
                board[0][0].lastTaken = board[posYofMove][posXofMove];
            }
            //if takes and backwards and knight is on left side of the board
            else if(board[posYofMove][posXofMove].getType()!='l' && board[posYofMove][posXofMove].getColor()!=piece.getColor() && piece.getPosY()-posXofMove==1 && posYofMove-piece.getPosX()==2){
                isLegal=true;
                board[0][0].lastTaken = board[posYofMove][posXofMove];
            }
        }
        else if(piece.getType()=='R'){
            int FbX = 0;
            int FbY = 0;
            int FbX2 = 0;
            int FbY2 = 0;
            int i = 0;
            int j=0;

            while(j!=8){
                /*System.out.println("X of checking: " + j);
                System.out.println("Y of checking: " + piece.getPosY());*/
                if(!board[j][piece.getPosY()].isUsed() && board[j][piece.getPosY()].getType()!='l'){
                    this.blockades5.add(board[j][piece.getPosY()]);
                    FbX = j;
                    FbY = piece.getPosY();
                }
                j++;
            }
            while(i!=8){
                /*System.out.println("i: " + i);
                System.out.println("Y of checking: " + piece.getPosX());*/
                if(!board[piece.getPosX()][i].isUsed() && board[piece.getPosX()][i].getType()!='l'){
                    this.blockades6.add(board[piece.getPosX()][i]);
                    FbX2 = piece.getPosX();
                    FbY2 = i;
                }
                i++;
            }
            i=0;
            j=0;
            Piece XblockadeFriendly = board[0][0];
            Piece YblockadeFriendly = board[0][0];
            if(this.blockades5.size()==0){

            }
            else if(this.blockades5.size()==1){
                XblockadeFriendly = this.blockades5.get(0);
            }
            else{
                XblockadeFriendly = this.blockades5.get(this.blockades5.size()-1);
            }
            if(this.blockades6.size()==0){

            }
            else if(this.blockades6.size()==1){
                YblockadeFriendly = this.blockades6.get(0);
            }
            else{
                YblockadeFriendly = this.blockades6.get(this.blockades6.size()-1);
            }
            //if goes up and there is blockade
            if(
                    posXofMove == piece.getPosY()
                    && posYofMove != piece.getPosX()
                    && piece.getPosX() - posYofMove > 0
                    && posYofMove - XblockadeFriendly.getPosX() < 0
                    && XblockadeFriendly.getPosX() < piece.getPosX()
                    && this.blockades5.size()!=0
            )
            {
                isLegal=false;
            }
            //if goes down but there is blockade
            else if(
                            posXofMove == piece.getPosY()
                            && posYofMove != piece.getPosX()
                            && piece.getPosX() - posYofMove < 0
                            && posYofMove - XblockadeFriendly.getPosX() > 0
                            && XblockadeFriendly.getPosX() > piece.getPosX()
                            && this.blockades5.size()!=0
            )
            {
                isLegal=false;
            }
            //if goes right and there is blockade
            else if(
                            posXofMove != piece.getPosY()
                            && posYofMove == piece.getPosX()
                            && posXofMove - piece.getPosY() > 0
                            && posYofMove - YblockadeFriendly.getPosX() == 0
                            && YblockadeFriendly.getPosX() == piece.getPosX()
                            && posXofMove - YblockadeFriendly.getPosY() > 0
                            && piece.getPosY() - YblockadeFriendly.getPosY() < 0
                            && this.blockades6.size()!=0
            )
            {
                isLegal=false;
            }
            //if goes left and there is blockade
            else if(
                            posXofMove != piece.getPosY()
                            && posYofMove == piece.getPosX()
                            && posXofMove - piece.getPosY() < 0
                            && posYofMove - YblockadeFriendly.getPosX() == 0
                            && YblockadeFriendly.getPosX() == piece.getPosX()
                            && posXofMove - YblockadeFriendly.getPosY() < 0
                            && this.blockades6.size()!=0
            )
            {
                isLegal=false;
            }
            //if goes sideways and is filler and no blockade
            else if(board[posYofMove][posXofMove].getType()=='l' && posXofMove != piece.getPosY() && posYofMove == piece.getPosX()){
                piece.setDidMove(true);
                isLegal=true;
            }
            //if goes up/down and is filler and no blockade
            else if(board[posYofMove][posXofMove].getType()=='l' && posXofMove == piece.getPosY() && posYofMove != piece.getPosX()){
                piece.setDidMove(true);
                isLegal=true;
            }
            //if goes sideways and takes and no blockade
            else if(board[posYofMove][posXofMove].getType()!='l'
                    && board[posYofMove][posXofMove].getColor()!=piece.getColor()
                    && posXofMove != piece.getPosY() && posYofMove == piece.getPosX())
            {
                piece.setDidMove(true);
                isLegal=true;
                board[0][0].lastTaken = board[posYofMove][posXofMove];
            }
            //if goes up/down and takes and no blockade
            else if(
                    board[posYofMove][posXofMove].getType()!='l'
                    && board[posYofMove][posXofMove].getColor()!=piece.getColor()
                    && posXofMove == piece.getPosY()
                    && posYofMove != piece.getPosX())
            {
                piece.setDidMove(true);
                isLegal=true;
                board[0][0].lastTaken = board[posYofMove][posXofMove];
            }
        }
        else if(piece.getType()=='B'){
            Piece RightUpBlockade = board[0][0];
            Piece LeftUpBlockade = board[0][0];
            Piece RightDownBlockade = board[0][0];
            Piece LeftDownBlockade = board[0][0];
            int i = 1;
            int j = 1;
            while(j!=7 && i!=7){
                //X
                int kot = piece.getPosY()+j;
                //Y
                int pies = piece.getPosX()-i;
                if(!(pies<0) && !(kot>=8)){
                    if(!board[pies][kot].isUsed() && board[pies][kot].getType()!='l')
                    {
                        this.blockades.add(board[pies][kot]);
                    }
                }
                j++;
                i++;

            }
            if(this.blockades.size()==0){

            }
            else{
                RightUpBlockade = this.blockades.get(0);
            }
            i = 1;
            j = 1;
            while(j!=7 && i!=7){
                //X
                int kot = piece.getPosY()-j;
                //Y
                int pies = piece.getPosX()-i;
                if(!(pies<0) && !(kot<0)){
                    if(!board[pies][kot].isUsed() && board[pies][kot].getType()!='l')
                    {
                        this.blockades2.add(board[pies][kot]);
                    }
                }
                j++;
                i++;

            }
            if(this.blockades2.size()==0){

            }
            else{
                LeftUpBlockade = this.blockades2.get(0);
            }
            i = 1;
            j = 1;
            while(j!=7 && i!=7){
                //X
                int kot = piece.getPosY()+j;
                //Y
                int pies = piece.getPosX()+i;
                //Again, I have no idea why these are inverted when you put them in. Goofy ahh language
                if(!(pies>=8) && !(kot>=8)){
                    if(!board[pies][kot].isUsed() && board[pies][kot].getType()!='l')
                    {
                        this.blockades3.add(board[pies][kot]);
                    }
                }
                j++;
                i++;

            }
            if(this.blockades3.size()==0){

            }
            else{
                RightDownBlockade = this.blockades3.get(0);
            }
            i = 1;
            j = 1;
            while(j!=7 && i!=7){
                //X
                int kot = piece.getPosY()-j;
                //Y
                int pies = piece.getPosX()+i;
                //Again, I have no idea why these are inverted when you put them in. Goofy ahh language
                if(!(pies>=8) && !(kot<0)){
                    if(!board[pies][kot].isUsed() && board[pies][kot].getType()!='l')
                    {
                        this.blockades4.add(board[pies][kot]);
                    }
                }
                j++;
                i++;

            }
            if(this.blockades4.size()==0){

            }
            else{
                LeftDownBlockade = this.blockades4.get(0);
            }
            //if goes right up BUT there is blockade
            if(
                            this.blockades.size()!=0
                            && RightUpBlockade.getPosY()-posXofMove < 0
                            && RightUpBlockade.getPosX()-posYofMove > 0
                            && piece.getPosY()-posXofMove < 0
                            && piece.getPosX()-posYofMove > 0
                            &&
                            (
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 != 0 && posXofMove % 2 != 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 == 0 && posYofMove % 2 == 0)
                            ||
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 != 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 == 0)
                            )
            ){
                isLegal = false;
            }
            //if goes left up BUT there is blockade
            else if(
                            this.blockades2.size()!=0
                            && LeftUpBlockade.getPosY()-posXofMove > 0
                            && LeftUpBlockade.getPosX()-posYofMove > 0
                            && LeftUpBlockade.getPosY()-piece.getPosY()<0
                            && LeftUpBlockade.getPosX()-piece.getPosX()<0
                            && piece.getPosY()-posXofMove > 0
                            && piece.getPosX()-posYofMove > 0
                            &&
                            (
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 != 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 == 0)
                            ||
                            (piece.getPosY() % 2 != 0 && posXofMove % 2 != 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            )
            ){
                isLegal = false;
            }
            //if goes right down BUT there is blockade
            else if(
                            this.blockades3.size()!=0
                            && RightDownBlockade.getPosY()-posXofMove<0
                            && RightDownBlockade.getPosX()-posYofMove<0
                            && piece.getPosY()-posXofMove < 0
                            && piece.getPosX()-posYofMove < 0
                            &&
                            (
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 != 0 && piece.getPosX() % 2 == 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 != 0 && posXofMove % 2 != 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)

                            )
            ){
                isLegal = false;
            }
            //if goes left down BUT there is blockade
            else if(
                            this.blockades4.size()!=0
                            && piece.getPosY()-posXofMove > 0
                            && piece.getPosX()-posYofMove < 0
                            && LeftDownBlockade.getPosY()-posXofMove > 0
                            && LeftDownBlockade.getPosX()-posYofMove < 0
                            && (
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 != 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 == 0 && posYofMove % 2 != 0)
                            )
            ){
                isLegal = false;
            }
            //if goes right up and no blockade
            else if(
                    board[posYofMove][posXofMove].getType()=='l'
                    && board[posYofMove][posXofMove].isFiller()==true
                    && piece.getPosY()-posXofMove < 0
                    && piece.getPosX()-posYofMove > 0
                    &&
                            (
                    (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                    ||
                    (piece.getPosY() % 2 != 0 && posXofMove % 2 != 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                    ||
                    (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 == 0 && posYofMove % 2 == 0)
                    ||
                    (piece.getPosY() % 2 == 0 && posXofMove % 2 != 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 == 0)
                            )
            ){
                isLegal = true;
            }
            //if goes left up and no blockade
            else if(
                    board[posYofMove][posXofMove].getType()=='l'
                            && board[posYofMove][posXofMove].isFiller()==true
                            && piece.getPosY()-posXofMove > 0
                            && piece.getPosX()-posYofMove > 0

                            &&
                            (
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 != 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 == 0)
                            )
            ){
                isLegal = true;
            }
            //if goes left down and no blockade
            else if(
                    board[posYofMove][posXofMove].getType()=='l'
                            && board[posYofMove][posXofMove].isFiller()==true
                            && piece.getPosY()-posXofMove > 0
                            && piece.getPosX()-posYofMove < 0

                            &&
                            (
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 != 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 == 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 != 0 && posXofMove % 2 != 0 && piece.getPosX()==0 && posYofMove % 2 == 0)
                            )
            ){
                isLegal = true;
            }
            //if goes right down and no blockade
            else if(
                    board[posYofMove][posXofMove].getType()=='l'
                            && board[posYofMove][posXofMove].isFiller()==true
                            && piece.getPosY()-posXofMove < 0
                            && piece.getPosX()-posYofMove < 0

                            &&
                            (
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 != 0 && piece.getPosX() % 2 == 0 && posYofMove % 2 != 0)
                            )
            ){
                isLegal = true;
            }
            //if goes right up and no blockade and takes
            else if(
                    board[posYofMove][posXofMove].getType()!='l'
                            && board[posYofMove][posXofMove].getColor()!=piece.getColor()
                            && piece.getPosY()-posXofMove < 0
                            && piece.getPosX()-posYofMove > 0
                            &&
                            (
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 != 0 && posXofMove % 2 != 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 == 0 && posYofMove % 2 == 0)
                            )
            ){
                board[0][0].lastTaken = board[posYofMove][posXofMove];
                isLegal = true;
            }
            //if goes left up and no blockade and takes
            else if(
                    board[posYofMove][posXofMove].getType()!='l'
                            && board[posYofMove][posXofMove].getColor()!=piece.getColor()
                            && piece.getPosY()-posXofMove > 0
                            && piece.getPosX()-posYofMove > 0
                            &&
                            (
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 != 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 == 0)
                            )
            ){
                board[0][0].lastTaken = board[posYofMove][posXofMove];
                isLegal = true;
            }
            //if goes left down and no blockade and takes
            else if(
                    board[posYofMove][posXofMove].getType()!='l'
                            && board[posYofMove][posXofMove].getColor()!=piece.getColor()
                            && piece.getPosY()-posXofMove > 0
                            && piece.getPosX()-posYofMove < 0
                            &&
                            (
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            )
            ){
                board[0][0].lastTaken = board[posYofMove][posXofMove];
                isLegal = true;
            }
            //if goes right down and no blockade and takes
            else if(
                    board[posYofMove][posXofMove].getType()!='l'
                            && board[posYofMove][posXofMove].getColor()!=piece.getColor()
                            && piece.getPosY()-posXofMove < 0
                            && piece.getPosX()-posYofMove < 0

                            && piece.getPosY() % 2 == 0
                            && posXofMove % 2 == 0
                            && piece.getPosX() % 2 != 0
                            && posYofMove % 2 != 0
            ){
                board[0][0].lastTaken = board[posYofMove][posXofMove];
                isLegal = true;
            }
        }
        else if(piece.getType()=='Q'){
            int FbX = 0;
            int FbY = 0;
            int FbX2 = 0;
            int FbY2 = 0;
            int i = 0;
            int j=0;

            while(j!=8){
                /*System.out.println("X of checking: " + j);
                System.out.println("Y of checking: " + piece.getPosY());*/
                if(!board[j][piece.getPosY()].isUsed() && board[j][piece.getPosY()].getType()!='l'){
                    this.blockades5.add(board[j][piece.getPosY()]);
                    FbX = j;
                    FbY = piece.getPosY();
                }
                j++;
            }
            while(i!=8){
                /*System.out.println("i: " + i);
                System.out.println("Y of checking: " + piece.getPosX());*/
                if(!board[piece.getPosX()][i].isUsed() && board[piece.getPosX()][i].getType()!='l'){
                    this.blockades6.add(board[piece.getPosX()][i]);
                    FbX2 = piece.getPosX();
                    FbY2 = i;
                }
                i++;
            }
            i=0;
            j=0;
            Piece XblockadeFriendly = board[0][0];
            Piece YblockadeFriendly = board[0][0];
            if(this.blockades5.size()==0){

            }
            else if(this.blockades5.size()==1){
                XblockadeFriendly = this.blockades5.get(0);
            }
            else{
                XblockadeFriendly = this.blockades5.get(this.blockades5.size()-1);
            }
            if(this.blockades6.size()==0){

            }
            else if(this.blockades6.size()==1){
                YblockadeFriendly = this.blockades6.get(0);
            }
            else{
                YblockadeFriendly = this.blockades6.get(this.blockades6.size()-1);
            }
            Piece RightUpBlockade = board[0][0];
            Piece LeftUpBlockade = board[0][0];
            Piece RightDownBlockade = board[0][0];
            Piece LeftDownBlockade = board[0][0];
            i = 1;
            j = 1;
            while(j!=7 && i!=7){
                //X
                int kot = piece.getPosY()+j;
                //Y
                int pies = piece.getPosX()-i;
                if(!(pies<0) && !(kot>=8)){
                    if(!board[pies][kot].isUsed() && board[pies][kot].getType()!='l')
                    {
                        this.blockades.add(board[pies][kot]);
                    }
                }
                j++;
                i++;

            }
            if(this.blockades.size()==0){

            }
            else{
                RightUpBlockade = this.blockades.get(0);
            }
            i = 1;
            j = 1;
            while(j!=7 && i!=7){
                //X
                int kot = piece.getPosY()-j;
                //Y
                int pies = piece.getPosX()-i;
                if(!(pies<0) && !(kot<0)){
                    if(!board[pies][kot].isUsed() && board[pies][kot].getType()!='l')
                    {
                        this.blockades2.add(board[pies][kot]);
                    }
                }
                j++;
                i++;

            }
            if(this.blockades2.size()==0){

            }
            else{
                LeftUpBlockade = this.blockades2.get(0);
            }
            i = 1;
            j = 1;
            while(j!=7 && i!=7){
                //X
                int kot = piece.getPosY()+j;
                //Y
                int pies = piece.getPosX()+i;
                //Again, I have no idea why these are inverted when you put them in. Goofy ahh language
                if(!(pies>=8) && !(kot>=8)){
                    if(!board[pies][kot].isUsed() && board[pies][kot].getType()!='l')
                    {
                        this.blockades3.add(board[pies][kot]);
                    }
                }
                j++;
                i++;

            }
            if(this.blockades3.size()==0){

            }
            else{
                RightDownBlockade = this.blockades3.get(0);
            }
            i = 1;
            j = 1;
            while(j!=7 && i!=7){
                //X
                int kot = piece.getPosY()-j;
                //Y
                int pies = piece.getPosX()+i;
                //Again, I have no idea why these are inverted when you put them in. Goofy ahh language
                if(!(pies>=8) && !(kot<0)){
                    if(!board[pies][kot].isUsed() && board[pies][kot].getType()!='l')
                    {
                        this.blockades4.add(board[pies][kot]);
                    }
                }
                j++;
                i++;

            }
            if(this.blockades4.size()==0){

            }
            else{
                LeftDownBlockade = this.blockades4.get(0);
            }
            //if goes up and there is blockade
            if(
                    posXofMove == piece.getPosY()
                            && posYofMove != piece.getPosX()
                            && piece.getPosX() - posYofMove > 0
                            && posYofMove - XblockadeFriendly.getPosX() < 0
                            && XblockadeFriendly.getPosX() < piece.getPosX()
                            && this.blockades5.size()!=0
            )
            {
                System.out.println("pies");
                isLegal=false;
            }
            //if goes down but there is blockade
            else if(
                    posXofMove == piece.getPosY()
                            && posYofMove != piece.getPosX()
                            && piece.getPosX() - posYofMove < 0
                            && posYofMove - XblockadeFriendly.getPosX() > 0
                            && XblockadeFriendly.getPosX() > piece.getPosX()
                            && this.blockades5.size()!=0
            )
            {
                System.out.println("pies1");
                isLegal=false;
            }
            //if goes right and there is blockade
            else if(
                    posXofMove != piece.getPosY()
                            && posYofMove == piece.getPosX()
                            && posXofMove - piece.getPosY() > 0
                            && posYofMove - YblockadeFriendly.getPosX() == 0
                            && YblockadeFriendly.getPosX() == piece.getPosX()
                            && posXofMove - YblockadeFriendly.getPosY() > 0
                            && piece.getPosY() - YblockadeFriendly.getPosY() < 0
                            && this.blockades6.size()!=0
            )
            {
                System.out.println("pies2");
                isLegal=false;
            }
            //if goes left and there is blockade
            else if(
                    posXofMove != piece.getPosY()
                            && posYofMove == piece.getPosX()
                            && posXofMove - piece.getPosY() < 0
                            && posYofMove - YblockadeFriendly.getPosX() == 0
                            && YblockadeFriendly.getPosX() == piece.getPosX()
                            && posXofMove - YblockadeFriendly.getPosY() < 0
                            && this.blockades6.size()!=0
            )
            {
                System.out.println("pies3");
                isLegal=false;
            }
            //if goes sideways and is filler and no blockade
            else if(board[posYofMove][posXofMove].getType()=='l'
                    && posXofMove != piece.getPosY() && posYofMove == piece.getPosX()){
                System.out.println("pies");
                isLegal=true;
            }
            //if goes up/down and is filler and no blockade
            else if(board[posYofMove][posXofMove].getType()=='l' && posXofMove == piece.getPosY() && posYofMove != piece.getPosX()){
                isLegal=true;
            }
            //if goes sideways and takes and no blockade
            else if(
                    board[posYofMove][posXofMove].getType()!='l'
                    && board[posYofMove][posXofMove].getColor()!=piece.getColor()
                    && posXofMove != piece.getPosY() && posYofMove == piece.getPosX())
            {
                isLegal=true;
                board[0][0].lastTaken = board[posYofMove][posXofMove];
            }
            //if goes up/down and takes and no blockade
            else if(
                    board[posYofMove][posXofMove].getType()!='l'
                            && board[posYofMove][posXofMove].getColor()!=piece.getColor()
                            && posXofMove == piece.getPosY()
                            && posYofMove != piece.getPosX())
            {
                isLegal=true;
                board[0][0].lastTaken = board[posYofMove][posXofMove];
            }
            //if goes right up BUT there is blockade
            if(
                    this.blockades.size()!=0
                            && RightUpBlockade.getPosY()-posXofMove < 0
                            && RightUpBlockade.getPosX()-posYofMove > 0
                            && piece.getPosY()-posXofMove < 0
                            && piece.getPosX()-posYofMove > 0
                            &&
                            (
                                    (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                                            ||
                                            (piece.getPosY() % 2 != 0 && posXofMove % 2 != 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                                            ||
                                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 == 0 && posYofMove % 2 == 0)
                                            ||
                                            (piece.getPosY() % 2 == 0 && posXofMove % 2 != 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 == 0)
                            )
            ){
                System.out.println("right up blocked");
                isLegal = false;
            }
            //if goes left up BUT there is blockade
            else if(
                    this.blockades2.size()!=0
                            && LeftUpBlockade.getPosY()-posXofMove > 0
                            && LeftUpBlockade.getPosX()-posYofMove > 0
                            && LeftUpBlockade.getPosY()-piece.getPosY()<0
                            && LeftUpBlockade.getPosX()-piece.getPosX()<0
                            && piece.getPosY()-posXofMove > 0
                            && piece.getPosX()-posYofMove > 0
                            &&
                            (
                                    (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                                            ||
                                            (piece.getPosY() % 2 != 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 == 0)
                                            ||
                                            (piece.getPosY() % 2 != 0 && posXofMove % 2 != 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            )
            ){
                System.out.println("left up blocked");
                isLegal = false;
            }
            //if goes right down BUT there is blockade
            else if(
                    this.blockades3.size()!=0
                            && RightDownBlockade.getPosY()-posXofMove<0
                            && RightDownBlockade.getPosX()-posYofMove<0
                            && piece.getPosY()-posXofMove < 0
                            && piece.getPosX()-posYofMove < 0
                            &&
                            (
                                    (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                                            ||
                                            (piece.getPosY() % 2 == 0 && posXofMove % 2 != 0 && piece.getPosX() % 2 == 0 && posYofMove % 2 != 0)
                                            ||
                                            (piece.getPosY() % 2 != 0 && posXofMove % 2 != 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)

                            )
            ){
                System.out.println("Right Down Blocked");
                isLegal = false;
            }
            //if goes left down BUT there is blockade
            else if(
                    this.blockades4.size()!=0
                            && piece.getPosY()-posXofMove > 0
                            && piece.getPosX()-posYofMove < 0
                            && LeftDownBlockade.getPosY()-posXofMove > 0
                            && LeftDownBlockade.getPosX()-posYofMove < 0
                            &&
                            (
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 != 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 == 0 && posYofMove % 2 != 0)
                            )
            ){
                System.out.println("Left Down Blocked");
                isLegal = false;
            }
            //if goes right up and no blockade
            else if(
                    board[posYofMove][posXofMove].getType()=='l'
                            && piece.getPosY()-posXofMove < 0
                            && piece.getPosX()-posYofMove > 0
                            &&
                            (
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 != 0 && posXofMove % 2 != 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 == 0 && posYofMove % 2 == 0)
                            ||
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 != 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 == 0)
                            )
            ){
                System.out.println(board[posYofMove][posXofMove].getType());
                isLegal = true;
            }
            //if goes left up and no blockade
            else if(
                    board[posYofMove][posXofMove].getType()=='l'
                            && piece.getPosY()-posXofMove > 0
                            && piece.getPosX()-posYofMove > 0

                            &&
                            (
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 != 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 == 0)
                            )
            ){
                isLegal = true;
            }
            //if goes left down and no blockade
            else if(
                    board[posYofMove][posXofMove].getType()=='l'
                            && piece.getPosY()-posXofMove > 0
                            && piece.getPosX()-posYofMove < 0

                            &&
                            (
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 != 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 == 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 != 0 && posXofMove % 2 != 0 && piece.getPosX()==0 && posYofMove % 2 == 0)
                            )
            ){
                isLegal = true;
            }
            //if goes right down and no blockade
            else if(
                    board[posYofMove][posXofMove].getType()=='l'
                            && piece.getPosY()-posXofMove < 0
                            && piece.getPosX()-posYofMove < 0

                            &&
                            (
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 != 0 && piece.getPosX() % 2 == 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 != 0 && posXofMove % 2 != 0 && piece.getPosX() == 0 && posYofMove % 2 == 0)
                            )
            ){
                isLegal = true;
            }
            //if goes right up and no blockade and takes
            else if(
                    board[posYofMove][posXofMove].getType()!='l'
                            && board[posYofMove][posXofMove].getColor()!=piece.getColor()
                            && piece.getPosY()-posXofMove < 0
                            && piece.getPosX()-posYofMove > 0
                            &&
                            (
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 != 0 && posXofMove % 2 != 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 == 0 && posYofMove % 2 == 0)
                            )
            ){
                board[0][0].lastTaken = board[posYofMove][posXofMove];
                isLegal = true;
            }
            //if goes left up and no blockade and takes
            if(
                    board[posYofMove][posXofMove].getType()!='l'
                            && board[posYofMove][posXofMove].getColor()!=piece.getColor()
                            && piece.getPosY()-posXofMove > 0
                            && piece.getPosX()-posYofMove > 0
                            &&
                            (
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 != 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 == 0)
                            )
            ){
                board[0][0].lastTaken = board[posYofMove][posXofMove];
                isLegal = true;
            }
            //if goes left down and no blockade and takes
            if(
                    board[posYofMove][posXofMove].getType()!='l'
                            && board[posYofMove][posXofMove].getColor()!=piece.getColor()
                            && piece.getPosY()-posXofMove > 0
                            && piece.getPosX()-posYofMove < 0

                            &&
                            (
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 != 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 == 0 && posYofMove % 2 != 0)
                            ||
                            (piece.getPosY() % 2 != 0 && posXofMove % 2 != 0 && piece.getPosX()==0 && posYofMove % 2 == 0)
                            )
            )
            {
                board[0][0].lastTaken = board[posYofMove][posXofMove];
                isLegal = true;
            }
            //if goes right down and no blockade and takes
            else if(
                    board[posYofMove][posXofMove].getType()!='l'
                            && board[posYofMove][posXofMove].getColor()!=piece.getColor()
                            && piece.getPosY()-posXofMove < 0
                            && piece.getPosX()-posYofMove < 0
                            &&
                            (
                            (piece.getPosY() % 2 == 0 && posXofMove % 2 == 0 && piece.getPosX() % 2 != 0 && posYofMove % 2 != 0)
                            )
            ){
                board[0][0].lastTaken = board[posYofMove][posXofMove];
                isLegal = true;
            }
        }
        else if(piece.getType()=='K'){
            System.out.println(board[0][7]);
            System.out.println(board[0][6]);
            System.out.println(board[0][5]);
            //if goes up
            if (posXofMove-piece.getPosY() == 0 && piece.getPosX()-posYofMove == 1)
            {
                if(board[posYofMove][posXofMove].getType()!='l' && board[posYofMove][posXofMove].getColor()!=piece.getColor()){
                    isLegal = true;
                    board[0][0].lastTaken = board[posYofMove][posXofMove];
                    piece.setDidMove(true);
                }
                else{
                    isLegal = true;
                    piece.setDidMove(true);
                }
            }
            //if goes left up
            if (piece.getPosY()-posXofMove==1 && piece.getPosX()-posYofMove == 1)
            {
                if(board[posYofMove][posXofMove].getType()!='l' && board[posYofMove][posXofMove].getColor()!=piece.getColor()){
                    isLegal = true;
                    board[0][0].lastTaken = board[posYofMove][posXofMove];
                    piece.setDidMove(true);
                }
                else{
                    isLegal = true;
                    piece.setDidMove(true);
                }
            }
            //if goes right up
            if (piece.getPosY()-posXofMove==-1 && piece.getPosX()-posYofMove == 1)
            {
                if(board[posYofMove][posXofMove].getType()!='l' && board[posYofMove][posXofMove].getColor()!=piece.getColor()){
                    isLegal = true;
                    board[0][0].lastTaken = board[posYofMove][posXofMove];
                    piece.setDidMove(true);
                }
                else{
                    isLegal = true;
                    piece.setDidMove(true);
                }
            }
            //if goes left down
            if (piece.getPosY()-posXofMove == 1 && piece.getPosX()-posYofMove == -1)
            {
                if(board[posYofMove][posXofMove].getType()!='l' && board[posYofMove][posXofMove].getColor()!=piece.getColor()){
                    isLegal = true;
                    board[0][0].lastTaken = board[posYofMove][posXofMove];
                    piece.setDidMove(true);
                }
                else{
                    isLegal = true;
                    piece.setDidMove(true);
                }
            }
            //if goes right down
            if (piece.getPosY()-posXofMove == -1 && piece.getPosX()-posYofMove == -1)
            {
                if(board[posYofMove][posXofMove].getType()!='l' && board[posYofMove][posXofMove].getColor()!=piece.getColor()){
                    isLegal = true;
                    board[0][0].lastTaken = board[posYofMove][posXofMove];
                    piece.setDidMove(true);
                }
                else{
                    isLegal = true;
                    piece.setDidMove(true);
                }
            }
            //if goes down
            if (piece.getPosY()-posXofMove == 0 && piece.getPosX()-posYofMove == -1)
            {
                if(board[posYofMove][posXofMove].getType()!='l' && board[posYofMove][posXofMove].getColor()!=piece.getColor()){
                    isLegal = true;
                    board[0][0].lastTaken = board[posYofMove][posXofMove];
                    piece.setDidMove(true);
                }
                else{
                    isLegal = true;
                    piece.setDidMove(true);
                }
            }
            //short castle, white
            if(
                    posXofMove == 6
                    && posYofMove == 7
                    && piece.getColor()==true
                    && piece.isDidMove()==false
                    && board[7][7].getType()=='R'
                    && board[7][7].isDidMove()==false
                    && board[7][6].getType()=='l'
                    && board[7][5].getType()=='l'
                    && piece.getPosX()==posYofMove
                    && posXofMove-piece.getPosY()==2
            )
            {
                Piece rook = board[7][7];
                rook.setPosY(7);
                rook.setPosX(5);
                piece.setPosY(posXofMove);
                piece.setPosX(posYofMove);
                board[7][7]= new Piece('l',false,7,7,false); //filler in rook place
                board[7][4]= new Piece('l',false,7,4,false); //filler in king place
                board[7][6] = new Piece('K',true,7,6,false); //king in right castling place
                board[7][5] = new Piece('R',true,7,5,false); //rook in right castling place
            }
            //long castle, white
            if(
                            posXofMove == 2
                            && posYofMove == 7
                            && piece.getColor()==true
                            && piece.isDidMove()==false
                            && board[7][0].getType()=='R'
                            && board[7][0].isDidMove()==false
                            && board[7][1].getType()=='l'
                            && board[7][2].getType()=='l'
                            && board[7][3].getType()=='l'
                            && piece.getPosX()==posYofMove
                            && piece.getPosY()-posXofMove==2
            )
            {
                Piece rook = board[7][0];
                rook.setPosY(7);
                rook.setPosX(3);
                piece.setPosY(posXofMove);
                piece.setPosX(posYofMove);
                board[7][0]= new Piece('l',false,7,0,false); //filler in rook place
                board[7][4]= new Piece('l',false,7,4,false); //filler in king place
                board[7][2] = new Piece('K',true,7,2,false); //king in right castling place
                board[7][3] = new Piece('R',true,7,3,false); //rook in right castling place
            }
            //short castle, black
            if(
                            posXofMove == 6
                            && posYofMove == 0
                            && piece.getColor()==false
                            && piece.isDidMove()==false
                            && board[0][7].getType()=='R'
                            && board[0][7].isDidMove()==false
                            && board[0][6].getType()=='l'
                            && board[0][5].getType()=='l'
                            && piece.getPosX()==posYofMove
                            && posXofMove-piece.getPosY()==2
            )
            {
                Piece rook = board[0][7];
                rook.setPosY(0);
                rook.setPosX(5);
                piece.setPosY(posXofMove);
                piece.setPosX(posYofMove);
                board[0][7] = new Piece('l',false,0,7,false); //filler in rook place
                board[0][4] = new Piece('l',false,0,4,false); //filler in king place
                board[0][6] = new Piece('K',false,0,6,false); //king in right castling place
                board[0][5] = new Piece('R',true,0,5,false); //rook in right castling place
            }
            //long castle, black
            if(
                    posXofMove == 2
                            && posYofMove == 0
                            && piece.getColor()==false
                            && piece.isDidMove()==false
                            && board[0][0].getType()=='R'
                            && board[7][0].isDidMove()==false
                            && board[0][1].getType()=='l'
                            && board[0][2].getType()=='l'
                            && board[0][3].getType()=='l'
                            && piece.getPosX()==posYofMove
                            && piece.getPosY()-posXofMove==2
            )
            {
                Piece rook = board[0][0];
                rook.setPosY(0);
                rook.setPosX(3);
                piece.setPosY(posXofMove);
                piece.setPosX(posYofMove);
                board[0][0]= new Piece('l',false,0,0,false); //filler in rook place
                board[0][4]= new Piece('l',false,0,4,false); //filler in king place
                board[0][2] = new Piece('K',false,0,2,false); //king in right castling place
                board[0][3] = new Piece('R',false,0,3,false); //rook in right castling place
            }
        }
        piece.setUsed(false);
        return isLegal;
    }
    public void move_piece(Piece piece, int posXofMove,int posYofMove, boolean whoseTurn, Piece board[][]){
        ArrayList<Character> LegalPieces = new ArrayList<Character>();
        LegalPieces.add('Q');
        LegalPieces.add('B');
        LegalPieces.add('N');
        LegalPieces.add('R');
        if(piece.getColor()==whoseTurn) {
                if (board[posXofMove][posYofMove].check_legal(piece,board,posXofMove,posYofMove)) {
                    board[piece.getPosX()][piece.getPosY()]= new Piece('l',false,piece.getPosX(),piece.getPosY(),true);
                    if(piece.getType()=='P' && piece.getColor()==true && posYofMove==0){
                        while(true){
                            System.out.println("Choose the piece you want (Q - queen, R - rook, N - knight, B - bishop)");
                            Scanner type = new Scanner(System.in);
                            char pieceType = type.next().charAt(0);
                            if(LegalPieces.contains(pieceType)){
                                piece.setPosX(posYofMove);
                                piece.setPosY(posXofMove);
                                board[piece.getPosX()][piece.getPosY()]=new Piece(pieceType,true,piece.getPosX(),piece.getPosY(),false);
                                break;
                            }
                            else{
                                System.out.println("This is not a valid piece type! Please try again");
                            }
                        }

                    }
                    else if(piece.getType()=='P' && piece.getColor()==false && posYofMove==7){
                        while(true){
                            System.out.println("Choose the piece you want (Q - queen, R - rook, N - knight, B - bishop)");
                            Scanner type = new Scanner(System.in);
                            char pieceType = type.next().charAt(0);
                            if(LegalPieces.contains(pieceType)){
                                piece.setPosX(posYofMove);
                                piece.setPosY(posXofMove);
                                board[piece.getPosX()][piece.getPosY()]=new Piece(pieceType,false,piece.getPosX(),piece.getPosY(),false);
                                break;
                            }
                            else{
                                System.out.println("This is not a valid piece type! Please try again");
                            }
                        }
                    }
                    else{
                        piece.setPosX(posYofMove);
                        piece.setPosY(posXofMove);
                        board[piece.getPosX()][piece.getPosY()]=piece;
                    }
                }
                else{
                    System.out.println("You cant go to:" + posXofMove + "," + posYofMove + " with: " + piece.getType());

                }
        }
        else{
            System.out.println("Its not your turn!");
        }
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public char getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFiller() {
        return isFiller;
    }

    public void setFiller(boolean filler) {
        isFiller = filler;
    }

    public void setType(char type) {
        this.type = type;
    }

    public boolean getColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean isDidMove() {
        return didMove;
    }

    public void setDidMove(boolean didMove) {
        this.didMove = didMove;
    }

    public Piece getlastTaken() {
        return lastTaken;
    }

    public void setlastTaken(Piece lastTaken) {
        this.lastTaken = lastTaken;
    }

    @Override
    public String toString() {
        String pies;
        if(getColor()==true && getType()!='l'){
            pies = "w" + getType();
        }
        else if(getColor()==false && getType()!='l'){
            pies = "b" + getType();
        }
        else{
            pies = "f" + getType();
        }
        return pies;
    }
}
