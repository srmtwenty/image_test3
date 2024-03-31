package com.scott.repositories;

import com.scott.models.Filedb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FiledbRepository extends JpaRepository<Filedb, String> {
    Filedb findByName(String name);
}
