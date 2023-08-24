package practice;

public class DisjointSetTest {
	
	static int N;			// 초기 집합의 개수
	static int parents[];
	
	private static void make() {
		parents=new int[N];
		for (int i=0;i<N;i++) {
			parents[i]=i;			// 모든 요소는 자기만 원소로 갖는 단위 서로소 집합이 되게 한다.(자신이 곧 대표자인 루트로 표현)
		}

	}
	
	private static int find(int a) {
		if (parents[a]==a) {
			return a;
		}
		return parents[a]=find(parents[a]);		// path compression
	}
	
	private static boolean union(int a, int b) {		// a가 속한 집합과 b가 속한 집합을 합치고 true 반환, 아니면 false
		int aRoot=find(a);
		int bRoot=find(b);
		
		// 서로의 대표자가 같은 즉, 같은 집합의 상황이므로 union하지 않음.
		if (aRoot==bRoot) {
			return false;
		}
		// union 처리 (bRoot를 aRoot 아래로 붙이기!: 임의로..)
		parents[bRoot]=aRoot;
		return true;
	}
	
	public static void main(String[] args) {
		N=5;
		make();
		System.out.println(union(0,1));
		

	}

}
