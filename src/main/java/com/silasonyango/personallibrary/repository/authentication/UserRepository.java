package com.silasonyango.personallibrary.repository.authentication;

import com.silasonyango.personallibrary.models.authentication.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {


    @Query(nativeQuery = true)
    public List<UserModel> getUsers();
    public List<UserModel> findByEmail(@Param("email") String email);
}
