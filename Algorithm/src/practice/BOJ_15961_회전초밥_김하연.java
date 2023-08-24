package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * BOJ 15961: ȸ�� �ʹ�
 * ������ ������� �ʰ� �����ؼ� �Դ� 4���� �ʹ��� ������ ���Ѵ�.
 * ������ ����ؼ� �߰��� ���� �� �ִ� �ʹ���� ����Ͽ� �ʹ� �������� �ִ��� ���Ѵ�.
 * 
 * �ʹ� �������� ����ϱ� ���� set �ڷᱸ���� Ȱ���Ѵ�.
 * 
 */
public class BOJ_15961_ȸ���ʹ�_���Ͽ� {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;					// ȸ�� �ʹ� ��Ʈ�� ���� ������ ��
	static int d;					// �ʹ��� ������
	static int k;					// �����ؼ� �Դ� ������ ��
	static int c;					// ���� ��ȣ
	
	static int[] sushi;				// ȸ�� �ʹ� ��Ʈ�� ���� �ʹ��� ����
	
	static int[] eaten;				// �ش� ��ġ�� �ʹ��� �� �� �Ծ����� �����ϴ� �迭
	static int result=0;
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// ������ ��, �ʹ��� ������, �����ؼ� �Դ� ������ ��, ���� ��ȣ�� �Է� �޴´�.
		st=new StringTokenizer(br.readLine().trim());
		N=Integer.parseInt(st.nextToken());
		d=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken())-1;
		
		// N���� �ٿ� ��Ʈ�� �� ��ġ���� �����Ͽ� ȸ�� ������ ���󰣴�.
		sushi=new int[N];
		eaten=new int[d];
		
		for (int idx=0;idx<N;idx++) {
			sushi[idx]=Integer.parseInt(br.readLine().trim())-1;
		}
		
		int cnt=0;
		for (int idx=0;idx<k;idx++) {
			// ���ݱ��� �� �Ծ ���� ������ ���
			if (eaten[sushi[idx]]==0) {
				// ���� ������ �ø���.
				cnt+=1;
			}
			eaten[sushi[idx]]+=1;
		}
		
		for (int idx=0;idx<N;idx++) {
			
			// ���� �ʹ� ���� ������ �ִ��� ���� ���
			if (cnt>=result) {
				// ���� �ʹ� Ȯ��
				if (eaten[c]==0) result=cnt+1;
				else result=cnt;
			
			}
			// �� ó�� �ʹ� �ϳ� ����
			eaten[sushi[idx]]-=1;
			if (eaten[sushi[idx]]==0) cnt-=1;
			
			// �� ���� �ʹ� �ϳ� �ֱ�
			int end=(idx+k)%N;
			if (eaten[sushi[end]]==0) cnt++;
			eaten[sushi[end]]+=1;
		}
		System.out.println(result);
	}
	

}
