package practice;
import java.util.*;

public class HanoiTest {
	static StringBuffer sb;
	public static void main(String[] args) {
		sb=new StringBuffer();
		
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		
		hanoi(1,2,3,N);
	}
	
	public static void hanoi(int src,int mid, int dst, int cnt) {
		if (cnt==1) {		// ����� ������ 1�� ���
			sb.append(src+" "+dst);
			return;
		}else {				// ����� ������ 2 �̻��� ���
			
		}
	}
}
