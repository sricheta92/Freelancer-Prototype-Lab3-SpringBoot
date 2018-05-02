package com.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userid;
	
	private String firstname;
	
	private String lastname;
	
	private String prof_headline;
	
	private String email;
	
	private String phone;
	
	private String address_line_1;
	
	private String address_line_2;
	
	private String city;
	
	private String country;
	
	private int bid_count;
	
	@Column(name = "primary_role")
	private String role;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ProjectBid> biddedProjects = new HashSet<ProjectBid>();
	
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ProjectUser> postedProjects = new HashSet<ProjectUser>();;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "skill_user", joinColumns = @JoinColumn(name = "userid", referencedColumnName = "userid"), 
	inverseJoinColumns = @JoinColumn(name = "skillid", referencedColumnName = "skill_id"))
	private Set<Skill> skills;
	

	
    public Set<ProjectBid> getBiddedProjects() {
        return biddedProjects;
    }
	
	
	public User() {
		
	}
	
	
	public User(int userID2) {
		this.userid = userID2;
	//	postedProjects= new HashSet<ProjectUser>();
	}


	private Date created_ts;
	
	private String username;
	
	private String password;
	
	private String profilePicPath;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public Set<Skill> getSkills() {
	   return skills;
	}

	public void setSkills(Set<Skill> skills) {
	   this.skills = skills;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getProf_headline() {
		return prof_headline;
	}

	public void setProf_headline(String prof_headline) {
		this.prof_headline = prof_headline;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress_line_1() {
		return address_line_1;
	}

	public void setAddress_line_1(String address_line_1) {
		this.address_line_1 = address_line_1;
	}

	public String getAddress_line_2() {
		return address_line_2;
	}

	public void setAddress_line_2(String address_line_2) {
		this.address_line_2 = address_line_2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getBid_count() {
		return bid_count;
	}

	public void setBid_count(int bid_count) {
		this.bid_count = bid_count;
	}

	public Date getCreated_ts() {
		return created_ts;
	}

	public void setCreated_ts(Date created_ts) {
		this.created_ts = created_ts;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfilePicPath() {
		return profilePicPath;
	}

	public void setProfilePicPath(String profilePicPath) {
		this.profilePicPath = profilePicPath;
	}


	public void setBiddedProjects(Set<ProjectBid> biddedProjects) {
		this.biddedProjects = biddedProjects;
	}
	
	  @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        User tag = (User) o;
	        return Objects.equals(userid, tag.userid);
	    }
	 
	    @Override
	    public int hashCode() {
	        return Objects.hash(userid);
	    }

	    
		public Set<ProjectUser> getPostedProjects() {
			return postedProjects;
		}


		public void setPostedProjects(Set<ProjectUser> postedProjects) {
			this.postedProjects = postedProjects;
		}


		public String getRole() {
			return role;
		}


		public void setRole(String role) {
			this.role = role;
		}
	

}
