package com.scott.controllers;

import com.scott.models.Address;
import com.scott.models.Filedb;
import com.scott.models.Person;
import com.scott.repositories.AddressRepository;
import com.scott.repositories.FiledbRepository;
import com.scott.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/people")
@CrossOrigin(origins="http://localhost:3000")
public class PersonController {

    private final PersonRepository personRepository;
    @Autowired
    AddressRepository addressRepository;
    private PersonController(PersonRepository personRepository, AddressRepository addressRepository){
        this.personRepository=personRepository;

    }
    @Autowired
    FiledbRepository filedbRepository;

    @PostMapping("/create")
    public Person createPerson(@RequestBody Person person){
        return personRepository.save(person);
    }

    @GetMapping
    public List<Person> getAllPeople(){
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Long id){
        return personRepository.findById(id).orElseThrow(()->new RuntimeException("Person has not found!"));
    }

    @PutMapping("/{id}/update")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person person){
        Person updateP=this.getPersonById(id);
        updateP.setName(person.getName());
        updateP.setDescription(person.getDescription());
        return personRepository.save(updateP);
    }

    @DeleteMapping("/{id}/delete")
    public void deletePerson(@PathVariable Long id){
        personRepository.deleteById(id);
    }

    @GetMapping("/{id}/images")
    public Set<Filedb> getImagesForPerson(@PathVariable Long id){
        Person person=this.getPersonById(id);
        return person.getImages();
    }

    @GetMapping("/{id}/addresses")
    public Set<Address> getAddressesForPerson(@PathVariable Long id){
        Person person=this.getPersonById(id);
        return person.getAddresses();
    }

    @PutMapping("/{id}/addImage/{imageId}")
    public Person addImage(@PathVariable Long id, @PathVariable String imageId){
        Person updateP=personRepository.findById(id).get();
        Filedb image=filedbRepository.findById(imageId).get();
        updateP.addImage(image);
        return personRepository.save(updateP);
    }

    @PutMapping("/{id}/removeImage/{imageId}")
    public Person removeImage(@PathVariable Long id, @PathVariable String imageId){
        Person updateP=personRepository.findById(id).get();
        Filedb image=filedbRepository.findById(imageId).get();
        updateP.removeImage(image);
        return personRepository.save(updateP);
    }

    @PutMapping("/{id}/addAddress/{addressId}")
    public Person addAddress(@PathVariable Long id, @PathVariable Long addressId){
        Person updateP=personRepository.findById(id).get();
        Address address=addressRepository.findById(addressId).get();
        updateP.addAddress(address);
        return personRepository.save(updateP);
    }

    @PutMapping("/{id}/removeAddress/{addressId}")
    public Person removeAddress(@PathVariable Long id, @PathVariable Long addressId){
        Person updateP=personRepository.findById(id).get();
        Address address=addressRepository.findById(addressId).get();
        address.removePerson(updateP);
        addressRepository.save(address);
        return personRepository.save(updateP);
    }

    @PutMapping("/{id}/addAddressAlt")
    public Person addAddressAlt(@PathVariable Long id, @RequestBody Address address){
        Person updateP=personRepository.findById(id).get();
        updateP.addAddress(address);
        return personRepository.save(updateP);
    }
}
