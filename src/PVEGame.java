import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PVEGame extends JFrame {
    JFrame frame = new JFrame("PVEGame");
    public JPanel leftPanel;
    public JPanel rightPanel;
    public JButton TimeButton, SaveButton, ScoreButton1, ScoreButton2, StepButton;
     JButton ChessButton[][] = new JButton[8][8];
    JLabel Board = new JLabel();
    JLabel chess = new JLabel();
    JLabel avatar1 = new JLabel();
    JLabel avatar2 = new JLabel();
    JLabel timerLabel = new JLabel();
    JLabel score1=new JLabel();
    JLabel score2=new JLabel();
    JLabel chessPattern[][]=new JLabel[8][8];
    int count = 0;
    public Timer timer;
    java.util.List<ImageIcon> chessImageList = new ArrayList<>();
    int currentImageIndex;
    int chessBoard[][]=new int[][]{
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,1,-1,0,0,0},
            {0,0,0,-1,1,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0}
    };



    //创建左面板
    public void updateLeftPanel() {
        this.leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 500, 1000);
        frame.setLayout(null);
        leftPanel.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1000);
        leftPanel.setBackground(Color.white);
        frame.add(leftPanel);
        //创建任务栏按钮
        TimeButton = new JButton();
        SaveButton = new JButton("Save");
        ScoreButton1 = new JButton();
        ScoreButton2 = new JButton();
        StepButton=new JButton();

        // 设置按钮大小和位置
        TimeButton.setBounds(0, 0, 250, 200);
        SaveButton.setBounds(300, 400, 200, 200);
        ScoreButton1.setBounds(100, 200, 400, 100);
        ScoreButton2.setBounds(100, 300, 400, 100);
        StepButton.setBounds(300,600,200,300);

        //设置按钮颜色
        TimeButton.setBackground(Color.pink);
        SaveButton.setBackground(Color.pink);
        ScoreButton1.setBackground(Color.pink);
        ScoreButton2.setBackground(Color.pink);
        StepButton.setBackground(Color.pink);

        //设置字体颜色
        TimeButton.setForeground(Color.WHITE);
        SaveButton.setForeground(Color.WHITE);
        ScoreButton1.setForeground(Color.WHITE);
        ScoreButton2.setForeground(Color.WHITE);
        StepButton.setForeground(Color.WHITE);
        //设置字体样式
        Font font = new Font("SanSerif", Font.BOLD, 50);
        TimeButton.setFont(font);
        SaveButton.setFont(font);
        ScoreButton1.setFont(font);
        ScoreButton2.setFont(font);
        StepButton.setFont(font);
        //将按钮添加到面板中
        leftPanel.add(TimeButton);
        leftPanel.add(SaveButton);
        leftPanel.add(ScoreButton1);
        leftPanel.add(ScoreButton2);
        leftPanel.add(StepButton);
        //添加计时器
        timerLabel = new JLabel("0");
        timerLabel.setBounds(0, 0, 250, 200);
        timerLabel.setFont(font);
        timerLabel.setForeground(Color.WHITE);
        TimeButton.add(timerLabel);
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 更新标签显示计时器进度
                count++;
                timerLabel.setText(+count + "s");
            }
        });
        timer.start();
//添加头像
        java.util.List<ImageIcon> imageList = new ArrayList<>();
        imageList.add(new ImageIcon("touhou/Avatar/avatar/smallchicken1.jpg"));
        imageList.add(new ImageIcon("touhou/Avatar/avatar/smallchicken2.jpg"));
        imageList.add(new ImageIcon("touhou/Avatar/avatar/smallchicken3.jpg"));
        avatar1=new JLabel(imageList.get(LaunchPanel.storedAvatar));
        avatar2=new JLabel(imageList.get(0));
        avatar1.setBounds(0,200,100,100);
        avatar2.setBounds(0,300,100,100);
        leftPanel.add(avatar1);
        leftPanel.add(avatar2);

//添加分数
        score1=new JLabel(LaunchPanel.storedText1+":");
        score2=new JLabel("Computer:");
        score1.setBounds(100,200,400,100);
        score2.setBounds(100,300,400,100);
        ScoreButton1.add(score1);
        ScoreButton2.add(score2);
    }

    //创建右面板
    public void updateRightPanel() {
        this.rightPanel = new JPanel(new GridLayout(8, 8));
        rightPanel.setBounds(470,-20 , 1000, 1000);
        rightPanel.setOpaque(false);
        rightPanel.setLayout(null);
        Board = new JLabel(new ImageIcon("touhou/Board.png"));
        Board.setBounds(0, -20, 950, 950);
        rightPanel.add(Board);
        frame.add(rightPanel);
        currentImageIndex=0;
        chessImageList.add(new ImageIcon("touhou/Black.png"));
        chessImageList.add(new ImageIcon("touhou/White.png"));
        chess=new JLabel(chessImageList.get(currentImageIndex));
        chess.setBounds(280,0,200,200);
        leftPanel.add(chess);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessButton[i][j] = new JButton();
                ChessButton[i][j].setContentAreaFilled(false); // 设置内容区域不填充
                ChessButton[i][j].setBorderPainted(false); // 设置无边框
                ChessButton[i][j].setOpaque(false); // 设置按钮不透明
                ChessButton[i][j].setFocusPainted(false);
                ChessButton[i][j].setBounds(84 + 97 * i, 65 + 97 * j, 97, 97);
                ChessButton[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        currentImageIndex = (currentImageIndex + 1) % chessImageList.size();
                        chess.setIcon(chessImageList.get(currentImageIndex));
                    }
                });
                rightPanel.add(ChessButton[i][j],0);
            }
        }
        frame.setVisible(true);

    }

    public void arrangeChess(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.println(chessBoard[i][j]);
                if (chessBoard[i][j]==1){
                     ChessButton[i][j].setIcon(new ImageIcon("touhou/smallblack.png"));
                } else if (chessBoard[i][j]==-1) {
                     ChessButton[i][j].setIcon(new ImageIcon("touhou/smallWhite.png"));


                }
            }
        }

    }





}