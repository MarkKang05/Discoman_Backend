package com.mywork.discoman.domain.artist.dao;

import com.mywork.discoman.domain.artist.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    boolean existsByName(String name);
}
