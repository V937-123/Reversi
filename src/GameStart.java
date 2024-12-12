public class GameStart {

    //定义Board
    private int[][] Board;

    //初始化棋盘
    public void initBoard() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Board[i][j] = 0;
            }
        }
        Board[3][3] = 1;
        Board[4][4] = 1;
        Board[3][4] = -1;
        Board[4][3] = -1;

    }

    //打印最初的棋盘
    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(Board[i][j] + " ");
            }
            System.out.println();//换行

        }
    }
}





