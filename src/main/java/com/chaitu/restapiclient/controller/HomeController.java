package com.chaitu.restapiclient.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.chaitu.restapiclient.model.User;

@Controller
public class HomeController {
	
	
	public String homepage(Model model) {
		
		String viewname="homepage";
		
		RestTemplate template=new RestTemplate();
		
		User user=template.getForObject("http://localhost:8081/mydiaryapi/users/5",User.class);
		
		ResponseEntity<User> r=template.getForEntity("http://localhost:8081/mydiaryapi/users/5",User.class);
		
		System.out.println("Responsebody:"+r.getBody().getUsername());
		System.out.println("Headres:"+r.getHeaders());
		System.out.println("Status code"+r.getStatusCode());
		System.out.println(r.getClass());
		model.addAttribute("user",user);
		
		//get request to retrieve multiple requests
		
		ResponseEntity<User[]> s=template.getForEntity("http://localhost:8081/mydiaryapi/users/",User[].class);
		
		User users[]=s.getBody();
		for(int i=0;i<users.length;i++) {
			System.out.println(users[i]);
		}
		
		//post request
		
		User u=new User();
		u.setUsername("Lakshman");
		u.setPassword("lakshman");
		
		//User us=template.postForObject("http://localhost:8081/mydiaryapi/users/", u, User.class);
		//System.out.println("post:"+us.toString());
		
		
		//put request
		
		User u1=new User();
		u1.setId(12);
		u1.setUsername("Lakshman");
		u1.setPassword("12345");
		
		template.put("http://localhost:8081/mydiaryapi/users/", u1);
		
		
		
		
		//delete request
		
		//template.delete("http://localhost:8081/mydiaryapi/users/1");
		
		
		return viewname;
		
	}
	
	

}
