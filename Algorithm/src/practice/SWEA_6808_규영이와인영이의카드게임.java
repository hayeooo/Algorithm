package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
/*
 * SWEA 6808: �Կ��̿� �ο����� ī�����
 * 
 * �Կ��̿� �ο��̴� 1���� 18������ ���� ���� 18���� ī��� ������ �ϰ� �ִ�.
 * ��ȩ ���忡 ���� ������ ����Ǹ�,
 * �� ���忡�� �� �徿 ī�带 �� ���� �� ����� �� ī�忡 ���� ���� ���ؼ� ������ ����Ѵ�.
 * ���� ���� ���� ī�带 �� ����� �� ī�忡 ���� ���� �ո�ŭ ������ ���,
 * ���� ���� ���� ī�带 �� ����� �ƹ��� ������ ���� �� ����.
 * 
 * ������ ������ �� �� ���� ����� ������ ���ڰ� �ȴ�.
 * �Կ��̰� ���� ī��� ī���� ������ �����Ǿ� �ִ�.
 * �ο��̰� ��� ī�带 �������� ���� �Կ����� ���а� ��������.
 * �Կ��̰� �̱�� ���� ���� ��찡 �� �� �������� ���ϴ� ���α׷��� �ۼ��Ѵ�.
 * 
 * 1. �׽�Ʈ���̽� ���� �Է¹޴´�.
 * 2. �� �׽�Ʈ���̽����� �Կ��̰� ���� ī�� ������ �Է� �޴´�.
 * 3. �Կ��̰� �� ī�带 ������ ī��(�ο��̰� ���� ī��)�� �˾Ƴ���.
 * 4. �ο��̰� ���� ī���� 9! ������ ���Ѵ�.
 * 5. �� �������� �Կ��̿��� ������ �����ϸ� ���и� �����Ѵ�.
 * 6. �ο��̰� ���� ī���� ��� ���տ� ���� ������ �����ٸ� �̱� Ƚ���� �� Ƚ���� ����Ѵ�.
 * */
public class SWEA_6808_�Կ��̿��ο�����ī����� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int T;								// �׽�Ʈ���̽� ��
	static boolean[] cards;						// �Կ��̰� �� ī�� ������ �����ϴ� �迭
	static List<Integer> inPlayer;				// �ο��̰� ���� ī��
	static List<Integer> gyuPlayer;				// �Կ��̰� ���� ī��
	static int[] comb;							// �ο����� ī�� ����
	static boolean[] isSelected;				// ���� ������ ��, �ߺ� ���� �˻縦 ���� �迭
	
	static int win;								// �Կ��̰� �̱� Ƚ��
	static int lose;							// �Կ��̰� �� Ƚ��
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// �׽�Ʈ���̽� �Է�
		T=Integer.parseInt(br.readLine().trim());
		
		// �׽�Ʈ���̽� ����ŭ �ݺ�
		for (int test_case=1;test_case<=T;test_case++) {
			
			// �� �÷��̾ ���� ī�带 �����ϴ� ��ü ����
			inPlayer=new ArrayList<Integer>();			
			gyuPlayer=new ArrayList<Integer>();
			
			// �Կ��̰� �� ī�� ������ �����ϴ� �迭 ũ�� �ʱ�ȭ
			cards=new boolean[19];
			
			// �Կ��̰� ���� ī�� ������ ī�� ������ �����Ѵ�.
			st=new StringTokenizer(br.readLine().trim());
			for (int idx=0;idx<9;idx++) {
				int card=Integer.parseInt(st.nextToken());
				gyuPlayer.add(card);
				// �Կ��̰� �� ī��� ǥ�ø� ���ش�.
				cards[card]=true;
			}
			
			// �ο��̰� �� �� �ִ� ī��
			for (int idx=1;idx<19;idx++) {
				// �Կ��̰� ���� ���� ī��� �ο��̰� ������.
				if (!cards[idx]) {
					inPlayer.add(idx);
				}
			}
			
			// �ο��̰� �� ī�� ���� ���ϱ�
			comb=new int[9];
			isSelected=new boolean[9];
			// �̱� Ƚ���� �� Ƚ���� �ʱ�ȭ �Ѵ�.
			win=0;
			lose=0;
			// �ο��̰� �� �� �ִ� ī�� ������ ���ϰ�, ������ �������
			// �̱� Ƚ���� �� Ƚ���� ���Ѵ�.
			permutations(0);
			
			System.out.println("#"+test_case+" "+win+" "+lose);
		}
	}
	// �ο����� ī�� ������ ���ϴ� ���� �޼ҵ�
	public static void permutations(int cnt){
		// 9��° �������� ī�� ������ ������ ���
		if (cnt==9) {
			// ������ �����Ų��.
			startGame();
			return;
		}
		// ���� �ڸ��� �� ī�带 �����Ѵ�.
		for (int idx=0;idx<inPlayer.size();idx++) {
			if (isSelected[idx]) continue;	// �̹� ���õ� ī��� �����Ѵ�.
			comb[cnt]=inPlayer.get(idx);
			isSelected[idx]=true;			// ���� ó���� �Ѵ�.
			permutations(cnt+1);			// ���� �ڸ� ī�带 ����.
			isSelected[idx]=false;			// ���� �ڸ��� �ٸ� ���ڸ� �־�� ���� ���� ó���� ��ȿȭ�Ѵ�.
		}
	}
	// ������ �����Ͽ� �Կ��̰� �¸��ϴ��� ���θ� ����Ѵ�.
	public static void startGame() {
		int inScore=0;
		int gyuScore=0;
		// 9���� ī�带 �ϳ��� ���Ѵ�.
		for (int round=0;round<9;round++) {
			int inCard=comb[round];
			int gyuCard=gyuPlayer.get(round);
			// �� ī���� ũ�⸦ ���Ͽ� �̱� ���, �� ī���� ���� ������ ����ϰ�
			// �� ���, ������ ȹ������ ���Ѵ�.
			if (inCard>gyuCard) {
				inScore+=(inCard+gyuCard);
			}else if(inCard<gyuCard) {
				gyuScore+=(inCard+gyuCard);
			}
		}
		// �Կ��̰� �� ���
		if (inScore>gyuScore) {
			lose+=1;
		}
		// �Կ��̰� �̱� ���
		else if(gyuScore>inScore) {
			win+=1;
		}
		// ���ºδ� �������� �ʴ´�.
	}

}
