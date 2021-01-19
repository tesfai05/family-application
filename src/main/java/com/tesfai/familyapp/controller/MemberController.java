package com.tesfai.familyapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tesfai.familyapp.exception.MemberNotFoundException;
import com.tesfai.familyapp.model.Member;
import com.tesfai.familyapp.service.MemberService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

	private MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@ApiOperation(value = "Get all members", notes = "Get all members", response = Member.class)
	@GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Member>> getAllMember() {
		List<Member> membersList = memberService.getAllMembers();
		return ResponseEntity.ok(membersList);
	}

	@ApiOperation(value = "Get one member", notes = "Get one member", response = Member.class)
	@GetMapping(path = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Cacheable(key = "#id", value = "members")
	public ResponseEntity<Member> getMember(@PathVariable("id") Integer id) throws MemberNotFoundException {
		System.out.println("Feched from DB...");
		Member p = memberService.getMember(id);
		return ResponseEntity.ok(p);
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Add a new member", notes = "Add a new member", response = Member.class)
	@PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public <T> ResponseEntity<T> AddMember(@Valid @RequestBody Member member, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			return (ResponseEntity<T>) ResponseEntity.badRequest().body(result.getFieldError());
		}

		Member p = memberService.addMember(member);
		return (ResponseEntity<T>) ResponseEntity.ok(p);
	}

	@ApiOperation(value = "Delete a member", notes = "Delete a member", response = Member.class)
	@DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Member> deleteMember(@PathVariable("id") Integer id) throws MemberNotFoundException {

		Member p = memberService.deleteMember(id);
		return ResponseEntity.ok(p);
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Update a member", notes = "Update a member", response = Member.class)
	@PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CacheEvict(value = "members", key = "#id")
	public <T> ResponseEntity<T> updateMember(@PathVariable("id") Integer id, @Valid @RequestBody Member member,
			BindingResult result) throws Exception {

		if (result.hasErrors()) {
			return (ResponseEntity<T>) ResponseEntity.badRequest().body(result.getFieldErrors());
		}

		Member p = memberService.updateMember(id, member);
		return (ResponseEntity<T>) ResponseEntity.ok(p);
	}

}
