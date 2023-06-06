package com.albaco.depositorigination.weborigination.api.v1.dao;

import com.albaco.depositorigination.weborigination.api.v1.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    @Query("select c from CustomerModel c")
    List<CustomerModel> getAll();

    @Query("select c from CustomerModel c where c.customerId = ?1")
    CustomerModel getByCustomerId(String customerId);
}
