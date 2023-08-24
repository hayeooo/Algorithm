package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * BOJ 15961: 회전 초밥
 * 쿠폰을 고려하지 않고 연속해서 먹는 4가지 초밥의 종류를 구한다.
 * 쿠폰을 사용해서 추가로 먹을 수 있는 초밥까지 고려하여 초밥 가짓수의 최댓값을 구한다.
 * 
 * 초밥 가짓수를 고려하기 위해 set 자료구조를 활용한다.
 * 
 */
public class BOJ_15961_회전초밥_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;					// 회전 초밥 벨트에 놓인 접시의 수
	static int d;					// 초밥의 가짓수
	static int k;					// 연속해서 먹는 접시의 수
	static int c;					// 쿠폰 번호
	
	static int[] sushi;				// 회전 초밥 벨트에 놓인 초밥의 종류
	
	static int[] eaten;				// 해당 위치의 초밥을 몇 개 먹었는지 저장하는 배열
	static int result=0;
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 접시의 수, 초밥의 가짓수, 연속해서 먹는 접시의 수, 쿠폰 번호를 입력 받는다.
		st=new StringTokenizer(br.readLine().trim());
		N=Integer.parseInt(st.nextToken());
		d=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken())-1;
		
		// N개의 줄에 벨트의 한 위치부터 시작하여 회전 방향을 따라간다.
		sushi=new int[N];
		eaten=new int[d];
		
		for (int idx=0;idx<N;idx++) {
			sushi[idx]=Integer.parseInt(br.readLine().trim())-1;
		}
		
		int cnt=0;
		for (int idx=0;idx<k;idx++) {
			// 지금까지 안 먹어본 스시 종류인 경우
			if (eaten[sushi[idx]]==0) {
				// 스시 종류를 늘린다.
				cnt+=1;
			}
			eaten[sushi[idx]]+=1;
		}
		
		for (int idx=0;idx<N;idx++) {
			
			// 현재 초밥 종류 개수가 최댓값을 넘을 경우
			if (cnt>=result) {
				// 쿠폰 초밥 확인
				if (eaten[c]==0) result=cnt+1;
				else result=cnt;
			
			}
			// 맨 처음 초밥 하나 빼고
			eaten[sushi[idx]]-=1;
			if (eaten[sushi[idx]]==0) cnt-=1;
			
			// 그 다음 초밥 하나 넣기
			int end=(idx+k)%N;
			if (eaten[sushi[end]]==0) cnt++;
			eaten[sushi[end]]+=1;
		}
		System.out.println(result);
	}
	

}
