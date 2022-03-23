package com.example.subs.Repository;

import com.example.subs.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//configure this class as Repository
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    

}