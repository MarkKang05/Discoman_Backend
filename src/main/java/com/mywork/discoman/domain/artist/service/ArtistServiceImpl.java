package com.mywork.discoman.domain.artist.service;

import com.mywork.discoman.domain.artist.domain.Artist;
import com.mywork.discoman.domain.artist.dto.request.RequestArtistDto;
import com.mywork.discoman.domain.artist.dao.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl {

    private final ArtistRepository artistRepository;

    // TODO: 2022/02/19 Refactoring Optional
    public List<Artist> getAllArtist(){
        List<Artist> artists = artistRepository.findAll();
        return artists;
    }

    public Optional<Artist> getArtist(Long id){
        Optional<Artist> artist = artistRepository.findById(id);
        return artist;
    }

    public Artist createArtist(RequestArtistDto requestArtistDto){
        if (artistRepository.existsByName(requestArtistDto.getName()))
            return null;
        return artistRepository.save(requestArtistDto.toEntity());
    }

    public Artist modifyArtist(Long id, RequestArtistDto requestArtistDto){
        if( !artistRepository.existsById(id) )
            return null;
        Artist artist = requestArtistDto.toEntity();
        artist.setId(id);
        return artistRepository.save(artist);
    }

    public Optional<Artist> deleteArtist(Long id){
        Optional<Artist> artist = artistRepository.findById(id);
        if(!artist.isPresent()){
            return null;
        }
        artistRepository.delete(artist.get());
        return artist;

    }

}
