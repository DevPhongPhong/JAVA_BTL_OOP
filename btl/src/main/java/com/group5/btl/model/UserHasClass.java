package com.group5.btl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "UserHasClasses")
public class UserHasClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "INT", nullable = false)
    @Getter
    @Setter
    private int ID;

    @Column(name = "UserID", columnDefinition = "INT", nullable = false)
    @Getter
    @Setter
    private int UserID;

    @Column(name = "ClassID", columnDefinition = "INT", nullable = false)
    @Getter
    @Setter
    private int ClassID;

    @Column(name = "Status", columnDefinition = "BIT", nullable = false)
    @Getter
    @Setter
    private boolean Status;
}
