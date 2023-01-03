import java.util.ArrayList;

public class Piece {
    private char type;
    private boolean color;
    private int posX;
    private int posY;

    private int id;
    private boolean isFiller;
    private boolean didMove;
    private Piece lastTaken;
    private boolean isUsed;
    private ArrayList<Piece> blockades = new ArrayList<Piece>();
    private ArrayList<Piece> blockades2 = new ArrayList<Piece>();

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
                lastTaken = board[posYofMove][posXofMove];
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
                lastTaken = board[posYofMove][posXofMove];
            }
            else{
                isLegal=false;
            }
        }
        else if(piece.getType()=='N'){
            System.out.println("X of piece: " + piece.getPosY()); //X
            System.out.println("Y of piece: " + piece.getPosX()); //Y
            //for some reason X and Y ^^ are inverted here, god knows why
            System.out.println("pos x of move: " + posXofMove); //X of where you want to go
            System.out.println("pos y of move: " + posYofMove); //Y of where you want to go
            System.out.println(posXofMove-piece.getPosY());
            System.out.println(piece.getPosX()-posYofMove);
            System.out.println(board[posYofMove][posXofMove].getType()); //the type of piece on the place you want to go
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
                lastTaken = board[posYofMove][posXofMove];
            }
            //if its filler and goes left back
            else if(board[posYofMove][posXofMove].getType()=='l' && posXofMove-piece.getPosY()==-2 && piece.getPosX()-posYofMove==-1){
                isLegal=true;
            }
            //if takes and goes left back
            else if(board[posYofMove][posXofMove].getType()!='l' && board[posYofMove][posXofMove].getColor()!=piece.getColor() && posXofMove-piece.getPosY()==-2 && piece.getPosX()-posYofMove==-1){
                isLegal=true;
                lastTaken = board[posYofMove][posXofMove];
            }
            //if takes and goes left
            else if(board[posYofMove][posXofMove].getType()!='l' && board[posYofMove][posXofMove].getColor()!=piece.getColor() && posXofMove-piece.getPosY()==-2 && piece.getPosX()-posYofMove==1){
                isLegal=true;
                lastTaken = board[posYofMove][posXofMove];
            }
            //if takes and goes right
            else if(board[posYofMove][posXofMove].getType()!='l' && board[posYofMove][posXofMove].getColor()!=piece.getColor() && posXofMove-piece.getPosY()==2 && piece.getPosX()-posYofMove==1){
                isLegal=true;
                lastTaken = board[posYofMove][posXofMove];
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
                lastTaken = board[posYofMove][posXofMove];
            }
            //if takes and forward and knight is on left side of board
            else if(board[posYofMove][posXofMove].getType()!='l' && board[posYofMove][posXofMove].getColor()!=piece.getColor() && piece.getPosX()-posYofMove==2 && posXofMove-piece.getPosY()==1){
                isLegal=true;
                lastTaken = board[posYofMove][posXofMove];
            }
            //if takes and backwards and knight is on right side of the board
            else if(board[posYofMove][posXofMove].getType()!='l' && board[posYofMove][posXofMove].getColor()!=piece.getColor() && piece.getPosY()-posXofMove==1 && piece.getPosX()-posYofMove==2){
                isLegal=true;
                lastTaken = board[posYofMove][posXofMove];
            }
            //if takes and backwards and knight is on left side of the board
            else if(board[posYofMove][posXofMove].getType()!='l' && board[posYofMove][posXofMove].getColor()!=piece.getColor() && piece.getPosY()-posXofMove==1 && posYofMove-piece.getPosX()==2){
                isLegal=true;
                lastTaken = board[posYofMove][posXofMove];
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
                    this.blockades.add(board[j][piece.getPosY()]);
                    FbX = j;
                    FbY = piece.getPosY();
                }
                j++;
            }
            System.out.println("blockade for y axis:");
            while(i!=8){
                /*System.out.println("i: " + i);
                System.out.println("Y of checking: " + piece.getPosX());*/
                if(!board[piece.getPosX()][i].isUsed() && board[piece.getPosX()][i].getType()!='l'){
                    this.blockades2.add(board[piece.getPosX()][i]);
                    FbX2 = piece.getPosX();
                    FbY2 = i;
                }
                i++;
            }
            i=0;
            j=0;
            Piece XblockadeFriendly = board[0][0];
            Piece YblockadeFriendly = board[0][0];
            if(this.blockades.size()==0){

            }
            else if(this.blockades.size()==1){
                XblockadeFriendly = this.blockades.get(0);
            }
            else{
                XblockadeFriendly = this.blockades.get(this.blockades.size()-1);
            }
            if(this.blockades2.size()==0){

            }
            else if(this.blockades2.size()==1){
                YblockadeFriendly = this.blockades2.get(0);
            }
            else{
                YblockadeFriendly = this.blockades2.get(this.blockades2.size()-1);
            }

            System.out.println("X of piece: " + piece.getPosY()); //X
            System.out.println("Y of piece: " + piece.getPosX()); //Y
            //for some reason X and Y ^^ are inverted here, god knows why
            System.out.println("pos x of move: " + posXofMove); //X of where you want to go
            System.out.println("pos y of move: " + posYofMove); //Y of where you want to go

            System.out.println("The friendly blockade of the X axis is: " + XblockadeFriendly);
            System.out.println("The friendly blockade of the Y axis is: " + YblockadeFriendly);
            System.out.println("X of up/down blockade: " + XblockadeFriendly.getPosY());
            System.out.println("Y of up/down blockade: " + XblockadeFriendly.getPosX());
            System.out.println("X of right/left blockade: " + YblockadeFriendly.getPosY());
            System.out.println("Y of right/left blockade: " + YblockadeFriendly.getPosX());
            System.out.println("less than 0 is left more than 0 is right ");
            System.out.println(posXofMove-piece.getPosY());
            System.out.println("less than 0 is down more than 0 is up");
            System.out.println(piece.getPosX()-posYofMove);
            System.out.println(board[posYofMove][posXofMove].getType()); //the type of piece on the place you want to go
            //System.out.println("blockade for x axis:");

            //if goes up and there is blockade
            if(
                    board[posYofMove][posXofMove].getType()=='l'
                    && posXofMove == piece.getPosY()
                    && posYofMove != piece.getPosX()
                    && piece.getPosX() - posYofMove > 0
                    && posYofMove - XblockadeFriendly.getPosX() < 0
                    && XblockadeFriendly.getPosX() < piece.getPosX()
            )
            {
                System.out.println("pies");
                isLegal=false;
            }
            //if goes down but there is blockade
            else if(
                    board[posYofMove][posXofMove].getType()=='l'
                            && posXofMove == piece.getPosY()
                            && posYofMove != piece.getPosX()
                            && piece.getPosX() - posYofMove < 0
                            && posYofMove - XblockadeFriendly.getPosX() > 0
                            && XblockadeFriendly.getPosX() > piece.getPosX()
            )
            {
                System.out.println("pies1");
                isLegal=false;
            }
            //if goes right and there is blockade
            else if(
                    board[posYofMove][posXofMove].getType()=='l'
                            && posXofMove != piece.getPosY()
                            && posYofMove == piece.getPosX()
                            && posXofMove - piece.getPosY() > 0
                            && posYofMove - YblockadeFriendly.getPosX() == 0
                            && YblockadeFriendly.getPosX() == piece.getPosX()
                            && posXofMove - YblockadeFriendly.getPosY() > 0
                            && piece.getPosY() - YblockadeFriendly.getPosY() < 0
                            && this.blockades2.size()!=0
            )
            {
                System.out.println("pies2");
                isLegal=false;
            }
            //if goes left and there is blockade
            else if(
                    board[posYofMove][posXofMove].getType()=='l'
                            && posXofMove != piece.getPosY()
                            && posYofMove == piece.getPosX()
                            && posXofMove - piece.getPosY() < 0
                            && posYofMove - YblockadeFriendly.getPosX() == 0
                            && YblockadeFriendly.getPosX() == piece.getPosX()
                            && posXofMove - YblockadeFriendly.getPosY() < 0
                            && this.blockades2.size()!=0
            )
            {
                System.out.println("pies3");
                isLegal=false;
            }
            //if goes sideways and is filler and no blockade
            else if(board[posYofMove][posXofMove].getType()=='l' && posXofMove != piece.getPosY() && posYofMove == piece.getPosX()){
                isLegal=true;
            }
            //if goes up/down and is filler and no blockade
            else if(board[posYofMove][posXofMove].getType()=='l' && posXofMove == piece.getPosY() && posYofMove != piece.getPosX()){
                isLegal=true;
            }
            //if goes sideways and takes and no blockade
            else if(board[posYofMove][posXofMove].getType()!='l'
                    && board[posYofMove][posXofMove].getColor()!=piece.getColor()
                    && posXofMove != piece.getPosY() && posYofMove == piece.getPosX())
            {
                isLegal=true;
                lastTaken = board[posYofMove][posXofMove];
            }
            //if goes up/down and takes and no blockade
            else if(
                    board[posYofMove][posXofMove].getType()!='l'
                    && board[posYofMove][posXofMove].getColor()!=piece.getColor()
                    && posXofMove == piece.getPosY()
                    && posYofMove != piece.getPosX())
            {
                isLegal=true;
                lastTaken = board[posYofMove][posXofMove];
            }
        }
        piece.setUsed(false);
        return isLegal;
    }
    public void move_piece(Piece piece, int posXofMove,int posYofMove, boolean whoseTurn, Piece board[][]){
        if(piece.getColor()==whoseTurn) {
                if (board[posXofMove][posYofMove].check_legal(piece,board,posXofMove,posYofMove)) {
                    //System.out.println("You can go there!");
                    board[piece.getPosX()][piece.getPosY()]= new Piece('l',false,piece.getPosX(),piece.getPosY(),true);
                    piece.setPosX(posYofMove);
                    piece.setPosY(posXofMove);
                    board[piece.getPosX()][piece.getPosY()]=piece;
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
