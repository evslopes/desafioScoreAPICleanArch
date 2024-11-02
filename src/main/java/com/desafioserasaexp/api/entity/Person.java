package com.desafioserasaexp.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.transform.Source;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    @Embedded
    private Address address;

    private String phone;

    private Integer score;

    @Transient
    private String scoreDescription;

    private Boolean deleted;
}