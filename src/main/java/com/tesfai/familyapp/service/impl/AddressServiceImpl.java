package com.tesfai.familyapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesfai.familyapp.model.Address;
import com.tesfai.familyapp.repository.AddressRepository;
import com.tesfai.familyapp.service.AddressService;


@Service
public class AddressServiceImpl implements AddressService {

	private AddressRepository addressRepository;

	@Autowired
	public AddressServiceImpl(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@Override
	public List<Address> getAllAddresses() {
		return addressRepository.findAll();
	}

}
