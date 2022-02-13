package com.mywork.discoman.repository;

import com.mywork.discoman.domain.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, Long> {
}
