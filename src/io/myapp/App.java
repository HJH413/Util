package io.myapp;

import javax.swing.*;
import java.awt.*;

class AppFrame extends JFrame{
    JFrame jFrameTitle;
    JTextField jtName, jtRsn, jtAge, jtFrom, jtSex;
    JTextArea jtextArea;
    JButton jbSave, jbOpen, jbCheck, jbClear;

    public AppFrame() {
        jFrameTitle = new JFrame("입력");
        jtName = new JTextField(10);
        jtRsn = new JTextField(10);
        jtAge = new JTextField(10);
        jtFrom = new JTextField(10);
        jtSex = new JTextField(10);

        jbSave = new JButton("저장하기");
        jbOpen = new JButton("불러오기");
        jbCheck = new JButton("확인");
        jbClear = new JButton("초기화");

        jtextArea = new JTextArea(100,100);
    }

    public void addLayout() {

        jFrameTitle.setLayout(new BorderLayout());

        jFrameTitle.add(jtextArea, BorderLayout.CENTER);

        JPanel main = new JPanel();
        main.setLayout(new GridLayout(5,2));
        main.add(new JLabel("이름"));
        main.add(jtName);
        main.add(new JLabel("주민번호"));
        main.add(jtRsn);
        main.add(new JLabel("나이"));
        main.add(jtAge);
        main.add(new JLabel("출생지"));
        main.add(jtFrom);
        main.add(new JLabel("성별"));
        main.add(jtSex);
        add(main, BorderLayout.WEST);

        JPanel button = new JPanel();
        button.setLayout(new GridLayout(1,4));
        button.add(jbSave);
        button.add(jbOpen);
        button.add(jbCheck);
        button.add(jbClear);
        add(button, BorderLayout.SOUTH);

        setSize(700,300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }




}

public class App {
    public static void main(String[] args) {
        AppFrame myApp = new AppFrame();
        myApp.addLayout();

    }
}
