import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class save {
    static int saveArray[][][]=new int[64][8][8];
    public static void save(){
        System.arraycopy(PVEGame.chessBoard,0,saveArray[PVEGame.step],0,8);
        Logic.print(saveArray[PVEGame.step]);
        System.out.println("now is step:"+PVEGame.step);
    }

    public static void ArrayToFile(){
        try (BufferedWriter myWriter = new BufferedWriter(new FileWriter(LaunchPanel.storedText1+"game.txt"))) {
                myWriter.write(String.valueOf(PVEGame.step));
                myWriter.newLine();
                for (int j = 0; j < saveArray[PVEGame.step].length; j++) {
                    for (int k = 0; k < saveArray[PVEGame.step][j].length; k++) {
                        myWriter.write(String.valueOf(saveArray[PVEGame.step][j][k]));
                    }
                    myWriter.newLine();
                }
                myWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void CreateFile(){
        try{
            File step=new File(LaunchPanel.storedText1+"game.txt");
            if(step.createNewFile()){
                System.out.println("File created");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}





