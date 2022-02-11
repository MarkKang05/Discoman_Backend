package com.mywork.discoman.service;

import com.mywork.discoman.domain.Artist;
import com.mywork.discoman.dto.CreateArtistDto;
import com.mywork.discoman.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl {

    private final ArtistRepository artistRepository;

    public List<Artist> getAllArtist(){
        List<Artist> artists = artistRepository.findAll();
        return artists;
    }

    public Artist createArtist(CreateArtistDto createArtistDto){
        if (artistRepository.existsByName(createArtistDto.getName()))
            return null;
        return artistRepository.save(createArtistDto.toEntity());
    }

    public Optional<Artist> getArtist(Long id){
        Optional<Artist> artist = artistRepository.findById(id);
        return artist;
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
