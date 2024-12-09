import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PVEGame extends JFrame {
    JFrame frame = new JFrame("PVEGame");
    public JPanel leftPanel;
    public JPanel rightPanel;
    public JButton TimeButton, SaveButton, ScoreButton1, ScoreButton2, chessButton;
    JButton ChessButton[][] = new JButton[8][8];
    JLabel Board = new JLabel();
    JLabel chess = new JLabel();
    JLabel avatar1 = new JLabel();
    JLabel avatar2 = new JLabel();
    JLabel timerLabel = new JLabel();
    int count = 0;
    public Timer timer;

    //创建左面板
    public void updateLeftPanel() {
        this.leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 500, 1000);
        frame.setLayout(null);
        leftPanel.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1450, 1000);
        leftPanel.setBackground(Color.white);
        frame.add(leftPanel);
        //创建任务栏按钮
        TimeButton = new JButton();
        SaveButton = new JButton("Save");
        ScoreButton1 = new JButton();
        ScoreButton2 = new JButton();


        // 设置按钮大小和位置
        TimeButton.setBounds(0, 0, 250, 200);
        SaveButton.setBounds(0, 830, 500, 100);
        ScoreButton1.setBounds(100, 200, 400, 100);
        ScoreButton2.setBounds(100, 300, 400, 100);

        //设置按钮颜色
        TimeButton.setBackground(Color.pink);
        SaveButton.setBackground(Color.pink);
        ScoreButton1.setBackground(Color.pink);
        ScoreButton2.setBackground(Color.pink);

        //设置字体颜色
        TimeButton.setForeground(Color.WHITE);
        SaveButton.setForeground(Color.WHITE);
        ScoreButton1.setForeground(Color.WHITE);
        ScoreButton2.setForeground(Color.WHITE);
        //设置字体样式
        Font font = new Font("SanSerif", Font.BOLD, 50);
        TimeButton.setFont(font);
        SaveButton.setFont(font);
        ScoreButton1.setFont(font);
        ScoreButton2.setFont(font);
        //将按钮添加到面板中
        leftPanel.add(TimeButton);
        leftPanel.add(SaveButton);
        leftPanel.add(ScoreButton1);
        leftPanel.add(ScoreButton2);


    }

    //创建右面板
    public void updateRightPanel() {
        this.rightPanel = new JPanel();
        rightPanel.setBounds(470, 0, 1000, 1000);
        rightPanel.setLayout(null);
        Board = new JLabel(new ImageIcon("touhou/Board.png"));
        Board.setBounds(0, 0, 1000, 1000);
        rightPanel.add(Board);
        frame.add(rightPanel);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessButton[i][j] = new JButton();
                ChessButton[i][j].setBounds(90 + 102 * i, 90 + 102 * j, 102, 102);
                rightPanel.add(ChessButton[i][j]);

            }
        }
        frame.setVisible(true);
    }


    //创建计时器
//    public void setTimer() {
//        timerLabel = new JLabel("0");
//        timerLabel.setBounds(0, 0, 250, 200);
//        leftPanel.add(timerLabel);
//        timer.start();
//        timer = new Timer(1000, new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // 更新标签显示计时器进度
//                count++;
//                timerLabel.setText("Time: " + count + " seconds");
//            }
//        });
//
//    }


    public static void main(String[] args) {
        PVEGame a = new PVEGame();
//        a.updateLeftPanel();
        a.updateRightPanel();


  }

}