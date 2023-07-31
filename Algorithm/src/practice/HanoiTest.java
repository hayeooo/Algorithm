package practice;
import java.util.*;

public class HanoiTest {
	static StringBuffer sb;
	public static void main(String[] args) {
		sb=new StringBuffer();
		
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		
		hanoi(1,3,N);
	}
	
	public static void hanoi(int src, int dst, int cnt) {
		if (cnt==1 && dst==3) {		// 블록의 개수가 1인 경우
			sb.append(src+" "+dst);
			return;
		}else {				// 블록의 개수가 2 이상인 경우
			if (src==1) {
				hanoi(src,2,cnt-1);
				hanoi(src,3,1);
			}else if(src==2) {
				hanoi(src,1,cnt-1);
				hanoi(src,3,1);
			}else {
				
			}
		}
	}
}
