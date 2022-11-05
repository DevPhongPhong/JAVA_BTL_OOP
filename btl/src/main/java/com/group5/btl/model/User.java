package com.group5.btl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = {"email"}), @UniqueConstraint(columnNames = {"phone_number"}) })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull
    @Column(columnDefinition = ("nvarchar(255)"))
    private String name;

    @NotNull
    private String email;
    
    @NotNull
    @Column(name = "phone_number")
    public String phoneNumber;
    
    @NotNull
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @NotNull
    private Role roleId;

	public User(String name, String email, String phoneNumber, String password, Role roleId) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.roleId = roleId;
	}  
    
}
