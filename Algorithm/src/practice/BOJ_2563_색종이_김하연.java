package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 2653: ������
 * ����, ���� ũ�Ⱑ ���� 100�� ���簢�� ����� ��� ��ȭ����
 * ����, ������ ũ�Ⱑ ���� 10�� ���簢�� ����� ������ �����̸� �������� ���� ��ȭ���� ���� �����ϵ��� ���δ�.
 * �����̸� �� �� ���� ���� ���� �� �����̰� ���� ���� ������ ���̸� ���Ѵ�.
 * 
 * �����̰� ��ȭ�� ������ ������ ���� ����.
 * 
 * 1. �������� ���� �Է� �޴´�.
 * 2. �������� ������ ��� ���� true�� ó���ϰ� totalArea�� ������Ų��. 
 * 	2-1. �̹� true�� ������ �ǳʶڴ�. (��ġ�� ���� ����)
 * 3. ��ġ�� ���� ����, ������ ����� ����Ѵ�.
 * */
public class BOJ_2563_������_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	static int N;
	static boolean[][] map;
						
	static int totalArea;
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// ������ ���� �Է� �޴´�.
		N=Integer.parseInt(br.readLine().trim());
		
		// ��ȭ�� �ʱ�ȭ
		map=new boolean[101][101];
		for (int idx=0;idx<N;idx++) {
			st=new StringTokenizer(br.readLine());
			
			// ������ ��ġ �Է� �ޱ�
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			
			// ������ ���� �ȿ� ��� ������ true
			// �̹� true ó���� ������ �ǳ� �ڴ�.
			for (int row=x;row<x+10;row++) {
				for (int col=y;col<y+10;col++) {
					if (!map[row][col]) {
						map[row][col]=true;
						totalArea+=1;
					}
				}
			}
		}
		// ����� ����Ѵ�.
		System.out.println(totalArea);
		
	}
}
