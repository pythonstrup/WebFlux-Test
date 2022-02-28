package com.cos.reactive;

public class App {
	public static void main(String[] args) {
		MyPub pub = new MyPub();
		MySub sub = new MySub();
		
		pub.subscribe(sub);
	}
}
