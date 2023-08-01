package Recursion;

import java.io.*;
import java.util.Arrays;

/*
 * 순열(중복X) 구현
 * 1. nPr의 n과 r 입력받기
 * 2. 1부터 n까지 숫자 중 r개를 뽑는 순열 구현
 * 3. r개 뽑았을 경우 결과값 출력
 * */
public class 순열 {
	static BufferedReader br;
	static boolean[] visited; // 숫자 중복 체크하는 배열
	static int[] res; // 결과를 담는 배열
	static int N; // nPr의 n
	static int R; // nPr의 r

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		// 입력
		N = Integer.parseInt(br.readLine().trim());
		R = Integer.parseInt(br.readLine().trim());

		// 입력값에 따른 배열 크기 초기화
		visited = new boolean[N + 1];
		res = new int[R];

		// 순열 구하기
		permutation(0);
	}

	public static void permutation(int cnt) {
		if (cnt >= R) { // R개를 뽑았을 경우, 결과 배열 출력
			System.out.println(Arrays.toString(res));
			return; // 재귀 종료
		}
		for (int number = 1; number <= N; number++) { // 숫자 1부터 N까지 순회하며 숫자 조합 생성
			if (visited[number]) { // 숫자 중복 체크
				continue;
			}
			// 결과 배열에 숫자를 넣은 후,
			// 방문 표시
			// 다음 자리에 들어갈 숫자 탐색(재귀 함수 호출)
			// 재귀 후, 현 자리에 다른 숫자가 들어가도록 방문 처리 취소(false)
			res[cnt] = number;
			visited[number] = true;
			permutation(cnt + 1);
			visited[number] = false;
		}
	}

}
