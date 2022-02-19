package com.mywork.discoman.domain.artist.service;

import com.mywork.discoman.domain.artist.dao.ArtistRepository;
import com.mywork.discoman.domain.artist.domain.Artist;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ArtistServiceImplTest {

    @InjectMocks
    private ArtistServiceImpl artistService;

    @Mock
    private ArtistRepository artistRepository;

    @Test
    void getAllArtist() {
    }

    @Test
    void getArtist() {

        Artist testArtist = Artist.builder()
                            .id(1L)
                            .name("elton")
                            .description("des")
                            .image("/dd")
                            .website("www.www.com")
                            .members("members")
                            .build();

        Optional<Artist> test = Optional.of(testArtist);
        Mockito.when(artistRepository.findById(1L))
                .thenReturn(test);
        Artist artist = artistService.getArtist(1L).get();


        assertEquals("elton", artist.getName());
        assertEquals(testArtist, artist);
    }

    @Test
    void createArtist() {
    }

    @Test
    void modifyArtist() {
    }

    @Test
    void deleteArtist() {
    }
}