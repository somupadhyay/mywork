/**
 * 
 */
package com.ng2.web.domain;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.util.StringUtils;

/**
 * @author upadhs5
 *
 */
@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String fname;
    private int age;
    private String gender;
    @Transient
    private List<String> skills;
    private String skills1;
    /**
	 * @return the skills1
	 */
	public String getSkills1() {
		return skills1;
	}
	/**
	 * @param skills1 the skills1 to set
	 */
	public void setSkills1(String skills1) {
		this.skills1 = skills1;
	}
    /**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the skills
	 */
	public List<String> getSkills() {
		return Arrays.asList(StringUtils.delimitedListToStringArray(this.skills1, ","));
	}
	/**
	 * @param skills the skills to set
	 */
	public void setSkills(List<String> skills) {
		this.setSkills1(StringUtils.collectionToDelimitedString(skills, ","));
		this.skills = skills;
	}
    
    
	
}
