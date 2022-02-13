package com.mywork.discoman.service;

import com.mywork.discoman.domain.Artist;
import com.mywork.discoman.domain.MasterAlbum;
import com.mywork.discoman.domain.Music;
import com.mywork.discoman.dto.CreateMasterAlbumsDto;
import com.mywork.discoman.dto.ResponseMasterAlbumDto;
import com.mywork.discoman.repository.ArtistRepository;
import com.mywork.discoman.repository.MusicRepository;
import com.mywork.discoman.repository.album.MasterAlbumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class MasterAlbumServiceImpl {

    private final MasterAlbumRepository masterAlbumRepository;
    private final MusicRepository musicRepository;
    private final ArtistRepository artistRepository;

    public List<MasterAlbum>  getAllMasterAlbums(){
        return masterAlbumRepository.findAll();
    }

    public Optional<MasterAlbum> getMasterAlbums(Long id){
        return masterAlbumRepository.findById(id);
    }

    public ResponseMasterAlbumDto createMasterAlbum(CreateMasterAlbumsDto albumsDto){
        MasterAlbum masterAlbum = albumsDto.toEntity();

        Artist artist = artistRepository.findById( albumsDto.getArtist() ).get();

        Set<Music> musicSet = new HashSet<>();
        List<Long> musics = albumsDto.getMusics();
        musics.forEach(s-> musicSet.add( musicRepository.getById(s) ) );

        masterAlbum.setMusics(musicSet);
        masterAlbum.setArtist(artist);

        MasterAlbum save = masterAlbumRepository.save(masterAlbum);

        ResponseMasterAlbumDto responseMasterAlbumDto= new ResponseMasterAlbumDto(save);
        responseMasterAlbumDto.setArtist(albumsDto.getArtist());
        responseMasterAlbumDto.setMusics(albumsDto.getMusics());

        return responseMasterAlbumDto;
    }

    public void deleteMasterAlbums(Long id) {
       masterAlbumRepository.deleteById(id);
    }
}
