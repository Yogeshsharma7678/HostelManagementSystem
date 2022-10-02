/*This is one of the important model 
 * of the project
 */
package com.HostelMS.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
//We're using getters and settersfrom lombok
@Getter
@Setter
@ToString
public class user {

	@Id
	// we are declaring auto increment primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@NotNull
	// not null annotation is used to declare that we cant leave it null from
	// validation api
	@Size(min = 3, max = 15, message = ("userName must be in the limit of 3-15 characters"))
	private String userName;
	@NotNull
	// pattern annotation is use to implementregular expression in a single line so
	// that we can give exact limit for the value
	@Pattern(regexp = "[0-9]{10}", message = "Phone no. must be of 10 digit")
	private String userPhone;
	// size annotaion is used to declare any specific size limit
	@Size(min = 5, max = 15, message = "Password must be at least 5 character and atmost 15")
	@NotNull
	private String userPassword;
	@Size(min = 3, max = 20, message = "Address must be in limit of 3 - 20 characters")
	private String userAddress;
	private String userRole;
	private int userFee;
	@ManyToOne
	private room userRoom;

}