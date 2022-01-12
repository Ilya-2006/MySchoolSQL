package com.school.repository;

import com.school.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{
    @Query("SELECT u FROM Profile u where u.idlogin = :idlogin")
    Profile getOneByIdLogin(@Param("idlogin") Long idlogin); //выборка анкеты по id логину юзера

}
