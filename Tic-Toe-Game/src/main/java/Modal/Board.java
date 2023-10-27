package Modal;

public class Board {
    public int size;
    public PlayingSymbol [][]board;
    public Board(int size){
         this.size = size;
         this.board = new PlayingSymbol[size][size];
    }

    public void printBoard(){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(board[i][j] == null){
                    System.out.print("   ");
                } else {
                    System.out.print(" "+board[i][j].symbol+" ");
                }
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public boolean addSymbol(int row, int column, PlayingSymbol symbol){
        if(row<0 || row>=size || column<0 || column>=size || board[row][column]!=null){
            return  false;
        }
        board[row][column] = symbol;
        return  true;
    }

    public boolean checkFreeCellInBoard(){
        boolean isFreeSpace = false;

        for(int i=0; i<size; i++){
            if(isFreeSpace) break;
            for(int j = 0; j<size; j++){
                  if(board[i][j]==null){
                      isFreeSpace = true;
                      break;
                  }
            }
        }

        return  isFreeSpace;
    }
}
