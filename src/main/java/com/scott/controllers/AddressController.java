package com.scott.controllers;

import com.scott.models.Address;
import com.scott.repositories.AddressRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@CrossOrigin("http://localhost:3000")
public class AddressController {
    private final AddressRepository addressRepository;

    private AddressController(AddressRepository addressRepository){
        this.addressRepository=addressRepository;
    }

    @PostMapping("/create")
    public Address createAddress(@RequestBody Address address){
        return addressRepository.save(address);
    }

    @GetMapping
    public List<Address> findAllAddresses(){
        return addressRepository.findAll();
    }

    @GetMapping("/{id}")
    public Address findAddressById(@PathVariable Long id){
        return addressRepository.findById(id).orElseThrow(()->new RuntimeException());
    }

    @PutMapping("/{id}/update")
    public Address updateAddress(@PathVariable Long id, @RequestBody Address address){
        Address updateA=this.findAddressById(id);
        updateA.setName(address.getName());
        updateA.setDescription(address.getDescription());
        return addressRepository.save(updateA);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteAddress(@PathVariable Long id){
        addressRepository.deleteById(id);
    }
}
