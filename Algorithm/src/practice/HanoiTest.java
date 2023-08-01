package practice;
import java.util.*;

public class HanoiTest {
	static StringBuffer sb;
	static int k;
	public static void main(String[] args) {
		sb=new StringBuffer();
		
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		
		hanoi(1,2,3,N);
		System.out.println(k);
		System.out.println(sb);
	}
	
	public static void hanoi(int src,int mid, int dst, int cnt) {
		if (cnt==1) {		// 블록의 개수가 1인 경우
			sb.append(src+" "+dst+"\n");
			k+=1;
			return;
		}else {				// 블록의 개수가 2 이상인 경우
			hanoi(src,dst,mid,cnt-1);		// 위에서부터 N-1 개의 원판을 임시 기둥으로 옮김
			hanoi(src,mid,dst,1);		// 나머지 하나 원판을 dst로 옮김
			hanoi(mid,src,dst,cnt-1);				// 위에서부터 N-1 개의 원판을 dst로 옮김
		}
	}
}
