package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 1107: ������
 * 1. ���峭 ��ư�� ������ �����ϰ� �̵��Ϸ��� ä�ο� ���� ����� ���ڸ� ���Ѵ�. (���� ���� ������)
 * 2. �̵��Ϸ��� ä�ΰ� ������ ä���� ���̸� ���Ѵ�.
 * 
 * �����̰� ���� ���� �ִ� ä���� 100���̴�.
 * 
 * �õ� 1: dfs
 * =======
 * �̵��Ϸ��� ä���� n�ڸ����, ���̰� ���� ���� n�ڸ� ä���� ���Ϸ��� ������
 * 10
 * 9
 * 1 2 3 4 5 6 7 8 9
 * tc���� ����
 * 
 * 0���� 999999���� ä�ο��� �ּ� ���̸� ���ؾ��Ѵ�. (�̵��Ϸ��� ä���� �ִ� 500000������)
 * ���� �ϳ��� ������ �� ��ư���� Ȯ���Ѵ�(�ִ� 6��)
 * 1000000*6=6�鸸 , �ð� ���� 2��
 * 
 */
public class BOJ_1107_������_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static String toMoveStr;					// �̵��Ϸ��� ä��(���ڿ�)
	static int toMoveInt;						// �̵��Ϸ��� ä��(������)
	static int M;								// ���峭 ��ư ����
	static boolean[] broken;		// ���峭 ��ư ���θ� �����ϴ� �迭
	
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// �̵��Ϸ��� ä�� ������ �Է¹޴´�.
		toMoveStr=br.readLine().trim();
		toMoveInt=Integer.parseInt(toMoveStr);
		
		// ���峭 ��ư ������ �Է¹޴´�.
		M=Integer.parseInt(br.readLine().trim());
		broken=new boolean[10];
		
		if (M>0) {
			// ���峭 ��ư ��ȣ�� �Է¹޴´�.
			st=new StringTokenizer(br.readLine().trim());
			for (int idx=0;idx<M;idx++) {
				int num=Integer.parseInt(st.nextToken());
				broken[num]=true;
			}			
		}
		
		// +, - ��ư�� ������ ���
		int onlyUpDown=Math.abs(toMoveInt-100);
		
		int usingNum=Integer.MAX_VALUE;
		
		// �̵��� �� �ִ� ä�� ���ο� ���� Ž���Ѵ�.(���� ��ư�� ������ ���)
		for (int tmpChannel=0;tmpChannel<=999999;tmpChannel++) {
			
			String tmpChannelStr=Integer.toString(tmpChannel);
			boolean valid=true;
			for (int idx=0;idx<tmpChannelStr.length();idx++) {
				// ���峭 ��ư�� ���
				if (broken[tmpChannelStr.charAt(idx)-'0']) {
					valid=false;
					break;
				}
			}
			// ��ȿ�� ä���� ���, ��ư ������ �ּ� Ƚ�� ���ϱ�
			if (valid) {
				int diff=Math.abs(toMoveInt-tmpChannel);
				diff+=tmpChannelStr.length();			// ä�� ��ȣ �ڸ���
				usingNum=Math.min(usingNum, diff);
			}
			
		}
		System.out.println(Math.min(onlyUpDown, usingNum));
		
		
	}
	
	

}
