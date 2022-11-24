package com.personalaccounting.api.repositories;

import com.personalaccounting.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
