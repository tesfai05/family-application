package com.tesfai.familyapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.tesfai.familyapp.validation.ValidGender;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name = "member_table")
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Integer memberId;

	@ApiModelProperty(notes = "First Name of Member")
	@Size(min = 2, max = 20, message = "{firstName.size}")
	@NotBlank(message = "{notblank}")
	private String firstName;

	@ApiModelProperty(notes = "Last Name of Member")
	@Size(min = 2, max = 20, message = "{lastName.size}")
	@NotBlank(message = "{notblank}")
	private String lastName;

	@ApiModelProperty(notes = "Gender of Member")
	@ValidGender(message = "{gender.type}")
	@NotBlank(message = "{notblank}")
	private String gender;

	@ApiModelProperty(notes = "Date of birth of Member")
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	@NotNull(message = "{notnull}")
	private LocalDate birthDate;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id")
	@Valid
	private Address address;

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, gender, birthDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		final Member member = (Member) obj;

		if ((this.firstName == null) ? (member.firstName != null) : !this.firstName.equals(member.firstName))
			return false;

		if ((this.lastName == null) ? (member.lastName != null) : !lastName.equals(member.lastName))
			return false;

		if ((this.gender == null) ? (member.gender != null) : !gender.equals(member.gender))
			return false;

		if ((this.birthDate == null) ? (member.birthDate != null) : !birthDate.equals(member.birthDate))
			return false;

		return true;
	}

	/*
	 * { "firstName":"Tesfai", "lastName":"Gebrekidan", "gender":"Male",
	 * "birthDate":"1986-12-03", "address":{ "street":"205 E Walnut",
	 * "city":"Fairfield", "zipcode":"52556", "country":"USA" } }
	 * 
	 */

}
