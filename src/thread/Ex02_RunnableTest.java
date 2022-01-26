package thread;

public class Ex02_RunnableTest{
	
	public static void main(String[] args) {
		
//		MakeCar2 mc1 = new MakeCar2("차틀만들기");
//		Thread t1 = new Thread(mc1);
//		t1.start();
		
		new Thread(new MakeCar2("차틀만들기")).start();
		
		
		new Thread(new MakeCar2("엔진 부착")).start();
		
//		System.out.println("프로그램 종료");  // main함수가 제일먼저 계산이 끝나서 제일 첫줄에 출력
		
	}

}

class MakeCar2 implements Runnable{ // 멀티 작업인것 처럼 보이게 하기 위함
	String work;
	MakeCar2(String work) { // 생성자
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