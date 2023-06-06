package com.albaco.depositorigination.weborigination.api.v1.dao;

import com.albaco.depositorigination.weborigination.api.v1.model.NationalityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NationalityRepository extends JpaRepository<NationalityModel, Long> {

    @Query("select n from NationalityModel n")
    List<NationalityModel> getAll();

    @Query("select n from NationalityModel n where n.countryCode = ?1")
    NationalityModel getByCountryCode(String countryCode);

}
