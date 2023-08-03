package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 12891 : DNA ��й�ȣ
 * 1. ���Ƿ� ���� ���ڿ� ���� S�� ����� �κй��ڿ� ���� P �Է� �ޱ�
 * 2. ù ��° ���ں��� �κй��ڿ� ���̱��� ������ {'A','C','G','T} ���� ����
 * 3. �κ� ���ڿ� �������� ������ �ű��, ���� �������� ������ ���ڴ� �ϳ� ����, ������ ������ ���� ������ �ϳ� ���ϴ� ������
 * 	  ��ü ���ڿ� ������ ����
 * 	3-1. �������� ������ �ű� ������ ��ȣ�� ���� �� �ִ� ��й�ȣ���� �����Ѵ�.
 * */

public class BOJ_12891_DNA��й�ȣ {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int S;						// ���Ƿ� ���� DNA ���ڿ� ����
	static int P;						// ��й�ȣ�� ����� �κй��ڿ� ����
	static char[] totalStr;				// ���Ƿ� ���� DNA ���ڿ�
	static int[] count;					// �κй��ڿ��� ���ԵǾ�� �� {'A','C','G','T}�� �ּ� ����
	static int[] tmp=new int[4];		// �� �κй��ڿ��� ���Ե� ������ ������ ���� �迭
	static final short A=0;				// �κ� ���ڿ��� ���ԵǾ�� �� A�� ���� index
	static final short C=1;
	static final short G=2;
	static final short T=3;
	
	static int kind;					// ��й�ȣ�� ������ ��
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// DNA ���ڿ� ���� S�� �κй��ڿ� ���� P �Է�
		S=Integer.parseInt(st.nextToken());
		P=Integer.parseInt(st.nextToken());
		
		// ��ü ���ڿ� �Է�
		totalStr=br.readLine().trim().toCharArray();
		
		st=new StringTokenizer(br.readLine());
		
		// �κй��ڿ��� ���ԵǾ�� �� {'A','C','G','T}�� �ּ� ������ ���� �迭
		count=new int[4];
		// �� ������ �ּ� ���� �Է�
		for (int idx=0;idx<4;idx++) {
			count[idx]=Integer.parseInt(st.nextToken());
		}
		
		// ù ���ڿ������� P ���� �ȿ� ���� ������ ���� ���ϱ�
		for (int idx=0;idx<P;idx++) {
			setCount(idx,1);
		}
		// ��й�ȣ�� �Ǵ��� Ȯ��
		isValid();
		
		// �κй��ڿ� �������� ������ �ϳ��� �Űܰ���
		// �κй��ڿ��� ���Ե� ���� ���� ����
		int startIdx=0;
		int endIdx=P-1;
		while (true) {
			setCount(startIdx,-1);	// ���� ���������� �ٲ�� ��, ���� �������� ��ġ�� ���� ���� �ϳ� ����.
			startIdx+=1;			// ���� ���������� �ű��.
			endIdx+=1;				// ���� �������� �ű��.
			if (endIdx>=S) {		// ������ ��ü ���ڿ��� ������ �Ѿ ���, �ݺ��� �ߴ��Ѵ�.
				break;
			}
			setCount(endIdx,1);		// ������ ����� ���� ���, ������ ��ġ�� ������ ������ �ϳ� �߰��Ѵ�.
			isValid();				// ��й�ȣ�� �Ǵ��� Ȯ���Ѵ�.
		}
		// ��� ���
		System.out.println(kind);	
	}
	// idx ��ġ�� �ִ� ������ ���� ������Ʈ�ϴ� �Լ�
	public static void setCount(int idx,int sign) {	// sign: -1�̸� ���� �ϳ� ����, 1�̸� ���� �ϳ� ���ϱ�
		switch(totalStr[idx]) {						// �� ���ڿ� �ش��ϴ� ���� ������Ʈ
		case 'A':
			tmp[A]+=1*sign;
			break;
		case 'C':
			tmp[C]+=1*sign;
			break;
		case 'G':
			tmp[G]+=1*sign;
			break;
		case 'T':
			tmp[T]+=1*sign;
			break;
		}
	}
	// ��й�ȣ�� �� �� �ִ��� Ȯ���ϴ� �޼ҵ�
	public static void isValid() {				
		boolean valid=true;					// �ϴ� �ȴٰ� ����
		for (int idx=0;idx<4;idx++) {
			if (tmp[idx]<count[idx] ) {		// ���ؾ��ϴ� �ּ� ���ں��� �۴ٸ� ��й�ȣ�� ���� �� ����
				valid=false;				// �ݺ��� �ߴ�
				break;
			}
		}									// �ݺ����� �ߴ����� �ʰ� ������ ���Ҵٸ� ��й�ȣ�� ���� �� �ִ�.
		if (valid) {
			kind+=1;						// ��й�ȣ ���� �ϳ� ���Ѵ�.
		}
	}

}
