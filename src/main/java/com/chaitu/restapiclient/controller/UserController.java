package com.chaitu.restapiclient.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.chaitu.restapiclient.model.Entry;
import com.chaitu.restapiclient.model.User;
import jakarta.servlet.http.HttpSession;
@Controller
public class UserController {
	
	private RestTemplate template=new RestTemplate();
	
	@Autowired
	HttpSession session;
	
	@GetMapping("home")
	public String homepage() {
		
		String model=new String("loginpage");
		
		return model;
	}
	
	
	@GetMapping("register")
	public String registrationPage() {
		
		String model=new String("registrationpage");
		
		return model;
	}
	
	
	
	@PostMapping(value="saveuser")//it means it accepts only post requests not get requests
	public String saveuser(@ModelAttribute("user") User user) {
		//code to save user details in DB
		
		String viewname="registersuccess";
		
		System.out.print("hi"+user.getUsername()+"hell");
		if(user.getUsername().equals(""))return "registrationpage";
		
		try {
			template.postForObject("http://localhost:8081/mydiaryapi/users/",user,User.class);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			return "registrationpage";
		}
		
		return viewname;
		
	}
	
	
	@PostMapping(value="/authenticate")
	public String authenticateuser(@ModelAttribute("user") User user,Model model) {
		
		String viewname="loginpage";
		
		User user1;
		try {
			user1 = template.getForObject("http://localhost:8081/mydiaryapi/users/user/{username}",User.class,user.getUsername());
		} catch (RestClientException e) {
			return viewname;
		}
		
		if(user1!=null && user.getPassword().equals(user1.getPassword())) {
			viewname="userhomepage";
			model.addAttribute("user", user1);
			
			session.setAttribute("user", user1);
			
			Entry[] entries=null;
			
			try {
				entries=template.getForObject("http://localhost:8081/mydiaryapi/entries/userid?userid={userid}",Entry[].class, user1.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("entrieslist", entries);
			System.out.println(entries[0].getEntrydate());
		}
		
		return viewname;
				
	}
	
	
	@GetMapping("addentry")
	public String addentry() {
		
		
		return "addentryform";
	}
	
	
	@PostMapping("saveentry")
	public String saveentry(@ModelAttribute("entry")Entry entry,Model model) {
		
		String viewname="userhomepage";
		if(entry.getEntrydate()!=null)
		template.postForEntity("http://localhost:8081/mydiaryapi/entries/",entry,Entry.class);
		User user1=(User)session.getAttribute("user");
		
		Entry[] entries=null;
		try {
			entries=template.getForObject("http://localhost:8081/mydiaryapi/entries/userid?userid={userid}",Entry[].class, user1.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("entrieslist", entries);
		
		
		return viewname;
	}
	
	
	@GetMapping("viewentry")
	public String viewentry(@RequestParam("id")int id,Model model) {
		
		String viewname="displayentry";
		
		try {
			Entry entry=template.getForObject("http://localhost:8081/mydiaryapi/entries/{id}",Entry.class,id);
			model.addAttribute("entry", entry);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return viewname;
	}

	
	@GetMapping("userhome")
	public String userhomepage(Model model) {
		
		String viewname="userhomepage";
		
		User user1=(User)session.getAttribute("user");
		Entry[] entries=null;
		try {
			entries=template.getForObject("http://localhost:8081/mydiaryapi/entries/userid?userid={userid}",Entry[].class, user1.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("entrieslist", entries);
		
		return viewname;
	}
	
	
	
	@GetMapping("updateentry")
	public String updateentry(@RequestParam("id")int id,Model model) {
		
		String viewname="displayupdateentry";
		
		try {
			Entry entry=template.getForObject("http://localhost:8081/mydiaryapi/entries/{id}",Entry.class,id);
			model.addAttribute("entry", entry);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		User user1=(User)session.getAttribute("user");
		if(user1==null) {
			viewname="loginpage";
		}
		
		return viewname;
	}
	
	
	
	@PostMapping("processentryupdate")
	public String processentryupdate(@ModelAttribute("entry")Entry entry,Model model) {
		
		String viewname="userhomepage";
		template.postForObject("http://localhost:8081/mydiaryapi/entries/", entry, Entry.class);
		User user1=(User)session.getAttribute("user");
		
		Entry[] entries=null;
		try {
			entries=template.getForObject("http://localhost:8081/mydiaryapi/entries/userid?userid={userid}",Entry[].class, user1.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("entrieslist", entries);
		
		
		return viewname;
	}
	
	
	@GetMapping("deleteentry")
	public String deleteentry(@RequestParam("id")int id,Model model) {
		
		String viewname="userhomepage";
		
		User user1=(User)session.getAttribute("user");
		

		if(user1==null) {
			viewname="loginpage";
		}
		
		else {
			try {
				Entry entry=template.getForObject("http://localhost:8081/mydiaryapi/entries/{id}",Entry.class,id);
				template.delete("http://localhost:8081/mydiaryapi/entries/{id}",entry.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		Entry[] entries=null;
		try {
			entries=template.getForObject("http://localhost:8081/mydiaryapi/entries/userid?userid={userid}",Entry[].class, user1.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("entrieslist", entries);
		
		
		return viewname;
		
	}

	@GetMapping("signout")
	public String logout() {
		
		String model=new String("loginpage");
		
		session.invalidate();
		
		return model;
	}
	

}
