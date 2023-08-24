package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * ĳ�� ���潺
 * ���� ��ġ�� ����Ʈ�� �����ϴ� ������� ����
 */
public class BOJ_17135_ĳ�����潺_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N, M;			// ������ ���� ��, ���� ��
	static int D;				// �ü��� ���� �Ÿ� ����
	
	static List<int[]> enemyLocList;	// ���� ��ġ�� �����ϴ� ����Ʈ
	static int[] comb;					// �ü��� �� ��ġ�� �����ϴ� �迭
	static int removedCnt=0;				// ������ ���� ��
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// N, M, D �Է� �޴´�.
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		D=Integer.parseInt(st.nextToken());
		
		enemyLocList=new ArrayList<>();
		// ������ �迭 ������ �Է¹޴´�.
		for (int row=0;row<N;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<M;col++) {
				int value=Integer.parseInt(st.nextToken());
				// ������ ������ ���̶�� ��ġ�� �����Ѵ�.
				if (value==1) {
					enemyLocList.add(new int[] {row,col});
				}
			}
		}
		
		comb=new int[3];
		combinations(0,0);
		System.out.println(removedCnt);
	}
	// 3���� �ü� �� ��ġ�� �����Ѵ�.
	public static void combinations(int cnt,int startIdx) {
		
		// 3���� ��ġ�� ����ٸ�
		if (cnt==3) {
			
			// ������ �����Ѵ�.
			startGame();
			return;
		}
		// ���� �� ��ġ�� ����.
		for (int idx=startIdx;idx<M;idx++) {
			comb[cnt]=idx;
			combinations(cnt+1,idx+1);
		}
	}
	
	// ������ �����Ѵ�.
	public static void startGame() {
		// ���� ���� �� ��ġ�� �����Ѵ�.
		List<int[]> tmpEnemy=new ArrayList<>(enemyLocList);
		int cnt=0;
		
		// ���� ��� ���ŵ� ������ �ݺ��Ѵ�.
		while (!tmpEnemy.isEmpty()) {
			// ������ ���� ��ġ�� ��� ����Ʈ
			List<int[]> toRemove=new ArrayList<>();
			
			// ������ �ü��� ���� ��ġ�� ���� ����� ���� �����Ѵ�.
			for (int attackerY:comb) {
				// �Ÿ��� ���� ª�� ���� ��ġ�� �Ÿ��� �����Ѵ�.
				int minX=N-1;
				int minY=M-1;
				int minDist=Integer.MAX_VALUE;
				boolean flag=false;
				
				// ���� �����ִ� ���� �߿��� �Ÿ��� ���� ª�� �� ����
				for (int[] enemyLoc:tmpEnemy) {
					
					// �Ÿ��� ���Ѵ�.
					// �ü� �� ��ġ�� N
					int dist=getDistance(N,attackerY,enemyLoc[0],enemyLoc[1]);
					
					// �Ÿ��� D ����
					if (dist>D) continue;
					
					// �ּ� �Ÿ��� ���
					if (dist<minDist) {
						minX=enemyLoc[0];
						minY=enemyLoc[1];
						minDist=dist;
						flag=true;
					}
					
					// �ּ� �Ÿ��� ���� ���
					else if(dist==minDist) {
						// ���ʿ� �ִ� ���� �����Ѵ�.
						if (enemyLoc[1]<minY) {
							minX=enemyLoc[0];
							minY=enemyLoc[1];
							flag=true;
						}
					}
				}
				
				// ���� ���õ� ���, ������ ��� �ִ´�.
				if (flag) {
					toRemove.add(new int[] {minX,minY});
				}
				
			}
			// 3 ���� ���� �����ߴٸ�, ���� �����Ѵ�.
			for (int[] removed:toRemove) {
				for (int originIdx=tmpEnemy.size()-1;originIdx>=0;originIdx--) {
					if ((tmpEnemy.get(originIdx)[0]==removed[0]) && (tmpEnemy.get(originIdx)[1]==removed[1])) {
						tmpEnemy.remove(originIdx);
						cnt+=1;
					}
				}
			}
			
			// �����ִ� ���� ��ġ�� �Ʒ��� �� ĭ�� �̵���Ų��.
			for (int originIdx=tmpEnemy.size()-1;originIdx>=0;originIdx--) {
				// �Ʒ��� �� ĭ �̵��Ѵ�.
				tmpEnemy.set(originIdx, new int[] {tmpEnemy.get(originIdx)[0]+1,tmpEnemy.get(originIdx)[1]});
				// �� ������ ���� ��� �����Ѵ�.
				if (tmpEnemy.get(originIdx)[0]>=N) {
					tmpEnemy.remove(originIdx);
				}
			}
			
		}
		removedCnt=Math.max(cnt, removedCnt);
		
	}
	
	// �� ��ġ ���̿� �Ÿ��� ���Ѵ�.
	public static int getDistance(int r1, int c1, int r2, int c2) {
		return Math.abs(r1-r2)+Math.abs(c1-c2);
	}

}
