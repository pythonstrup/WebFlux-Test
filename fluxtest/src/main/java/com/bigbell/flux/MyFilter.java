package com.bigbell.flux;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MyFilter implements Filter{

	private EventNotify eventNotify;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("필터 실행됨.");
		
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		servletResponse.setContentType("text/event-stream; charset=utf-8");
		
		// WebFlux의 기본
		// 1. Reactive Streams 라이브러리를 쓰면 표준을 지켜서 응답을 할 수 있다.
		PrintWriter out = servletResponse.getWriter();
		for (int i = 0; i < 5; i++) {
			out.print("response 완료: "+i+"\n");
			out.flush(); // 버퍼를 비우는 것. 그런데 실제로 전송이 안됨. MIME타입이 text/plain이기 때문이다. text/event-stream으로 바꿔주자.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// 2. SSE Emitter 라이브러리를 사용하면 편하게 사용할 수 있다.
		while(true) {
			try {
				if(eventNotify.getChange()) {
					int lastIndex = eventNotify.getEvents().size()-1;
					out.print("response 완료: "+eventNotify.getEvents().get(lastIndex)+"\n");
					out.flush();
					eventNotify.setChange(false);
				}
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// 3. WebFlux -> Reactive streams 가 적용된 stream을 배우고(비동기 단일스레드 동작)
		// 4. Servlet MVC -> Reactive streams 가 적용된 stream을 배우고(멀티스레드 동작)
	}
	
}
