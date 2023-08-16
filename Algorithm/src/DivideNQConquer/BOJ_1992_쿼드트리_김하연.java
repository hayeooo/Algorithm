package DivideNQConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ 1992: 쿼드트리
 * 주어진 영상이 모두 0으로만 되어 있으면 압축 결과는 "0"
 * 모두 1로만 되어 있으면 압축 결과는 "1"
 * 만약 0과 1이 섞여 있으면 전체를 한 번에 나타내지 못하고 4 개의 영역으로 쪼개 압축
 * 
 * 분할과 정복 알고리즘
 * 1. 모두 0 또는 1일 경우 해당 숫자만 return
 * 2. 그렇지 않은 경우, 괄호를 넣고 4개로 나눈 영역에 대한 재귀함수를 호출하고 괄호를 닫는다.
 * 
 * 각 영역의 기준점은 왼쪽 위 행과 열이다.
 */
public class BOJ_1992_쿼드트리_김하연 {
	static BufferedReader br;
	
	static int N;						// 영상의 크기
	static char[][] map;				// 영상의 정보를 담은 배열
	static String res;					// 압축 결과를 담을 문자열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 영상의 크기를 입력 받는다.
		N=Integer.parseInt(br.readLine().trim());
		
		// 영상 정보를 저장할 배열 크기를 초기화한다.
		map=new char[N][N];
		// 압축 결과를 담을 문자열을 빈 문자열로 초기화한다.
		res="";
		
		// 영상 정보를 입력 받는다.
		for (int row=0;row<N;row++) {
			map[row]=br.readLine().trim().toCharArray();
		}
		// 영상을 압축한다.
		compress(0,0,N);
		// 압축 결과를 출력한다.
		System.out.println(res);
	}
	// 영상을 압축하는 재귀함수
	public static void compress(int row, int col, int size) { // 각 영역의 row: 왼쪽 col: 위 size: 영역 크기
		
		// size가 1인 경우
		if (size==1) {
			// 결과에 해당 숫자를 더하고 종료한다.
			res+=map[row][col];
			return;
		}
		// 안에 숫자가 모두 1이거나 0인 경우
		if (check(row,col,size)) {
			// 결과에 해당 숫자를 더하고 종료한다.
			res+=map[row][col];
			return;
		}
		// 0과 1이 섞여있는 경우
		// 4 영역으로 쪼갠 다음 (1사분면)+(2사분면)+(3사분면)+(4사분면)
		res+="(";
		int nextSize=size/2;										// 다음 탐색할 영역의 크기
		compress(row,col,nextSize);									// 왼쪽 위
		compress(row,col+nextSize,nextSize);						// 오른쪽 위
		compress(row+nextSize,col,nextSize);						// 왼쪽 아래
		compress(row+nextSize,col+nextSize,nextSize);				// 오른쪽 아래
		res+=")";
		
	}
	// 해당 영역의 숫자가 모두 같은지 판단하는 함수
	public static boolean check(int row, int col, int size) {
		// 각 영역의 숫자에 대해
		for (int r=row;r<row+size;r++) {
			for (int c=col;c<col+size;c++) {
				if (map[row][col]!=map[r][c]) {	// 다르다면
					return false;				// false를 return
				}
			}
		}
		// 숫자가 모두 같다면
		// true를 return
		return true;
	}
}

