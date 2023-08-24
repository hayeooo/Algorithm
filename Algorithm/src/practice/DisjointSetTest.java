package practice;

public class DisjointSetTest {
	
	static int N;			// �ʱ� ������ ����
	static int parents[];
	
	private static void make() {
		parents=new int[N];
		for (int i=0;i<N;i++) {
			parents[i]=i;			// ��� ��Ҵ� �ڱ⸸ ���ҷ� ���� ���� ���μ� ������ �ǰ� �Ѵ�.(�ڽ��� �� ��ǥ���� ��Ʈ�� ǥ��)
		}

	}
	
	private static int find(int a) {
		if (parents[a]==a) {
			return a;
		}
		return parents[a]=find(parents[a]);		// path compression
	}
	
	private static boolean union(int a, int b) {		// a�� ���� ���հ� b�� ���� ������ ��ġ�� true ��ȯ, �ƴϸ� false
		int aRoot=find(a);
		int bRoot=find(b);
		
		// ������ ��ǥ�ڰ� ���� ��, ���� ������ ��Ȳ�̹Ƿ� union���� ����.
		if (aRoot==bRoot) {
			return false;
		}
		// union ó�� (bRoot�� aRoot �Ʒ��� ���̱�!: ���Ƿ�..)
		parents[bRoot]=aRoot;
		return true;
	}
	
	public static void main(String[] args) {
		N=5;
		make();
		System.out.println(union(0,1));
		

	}

}
