package Modal;

public class Board {
    public int size;
    public PlayingSymbol[][] board;

    public Board(int size){
        this.size = size;
        board = new PlayingSymbol[size][size];
    }

    public void printBoard(){
        int n = board.length;;
        int m = board[0].length;
        for(int i=0; i<n; i++){
            for(int j=0; j<m;j++){
                if(board[i][j]!=null){
                    System.out.print(board[i][j].symbol.name());
                }
                System.out.print("  |");
            }
            System.out.println("");
        }
    }

    public boolean isFreeSpaceExist(){
        boolean isEmptyCellExist = false;

        for(int i=0; i<board.length; i++){
            if(isEmptyCellExist) break;
            for(int j=0; j<board[0].length; j++){
                if(board[i][j] == null){
                    isEmptyCellExist = true;
                    break;
                }
            }
        }

        return  isEmptyCellExist;
    }

    public boolean addSymbol(int row, int column, PlayingSymbol playingSymbol){
        boolean isPlayerSymbolAddedSuccessfully = false;

        if(board[row][column]!=null){
             return  isPlayerSymbolAddedSuccessfully;
        }

        board[row][column] = playingSymbol;
        isPlayerSymbolAddedSuccessfully = true;

        return  isPlayerSymbolAddedSuccessfully;

    }
}
