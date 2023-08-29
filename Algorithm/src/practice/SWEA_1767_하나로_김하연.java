package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * SWEA 1767: �ϳ���
 * ������ ������ �����ϰ� �� ������ ���� ������ ������ �̾Ƽ� ����(PQ ���) + ���� �湮 ���� �ʿ�
 */
class IslandNode implements Comparable<IslandNode>{
	int node;
	long weight;
		
	public IslandNode(int node, long weight) {
		super();
		this.node = node;
		this.weight = weight;
	}

	@Override
	public int compareTo(IslandNode o) {
		return Long.compare(this.weight, o.weight);
	}
	
}
public class SWEA_1767_�ϳ���_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;					// �׽�Ʈ���̽� ��
	static int N;					// ���� ����
	static int[] islandX;			// ���� X��ǥ
	static int[] islandY;			// ���� Y��ǥ
	static double E;				// ���� �Ǽ�
	static List<IslandNode>[] adjList;		// ���� ���� ���夤
	static boolean[] visited;		// �� ���� �湮 ���θ� �����ϴ� �迭
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// �׽�Ʈ���̽��� �Է¹޴´�.
		T=Integer.parseInt(br.readLine().trim());
		
		for (int test_case=1;test_case<=T;test_case++) {
			// ���� ���� �Է¹޴´�.
			N=Integer.parseInt(br.readLine().trim());
			islandX=new int[N];
			islandY=new int[N];
			visited=new boolean[N];
			
			// ���� X ��ǥ�� ���Ѵ�.
			st=new StringTokenizer(br.readLine().trim());
			for (int idx=0;idx<N;idx++) {
				islandX[idx]=Integer.parseInt(st.nextToken());
			}
			
			// ���� Y ��ǥ�� ���Ѵ�.
			st=new StringTokenizer(br.readLine().trim());
			for (int idx=0;idx<N;idx++) {
				islandY[idx]=Integer.parseInt(st.nextToken());
			}
			// ������ �Է¹޴´�.
			E=Double.parseDouble(br.readLine().trim());
			
			
			// �ϳ��� ������ ��� ���� ���� �� �ִ�. ������ ������ �����Ѵ�.
			adjList=new ArrayList[N];
			for (int start=0;start<N;start++) {
				adjList[start]=new ArrayList<IslandNode>();
				
				for (int end=0;end<N;end++) {
					if (start==end)continue;
					adjList[start].add(new IslandNode(end,calDist(islandX[start],islandY[start],islandX[end],islandY[end])));
				}
			}
			
			// priorityQueue�� ������ ���� �ְ�
			// �� ������ ���� ����� ���� �̾���� �帧���� ����ȴ�.
			
			Queue<IslandNode> pq=new PriorityQueue<>();
			pq.offer(new IslandNode(0,0));
			
			long res=0;
			
			while (!pq.isEmpty()) {
				IslandNode curIsland=pq.poll();
				int curIslandNum=curIsland.node;
				
				// �̹� �湮�� ���� ��� ����
				if (visited[curIslandNum]) continue;
				
				// �湮�� ���̶�� ǥ��
				visited[curIslandNum]=true;
				res+=curIsland.weight;
				
				// ������ ������ Ž���Ѵ�.
				for (int idx=0;idx<adjList[curIslandNum].size();idx++) {
					IslandNode nextIsland=adjList[curIslandNum].get(idx);
					int nextIslandNum=nextIsland.node;
					
					if (visited[nextIslandNum]) continue;
					
					pq.offer(nextIsland);
				}
			}
			sb.append("#").append(test_case).append(" ").append(Math.round(E*res)).append("\n");
		}
		System.out.println(sb);
	}
	// �� �� ������ �Ÿ��� ���Ѵ�.
	public static long calDist(int x1,int y1,int x2,int y2) {
		long dist1=(long)Math.pow(x1-x2, 2);
		long dist2=(long)Math.pow(y1-y2, 2);
		return dist1+dist2;
	}
}
