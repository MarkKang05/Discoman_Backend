package com.mywork.discoman.domain.releaseAlbum.dao;

import com.mywork.discoman.domain.releaseAlbum.domain.ReleaseAlbum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReleaseAlbumRepository extends JpaRepository<ReleaseAlbum, Long> {
    List<ReleaseAlbum> findByMasterAlbumId(Long id);
}
