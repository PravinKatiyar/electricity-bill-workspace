package com.cg.springjwt.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class MailVerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
  
    private String token;
  
    @OneToOne(targetEntity = EbsUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private EbsUser yugunUser;

	public MailVerificationToken(String token, EbsUser yugunUser) {
		super();
		this.token = token;
		this.yugunUser = yugunUser;
	}
	public MailVerificationToken() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public EbsUser getUser() {
		return yugunUser;
	}

	public void setUser(EbsUser yugunUser) {
		this.yugunUser = yugunUser;
	}

}