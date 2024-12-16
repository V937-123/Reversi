import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LaunchPanel extends JFrame {
    public JPanel NorthPanel;
    public JPanel WestPanel;
    public JPanel textPanel;
    public JPanel humanPanel;
    public JButton gameButton, saveButton, quitButton, computerButton, humanButton, startButton;
    JFrame frame = new JFrame("游戏主页面");
     int currentImageIndex;
     List<ImageIcon> imageList = new ArrayList<>();
    JLabel Image1 = new JLabel();
    JLabel Image2 = new JLabel();
    public static String storedText1 ;
    public static String storedText2 ;
    public static int storedAvatar;
    int mode=1;
    JTextField textField1 = new JTextField();
    JLabel text1 = new JLabel("User:");
    JTextField textField2 = new JTextField();
    JLabel text2 = new JLabel("The Maximum time:");
    JTextField textField3 = new JTextField();
    JLabel text3 = new JLabel("User1:");
    JLabel text4 = new JLabel("User2:");
    public void updateNorthPanel() {
        this.NorthPanel = new JPanel();
        NorthPanel.setBounds(0, 0, 1500, 150);
        NorthPanel.setLayout(null);
        frame.setLayout(null);
        NorthPanel.setBackground(Color.pink);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0,0,1500, 1000);
        frame.add(NorthPanel);
//创建第一行任务栏按钮
        gameButton = new JButton("Game");
        saveButton = new JButton("Save");
        quitButton = new JButton("Quit");

//设置按钮位置/大小
        gameButton.setBounds(0, 0, 500, 150);
        saveButton.setBounds(500, 0, 500, 150);
        quitButton.setBounds(1000, 0, 500, 150);
//设置字体颜色
        gameButton.setForeground(Color.pink);
        saveButton.setForeground(Color.pink);
        quitButton.setForeground(Color.pink);

//设置字体样式
        Font font = new Font("SanSerif", Font.BOLD, 60);
        gameButton.setFont(font);
        saveButton.setFont(font);
        quitButton.setFont(font);

//将按钮添加到面板中
        NorthPanel.add(gameButton);
        NorthPanel.add(saveButton);
        NorthPanel.add(quitButton);


//添加点击事件处理逻辑
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
//更新左面板
    public void updateWestPanel() {
        this.WestPanel = new JPanel();
        WestPanel.setBounds(0, 150, 500, 850);
        frame.setLayout(null);
        WestPanel.setLayout(null);
        WestPanel.setBackground(Color.white);
        frame.add(WestPanel);
//创建第二行任务栏按钮
        computerButton = new JButton("Computer");
        humanButton = new JButton("Human");
        startButton = new JButton("Game Start!");
//设置按钮大小/位置
        computerButton.setBounds(0, 0, 250, 100);
        humanButton.setBounds(250, 0, 250, 100);
        startButton.setBounds(100, 500, 300, 100);
//设置字体颜色
        computerButton.setForeground(Color.pink);
        humanButton.setForeground(Color.pink);
        startButton.setForeground(Color.pink);
//设置字体样式
        Font font2 = new Font("SanSerif", Font.BOLD, 30);
        Font font1 = new Font("SanSerif", Font.BOLD, 40);
        computerButton.setFont(font2);
        humanButton.setFont(font2);
        startButton.setFont(font1);
//将按钮添加到面板中
        WestPanel.add(computerButton);
        WestPanel.add(humanButton);
        WestPanel.add(startButton);

//点击图片切换图片
        List<ImageIcon> imageList = new ArrayList<>();
        imageList.add(new ImageIcon("touhou/Avatar/avatar/chicken1.jpg"));
        imageList.add(new ImageIcon("touhou/Avatar/avatar/chicken2.jpg"));
        imageList.add(new ImageIcon("touhou/Avatar/avatar/chicken3.jpg"));
        // 初始化当前图片索引
        JLabel Image = new JLabel(imageList.get(currentImageIndex));
        JLabel Image1 = new JLabel(imageList.get(currentImageIndex));
        JLabel Image2 = new JLabel(imageList.get(currentImageIndex));
        Image.setBounds(130, 200, 250, 250);
        Image1.setBounds(10, 200, 250, 250);
        Image2.setBounds(240, 200, 250, 250);

        Image.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 点击时切换图片
                currentImageIndex = (currentImageIndex + 1) % imageList.size();
                Image.setIcon(imageList.get(currentImageIndex));
                storedAvatar=currentImageIndex;
            }
        });
        Image1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 点击时切换图片
                currentImageIndex = (currentImageIndex + 1) % imageList.size();
                Image1.setIcon(imageList.get(currentImageIndex));
            }
        });
        Image2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 点击时切换图片
                currentImageIndex = (currentImageIndex + 1) % imageList.size();
                Image2.setIcon(imageList.get(currentImageIndex));

            }
        });

        WestPanel.add(Image);
        //添加点击事件处理逻辑
        computerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WestPanel.removeAll();
                WestPanel.add(Image);
                WestPanel.add(computerButton);
                WestPanel.add(humanButton);
                WestPanel.add(startButton);
                WestPanel.repaint();
                textPanel.remove(text3);
                textPanel.remove(text4);
                textPanel.remove(textField3);
                textPanel.add(text1);
                textPanel.repaint();
                mode=-mode;

            }
        });
        humanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WestPanel.removeAll();
                WestPanel.add(Image1);
                WestPanel.add(Image2);
                WestPanel.add(computerButton);
                WestPanel.add(humanButton);
                WestPanel.add(startButton);
                WestPanel.repaint();
                textPanel.add(textField3);
                textPanel.remove(text1);
                textPanel.add(text3);
                textPanel.add(text4);
                textPanel.repaint();
                mode=-mode;

            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PVEGame a = new PVEGame();
                a.updateLeftPanel();
                a.updateRightPanel();
                PVEGame.arrangeChess();
            }
        });

    }


    //设置登录文本框
    public void TextPanel() {
        this.textPanel = new JPanel();
        textPanel.setLayout(null);
        textPanel.setBounds(500, 150, 1000, 850);
        textPanel.setBackground(Color.PINK);
        JButton complete = new JButton("Complete");
        complete.setBounds(400, 400, 200, 50);
        textPanel.add(complete);
//        JTextField textField1 = new JTextField();
//        JLabel text1 = new JLabel("User:");
//        JTextField textField2 = new JTextField();
//        JLabel text2 = new JLabel("The Maximum time:");
//        JTextField textField3 = new JTextField();
//        JLabel text3 = new JLabel("User1:");
//        JLabel text4 = new JLabel("User2:");
        textPanel.add(text1);
        textPanel.add(text2);
        text1.setBounds(250, 200, 200, 50);
        text3.setBounds(250, 100, 200, 50);
        text4.setBounds(250, 200, 200, 50);
        text2.setBounds(150, 300, 200, 50);
        text1.setFont(new Font("SanSerif", Font.BOLD, 20));
        text2.setFont(new Font("SanSerif", Font.BOLD, 20));
        text3.setFont(new Font("SanSerif", Font.BOLD, 20));
        text4.setFont(new Font("SanSerif", Font.BOLD, 20));
        textField1.setBounds(350, 200, 300, 50);
        textField1.setFont(new Font("SanSerif", Font.BOLD, 20));
        textField2.setBounds(350, 300, 300, 50);
        textField2.setFont(new Font("SanSerif", Font.BOLD, 20));
        textField3.setBounds(350, 100, 300, 50);
        textField3.setFont(new Font("SanSerif", Font.BOLD, 20));
        textPanel.add(textField1);
        textPanel.add(textField2);
        frame.add(textPanel);
        frame.setVisible(true);
        complete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 从 JTextField 中获取文本
                String text1 = textField1.getText();
                String text2 = textField2.getText();
                // 存储文本到变量中
                 storedText1 = text1;
                 storedText2 = text2;
            }
        });
    }

}






