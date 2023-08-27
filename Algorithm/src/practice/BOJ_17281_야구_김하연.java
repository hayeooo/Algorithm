package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 17281: �߱�
 * 1. 4�� ������ ù ��° ������ ��ġ�� ��, ������ �������� ������ �����Ѵ�.
 * 2. �� ������ �̴׿��� ��� ����� ���� ������ ����Ѵ�.
 * 	2-1. 3�ƿ��� �Ǳ� ������ �̴��� ������ �ʰ�, Ÿ���� �̴��� ����Ǿ ������ �����Ѵ�.
 * 	2-2. 1: ��Ÿ, 2: 2��Ÿ, 3: 3��Ÿ, 4: Ȩ��, 0: �ƿ�
 */
public class BOJ_17281_�߱�_���Ͽ� {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;							// �̴� ��
	static int[] comb;						// ���� ������ ����
	static int[] tmpComb;
	static boolean[] selected;				// ������ ������ �����ϴ� �迭
	static int[][] innings;					// �� ������ �̴׿��� ���� ���(1��~9��)
	static int maxScore;					// �ְ� ����
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		N=Integer.parseInt(br.readLine().trim());
		innings=new int[N][9];
		
		// �� ������ �� �̴׿��� ���� ����� �Է� �޴´�.
		for (int idx=0;idx<N;idx++) {
			st=new StringTokenizer(br.readLine().trim());
			
			for (int player=0;player<9;player++) {
				innings[idx][player]=Integer.parseInt(st.nextToken());
			}
		}
		
		comb=new int[9];
		tmpComb=new int[8];
		selected=new boolean[9];
		// 1�� ������ 4�� Ÿ�ڷ� �̸� �����Ѵ�.
		perm(0);
		comb[3]=0;
		selected[0]=true;
		System.out.println(maxScore);
	}
	
	// �������� ������ ���Ѵ�.
	public static void perm(int cnt) {
		// ������ ������ �����Ǹ�
		if (cnt==9) {
			// ������ �����Ѵ�.
			// 1�� ������ 4�� Ÿ�ڿ� �ְ� ������ �����Ѵ�.
			for (int idx=0;idx<3;idx++) {
				comb[idx]=tmpComb[idx];
			}
			comb[3]=0;
			for(int idx=3;idx<8;idx++) {
				comb[idx+1]=tmpComb[idx];
			}
			startGame();
			return;
		}
		for (int idx=1;idx<9;idx++) {
			if (selected[idx]) continue;
			tmpComb[cnt]=idx;
			selected[idx]=true;
			perm(cnt+1);
			selected[idx]=false;
		}
	}
	// ������ �����Ѵ�.
	public static void startGame() {
		int seq=0;				// ����
		int totalScore=0;
		// �� N�̴׵��� ������ �����ؾ� �Ѵ�.
		for (int curInnings=0;curInnings<N;curInnings++) {
			
			int out=0;
			boolean[] stand=new boolean[5];	// Ȩ, 1��, 2��, 3�� , Ȩ
			
			// 3�ƿ��� �߻����� ���� ������ �̴��� ������ �ʴ´�.
			while (out<3) {
				// ���� ���� ��ȣ
				int player=comb[seq];
				// Ÿ�� ���
				int res=innings[curInnings][player];
				stand[0]=true;
				// ��Ÿ�� ���
				if (res==1) {
					// �ϴ� �� �羿 �̵�
					for (int idx=3;idx>=0;idx--) {
						stand[idx+1]=stand[idx];
					}
					// Ȩ�� ������ ���, ���� ȹ��
					if (stand[4]==true) {
						totalScore+=1;
						stand[4]=false;
					}
				}
				// 2��Ÿ�� ���
				else if(res==2) {
					// 2 ĭ�� �̵�
					for (int c=0;c<2;c++) {
						for (int idx=3;idx>=0;idx--) {
							stand[idx+1]=stand[idx];
						}
						// Ȩ�� ������ ���, ���� ȹ��
						if (stand[4]==true) {
							totalScore+=1;
							stand[4]=false;
						}
						stand[0]=false;
					}
					
				}
				// 3��Ÿ�� ���
				else if(res==3) {
					// �� ĭ�� �̵�
					for (int c=0;c<3;c++) {
						// 3 ĭ�� �̵�
						for (int idx=3;idx>=0;idx--) {
							stand[idx+1]=stand[idx];
						}
						// Ȩ�� ������ ���, ���� ȹ��
						if (stand[4]==true) {
							totalScore+=1;
							stand[4]=false;
						}
						stand[0]=false;
					}
				}
				// Ȩ���� ���
				else if(res==4) {
					// 1��,2��,3�翡 ������ ������ ���� ȹ��
					for (int idx=1;idx<=3;idx++) {
						if (stand[idx]) totalScore+=1;
					}
					// Ÿ�ڰ� Ȩ�� ���� ���
					totalScore+=1;
					stand=new boolean[5];
				}
				// �ƿ��� ���
				else if(res==0) {
					out+=1;
				}
				seq=(seq+1)%9;
			}
		}
		// �ִ� �������� ������Ʈ
		maxScore=Math.max(maxScore, totalScore);
	}

}
