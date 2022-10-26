package com.group5.btl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TradingWishes")
public class TradingWish {

    @Column(name = "ID", columnDefinition = "INT IDENTITY(1,1)", nullable = false)
    @Getter
    @Setter
    @Id
    private int ID;

    @Column(name = "TradingID", columnDefinition = "INT", nullable = false)
    @Getter
    @Setter
    private int TradingID;

    @Column(name = "ClassID", columnDefinition = "INT", nullable = false)
    @Getter
    @Setter
    private int ClassID;

}
