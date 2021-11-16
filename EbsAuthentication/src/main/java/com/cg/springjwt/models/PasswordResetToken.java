package com.cg.springjwt.models;

import java.util.Date;
import javax.persistence.*;

@Entity
public class PasswordResetToken {
  
    private static final int EXPIRATION = 60 * 24;
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
  
    private String token;
  
    @OneToOne(targetEntity = EbsUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private EbsUser yugunUser;
  
    private Date expiryDate;

	public PasswordResetToken(String token, EbsUser yugunUser) {
		this.token = token;
		this.yugunUser = yugunUser;
	}
	
	

	public PasswordResetToken() {
		super();
	}

	public PasswordResetToken(Long id, String token, EbsUser yugunUser) {
		super();
		this.id = id;
		this.token = token;
		this.yugunUser = yugunUser;
	}



	public PasswordResetToken(Long id, String token, EbsUser yugunUser, Date expiryDate) {
		super();
		this.id = id;
		this.token = token;
		this.yugunUser = yugunUser;
		this.expiryDate = expiryDate;
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

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public static int getExpiration() {
		return EXPIRATION;
	}
    
    
}