package com.ng2.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ng2.web.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
