package com.tesfai.familyapp.service;

import java.util.List;

import com.tesfai.familyapp.exception.MemberAlreadyExistsException;
import com.tesfai.familyapp.exception.MemberNotFoundException;
import com.tesfai.familyapp.model.Member;


public interface MemberService {

	Member addMember(Member member) throws MemberAlreadyExistsException;

	Member getMember(Integer id)throws MemberNotFoundException;

	List<Member> getAllMembers();
	
	Member deleteMember(Integer id) throws MemberNotFoundException;
	
	Member updateMember(Integer id,Member member) throws MemberNotFoundException;

}
