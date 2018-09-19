package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("demo-service-second/demosecond")
public interface IGreetingClient {
	
	@RequestMapping("/greeting")
	public String getGreetingsFromSecondService();

}
