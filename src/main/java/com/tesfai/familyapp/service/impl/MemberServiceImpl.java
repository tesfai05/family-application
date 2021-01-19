package com.tesfai.familyapp.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesfai.familyapp.exception.MemberAlreadyExistsException;
import com.tesfai.familyapp.exception.MemberNotFoundException;
import com.tesfai.familyapp.model.Address;
import com.tesfai.familyapp.model.Member;
import com.tesfai.familyapp.repository.AddressRepository;
import com.tesfai.familyapp.repository.MemberRepository;
import com.tesfai.familyapp.service.AddressService;
import com.tesfai.familyapp.service.MemberService;


@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	private MemberRepository memberRepository;
	private AddressService addressService;
	private AddressRepository addressRepository;
	//private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Autowired
	public MemberServiceImpl(MemberRepository memberRepository, AddressService addressService,
			AddressRepository addressRepository) {
		this.memberRepository = memberRepository;
		this.addressService = addressService;
		this.addressRepository = addressRepository;
	}

	@Override
	public Member addMember(Member member) throws MemberAlreadyExistsException {

		List<Address> addresses = addressService.getAllAddresses();
		if (addresses != null) {
			for (Address addr : addresses) {
				if (addr.equals(member.getAddress())) {
					member.setAddress(addr);
					break;
				}
			}
		}

		List<Member> members = getAllMembers();
		if (members != null) {
			for (Member p : members) {
				if (p.equals(member)) {
					throw new MemberAlreadyExistsException("Member already exists !!!");
				}
			}
		}
		
		return memberRepository.save(member);
	}

	@Override
	public Member getMember(Integer id) throws MemberNotFoundException {

		Member member = memberRepository.findById(id).orElseThrow(() -> {
			throw new MemberNotFoundException("Memeber with ID " + id + " not found !!");
		});
		
		return member;
	}

	@Override
	public List<Member> getAllMembers() {
		return memberRepository.findAll();
	}

	@Override
	public Member deleteMember(Integer id) throws MemberNotFoundException {

		Member member = memberRepository.findById(id).orElseThrow(() -> {
			throw new MemberNotFoundException("Memeber with ID " + id + " not found !!");
		});

		memberRepository.deleteById(id);

		return member;

	}

	@Override
	public Member updateMember(Integer id, Member member) throws MemberNotFoundException {

		boolean addressFound = false;

		Member memberFromDB = memberRepository.findById(id).orElseThrow(() -> {
			throw new MemberNotFoundException("Member with ID " + id + " not found !!");
		});

		memberFromDB.setMemberId(id);
		memberFromDB.setFirstName(member.getFirstName());
		memberFromDB.setLastName(member.getLastName());
		memberFromDB.setGender(member.getGender());
		memberFromDB.setBirthDate(member.getBirthDate());

		List<Address> addresses = addressService.getAllAddresses();
		if (addresses != null) {
			for (Address addr : addresses) {
				if (addr.equals(member.getAddress())) {

					memberFromDB.setAddress(addr);
					addressFound = true;
					break;
				}
			}
		}
		if (!addressFound) {

			Address address = addressRepository.save(member.getAddress());
			memberFromDB.setAddress(address);
		}

		memberRepository.save(memberFromDB);

		return memberFromDB;
	}

}
