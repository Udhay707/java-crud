package com.demo.devops.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="COUNTRIES")
@ToString
public class Country {
    
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
