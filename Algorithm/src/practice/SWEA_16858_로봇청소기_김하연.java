package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * ������ ��ġ�� ���ϸ� ������ �ٲ۴�.
 * => ������ ���� ���̰ų�, ���� �ε��� ��� ������ �ٲ۴�.
 */
public class SWEA_�κ�û�ұ�_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;					// �׽�Ʈ���̽� ��
	static int N;					// ���� �� ���� ũ��
	static int M;					// ������ ����
	static int[][] map;				// �� ������ �����ϴ� �迭
	static int dustCnt;				// ������ ����
	static List<int[]> dustLocList;	// ������ ��ġ�� �����ϴ� ����Ʈ
	static int[] dx= {0,1,0,-1};	// ��, ��, ��, ��(���������� 90��)
	static int[] dy= {1,0,-1,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		// �׽�Ʈ���̽� ������ �Է¹޴´�.
		T=Integer.parseInt(br.readLine().trim());
		
		for (int test_case=1;test_case<=T;test_case++) {
			N=Integer.parseInt(br.readLine().trim());
			map=new int[N][N];
			
			dustLocList=new ArrayList<>();
			for (int idx=0;idx<11;idx++) {
				dustLocList.add(new int[] {});
			}
			
			dustCnt=0;
			// �� ������ �Է¹޴´�.
			for (int row=0;row<N;row++) {
				st=new StringTokenizer(br.readLine().trim());
				for (int col=0;col<N;col++) {
					map[row][col]=Integer.parseInt(st.nextToken());
					
					// ������ ���
					if (map[row][col]!=0) {
						dustLocList.set(map[row][col], new int[] {row,col});
						dustCnt+=1;
					}
				}
			}
			
			int catchedDust=0;
			int dustIdx=1;
			int d=0;			// ������ ������ ���� �ִ�.
			int curX=0;
			int curY=0;
			int rotationCnt=0;
			while (catchedDust<dustCnt) {
				// ã�ƾ��� ������ ��ġ
				int dustX=dustLocList.get(dustIdx)[0];
				int dustY=dustLocList.get(dustIdx)[1];
				
				// ������ ã�� ������
				while (curX!=dustX || curY!=dustY) {
					
					int nextX=curX+dx[d];
					int nextY=curY+dy[d];
					
					// �ϴ� �̵��Ѵ�.
					if (nextX>=0 && nextX<N && nextY>=0 && nextY<N) {
						curX=nextX;
						curY=nextY;
						
						if (curX==dustX && curY==dustY) break;
						// ���� �࿡ ������ �ٸ� ���� �ִ� ���
						if (curX==dustX) {
							// ���� ������ ��� �� �� ����� �Ѵ�.
							// ������ �ٶ󺸰� �ְ� ���� ��ġ�� �������� ���� ��
							if (d==3 && curY<dustY) {
								d=(d+1)%4;
								rotationCnt+=1;
								continue;
							}
							// ������ �ٶ󺸰� �ְ� ���� ��ġ�� �������� Ŭ ��
							if (d==1 && curY>dustY) {
								d=(d+1)%4;
								rotationCnt+=1;
								continue;
							}
							
						}
						// ���� ���� ������ �ٸ� �࿡ �ִ� ���
						else if (curY==dustY) {
							
							// ���� ������ ��� �� �� ����� �Ѵ�.
							// ������ �ٶ󺸰� �ְ�, ������ ���� ���� �ຸ�� Ŭ ��
							if (d==0 && curX<dustX) {
								rotationCnt+=1;
								d=(d+1)%4;
								continue;
							}
							// ������ �ٶ󺸰� �ְ�, ������ ���� ���� �ຸ�� ���� ��
							if (d==2 && curX>dustX) {
								rotationCnt+=1;
								d=(d+1)%4;
								continue;
							}
						}
						
					}
					else {
						rotationCnt+=1;
						d=(d+1)%4;
					}
					
				}
				// ������ ���������� ������ �÷��ְ� ���� ������ ã�´�.
				catchedDust+=1;
				dustIdx+=1;
			}
			sb.append("#"+test_case+" "+rotationCnt).append("\n");
			
		}
		System.out.println(sb);
	}

}
