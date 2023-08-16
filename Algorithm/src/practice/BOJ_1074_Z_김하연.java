package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * BOJ 1074: Z
 * (2^N)*(2^N)�� ������ �迭�� Z������� Ž���Ϸ��� �Ѵ�.
 * ���� ��ĭ, ������ ��ĭ, ���� �Ʒ�ĭ, ������ �Ʒ�ĭ ������� �湮�Ѵ�.
 * N�� �־����� ��, r�� c���� �� ��°�� �湮�ϴ��� ����Ѵ�.
 * 
 * ��ͷ� ���� �湮�� ��� -> �ð��ʰ�
 * r�� c�� ��ġ�� �߽����� ���� �湮���� �ʰ� ĭ�� ����ŭ ���ؼ� ����Ѵ�.
 */
public class BOJ_1074_Z_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;			// (2^N)*(2^N) �迭�� ũ�� N
	static int r;			// r��
	static int c;			// c��
	static int cnt;			// �� ��° Ž������ �����ϴ� ����
	
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		
		// N, r, c �Է� �޴´�.
		N=Integer.parseInt(st.nextToken());
		r=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		
		// ���� ũ�⸦ ���Ѵ�.
		int size=(int)Math.pow(2, N);
		
		// r�� c���� �� ��° Ž���ϴ��� ���Ѵ�.
		divideConquer(size,0,0);
		
		// ����� ����Ѵ�.
		System.out.println(cnt);
		
	}
	// �Է� ���� r,c�� �簢�� �� ��� ��ġ�� �ش��ϴ��� Ȯ���Ѵ�. (1..4�и�)
	// �������� Ȯ���Ϸ��� �簢���� ���� �� ��, ���̴�.
	// n: �簢�� ����, ���� ���� / row: �簢���� ���� ���� �� / col: �簢���� ���� ���� ��
	public static void divideConquer(int n,int row,int col) {
		// Ž���ϴ� �簢���� ũ�Ⱑ 1�� ���
		if (n==1) {
			// �߰����� Ž���� �����Ѵ�.
			return;
		}
		// Ž���Ϸ��� ���簢���� ũ�Ⱑ 1���� ū ���
		// r,c�� ��ġ�� ��� ��и鿡 ���ϴ��� ã�´�.
		
		// ���� Ž���� �簢���� ũ��
		int nextSize=n/2;
		
		// 1��и鿡 ��ġ�� ���
		if (r<row+nextSize && c<col+nextSize) {
			divideConquer(nextSize,row,col);
		}
		
		// 2��и鿡 ��ġ�� ���
		else if(r<row+nextSize && c>=col+nextSize) {
			// 1��и��� ĭ�� ����ŭ �����ش�.
			cnt+=(nextSize*nextSize);
			divideConquer(nextSize,row,col+nextSize);
		}
		
		// 3��и鿡 ��ġ�� ���
		else if(r>=row+nextSize && c<col+nextSize) {
			// 1,2��и��� ĭ ����ŭ �����ش�.
			cnt+=2*nextSize*nextSize;
			divideConquer(nextSize,row+nextSize,col);
		}
		// 4��и鿡 ��ġ�� ���
		else {
			cnt+=3*nextSize*nextSize;
			divideConquer(nextSize,row+nextSize,col+nextSize);
		}
	}

}
