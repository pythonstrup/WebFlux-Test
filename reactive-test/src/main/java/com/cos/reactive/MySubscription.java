package com.cos.reactive;

import java.util.Iterator;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

// 구독 정보(구독자, 어떤 데이터를 구독할지)
public class MySubscription implements Subscription{

	private Subscriber subscriber;
	private Iterator<Integer> it;
	
	public MySubscription(Subscriber subscriber, Iterable<Integer> iterable) {
		this.subscriber = subscriber;
		this.it = iterable.iterator();
	}

	@Override
	public void request(long n) { // 1개
		
		while(n > 0) {
			if(it.hasNext()) {
				subscriber.onNext(it.next()); // 1
			} else { // 더 이상 줄 데이터가 없을 때.
				subscriber.onComplete();
				break;
			}
			n--; // 0
		}
	}

	@Override
	public void cancel() {
		
		
	}

}
