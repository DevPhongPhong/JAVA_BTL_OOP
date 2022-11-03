package com.group5.btl.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoleID", columnDefinition = "INT")
    private int RoleID;

    @Column(name = "Name", columnDefinition = "NVARCHAR(15) NOT NULL")
    private int Name;

    @OneToMany(mappedBy = "RoleID",fetch = FetchType.LAZY)
    private List<User> ListUser;

}
