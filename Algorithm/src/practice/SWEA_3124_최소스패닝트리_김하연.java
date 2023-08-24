package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * SWEA 3124: �ּ� ���д� Ʈ��
 * �׷����� �־����� ��, MST�� ���ϴ� ���α׷�
 * MST: �־��� �׷����� ��� �������� �����ϴ� �κ� �׷��� �߿��� ����ġ�� ���� �ּ��� Ʈ��
 * �Է����� �־����� �׷����� �ϳ��� ���� �׷����̴�.
 * 
 * 1. �׽�Ʈ���̽� ���� �Է¹޴´�.
 * 2. ������ ������ ������ ������ �Է¹޴´�.
 * 3. E ���� �ٿ� �� ������ ���� ������ ��Ÿ���� ���� A, B, C�� �Է¹޴´�.
 * 4. C�� ������ �� �ִ�.
 * 
 * Kruskal �˰����� ����Ͽ���.
 * => �������� �����ؾ� �Ѵ�.
 */

class Edge implements Comparable<Edge>{
	int start;
	int end;
	int weight;

	public Edge(int start, int end, int weight) {
		super();
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.weight,o.weight);
	}


}
public class SWEA_3124_�ּҽ��д�Ʈ��_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;					// �׽�Ʈ���̽� ��
	static int V,E;					// ������ ����, ������ ����
	static int[] parent;			// �� ������ ���� Ʈ���� ��ǥ ������ �����ϴ� �迭
	static int[] rank;				// �� ������ ���̸� �����ϴ� �迭
	static Edge[] edges;			// ������ ������ �����ϴ� �迭
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// �׽�Ʈ ���̽� ���� �Է� �޴´�.
		T=Integer.parseInt(br.readLine().trim());
		
		// �� �׽�Ʈ ���̽��� ����
		for (int test_case=1;test_case<=T;test_case++) {
			// ������ ������ ������ ������ �Է¹޴´�.
			st=new StringTokenizer(br.readLine().trim());
			
			V=Integer.parseInt(st.nextToken());
			E=Integer.parseInt(st.nextToken());
			
			edges=new Edge[E];
			
			// E�ٿ� ���� ���� ������ �Է� �޴´�.
			for (int idx=0;idx<E;idx++) {
				st=new StringTokenizer(br.readLine().trim());
				int node1=Integer.parseInt(st.nextToken())-1;
				int node2=Integer.parseInt(st.nextToken())-1;
				int weight=Integer.parseInt(st.nextToken());
				
				edges[idx]=new Edge(node1,node2,weight);
			}
			// �������� weight�� �߽����� �������� ������ �Ѵ�.
			Arrays.sort(edges);
			
			makeSet();
			
			// ������� ���õ� ���� ���� (V-1)������ ���õǾ�� �Ѵ�.
			int selectedCnt=0;
			// �ּ� ����ġ ��
			long totalWeight=0;
			
			// MST�� �����Ѵ�.
			for (Edge edge:edges) {
				if (union(edge.start,edge.end)) {
					totalWeight+=edge.weight;
					selectedCnt+=1;
					if (selectedCnt==V-1) break;
				}
			}
			// �׽�Ʈ���̽� ��ȣ�� �ּҽ���Ʈ�� ����ġ ���� �����Ѵ�.
			sb.append("#"+test_case+" "+totalWeight+"\n");
		}
		// ����� ����Ѵ�.
		System.out.println(sb);
	}
	
	// ���� ���� �ʱ�ȭ
	public static void makeSet() {
		// ������ ������ �°� �迭 ũ�⸦ �ʱ�ȭ�Ѵ�.
		parent=new int[V];
		rank=new int[V];
		
		// �� ������ ���� Ʈ���� ��ǥ ������ �ڱ� �ڽ��̴�.
		for (int node=0;node<V;node++) {
			parent[node]=node;
		}
		
	}
	
	
	// �κ� Ʈ������ �ش� ������ ���ϴ� ��ǥ ���� return + ��� ����
	public static int find(int a) {
		// ���� ������ ��ǥ �����̶�� ����
		if (a==parent[a]) {
			return a;
		}
		// �׷��� ���� ���, ��ǥ ���� Ž�� �� ��� ����
		return parent[a]=find(parent[a]);
	}
	
	// �� ���� �κ�Ʈ���� ��ġ�� ����
	public static boolean union(int a, int b) {
		int aRoot=find(a);
		int bRoot=find(b);
		
		// �̹� �� ������ ���� Ʈ���� ���Ѵٸ� ������ �� ����.
		if (aRoot==bRoot) {
			return false;
		}
		if (rank[aRoot]>rank[bRoot]) {
			parent[bRoot]=aRoot;
		}
		else if (rank[aRoot]<rank[bRoot]) {
			parent[aRoot]=bRoot;
		}
		else {
			rank[aRoot]+=1;
			parent[bRoot]=aRoot;
		}
		return true;

		
	}
}
