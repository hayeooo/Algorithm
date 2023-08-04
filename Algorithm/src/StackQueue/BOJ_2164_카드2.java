package StackQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/*
 * BOJ 2164: 카드2
 * N장의 카드가 있고, 1번 카드가 제일 위에, N번 카드가 제일 아래에 있다.
 * 제일 위에 있는 카드를 바닥에 버리고, 그 다음 제일 위에 있는 카드를 제일 아래로 옮긴다.
 * 카드가 한 장 남을 때까지 반복한다.
 * 
 * 가장 위에 있는 카드를 버리고 가장 아래에 카드를 추가하므로
 * 양방향성을 가지는 ArrayDeque를 활용한다.
 * 
 * */
public class BOJ_2164_카드2 {
	static BufferedReader br;
	static int N; // N장의 카드
	static Deque<Integer> que; // 나열된 카드 정보를 저장하는 queue

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		que = new ArrayDeque<>(); // queue 객체 생성

		N = Integer.parseInt(br.readLine()); // N 입력

		// 1부터 N까지 카드를 넣는다.
		for (int card = 1; card <= N; card++) {
			que.offer(card);
		}

		// 카드가 한 장 남을 때까지 반복한다.
		while (que.size() > 1) {
			que.poll(); // 제일 위에 있는 카드를 바닥에 버린다.
			int toMoved = que.poll(); // 그 다음, 제일 위에 있는 카드를
			que.offer(toMoved); // 제일 아래에 있는 카드 밑으로 옮긴다.
		}
		System.out.println(que.peek()); // 제일 마지막에 남게 되는 카드를 출력한다.
	}
}
