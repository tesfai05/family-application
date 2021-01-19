package com.tesfai.familyapp.service.impl;
//package com.example.FamilySite.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.FamilySite.model.User;
//import com.example.FamilySite.model.UserPrincipal;
//import com.example.FamilySite.repository.UserRepository;
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//	@Autowired
//	private UserRepository userRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepository.findUserByUsername(username).orElseThrow(()->{
//			throw new UsernameNotFoundException("No user is found with username : "+username);
//		});
//	
//		
//		return new UserPrincipal(user);
//	}
//
//}
