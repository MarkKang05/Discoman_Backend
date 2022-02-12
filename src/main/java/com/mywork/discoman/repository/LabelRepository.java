package com.mywork.discoman.repository;

import com.mywork.discoman.domain.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LabelRepository extends JpaRepository<Label, Long> {
    boolean existsByName(String name);
}
