package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
/*
 * BOJ 16435: 스네이크버드
 * 과일들이 지상으로부터 일정 높이를 두고 떨어져있다.
 * 스네이크버드는 자신의 길이보다 작거나 같은 높이에 있는 과일들을 먹을 수 있다.
 * 스네이크버드의 처음 길이가 L일 때 과일들을 먹어 늘릴 수 있는 최대 길이를 구한다.
 * 
 * */
public class BOJ_16435_스네이크버드_김하연 {
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
