package network1.basic;

import java.io.*;
import java.net.*;

public class SimpleServer {
											// System portNumber : 0~1023 건들면 안됨!
	public final static int PORT = 5000;	// PortNumber : 1024 ~ 2의 16제곱 까지 사용가능
	
	public static void main( String args[] ) {
		
		ServerSocket 		server = null;
		DataInputStream 	in = null;
		DataOutputStream out = null;
		Socket clientSocket = null;
		
		try{
			// 1. 서버 소켓 생성
			server = new ServerSocket( PORT );
			System.out.println("SimpleServer started..");
		
			// 2. 클라이언트 접속시 소켓 생성
			// 3. 소켓의 입출력 스트림 얻기
			clientSocket = server.accept(); 
			in = new DataInputStream( clientSocket.getInputStream()); // getInputStream byte형으로 읽어오면 불편해서 필터링으로 DataInputStream 사용
			out = new DataOutputStream( clientSocket.getOutputStream() );
		
			// 4. 데이터 전송
				String line = in.readUTF();
				System.out.println("짝궁에게 받은 말 : " + line );
				if( line.compareTo("안녕") == 0 ) {  // line.equals("안녕")
					out.writeUTF("배고프다." );	
				} else {
					out.writeUTF("인사말이 아닙니다.");
				}
		
			// 5. 소켓닫기
			in.close();
			out.close();
			clientSocket.close();
		} catch ( Exception ex ) {
			System.out.println( ex.getMessage() );	
		}
	}	
}