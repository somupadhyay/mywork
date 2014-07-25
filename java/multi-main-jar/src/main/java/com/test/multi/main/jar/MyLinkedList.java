package com.test.multi.main.jar;


public class MyLinkedList<T> {

	private MyLinkedList next;
	private T data;
	

	private MyLinkedList(MyLinkedList next, T data) {
		super();
		this.next = next;
		this.data = data;
	}

	private MyLinkedList() {
		super();
	}
	
	public void add(T data){
		MyLinkedList<T> tempLL = new MyLinkedList<T>();
		tempLL.add(data);
		this.next=tempLL;
	}
	
	public T get(){
		return this.data;
	}
	
}
