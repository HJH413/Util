package io.objectstream;


import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ObjectStreamTest
{
	public static void main( String [] args )
	{
		UIForm  ui = new UIForm();
		ui.addToLayout();
		ui.eventProc();
	}
}


//-----------------------------------------
// 화면을 관리하는 클래스
class UIForm extends JFrame
{
	// 데이타를 저장할 변수
	String name;
	int		age;
	double 	height;
	char	bloodType;
	
	// 화면 GUI에 관련한 변수
	JTextField tfName, tfAge, tfHeight, tfBloodType;
	JButton	   bSave, bLoad;
	
	
	UIForm()
	{
		tfName 		= new JTextField(10);
		tfAge 		= new JTextField(10);
		tfHeight 	= new JTextField(10);
		tfBloodType = new JTextField(10);
		
		bSave		= new JButton("저장하기", new ImageIcon("../img/save.gif"));
		bLoad		= new JButton("읽어오기", new ImageIcon("../img/load.gif"));
		
	}
	
	// 화면 구성하는 메소
	void addToLayout()
	{
		JPanel pCenter = new JPanel();
		pCenter.setLayout( new GridLayout(5 ,2,10,10) );
		pCenter.add( new JLabel("이름") );
		pCenter.add( tfName );
		pCenter.add( new JLabel("나이") );
		pCenter.add( tfAge );
		pCenter.add( new JLabel("신장") );
		pCenter.add( tfHeight );
		pCenter.add( new JLabel("혈액형") );
		pCenter.add( tfBloodType );
		
		pCenter.add( bSave );
		pCenter.add( bLoad );
		
		getContentPane().add("Center", pCenter);
		
		setSize( 400, 300 );
		setVisible( true );
		
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

	}
	
	
	// 이벤트 처리하는 메소드 
	void eventProc()
	{
		// "저장하기" 버튼이 눌렸을 
		bSave.addActionListener(new ActionListener() { 
			public void actionPerformed( ActionEvent ev ){
				saveData();
			}
		});
		
		// "읽어오기" 버튼이 눌렸을 때
		bLoad.addActionListener(new ActionListener() { 
			public void actionPerformed( ActionEvent ev ){
				readData();
			}
		});			
	}

	void saveData() {
		/*
		1. 텍스트필드에서 입력값 얻어와서 변수에 저장
		2. 파일출력스트림 생성 ( filter 포함 )
		3. 스트림에 출력
		4. 스트림 닫기
		5. 텍스트필드 초기화 
		*/
		name		= tfName.getText();
		age			= Integer.parseInt( 	tfAge.getText() );
		height		= Double.parseDouble(	tfHeight.getText() );
		bloodType 	= (tfBloodType.getText()).charAt(0);
		
		/** 
		 * @@@@@@@@@@@@@@@@@@@@@@@@@
		 * */
		try{

			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data2.txt"));
			Record r = new Record(name, age, height, bloodType);
			oos.writeObject(r);  //하나의 덩어리를 만들어서 저장
			
			oos.close();
		}catch( Exception ex ){ 
			System.out.println("쓰기 실패" + ex.getMessage());
			ex.printStackTrace(); // 모든 문제를 출력하세요
		}
		
		tfName.setText("");
		tfAge.setText("");
		tfHeight.setText("");
		tfBloodType.setText("");
			
		
	}
	
	void readData() {
		/*
		1. 파일입력스트림 생성 (filter 포함 )
		2. 스트림에서 데이타 읽어서 변수에 저장
		3. 텍스트필드에  출력
		4. 스트림 닫기 			
		*/
			try{
				
				ObjectInputStream oos = new ObjectInputStream(new FileInputStream("data2.txt")); // 자바로 데이터불러오기
				Record r = (Record)oos.readObject(); // 역 직렬화 (Record)casting
				
				
				
				// 텍스트 나열
				
				tfName.setText(name);
//				tfName.setText(r.getName());
				
//				tfAge.setText(Integer.toString(age));  // 정수형 스트링으로 
				tfAge.setText(String.valueOf(age));
//				tfAge.setText(String.valueOf(r.getAge()));
				
//				tfHeight.setText(Double.toString(height)); // 더블형 스트링으로
				tfHeight.setText(String.valueOf(height));
//				tfHeight.setText(String.valueOf(r.getHeight()));
				
				
//				tfBloodType.setText(Character.toString( bloodType)); // 문자열 스트링으로
				tfBloodType.setText(String.valueOf(bloodType));
//				tfHeight.setText(String.valueOf(r.getBloodType()));
				
				oos.close();
			}catch( Exception ex ){
				System.out.println("읽기 실패");
			}

	}
	

}