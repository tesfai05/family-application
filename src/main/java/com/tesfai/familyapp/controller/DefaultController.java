package com.tesfai.familyapp.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

	@GetMapping("/")
	public void loadIndex(HttpServletResponse httpResponse) throws Exception {
		// String currentUsername =
		// SecurityContextHolder.getContext().getAuthentication().getName();
		// httpResponse.sendRedirect("/user/"+currentUsername);

		httpResponse.sendRedirect("swagger-ui.html");
	}

//	@RequestMapping("user")
//	public Principal user(Principal principal) {
//
//		return principal;
//	}

}
