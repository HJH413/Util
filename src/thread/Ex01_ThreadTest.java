package thread;

public class Ex01_ThreadTest {
	public static void main(String[] args) {
		
		MakeCar1 mc1 = new MakeCar1("차틀만들기");
		mc1.start(); // Thread 에서 run method 호출 할때 사용하는 start 함수 
		
		MakeCar1 mc2 = new MakeCar1("엔진부착");
		mc2.start();
		
		System.out.println("프로그램 종료");  // main함수가 제일먼저 계산이 끝나서 제일 첫줄에 출력
		
		
	}
} // end of class

class MakeCar1 extends Thread{ // 멀티 작업인것 처럼 보이게 하기 위함
	String work;
	MakeCar1(String work) {
		this.work = work;
	}
	
	public void run () {  // Thread class run method overriding
		for(int i = 0 ; i<5 ; i++) {
			System.out.println(work + " 작업중");
			try {
				Thread.sleep(500); // static 0.0001s단위
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

