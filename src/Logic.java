import java.util.Scanner;

public class Logic {
    static Scanner input = new Scanner(System.in);
//    static int[][] test = new int[][]{
//            {0, 0, 0, -1, 0, 0, 0, 0},
//            {0, 0, 0, -1, 0, 0, 0, 0},
//            {0, 0, 0, -1, 0, 0, 0, 0},
//            {0, 0, 0, -1, 0, 0, 0, 0},
//            {0, 0, 0, 1, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0}
//    };


    public static void main(String[] args) {
        print(PVEGame.chessBoard);
        int color = 1;
        while (true) {
            System.out.println("current color: "+color);
            boolean[][] canPlacePositions = canPlace(PVEGame.chessBoard, color);
            int s=0;
            for (int i = 0; i <8 ; i++) {
                for (int j = 0; j < 8; j++) {
                    s+=canPlacePositions[i][j]?1:0;
                }
            }
            if(s==0){
                color=-color;
                continue;
            }
            int x = input.nextInt(), y = input.nextInt();
            System.out.println("can flip: " + canFlip(PVEGame.chessBoard, x, y, color));
            System.out.println("can place:");
            print(canPlace(PVEGame.chessBoard, color));
            Operate(PVEGame.chessBoard, x, y, color);
            print(PVEGame.chessBoard);
            color=noOperate(PVEGame.chessBoard,color);
        }
    }

    public static void print(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void print(boolean[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static final int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    private static final int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};

    //判断在棋盘范围内
    public static boolean isValid(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }


    //找到敌方棋子
    private static int[][] Enemy(int[][] Board, int color) {
        int[][] enemy = new int[64][2];
        enemy[0][0] = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //颜色相反即为敌人
                if (Board[i][j] == -color)
                    enemy[++enemy[0][0]] = new int[]{0, i, j};
            }
        }
        return enemy;
    }


    //判断能否落子
    public static boolean canFlip(int[][] Board, int x, int y, int color) {
        //包含敌方棋子位置的二维数组
        int[][] k = Enemy(Board, color);
        //变量Flag辅助标记可落子点
        int Flag = 0;
        for (int i = 1; i <= k[0][0]; i++) {
            if (Math.abs(k[i][1] - x) > 1 || Math.abs(k[i][2] - y) > 1)
                continue;
            //遍历八个方向
            for (int j = 0; j < 8; j++) {
                //从 (x, y) 出发，沿着方向 j 是否直接指向一个敌方棋子的位置
                if (x + dx[j] == k[i][1] &&
                        y + dy[j] == k[i][2]) {
                    //沿着方向 j 继续检查，直到遇到边界或己方棋子
                    for (int t = 1; isValid(k[i][1] + t * dx[j], k[i][2] + t * dy[j]); t++) {
                        //计算敌方棋子的坐标
                        int nx = k[i][1] + t * dx[j], ny = k[i][2] + t * dy[j];
                        //检查在 nx, ny 位置的棋子是否为己方棋子
                        if (Board[nx][ny] == color) {
                            Flag = 1;
                            break;
                        } else if (Board[nx][ny] == 0) {
                            break;
                        }
                    }
                }
            }
        }
        return Flag == 1;
    }


    //找到可以落子的位置
    public static boolean[][] canPlace(int[][] Board, int color) {//8*8正数组Board为棋盘状态
        //创建数组flag储存可以落子位置
        boolean[][] flag = new boolean[8][8];
        //遍历棋盘
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //检查位置（i，j）是否是空的
                if (Board[i][j] == 0) {
                    for (int d = 0; d < 8; d++) {
                        if (canFlip(Board, i, j, color)) {
                            flag[i][j] = canFlip(Board, i, j, color);
                        }
                    }
                }
            }
        }
        return flag;
    }


    //判断是否无处可下 需要让手
    public static int noOperate(int[][] Board, int color) {
        boolean[][] valid = canPlace(Board, -color);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //当前方不可下 换对手
                if (valid[i][j]) {
                    return -color;
                }
            }
        }
        return color;
    }


    //落子操作
    public static int[][] Operate(int[][] Board, int x, int y, int color) {
        // 调用 canPlace 获取可以放置棋子的位置
        boolean[][] canPlacePositions = canPlace(Board, color);
        //检查是否可以放棋子
        if (canPlacePositions[x][y]) {
            int[][] k = Enemy(Board, color);
            //遍历Enemy
            for (int i = 1; i <= k[0][0]; i++) {
                //排除中间有空棋
                if (Math.abs(k[i][1] - x) > 1 || Math.abs(k[i][2] - y) > 1)
                    continue;
                //检查八个方向
                for (int j = 0; j < 8; j++) {
                    //从 (x, y) 出发，沿着方向 j 是否直接指向一个敌方棋子的位置
                    if (x + dx[j] == k[i][1] &&
                            y + dy[j] == k[i][2]) {
                        //沿着方向 j 继续检查，直到遇到边界或己方棋子
                        for (int n = 1, m = n; isValid(k[i][1] + n * dx[j], k[i][2] + n * dy[j]); m = n++) {
                            int lx = k[i][1] + n * dx[j],
                                    ly = k[i][2] + n * dy[j];
                            if (Board[lx][ly] == color) {//如果找到本方棋子
                                //翻转
                                while (--m >= 0)
                                    Board[k[i][1] + m * dx[j]][k[i][2] + m * dy[j]] = color;
                                break;
                            } else if (Board[lx][ly] == 0)//找不到本方棋子
                                break;
                        }
                    }
                }
            }
            //将中间的棋子翻转为本方棋子
            Board[x][y] = color;
        }
        return Board;
    }
    //判断游戏结束 分出胜负
    public static int[] Finish(int[][] Board) {
        int scoreBlack = 0;
        int scoreWhite = 0;
        //棋盘上无处可落子
        boolean flag = false;
        //遍历棋盘 计算分数
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Board[i][j] == -1)
                    scoreBlack++;
                else if (Board[i][j] == 1)
                    scoreWhite++;
                else if (Board[i][j] == 0)
                    flag = true;
            }
        }
        //都无处可下
        if (noOperate(Board, -1) == -1 && noOperate(Board, 1) == 1) {
            //棋盘上无处可落子
            flag = false;
        }
        //黑白方分数都不为零，且比赛没结束
        if (flag)
            return new int[]{0, scoreBlack - scoreWhite};
            //黑胜
        else if (scoreBlack > scoreWhite)
            return new int[]{-1, scoreBlack - scoreWhite};
            //白胜
        else if (scoreBlack < scoreWhite)
            return new int[]{1, scoreWhite - scoreBlack};
            //平局
        else
            return new int[]{2, 0};
    }
}






















