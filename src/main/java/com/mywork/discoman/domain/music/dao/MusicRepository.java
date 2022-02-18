package com.mywork.discoman.domain.music.dao;

import com.mywork.discoman.domain.music.domain.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, Long> {
}
