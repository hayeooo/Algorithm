package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 2653: 색종이
 * 가로, 세로 크기가 각각 100인 정사각형 모양의 흰색 도화지에
 * 가로, 세로의 크기가 각각 10인 정사각형 모양의 검은색 색종이를 색종이의 변과 도화지의 변이 평행하도록 붙인다.
 * 색종이를 한 장 여러 장을 붙인 후 색종이가 붙은 검은 영역의 넓이를 구한다.
 * 
 * 색종이가 도화지 밖으로 나가는 경우는 없다.
 * 
 * 1. 색종이의 수를 입력 받는다.
 * 2. 색종이의 범위에 드는 값은 true로 처리하고 totalArea를 증가시킨다. 
 * 	2-1. 이미 true인 범위는 건너뛴다. (겹치는 영역 제외)
 * 3. 겹치는 영역 제외, 영역의 결과를 출력한다.
 * */
public class BOJ_2563_색종이_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	static int N;
	static boolean[][] map;
						
	static int totalArea;
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 색종이 수를 입력 받는다.
		N=Integer.parseInt(br.readLine().trim());
		
		// 도화지 초기화
		map=new boolean[101][101];
		for (int idx=0;idx<N;idx++) {
			st=new StringTokenizer(br.readLine());
			
			// 색종이 위치 입력 받기
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			
			// 색종이 범위 안에 드는 영역은 true
			// 이미 true 처리된 영역을 건너 뛴다.
			for (int row=x;row<x+10;row++) {
				for (int col=y;col<y+10;col++) {
					if (!map[row][col]) {
						map[row][col]=true;
						totalArea+=1;
					}
				}
			}
		}
		// 결과를 출력한다.
		System.out.println(totalArea);
		
	}
}
