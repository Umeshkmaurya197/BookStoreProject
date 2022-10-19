package com.git.bookstore.repository;

import com.git.bookstore.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserData, Integer> {

    @Query(value = "Select * from book_store.user_data e where e.user_id=:userId",nativeQuery = true)
    UserData getUserById(@Param(value = "userId")Integer useId);
    @Query(value = "Select * from book_store.user_data e where e.full_name=:fName",nativeQuery = true)
    List<UserData> findUserByFirstName(@Param(value="fName")String fullName);

    @Query(value = "Select * from book_store.user_data e where e.email=:email",nativeQuery = true)
    Optional<UserData> findUserByEmail(@Param(value="email")String email);

    @Query(value = "Select * from book_store.user_data e where e.email=:loginId",nativeQuery = true)
    Optional<UserData> findUserByLoginId(@Param(value="loginId")String loginId);
}
