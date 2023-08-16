package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/*
 * BOJ 6987: ������
 * ������ ���� ���� �������� 6�������� ������ �� ������ ������ ���� �Ҽӵ� ������� �� ����, �� 5���� ��⸦ ġ����.
 * �������װ� ���� ��, ���ڰ� ������ �� ������ ��, ���º�, ���� ���� ������ ��������� �Ǻ��Ϸ��� �Ѵ�.
 * 
 * �� ���� ������ �ٸ� ���� ������ �����س����� ����Ѵ�.
 * ��� ����� ������ �� ���� ���� �߿� 0�� �ƴ� ���� ���´ٸ�, �Ұ����� ����̴�.
 * 
 * ���� ���� A vs B, A vs C, A vs D, A vs F,.. E vs F ������ �̷������.
 * ������� ��: ����ϴ� �� ���� ����� ��� ���� (�� ���� �̰��� ���, ���º��� ���, �� ���)�� Ž���ϴ� DFS�� �����Ű�� ��
 * => google�� Ǯ���ڵ� ������ ����..!!
 */
public class BOJ_6987_������_���Ͽ� {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int[][] scores;		// �� ���� ����(��, ���º�, ��)�� �����ϴ� �迭
	static final int win=0;
	static final int same=1;
	static final int lose=2;
	
	// home vs enemy�� 15���� ������ ����ȴ�.
	// => �� �κ� Ǯ�� �ڵ� ������.
	static int[] home= {0,0,0,0,0,1,1,1,1,2,2,2,3,3,4};
	static int[] enemy= {1,2,3,4,5,2,3,4,5,3,4,5,4,5,5};
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// ������ ������ �迭 ũ�� �ʱ�ȭ
		scores=new int[6][3];
		
		// ù° �ٺ��� ��° �ٱ��� �� �ٸ��� 6������ ����� ���󺰷� ��, ���º�, ���� ������ �־�����.
		for (int idx=0;idx<4;idx++) {
			st=new StringTokenizer(br.readLine().trim());
			// �� ���� ������ �Է� �޴´�.
			for (int team=0;team<6;team++) {
				scores[team]=new int[] {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
			}
		}
		dfs(0);
	}
	
	public static boolean dfs(int stage) {
		// ���� ����
		if (stage==15) {
			return true;
		}
		int homeTeam=home[stage];
		int enemyTeam=enemy[stage];
		
		// �ش� stage���� homeTeam�� �̱� ���
		if (scores[homeTeam][win]>0 && scores[enemyTeam][lose]>0) {
			scores[homeTeam][win]--;
			scores[enemyTeam][lose]--;
			dfs(stage+1);
		}
		// �ش� stage���� homeTeam�� ���º��� ���
		if (scores[homeTeam][same]>0 && scores[enemyTeam][lose]>0) {
			
		}
		// �ش� stage���� homeTeam�� �� ���
		if (scores[homeTeam][lose]>0 && scores[enemyTeam][lose]>0) {
			
		}
		return false;
	}
}
