package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MSTKruskalTest {

	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(weight, o.weight);
		}
	}
	
	static Edge[] edgeList;
	static int V,E;
	static int [] parents;
	
	static void make() {
		parents=new int[V];
		for (int i=0;i<V;i++) {
			parents[i]=i;
		}
	}
	
	static int find(int a) {
		if (parents[a]==a) {
			return a;
		}
		return parents[a]=find(parents[a]);
	}
	
	static boolean union(int a,int b) {
		int aRoot=find(a);
		int bRoot=find(b);
		
		if (aRoot==bRoot) return false;	// ����Ŭ �߻�
		parents[aRoot]=bRoot;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine()," ");
		
		V=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());
		
		edgeList=new Edge[E];
		for (int i=0;i<E;i++) {
			st=new StringTokenizer(in.readLine()," ");
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			int weight=Integer.parseInt(st.nextToken());
			edgeList[i]=new Edge(from,to,weight);
		}
		// ��������Ʈ ����ġ ���� �������� ����
		Arrays.sort(edgeList);
		
		// V���� �������� make set �۾�
		make();
		
		int result=0;
		int count=0;
		for(Edge edge:edgeList) {	// ����� ���� ���� ������ ������ ó��
			// union ������ �����ϴٸ� ���� �߰�
			if (union(edge.from,edge.to)) {
				result+=edge.weight;
				if (++count==V-1) break;
			}
		}
		System.out.println(result);
	}

}
