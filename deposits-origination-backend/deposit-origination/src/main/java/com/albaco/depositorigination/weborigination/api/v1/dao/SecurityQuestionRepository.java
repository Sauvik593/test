package com.albaco.depositorigination.weborigination.api.v1.dao;

import com.albaco.depositorigination.weborigination.api.v1.model.CustomerAddressModel;
import com.albaco.depositorigination.weborigination.api.v1.model.SecurityQuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecurityQuestionRepository extends JpaRepository<SecurityQuestionModel, Long> {

    @Query("select sq from SecurityQuestionModel sq")
    List<SecurityQuestionModel> getAll();

    @Query("select sq from SecurityQuestionModel sq where sq.customerId = ?1")
    List<SecurityQuestionModel> getByCustomerId(String customerId);
}
