package com.example.subs.Repository;

import com.example.subs.Entity.User;
import com.example.subs.Entity.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//configure this class as Repository
@Repository
public interface LogRepository extends JpaRepository<UserLog,Long> {

    //return user object of type userLog using the user
    List<UserLog> findAllByUserId(User userId);

}