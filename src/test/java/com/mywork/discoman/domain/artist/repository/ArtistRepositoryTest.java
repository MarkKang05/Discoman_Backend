package com.mywork.discoman.domain.artist.repository;

import com.mywork.discoman.domain.artist.dao.ArtistRepository;
import com.mywork.discoman.domain.artist.domain.Artist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ArtistRepositoryTest {
    @Mock
    private ArtistRepository artistRepository;

    @Test
    @DisplayName("안녕")
    void 안녕() {
        Artist artist = Artist.builder()
                .name("artist name")
                .description("des")
                .image("image")
                .website("www.ww.com")
                .members("members")
                .build();
        List<Artist> artists = new ArrayList<>();
        artists.add(artist);

        given(artistRepository.findAll()).willReturn(artists);

        List<Artist> findArtists = artistRepository.findAll();
        System.out.println();

        Assertions.assertEquals(1, findArtists.size());
        Assertions.assertEquals(artist.getName(), findArtists.get(0).getName());
    }

}
