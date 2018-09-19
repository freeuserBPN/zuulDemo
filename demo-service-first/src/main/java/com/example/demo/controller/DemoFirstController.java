package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.IGreetingClient;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@RestController
@RequestMapping("demofirst")
public class DemoFirstController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	IGreetingClient iGreetingClient;

	@GetMapping("/greeting")
	public String getGreetings() {
		return "greetings from demo-service-first";
//		return getsecondServiceGreetViaRestTemplate();
	}

	public String getsecondServiceGreetViaRestTemplate() {
		Application application = eurekaClient.getApplication("demo-service-second");
		InstanceInfo instanceInfo = application.getInstances().get(0);
		String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "demosecond/greeting";
		System.out.println("via RestTemplate clients to get greetings....");
		return restTemplate.getForObject(url, String.class);
	}
	
	@GetMapping("/feign/greeting")
	public String getGreetingViaFeign() {
//		return "greetings from demo-service-first";
		System.out.println("via Feign clients to get greetings....");
		return iGreetingClient.getGreetingsFromSecondService();
	}

}
