package io.readerwriter;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ReaderWriterTest
{
	public static void main( String args[])
	{
		UIForm3 ui = new UIForm3();
		ui.addLayout();
		ui.eventProc();	
	}	
}

//========================================
//	화면을 관리하는 클래스 
//----------------------------------------
class UIForm3 extends JFrame
{
	JTextArea	ta;
	JButton 		bSave, bLoad, bClear;
	
	UIForm3()
	{
		ta		= new JTextArea();		
		bSave 	= new JButton("파일저장");
		bLoad	= new JButton("파일읽기");
		bClear	= new JButton("화면지우기");
	}
	
	void addLayout()
	{
		JPanel pCenter 	= new JPanel();
		pCenter.setLayout( new BorderLayout() );
//		pCenter.add("Center", ta ); //옛날방식
		pCenter.add(new JScrollPane(ta), BorderLayout.CENTER);
	
		JPanel pSouth	= new JPanel();
		pSouth.add( bSave );
		pSouth.add( bLoad );
		pSouth.add( bClear );
		
		getContentPane().add("Center", pCenter );
		getContentPane().add("South",  pSouth );
		
		setSize( 400, 350 );
		setVisible( true );
		
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	
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
		
		bClear.addActionListener(new ActionListener() { 
			public void actionPerformed( ActionEvent ev ){
				ta.setText(null);
			}
		});	
		
	}
	

	
	void saveData() {
		JFileChooser fd = new JFileChooser(); // file 선택창
		int returnValue = fd.showSaveDialog( null ); //
		if( returnValue == JFileChooser.APPROVE_OPTION )
		{
			File f = fd.getSelectedFile(); 
			try{
				
				FileWriter out = new FileWriter(f); // 어떤 파일명으로 저장할지
				
				out.write(ta.getText()); // 파일명에다가 ta 저장하기
				
				out.close();
			}catch(Exception ex){
				System.out.println("저장 실패");
			}	
		}
	}
	
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
	

}