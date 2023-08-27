package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * SWEA 1251: �ϳ���
 * �� ȯ�� �δ���� �ּҷ� �����ϸ�, N���� ��� ���� ������ �� �ִ� ���� �ý��� ����
 * �� ���� ��� ������ �� �ִ� ������ ������ ���������� �ּҷ� �����ؾ� �Ѵ�.
 * �� �������� ����Ͽ� ���� ����� ������ �����ϴ� ������ MST�� �����س�����.(Prim Algo)
 * 
 */
public class SWEA_1251_�ϳ���_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;					// �׽�Ʈ���̽� ��
	static int N;					// ���� ����
	static int[] islandX;			// �� ������ ������ X��ǥ
	static int[] islandY;			// �� ������ ������ Y��ǥ
	static int E;					// �����ͳ� �Ǽ��� ȯ�� �δ� ���� �Ǽ�
	
	static boolean[] visited;		// �ش� ������ �����ߴ��� ���θ� �����ϴ� �迭
	static double totalCost;			// ��� ������ �մ� �ּ� ȯ�� �δ��
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// �׽�Ʈ���̽��� �Է¹޴´�.
		T=Integer.parseInt(br.readLine().trim());
		
		for (int test_case=1;test_case<=T;test_case++) {
			// ���� ������ �Է¹޴´�.
			N=Integer.parseInt(br.readLine().trim());
			islandX=new int[N];
			islandY=new int[N];
			
			// ���� X��ǥ���� �����Ѵ�.
			st=new StringTokenizer(br.readLine().trim());
			for (int idx=0;idx<N;idx++) {
				islandX[idx]=Integer.parseInt(st.nextToken());
			}
			
			// ���� Y��ǥ���� �����Ѵ�.
			st=new StringTokenizer(br.readLine().trim());
			for (int idx=0;idx<N;idx++) {
				islandY[idx]=Integer.parseInt(st.nextToken());
			}
			
			// ������ �Է¹޴´�.
			E=Integer.parseInt(br.readLine().trim());
			
			totalCost=0;
			connectIsland(0);
			sb.append("#").append(test_case).append(" ").append(totalCost).append("\n");
		}
		System.out.println(sb);

	}
	// ������ �մ´�.
	public static void connectIsland(int start) {
		visited=new boolean[N];
		Deque<Integer> que=new ArrayDeque<>();
		
		que.offer(start);
		visited[start]=true;
		
		while (!que.isEmpty()) {
			int curIsland=que.poll();
			
			int minIdx=-1;
			double minCost=Integer.MAX_VALUE;
			
			for (int idx=0;idx<N;idx++) {
				// �̹� ������ ���̸� ����
				if (visited[idx]) continue;
				
				double tmpCost=getCost(islandX[curIsland],islandY[curIsland],islandX[idx],islandY[idx]);
				if (tmpCost<minCost) {
					minIdx=idx;
					minCost=tmpCost;
				}
			}
			
			// ������ ���� ���� ���
			if (minIdx==-1) break;
			
			// �ּ� ������� ������ �� �ִ� �� ����
			que.offer(minIdx);
			visited[minIdx]=true;
			totalCost+=minCost;
		}
	}
	
	// ������ ���Ѵ�.
	public static double getCost(int x1,int y1, int x2,int y2) {
		return E*((x1-x2)^2+(y1-y2)^2);
	}

}
