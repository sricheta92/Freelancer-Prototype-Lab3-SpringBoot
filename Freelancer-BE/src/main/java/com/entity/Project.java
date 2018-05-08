package com.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "project_id")
	private int projectId;
	
	private String project_name;
	
	private String description;
	
	private String project_pay_type;
	
	private String budget_range;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "project_skill", joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "project_id"), 
	inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "skill_id"))
	private Set<Skill> skills;

	private String document;

	
	@Temporal(TemporalType.DATE)
	private Date create_ts;

	@OneToMany(mappedBy = "project")
	private Set<ProjectBid> biddedProjects = new HashSet<ProjectBid>();
	

	@OneToMany(mappedBy = "project")
	private Set<ProjectUser> postedProjects = new HashSet<ProjectUser>();

	
	public Project() {
		
	}
	
	
	public Project(int projectid) {
		this.projectId = projectid;
	}


	public Set<ProjectBid> getBiddedProjects() {
	      return biddedProjects;
	}

    
    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }


    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }
    
	
	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProject_pay_type() {
		return project_pay_type;
	}

	public void setProject_pay_type(String project_pay_type) {
		this.project_pay_type = project_pay_type;
	}

	public String getBudget_range() {
		return budget_range;
	}

	public void setBudget_range(String budget_range) {
		this.budget_range = budget_range;
	}

	public Date getCreate_ts() {
		return create_ts;
	}

	public void setCreate_ts(Date create_ts) {
		this.create_ts = create_ts;
	}



	public void setBiddedProjects(Set<ProjectBid> biddedUsers) {
		this.biddedProjects = biddedUsers;
	}
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	 
	        if (o == null || getClass() != o.getClass()) 
	            return false;
	 
	        Project post = (Project) o;
	        return Objects.equals(projectId, post.projectId);
	    }
	 
	    @Override
	    public int hashCode() {
	        return Objects.hash(projectId);
	    }

	    
		public Set<ProjectUser> getPostedProjects() {
			return postedProjects;
		}


		public void setPostedProjects(Set<ProjectUser> postedProjects) {
			this.postedProjects = postedProjects;
		}


		public int getProjectId() {
			return projectId;
		}


		public void setProjectId(int projectId) {
			this.projectId = projectId;
		}
}
