package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 1759: 암호만들기
 * 최소 한 개의 모음과 최소 두 개의 자음으로 구성되어 있는 사전 순대로 나열된 암호 출력
 */
public class BOJ_1759_암호만들기_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int L, C;				// 서로 다른 L 개의 알파벳 소문자, C 개의 문자가 주어진다.
	static char[] alphas;
	static char[] comb;
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		st=new StringTokenizer(br.readLine().trim());
		// L, C 입력 받기
		L=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		// 알파벳 정보를 저장할 배열 크기 초기화하기
		alphas=new char[C];
		
		// C 개의 알파벳을 입력받는다.
		st=new StringTokenizer(br.readLine().trim());
		for (int idx=0;idx<C;idx++) {
			alphas[idx]=st.nextToken().charAt(0);
		}
		
		// 사전 순으로 암호문을 만들기 위해 알파벳 배열을 정렬한다.
		Arrays.sort(alphas);
		comb=new char[L];
		combinations(0,0,0,0);
	}
	// 최소 두 개의 자음과 최소 한 개의 모음으로 구성된 암호문
	public static void combinations(int cnt,int startIdx,int consonants, int vowels) {
		// 암호문이 완성되었을 때
		if (cnt==L) {
			// 최소 두개의 자음과 최소 한 개의 모음으로 이루어진지 확인
			// 조건에 만족한다면 출력한다.
			if (consonants>=2 && vowels>=1) {
				for (int pwIdx=0;pwIdx<L;pwIdx++) {
					System.out.print(comb[pwIdx]);
				}
				System.out.println();
			}
			return;
		}
		// 현재 위치에 들어갈 알파벳을 선택한다.
		for (int idx=startIdx;idx<C;idx++) {
			comb[cnt]=alphas[idx];			// 알파벳 하나를 선택한다.
			// 모음인 경우
			// 모음의 개수를 하나 증가시키고 다음 위치에 들어갈 알파벳을 선택한다.
			if (comb[cnt]=='a'||comb[cnt]=='e'||comb[cnt]=='i'||comb[cnt]=='o'||comb[cnt]=='u') {
				combinations(cnt+1,idx+1,consonants,vowels+1);
			}
			// 자음인 경우
			// 자음의 개수를 하나 증가시키고 다음 위치에 들어갈 알파벳을 선택한다.
			else {
				combinations(cnt+1,idx+1,consonants+1,vowels);
			}
			
		}
	}
}
