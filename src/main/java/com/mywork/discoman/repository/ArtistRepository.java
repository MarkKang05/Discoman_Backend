package com.mywork.discoman.repository;

import com.mywork.discoman.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    boolean existsByName(String name);
}
