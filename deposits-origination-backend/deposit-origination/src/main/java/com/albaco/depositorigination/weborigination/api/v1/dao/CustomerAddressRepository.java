package com.albaco.depositorigination.weborigination.api.v1.dao;

import com.albaco.depositorigination.weborigination.api.v1.model.CustomerAddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddressModel, Long> {

    @Query("select ca from CustomerAddressModel ca")
    List<CustomerAddressModel> getAll();

    @Query("select ca from CustomerAddressModel ca where ca.customerId = ?1")
    CustomerAddressModel getByCustomerId(String customerId);

}
