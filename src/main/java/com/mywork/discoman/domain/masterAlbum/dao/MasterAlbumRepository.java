package com.mywork.discoman.domain.masterAlbum.dao;

import com.mywork.discoman.domain.masterAlbum.domain.MasterAlbum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterAlbumRepository extends JpaRepository<MasterAlbum, Long> {
}
