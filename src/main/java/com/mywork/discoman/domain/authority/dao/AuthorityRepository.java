package com.mywork.discoman.domain.authority.dao;

import com.mywork.discoman.domain.authority.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    List<Authority> findByUserId(Long userId);
}
