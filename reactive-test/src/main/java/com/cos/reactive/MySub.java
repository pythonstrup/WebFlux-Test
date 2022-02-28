package com.cos.reactive;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class MySub implements Subscriber<Integer>{

	private Subscription s;
	private int bufferSize = 3;
	private int constentSize = 3;
	
	@Override
	public void onSubscribe(Subscription s) {
		
		System.out.println("4. 구독자: 구독 정보 수신 완료, 매일 신문 한 개씩 배달 바람.");
		this.s = s;
//		s.request(1); // 신문 한 개씩 매일 주세요. (백프레셔 - 소비자가 한 번에 처리할 수 있는 개수 요청)
		s.request(bufferSize);
	}

	@Override
	public void onNext(Integer t) {

		System.out.println("구독 데이터 전달: onNext()="+t);
		bufferSize--;
		if (bufferSize == 0) {
			System.out.println("하루 지남.");
			bufferSize = constentSize;
			s.request(bufferSize);
		}
		
	}

	@Override
	public void onError(Throwable t) {
		
		System.out.println("구독 중 에러");
	}

	@Override
	public void onComplete() {
		
		System.out.println("구독 완료");
	}

}
