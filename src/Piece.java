public class Piece {
    private char type;
    private boolean color;
    private int posX;
    private int posY;

    private int id;
    private boolean isFiller;
    private boolean didMove;

    public Piece(char type, boolean color, int posX, int posY, boolean isFiller) {
        this.id++;
        this.type = type;
        this.color = color;
        this.posX = posX;
        this.posY = posY;
        this.isFiller = isFiller;
        this.didMove=true;
    }
    public boolean check_legal(Piece piece, Piece board[][],int posXofMove,int posYofMove){
        boolean isLegal=false;
        if(piece.getType()=='P' && piece.getColor()==true){
            System.out.println(piece.getPosY());
            System.out.println(posXofMove);
            System.out.println(posYofMove);
            System.out.println(piece.getPosX());
            //System.out.println(board[piece.getPosY()][posYofMove].getType());
            //pies
            System.out.println(board[posYofMove][piece.getPosY()].getType());
            System.out.println(board[posYofMove][posXofMove].getType());
            if(piece.getPosX()==6 && 6-posYofMove==2 && piece.getPosY()==posXofMove && board[posYofMove][posXofMove].getType()=='l'){
                isLegal = true;
            }
            else if(piece.getPosX()-posYofMove==1 && piece.getPosY()==posXofMove && board[posYofMove][posXofMove].getType()=='l'){
                isLegal = true;
            }
            else if(piece.getPosX()-posYofMove==1 && board[posYofMove][posXofMove].getType()!='l' && posXofMove!=piece.getPosY() && board[posYofMove][posXofMove].getColor()!=piece.getColor()){
                isLegal = true;
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
            if(piece.getPosX()==1 && piece.getPosX()+posYofMove==4 && piece.getPosY()==posXofMove && board[posYofMove][posXofMove].getType()=='l'){
                isLegal = true;
            }
            else if(posYofMove-piece.getPosX()==1 && piece.getPosY()==posXofMove && board[posYofMove][posXofMove].getType()=='l'){
                isLegal = true;
            }
            else if(posYofMove-piece.getPosX()==1 && board[posYofMove][posXofMove].getType()!='l' && board[posYofMove][posXofMove].getColor()!=piece.getColor()){
                isLegal = true;
            }
            else{
                isLegal=false;
            }
        }
        return isLegal;
    }
    public void move_piece(Piece piece, int posXofMove,int posYofMove, boolean whoseTurn, Piece board[][]){
        if(piece.getColor()==whoseTurn) {
            if (piece.getType() == 'P') {
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
        }
        else{
            System.out.println("Its not your turn!");
        }
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
