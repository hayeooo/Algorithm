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
		if (cnt==1) {		// ����� ������ 1�� ���
			sb.append(src+" "+dst+"\n");
			k+=1;
			return;
		}else {				// ����� ������ 2 �̻��� ���
			hanoi(src,dst,mid,cnt-1);		// ���������� N-1 ���� ������ �ӽ� ������� �ű�
			hanoi(src,mid,dst,1);		// ������ �ϳ� ������ dst�� �ű�
			hanoi(mid,src,dst,cnt-1);				// ���������� N-1 ���� ������ dst�� �ű�
		}
	}
}
