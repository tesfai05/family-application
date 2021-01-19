//package com.tesfai.familyapp.service.impl;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.tesfai.familyapp.dto.UserRegistrationDto;
//import com.tesfai.familyapp.model.Role;
//import com.tesfai.familyapp.model.User;
//import com.tesfai.familyapp.repository.UserRepository;
//import com.tesfai.familyapp.service.UserService;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
//
//	@Override
//	public User findByUsername(String email) {
//		return userRepository.findByEmail(email).orElseThrow(() -> {
//			throw new UsernameNotFoundException("Invalid username or password.");
//
//		});
//	}
//
//	@Override
//	public User save(UserRegistrationDto registration) {
//		User user = new User();
//		user.setUsername(registration.getEmail());
//		user.setPassword(passwordEncoder.encode(registration.getPassword()));
//		user.setRoles(Arrays.asList(new Role("ROLE_USER")));
//		return userRepository.save(user);
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		User user = userRepository.findByEmail(email).orElseThrow(() -> {
//			throw new UsernameNotFoundException("Invalid username or password.");
//
//		});
//		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//				mapRolesToAuthorities(user.getRoles()));
//	}
//
//	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
//		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//	}
//}