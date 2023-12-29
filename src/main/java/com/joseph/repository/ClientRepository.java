package com.joseph.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joseph.entity.Client;

@Repository("clientRepository")
public interface ClientRepository extends JpaRepository<Client, Integer> {

}