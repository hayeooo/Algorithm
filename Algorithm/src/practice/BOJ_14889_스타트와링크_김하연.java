package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 14889: ��ŸƮ�� ��ũ
 * N���� N/2������ �̷���� ��ŸƮ ���� ��ũ ������ ������.
 * ���� ���� ��� ���� �ɷ�ġ Sij�� Sji�� ���� ���Ѵ�.
 * ��ŸƮ ���� �ɷ�ġ�� ��ũ ���� �ɷ�ġ�� ���� �ּڰ��� ���Ѵ�.
 * */
public class BOJ_14889_��ŸƮ�͸�ũ_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;						// N���� �����
	static int[][] s;					// �ɷ�ġ�� �����ϴ� �迭
	
	static int diff;					// ��ŸƮ ���� ��ũ �� �ɷ�ġ ����
	static boolean[] visited;			// ��ŸƮ ���� ���� ��� ǥ��
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// N �� ������� �Է� �޴´�.
		N=Integer.parseInt(br.readLine().trim());		
		
		// ���� �� ���� �ɷ�ġ�� �����ϴ� �迭
		s=new int[N][N];
		// �ɷ�ġ ������ �Է¹޴´�.
		for (int row=0;row<N;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<N;col++) {
				s[row][col]=Integer.parseInt(st.nextToken());
			}
		}
		// ��ŸƮ���� ��ũ���� ������ �迭
		visited=new boolean[N];
		
		// �� ���� �ɷ�ġ ���̴� �ִ��� ����
		diff=Integer.MAX_VALUE;
		
		// ��ŸƮ�� ��ũ ���� ������ ���� �� �� ���� �ɷ�ġ ���̸� ���Ѵ�.
		getSubSet(0,0);
		// �ּ� �ɷ�ġ ���̸� ����Ѵ�.
		System.out.println(diff);
	}
	// �κ��������� ����
	public static void getSubSet(int cnt,int chosen) { // cnt: ������� ����� ��, chosen: �� ���� �� ����� ��
		// ��ŸƮ ���� ¥������.
		if (cnt==N) {		// ��� ���� ����Ǿ��� ��
			if (chosen==N/2) {	// �� ���� ���� ��Ȯ�� N/2�� ��츸 ��ȿ�ϴ�.
				getDiff();		// �� ���� �ɷ�ġ ���̸� ���Ѵ�.
			}
			return;
		}
		// ��� ���� ������� �ʾ��� ��
		visited[cnt]=true;		// ���� ������ ��ŸƮ ���� �ִ´�.
		getSubSet(cnt+1,chosen+1);// ���� ������ ���Ѵ�.
		visited[cnt]=false;		// ���� ������ ��ŸƮ ���� ���� �ʰ�
		getSubSet(cnt+1,chosen);// �� ������ ���Ѵ�.
	}
	// ��ŸƮ���� ��ũ���� �ɷ�ġ ���̸� ���Ͽ�
	// �ɷ�ġ ������ �ּڰ��� �����Ѵ�.
	public static void getDiff() {
		// �� ���� �ɷ�ġ�� ������ ����
		int startTeam=0;
		int linkTeam=0;
		
		// �κ������� ���� visited�� true��, ��ŸƮ �����̰�
		// false��, ��ũ �����̴�.
		for (int row=0;row<N;row++) {
			for (int col=0;col<N;col++) {
				if (visited[row] && visited[col]) {
					startTeam+=s[row][col];
				}else if (!visited[row] && !visited[col]) {
					linkTeam+=s[row][col];
				}
			}
		}
		// �ɷ�ġ ������ �ּڰ��� �����Ѵ�.
		diff=Math.min(diff, Math.abs(startTeam-linkTeam));
	}

}
