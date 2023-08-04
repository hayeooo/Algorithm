package StackQueue;

import java.util.Arrays;

/*
 * Stack
 * LIFO 자료구조
 * 1. push(): 스택에 요소 추가
 * 2. pop(): top에 위치한 요소 삭제
 * 3. peek(): stack top에 있는 요소 반납
 * 4. isEmpty(): stack이 비어있는지 검사
 * */
public class Stack {
	int top = -1; // 현재 stack top의 위치
	Object[] arr; // stack의 요소를 저장하는 배열
	int N = 0; // 현재 stack에 저장된 요소의 수

	public Stack() { // stack 객체를 생성하면 배열을 생성한다.
		arr = new Object[] {};
	}

	// 스택에 요소 추가
	public void push(Object element) {
		N += 1; // 현재 stack에 저장된 요소의 수를 하나 늘린다.
		Object[] tmp = new Object[N]; // 새로운 요소가 들어갈 수 있는 배열을 새로 할당한다.
		System.arraycopy(arr, 0, tmp, 0, N - 1); // 이전에 저장되어있던 요소를 복사한다.

		top += 1; // stack의 top 위치를 변경한다.
		tmp[top] = element; // top 위치에 새로운 요소를 넣는다.
		arr = tmp; // 변경된 stack을 저장한다.
	}

	// stack의 요소 삭제
	public Object pop() {
		if (isEmpty()) { // 스택이 비었을 경우 -1 리턴
			return null;
		}
		Object last = arr[top]; // 리턴할 요소를 미리 저장
		arr = Arrays.copyOfRange(arr, 0, top); // 리턴할 요소를 제외하고 앞쪽에 위치한 요소들을 복사
		top -= 1; // top의 위치 변경
		return last; // 요소 리턴
	}

	// stack top에 있는 요소 반납
	public Object peek() {
		return arr[top];
	}

	// 현재 스택이 비었는지 검사
	public boolean isEmpty() {
		if (top == -1)
			return true;
		return false;
	}

	// 현재 스택의 크기 리턴
	public int size() {
		return N;
	}
}
