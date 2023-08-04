package StackQueue;
/*
 * Queue
 * FIFO 자료구조
 * 1. offer(): queue에 요소 추가
 * 2. poll(): queue 요소 삭제
 * 3. peek(): queue의 첫 번째 요소 리턴 
 * 4. isEmpty(): queue가 비어있는지 검사
 * 5. size(): queue의 현재 크기 리턴
 * */

import java.util.Arrays;

public class Queue {

	int top; // queue의 마지막 요소 위치
	Object[] arr; // queue의 요소를 담는 배열
	int N; // queue 안에 담겨있는 요소 개수

	// 생성자 호출 시, 초기화
	Queue() {
		arr = new Object[] {};
		top = -1; // 비어있는 상태
		N = 0; // queue의 크기는 0
	}

	// queue에 요소 추가
	public void offer(Object element) {
		N += 1; // queue의 크기 1 증가
		Object[] tmp = new Object[N]; // 새로운 요소를 담을 배열 임시 생성
		System.arraycopy(arr, 0, tmp, 0, N - 1); // 이전에 담은 요소들을 복사

		top += 1; // 마지막 요소가 담긴 위치 변경
		tmp[top] = element; // 마지막 요소 추가
		arr = tmp; // 변경된 queue 반영
	}

	// queue에서 요소 삭제
	public Object poll() {
		if (isEmpty())
			return null; // queue가 비어있는 상태라면 null 반환
		Object first = arr[0]; // 삭제할 값
		arr = Arrays.copyOfRange(arr, 1, N); // 첫 번째 요소를 제외한 나머지 요소들 복사
		N -= 1; // queue에 들어간 요소의 개수 1개 감소
		top -= 1; // queue에 들어있는 마지막 요소 위치 변경
		return first; // 첫번째 요소 반환
	}

	// queue에서 첫 번째 원소 반환
	public Object peek() {
		if (isEmpty())
			return null; // queue가 비어있다면 null 반환
		return arr[0]; // 첫 번째 요소 반환
	}

	// queue가 비어있는지 검사
	public boolean isEmpty() {
		if (top == -1)
			return true;
		return false;
	}

	// queue의 size 반환
	public int size() {
		return N;
	}

}
