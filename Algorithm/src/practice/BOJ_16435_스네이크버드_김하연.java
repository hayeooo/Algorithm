package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
/*
 * BOJ 16435: ������ũ����
 * ���ϵ��� �������κ��� ���� ���̸� �ΰ� �������ִ�.
 * ������ũ����� �ڽ��� ���̺��� �۰ų� ���� ���̿� �ִ� ���ϵ��� ���� �� �ִ�.
 * ������ũ������ ó�� ���̰� L�� �� ���ϵ��� �Ծ� �ø� �� �ִ� �ִ� ���̸� ���Ѵ�.
 * 
 * */
public class BOJ_16435_������ũ����_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	static int N;
	static int L;
	static int[] fruits;
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		N=Integer.parseInt(st.nextToken());
		L=Integer.parseInt(st.nextToken());
		
		fruits=new int[N];
		
		st=new StringTokenizer(br.readLine().trim());
		for (int idx=0;idx<N;idx++) {
			fruits[idx]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(fruits);
		
		for (int fruitIdx=0;fruitIdx<N;fruitIdx++) {
			if (L>=fruits[fruitIdx]) {
				L+=1;
			}else {
				break;
			}
		}
		System.out.println(L);
	}

}
