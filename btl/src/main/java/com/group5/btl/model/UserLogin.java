package com.group5.btl.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.google.common.hash.Hashing;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "UserLogins")
public class UserLogin {
    @Id
    @Column(name = "UserID", columnDefinition = "INT", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private int UserID;
    
    @Column(name = "UserName", length = 30, columnDefinition = "NVARCHAR(30)", nullable = false, unique = true)
    @Getter
    @Setter
    private String UserName;

    // Mã hóa password dạng
    @Column(name = "Password", length = 64, columnDefinition = "Nvarchar(64)", nullable = false)
    @Getter
    private String Password;

    public void setPassword(String password) {
        this.Password = Hashing.sha256().hashString(password, null).toString();
    }
}
