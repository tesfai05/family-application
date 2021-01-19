
package com.tesfai.familyapp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "address_table")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Integer addressId;

	@Column(name = "street")
	@NotBlank(message = "{notblank}")
	private String street;

	@Column(name = "city")
	@NotBlank(message = "{notblank}")
	private String city;

	@Column(name = "zip_code")
	@NotBlank(message = "{notblank}")
	private String zipcode;

	@Column(name = "country")
	@NotBlank(message = "{notblank}")
	private String country;

	@Transient
	@JsonIgnore
	@OneToMany(mappedBy = "address")
	private List<Member> members;

	@Override
	public int hashCode() {
		return Objects.hash(city, country, street, zipcode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || this.getClass() != obj.getClass())
			return false;

		final Address address = (Address) obj;
		
		if ((this.city == null) ? (address.city != null) : !this.city.equals(address.city))
			return false;

		if ((this.country == null) ? (address.country != null) : !country.equals(address.country))
			return false;

		if ((this.street == null) ? (address.street != null) : !street.equals(address.street))
			return false;

		if ((this.zipcode == null) ? (address.zipcode != null) : !zipcode.equals(address.zipcode))
			return false;

		return true;
	}

}
