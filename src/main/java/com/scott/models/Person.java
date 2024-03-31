package com.scott.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToMany(fetch= FetchType.LAZY)
    @JoinTable(
            name="people_images",
            joinColumns=@JoinColumn(name="person_id"),
            inverseJoinColumns = @JoinColumn(name="image_id")
    )
    private Set<Filedb> images=new HashSet<>();

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="people_addresses",
            joinColumns=@JoinColumn(name="person_id"),
            inverseJoinColumns = @JoinColumn(name="address_id")
    )
    private Set<Address> addresses=new HashSet<>();

    public void addImage(Filedb image){
        images.add(image);
    }
    public void removeImage(Filedb image){
        images.remove(image);
    }

    public void addAddress(Address address){
        addresses.add(address);
    }
    public void removeAddress(Address address){
        addresses.remove(address);
    }
}
