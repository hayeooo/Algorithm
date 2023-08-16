package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * BOJ 1074: Z
 * (2^N)*(2^N)인 이차원 배열을 Z모양으로 탐색하려고 한다.
 * 왼쪽 위칸, 오른쪽 위칸, 왼쪽 아래칸, 오른쪽 아래칸 순서대로 방문한다.
 * N이 주어졌을 때, r행 c열은 몇 번째로 방문하는지 출력한다.
 * 
 * 재귀로 직접 방문한 경우 -> 시간초과
 * r행 c열 위치를 중심으로 직접 방문하지 않고 칸의 수만큼 더해서 계산한다.
 */
public class BOJ_1074_Z_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;			// (2^N)*(2^N) 배열의 크기 N
	static int r;			// r행
	static int c;			// c행
	static int cnt;			// 몇 번째 탐색인지 저장하는 변수
	
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		
		// N, r, c 입력 받는다.
		N=Integer.parseInt(st.nextToken());
		r=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		
		// 실제 크기를 구한다.
		int size=(int)Math.pow(2, N);
		
		// r행 c열은 몇 번째 탐색하는지 구한다.
		divideConquer(size,0,0);
		
		// 결과를 출력한다.
		System.out.println(cnt);
		
	}
	// 입력 받은 r,c가 사각형 중 어느 위치에 해당하는지 확인한다. (1..4분면)
	// 기준점은 확인하려는 사각형의 왼쪽 위 행, 열이다.
	// n: 사각형 가로, 세로 길이 / row: 사각형의 왼쪽 위의 행 / col: 사각형의 왼쪽 위의 열
	public static void divideConquer(int n,int row,int col) {
		// 탐색하는 사각형의 크기가 1인 경우
		if (n==1) {
			// 추가적인 탐색을 종료한다.
			return;
		}
		// 탐색하려는 정사각형의 크기가 1보다 큰 경우
		// r,c의 위치가 어느 사분면에 속하는지 찾는다.
		
		// 다음 탐색할 사각형의 크기
		int nextSize=n/2;
		
		// 1사분면에 위치한 경우
		if (r<row+nextSize && c<col+nextSize) {
			divideConquer(nextSize,row,col);
		}
		
		// 2사분면에 위치한 경우
		else if(r<row+nextSize && c>=col+nextSize) {
			// 1사분면의 칸의 수만큼 더해준다.
			cnt+=(nextSize*nextSize);
			divideConquer(nextSize,row,col+nextSize);
		}
		
		// 3사분면에 위치한 경우
		else if(r>=row+nextSize && c<col+nextSize) {
			// 1,2사분면의 칸 수만큼 더해준다.
			cnt+=2*nextSize*nextSize;
			divideConquer(nextSize,row+nextSize,col);
		}
		// 4사분면에 위치한 경우
		else {
			cnt+=3*nextSize*nextSize;
			divideConquer(nextSize,row+nextSize,col+nextSize);
		}
	}

}
