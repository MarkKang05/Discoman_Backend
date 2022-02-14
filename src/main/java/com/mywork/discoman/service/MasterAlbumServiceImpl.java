package com.mywork.discoman.service;

import com.mywork.discoman.domain.Artist;
import com.mywork.discoman.domain.MasterAlbum;
import com.mywork.discoman.domain.Music;
import com.mywork.discoman.dto.RequestMasterAlbumsDto;
import com.mywork.discoman.dto.ResponseMasterAlbumDto;
import com.mywork.discoman.repository.ArtistRepository;
import com.mywork.discoman.repository.MusicRepository;
import com.mywork.discoman.repository.album.MasterAlbumRepository;
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
        List<MasterAlbum> all = masterAlbumRepository.findAll();
        List<ResponseMasterAlbumDto> albumDtos = new ArrayList<>();

        all.forEach(s-> {
            List<Long> musics = new ArrayList<>();
            s.getMusics().forEach(m-> musics.add(m.getId()) );
            albumDtos.add(new ResponseMasterAlbumDto(s, s.getArtist().getId(), musics));
        });
        return albumDtos;
//        return masterAlbumRepository.findAll();
    }

    public ResponseMasterAlbumDto getMasterAlbums(Long id){
        if (!masterAlbumRepository.existsById(id))
            return null;
        MasterAlbum masterAlbum = masterAlbumRepository.findById(id).get();

        List<Long> musics = new ArrayList<>();
        masterAlbum.getMusics().forEach(m-> musics.add(m.getId()) );
        return new ResponseMasterAlbumDto(
                masterAlbum, masterAlbum.getArtist().getId(), musics
                );
    }

    public ResponseMasterAlbumDto createMasterAlbum(RequestMasterAlbumsDto albumsDto){
        MasterAlbum masterAlbum = albumsDto.toEntity();

        Artist artist = artistRepository.findById( albumsDto.getArtist() ).get();

        Set<Music> musicSet = new HashSet<>();
        List<Long> musics = albumsDto.getMusics();
        musics.forEach(s-> musicSet.add( musicRepository.getById(s) ) );

        masterAlbum.setMusics(musicSet);
        masterAlbum.setArtist(artist);

        MasterAlbum save = masterAlbumRepository.save(masterAlbum);

        ResponseMasterAlbumDto responseMasterAlbumDto= new ResponseMasterAlbumDto(save, albumsDto.getArtist(), albumsDto.getMusics());

        return responseMasterAlbumDto;
    }

    public boolean deleteMasterAlbums(Long id) {
        if (!masterAlbumRepository.existsById(id) )
            return false;
       masterAlbumRepository.deleteById(id);
       return true;
    }
}
