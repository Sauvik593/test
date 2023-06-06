package com.albaco.depositorigination.weborigination.api.v1.dao;

import com.albaco.depositorigination.weborigination.api.v1.model.DepositApplicationModel;
import com.albaco.depositorigination.weborigination.api.v1.model.NationalityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositApplicationRepository extends JpaRepository<DepositApplicationModel, Long> {

    @Query("select da from DepositApplicationModel da")
    List<DepositApplicationModel> getAll();

    @Query("select da from DepositApplicationModel da where da.customerId = ?1")
    List<DepositApplicationModel> getByCustomerId(String customerId);
}
