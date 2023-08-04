package StackQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * SWEA 1218: ��ȣ ¦����
 * 4 ������ ��ȣ ���ڵ� '()','[]','{}','<>'
 * �� ���ڿ��� ���� ��ȣ���� ¦�� ��� �´��� �Ǻ��ϴ� ���α׷�
 * 
 * 1. ���ڿ� ���̸�ŭ char�� Ȯ���Ѵ�.
 * 2. ���� ��ȣ��� stack�� push�Ѵ�.
 * 3. ���� ��ȣ��� stack���� pop�Ͽ� ���� ��ȣ�� ¦�� �´��� Ȯ���Ѵ�.
 * 4. ���ڿ� ���̸�ŭ Ȯ������ ���ϰ� �߰��� stack�� ������� ���, ��ȿ�� ���ڿ��� �ƴϴ�.
 * 5. ¦�� ���� ���� ���, ��ȿ�� ���ڿ��� �ƴϴ�.
 * 6. ���ڿ� ������ Ž���� �Ŀ��� stack�� ��ȣ�� �����ִٸ� ��ȿ�� ���ڿ��� �ƴϴ�.
 * */
public class SWEA_1218_��ȣ¦���� {
	static BufferedReader br;
	static int N;						// ���ڿ� ����
	static int T=10;					// �׽�Ʈ���̽� �� ����
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// �׽�Ʈ���̽���ŭ �ݺ�
		for (int test_case=1;test_case<=T;test_case++) {
			
			// ���ڿ� ���� �Է�
			N=Integer.parseInt(br.readLine().trim());
			
			// ��ȣ ���ڿ� ��ü �Է� �ޱ�
			String testLine=br.readLine().trim();
			
			// stack ��ü ����
			BracketStack stack=new BracketStack();
			
			// ��ȿ�� ���ڿ��̶�� ����
			boolean isValid=true;
			
			// ��ü ���ڸ� Ȯ��
			for (int cIdx=0;cIdx<N;cIdx++) {
				// ���ڿ� �ϳ�
				char bracket=testLine.charAt(cIdx);
				
				// ���� ��ȣ�� stack�� push
				if (bracket=='(' || bracket=='[' || bracket=='{' || bracket=='<') {
					stack.push(bracket);
				}
				// ���� ��ȣ�� stack���� pop �ؼ� �� 
				else {
					// ��ü ���ڿ��� �� Ȯ���ϱ� ����
					// stack�� ����ٸ� ��ȿ�� ���ڿ��� �ƴ�.
					if (stack.isEmpty()) {
						isValid=false;
						break;
					}
					
					//���� ���� ��ȣ pop
					char toCompare=stack.pop();
					
					// ¦�� ���� ��� continue
					if (bracket==')' && toCompare=='(') {
						continue;
					}
					else if (bracket==']' && toCompare=='[') {
						continue;
					}else if (bracket=='}' && toCompare=='{') {
						continue;
					}else if (bracket=='>' && toCompare=='<') {
						continue;
					}
					// �׷��� ���� ��� ��ȿ���� ���� ���ڿ�
					else {
						isValid=false;
						break;
					}
					
				}
			}
			// ��� ���ڿ��� Ȯ���Ͽ�����, stack�� ���ڰ� �����ִ� ��� ��ȿ�� ���ڿ��� �ƴ�.
			if (!stack.isEmpty()) isValid=false;
			// ��ȿ�� ���ڿ� ������ ���
			System.out.println("#"+test_case+" "+(isValid?1:0));
		}
		
	}

}

// ��ȣ�� push�ϰ� pop�ϴ� stack class
class BracketStack{
	int top;				// stack�� ���� ���κ��� ����Ű�� ����
	char[] arr;				// ���� ��ȣ ���ڸ� �����ϴ� �迭
	int N;					// stack�� ����
	
	// ������
	public BracketStack() {	// ���� �ʱ�ȭ	
		top=-1;
		arr=new char[]{};
		N=0;
	}
	
	// ��ȣ�� stack�� �߰�
	public void push(char bracket) {
		N+=1;
		char[] tmp=new char[N];
		System.arraycopy(arr, 0, tmp, 0, N-1);
		
		top+=1;
		tmp[top]=bracket;
		arr=tmp;
	}
	
	// stack�� ������ ��ȣ ����
	public char pop() {
		if (isEmpty()) return (Character) null;
		char last=arr[top];
		arr=Arrays.copyOfRange(arr, 0, top);
		N-=1;
		top-=1;
		return last;
	}
	
	// stack�� ������ ��ȣ ��ȯ
	public char peek() {
		if (isEmpty()) return (Character) null;
		return arr[top];
	}
	
	// stack�� ũ�� ��ȯ
	public boolean isEmpty() {
		if (top==-1) return true;
		return false;
	}
}