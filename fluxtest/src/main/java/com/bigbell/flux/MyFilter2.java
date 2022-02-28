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
public class MyFilter2 implements Filter{

	private EventNotify eventNotify;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("필터2 실행됨.");
		
		// 데이터 발생시켜서 웹페이지에 반영시키기
		eventNotify.add("새로운 데이터");
	}
	
}
