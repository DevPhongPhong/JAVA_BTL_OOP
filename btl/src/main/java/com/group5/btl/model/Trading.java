package com.group5.btl.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Tradings")
public class Trading {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "INT IDENTITY(1, 1)")
    private int ID;

    @Getter
    @Setter
    @Column(name = "ClassID", columnDefinition = "INT", nullable = false)
    private int ClassID;

    @Getter
    @Setter
    @Column(name = "UserID", columnDefinition = "INT", nullable = false)
    private int UserID;

    @Getter
    @Setter
    @Column(name = "Status", columnDefinition = "BIT", nullable = false)
    private boolean Status;

    @Getter
    @Setter
    @Column(name = "CreatedDate", columnDefinition = "DATETIME", nullable = false)
    private Timestamp CreatedDate;

    @Getter
    @Setter
    @Column(name = "LastestModifiedDate", columnDefinition = "DATETIME", nullable = false)
    private Timestamp LastestModifiedDate;
}
