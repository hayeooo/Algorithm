package Recursion;

import java.util.*;
import java.io.*;

/*
 * SWEA 1208: Flatten
 * 최고점과 최저점의 간격을 줄이는 평탄화 작업 수행
 * 1. 1..10까지 테스트케이스 반복
 * 2. 덤프 횟수 입력
 * 3. 각 상자의 높이 값 배열로 입력받기
 * 4. 정렬을 사용하여 최고점-=1, 최저점+=1 반복
 * 5. 최고점과 최저점 차이 출력
 * */
public class SWEA_1208_Flatten {
	static BufferedReader br;
	static final int T = 10; // 테스트케이스 횟수
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		// 테스트 케이스 수만큼 반복
		for (int test_case = 1; test_case <= T; test_case++) {
			List<Integer> list = new ArrayList<>(); // 상자의 높이가 들어갈 ArrayList

			int N = Integer.parseInt(br.readLine()); // dump 횟수 입력

			// 상자의 높이 입력
			st = new StringTokenizer(br.readLine(), " ");
			while (st.hasMoreTokens()) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			// N 번만큼 dump 진행
			for (int dump = 0; dump < N; dump++) {
				Collections.sort(list); // 정렬을 수행하여 최저점은 index 0번, 최고점은 list.size()-1 지점에 위치하도록 함
				list.set(0, list.get(0) + 1); // 최저점+=1
				list.set(list.size() - 1, list.get(list.size() - 1) - 1); // 최고점+=1
			}
			Collections.sort(list);
			// 최고점과 최저점의 차이 출력
			System.out.printf("#%d %d%n", test_case, list.get(list.size() - 1) - list.get(0));
		}
	}

}
