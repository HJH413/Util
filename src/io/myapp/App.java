package io.myapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;

public class App {

        JFrame jFrameTitle;
        JTextField jtName, jtRsn, jtAge, jtFrom, jtSex;
        JTextArea jtextArea;
        JButton jbSave, jbOpen, jbReturn, jbClear, jbExit;

        public App() {
            jFrameTitle = new JFrame("test");

            jtName = new JTextField(10);
            jtRsn = new JTextField(10);
            jtAge = new JTextField(10);
            jtFrom = new JTextField(10);
            jtSex = new JTextField(10);

            jtextArea = new JTextArea(100,100);

            jbSave = new JButton("저장하기");
            jbOpen = new JButton("불러오기");
            jbReturn = new JButton("되돌리기");
            jbClear = new JButton("초기화");
            jbExit = new JButton("종료");

        }

        void addLayout() {

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
            jFrameTitle.add(main, BorderLayout.WEST);

            JPanel button = new JPanel();
            button.setLayout(new GridLayout(1,5));
            button.add(jbSave);
            button.add(jbOpen);
            button.add(jbReturn);
            button.add(jbClear);
            button.add(jbExit);
            jFrameTitle.add(button, BorderLayout.SOUTH);

            jFrameTitle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrameTitle.setSize(500,300);
            jFrameTitle.setVisible(true);

        } // end of Frame

        void eventProc() {

            // "저장하기" 버튼이 눌렸을
            jbSave.addActionListener(new ActionListener() {
                public void actionPerformed( ActionEvent ev ){
                    saveData();
                }
            });

            void saveData() {
                JFileChooser fd = new JFileChooser(); // file 선택창
                int returnValue = fd.showSaveDialog( null ); //
                if( returnValue == JFileChooser.APPROVE_OPTION )
                {
                    File f = fd.getSelectedFile();
                    try{

                        FileWriter out = new FileWriter(f); // 어떤 파일명으로 저장할지

                        out.write(jtName.getText()); // 파일명에다가 ta 저장하기
                        out.write(jtRsn.getText());
                        out.write(jtAge.getText());
                        out.write(jtFrom.getText());
                        out.write(jtSex.getText());
                        out.write(jtextArea.getText());

                        out.close();
                    }catch(Exception ex){
                        System.out.println("저장 실패");
                    }
                }
            }

            // "읽어오기" 버튼이 눌렸을 때
            jbOpen.addActionListener(new ActionListener() {
                public void actionPerformed( ActionEvent ev ){
                    readData();
                }
            });

            void readData() {
                JFileChooser fd = new JFileChooser();
                int returnValue = fd.showOpenDialog(null);
                if( returnValue == JFileChooser.APPROVE_OPTION )
                {
                    File f = fd.getSelectedFile();
                    try{
                        FileReader in = new FileReader(f); // 어떤파일 읽을지
                        char [] result = new char[1024*1024]; // 불러올 문자열 배열 크기
                        in.read(result);
                        in.close();
                        ta.setText(String.valueOf(result) ); // ta에 불러온 파일 내용 적기
                    }catch(Exception ex){
                        System.out.println("불러오기 실패");
                    }
                }

            }

            // 주민번호 입력창 (jtRsn) 에서 엔터 쳤을 때
            jtRsn.addActionListener(new ActionListener(){ //Java만든 거를 불러와서

                public void actionPerformed(ActionEvent e) { // 오버라이딩
                    String id = jtRsn.getText();

                    // 성별을 구해서
                    char sex = id.charAt(7); // 문자열에 몇 번째 문자 불려오기 0부터 시작

                    if(sex == '1' || sex == '3') {
                        jtSex.setText("남자");
                    } else {
                        jtSex.setText("여자");
                    }

                    // 출신지를 구해서
                    char home = id.charAt(8);
                    // 0 -서울
                    // 1 -광역시
                    // 2 -경기
                    // 3 -지방
                    // 9 -제주도
                    switch (home) { // 문자, 정수, 스트링 변수
                        case '0':
                            jtFrom.setText("서울");
                            break;
                        case '1':
                            jtFrom.setText("광역시");
                            break;
                        case '2':
                            jtFrom.setText("경기");
                            break;
                        case '3':
                            jtFrom.setText("지방");
                            break;
                        case '9':
                            jtFrom.setText("제주도");
                            break;
                        default:
                            System.out.println("알수없음");
                            break;
                    }

                    //-----------------------
                    // 나이를 구해서

                    Calendar now = Calendar.getInstance();
                    int year = now.get(Calendar.YEAR);

                    String idage = id.substring(0,2);  // 몇 번째 부터 몇 번까지
                    if(sex == '1' || sex == '2') {
                        String year1 = "19" ;
                        int intage1 = year-(Integer.parseInt(year1+idage))+1;
                        jtAge.setText(Integer.toString(intage1));
                    } else if(sex == '3' || sex == '4') {
                        String  year2 = "20" ;
                        int intage2 = year-(Integer.parseInt(year2+idage))+1;
                        jtAge.setText(Integer.toString(intage2));
                    }



                }
            }); // end of addActionListener

            //초기화버튼이 눌렸을 때
            jbClear.addActionListener(new ActionListener(){ //Java만든 거를 불러와서

                public void actionPerformed(ActionEvent e) { // 오버라이딩
                    jtName.setText(null);
                    jtRsn.setText(null);
                    jtSex.setText(null);
                    jtAge.setText(null);
                    jtFrom.setText(null);
                    jtextArea.setText(null);
                }
            }); // end of addActionListener

            //종료버튼이 눌렀을 때 프로그램 종료함
            jbExit.addActionListener(new ActionListener(){ //Java만든 거를 불러와서

                public void actionPerformed(ActionEvent e) { // 오버라이딩
                    System.exit(0); // 종료
                }
            }); // end of addActionListener
        }

    public static void main(String[] args) {
        App myApp = new App();
        myApp.addLayout();
        myApp.eventProc();
    } // end of main
} // end of class
