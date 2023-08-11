package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * BOJ 2839: ���� ���
 * 3kg ������ 5kg ������ ���� Nkg�� �ִ��� ���� ���� ������ ����Ϸ��� �Ѵ�.
 * ������ ��Ȯ�ϰ� Nkg ����ؾ� �� ��, ������ �� �� �������� �Ǵ��� �� ���� ���Ѵ�.
 * */
public class BOJ_2839_�������_���Ͽ� {
	static BufferedReader br;
	static int N;							// ������ ����
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		// ������ ���Ը� �Է� �޴´�.
		N=Integer.parseInt(br.readLine());
		
		// �ּ� ������ ��
		int cnt=0;
		
		// 5�� �������� �� ���� ������ 3kg�� ����.
		while (true) {
			// N�� ������ ���
			if (N<0) {
				break;
			}
			// 5�� ���� �� �ִ� ���
			else if(N%5==0) {
				break;
			}
			// ���� ���� 3���� ���� �� ���� ���
			else if (N-3>=0) {
				cnt+=1;
			}
			N-=3;
		}
		// ������ ���, Nkg ������ ��Ȯ�ϰ� ���� �� ����.
		if (N<0) {
			System.out.println(-1);
		}
		// N�� 0�� ���, 3kg�����θ� ���� �� �ִ�.
		else if(N==0) {
			System.out.println(cnt);
		}
		// ���� ���� ���Կ��� ����� �� �ִ� 5kg ���� ������ ���Ѵ�.
		else {
			System.out.println(cnt+(N/5));
		}
		
	}

}
