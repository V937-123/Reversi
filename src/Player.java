public class Player {
    //用户名
    String playerName1 = "NAME1";
    String playerName2 = "NAME2";
    //游戏次数
    int gameCount = 0;
    //胜场数
    int winCount = 0;
    //得分
    int scorecount = 0;


    //计算胜率
    public double winRate(){
        return gameCount == 0 ? 0 :(double) winCount / gameCount;
    }


}
