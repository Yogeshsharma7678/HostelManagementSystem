/*This is one of the important model 
 * of the project
 */
package com.HostelMS.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//We're using getters and settersfrom lombok
@Entity
@Getter
@Setter
@ToString
public class room {

	@Id
	private int roomId;
	private String roomName;
	private String roomType;
}