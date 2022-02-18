package com.mywork.discoman.domain.label.dao;

import com.mywork.discoman.domain.label.domain.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {
    boolean existsByName(String name);
}
