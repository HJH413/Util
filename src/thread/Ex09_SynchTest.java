package thread;

class Count {
	int i;
	void increment() {
		synchronized(this) {i++;} 	//synchronized (객체) { } 블럭 생성 보통 (this) 이렇게 사용 {}
//		1. read i 
//		2. i + 1
//		3. write i
// synchronized <- 먼저한 쓰레드를 작업이 끝낸뒤에 다음 쓰레드 작업 시작
	} // method 함수
	
}

class ThreadCount extends Thread {
	
	Count cnt;
	
	ThreadCount(Count cnt) {
		this.cnt = cnt;
	}
	
	public void run() {
		for(int i=0; i<100000000; i++) {
			cnt.increment();
		}
	}
	
}

public class Ex09_SynchTest {
	public static void main(String[] args) {
		
		Count count = new Count(); // Count 변수 선언
		
		ThreadCount tc1 = new ThreadCount(count); 
		tc1.start();
		
		ThreadCount tc2 = new ThreadCount(count);
		tc2.start();
		
		try {
			tc1.join();
			tc2.join();
		} catch (Exception ex) {
			
		}
		
		
		
		
		System.out.println("최종 i의 결과 : " + count.i);
		
	}
}
