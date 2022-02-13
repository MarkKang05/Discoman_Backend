package com.mywork.discoman.repository.album;

import com.mywork.discoman.domain.MasterAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MasterAlbumRepository extends JpaRepository<MasterAlbum, Long> {
}
