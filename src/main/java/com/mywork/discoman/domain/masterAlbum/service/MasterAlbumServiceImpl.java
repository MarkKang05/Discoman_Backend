package com.mywork.discoman.domain.masterAlbum.service;

import com.mywork.discoman.domain.artist.domain.Artist;
import com.mywork.discoman.domain.masterAlbum.domain.MasterAlbum;
import com.mywork.discoman.domain.music.domain.Music;
import com.mywork.discoman.domain.masterAlbum.dto.request.RequestMasterAlbumsDto;
import com.mywork.discoman.domain.masterAlbum.dto.response.ResponseMasterAlbumDto;
import com.mywork.discoman.domain.artist.dao.ArtistRepository;
import com.mywork.discoman.domain.music.dao.MusicRepository;
import com.mywork.discoman.domain.masterAlbum.dao.MasterAlbumRepository;
import com.mywork.discoman.domain.music.dto.RequestMusicDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MasterAlbumServiceImpl {

    private final MasterAlbumRepository masterAlbumRepository;
    private final MusicRepository musicRepository;
    private final ArtistRepository artistRepository;

    public List<ResponseMasterAlbumDto>  getAllMasterAlbums(){
        List<MasterAlbum> masterAlbums = masterAlbumRepository.findAll();
        List<ResponseMasterAlbumDto> albumDtos = new ArrayList<>();

        masterAlbums.forEach(masterAlbum -> {
            albumDtos.add(new ResponseMasterAlbumDto(masterAlbum));
        });

        return albumDtos;
    }

    public ResponseMasterAlbumDto getMasterAlbums(Long id){
        if (!masterAlbumRepository.existsById(id))
            return null;
        MasterAlbum masterAlbum = masterAlbumRepository.findById(id).get();

        return new ResponseMasterAlbumDto(masterAlbum);
    }

    public ResponseMasterAlbumDto createMasterAlbum(RequestMasterAlbumsDto albumsDto){
        MasterAlbum masterAlbum = albumsDto.toEntity();

        Artist artist = artistRepository.findById( albumsDto.getArtist() ).get();

        Set<Music> musicSet = new HashSet<>();
        Set<Music> musics = albumsDto.toMusicEntity();
        musics.forEach(music -> {
            musicSet.add(musicRepository.save(music));
        });

        masterAlbum.setArtist(artist);
        masterAlbum.setMusics(musicSet);

        MasterAlbum save = masterAlbumRepository.save(masterAlbum);

        ResponseMasterAlbumDto responseMasterAlbumDto
                = new ResponseMasterAlbumDto(save);

        return responseMasterAlbumDto;
    }

    public ResponseMasterAlbumDto modifyMAlbum(Long id, RequestMasterAlbumsDto albumsDto) {
        if ( !masterAlbumRepository.existsById(id))
            return null;
        if( !artistRepository.existsById(albumsDto.getArtist()) )
            return null;

        MasterAlbum masterAlbum = albumsDto.toEntity();
        masterAlbum.setId(id);

        Artist artist = artistRepository.findById( albumsDto.getArtist() ).get();

        Set<Music> musicSet = new HashSet<>();
        Set<Music> musics = albumsDto.toMusicEntity();
        musics.forEach(music -> {
            musicSet.add(musicRepository.save(music));
        });

        masterAlbum.setMusics(musicSet);
        masterAlbum.setArtist(artist);

        MasterAlbum save = masterAlbumRepository.save(masterAlbum);

        ResponseMasterAlbumDto responseMasterAlbumDto
                = new ResponseMasterAlbumDto(save);

        return responseMasterAlbumDto;
    }

    public boolean deleteMasterAlbums(Long id) {
        if (!masterAlbumRepository.existsById(id) )
            return false;
       masterAlbumRepository.deleteById(id);
       return true;
    }
}
