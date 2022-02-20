package com.mywork.discoman.domain.artist.service;

import com.mywork.discoman.domain.artist.dao.ArtistRepository;
import com.mywork.discoman.domain.artist.domain.Artist;
import com.mywork.discoman.domain.artist.dto.request.RequestArtistDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtistServiceImplTest {

    @InjectMocks
    private ArtistServiceImpl artistService;

    @Mock
    private ArtistRepository artistRepository;

    @Test
    void getAllArtist() {
        // given
        List<Artist> artists = new ArrayList<>();
        artists.add(new Artist(0L, "artist1", "des", "image", "web", "mem"));
        artists.add(new Artist(1L, "artist2", "des", "image", "web", "mem"));
        artists.add(new Artist(2L, "artist3", "des", "image", "web", "mem"));

        when(artistRepository.findAll()).thenReturn(artists);

        // when
        List<Artist> allArtist = artistService.getAllArtist();

        // then
        assertEquals(artists.size(), allArtist.size());

    }

    @Test
    void getArtist() {

        // given
        Artist test = Artist.builder()
                .id(1L)
                .name("elton")
                .description("des")
                .image("/dd")
                .website("www.www.com")
                .members("members")
                .build();

        Optional<Artist> expectedArtist = Optional.of(test);
        when(artistRepository.findById(1L))
                .thenReturn(expectedArtist);

        // when
        Artist artist = artistService.getArtist(1L).get();


        // then
        assertEquals("elton", artist.getName());
        assertEquals(expectedArtist.get(), artist);
    }

    @Test
    void createArtist() {

        // given
        RequestArtistDto artistDto = new RequestArtistDto();
        artistDto.setName("nameDto");

        Artist expectedResult = artistDto.toEntity();

        ReflectionTestUtils.setField(expectedResult, "id", 1L);

        when(artistRepository.existsByName(artistDto.getName()))
                .thenReturn(false);

        when(artistRepository.save(Mockito.any()))
                .thenReturn(expectedResult);

        // when
        Artist artist = artistService.createArtist(artistDto);

        //then
        assertEquals(artistDto.getName(), artist.getName());
    }

    @Test
    void modifyArtist() {

        // given
        RequestArtistDto artistDto = new RequestArtistDto();
        artistDto.setName("changedName");

        Artist expectedArist = artistDto.toEntity();

        when(artistRepository.existsById(1L))
                .thenReturn(true);
        when(artistRepository.save(Mockito.any(Artist.class)))
                .thenReturn(expectedArist);

        // when
        Artist artist = artistService.modifyArtist(1L, artistDto);

        // then
        assertEquals(expectedArist.getName(), artistDto.getName());

    }

    @Test
    void deleteArtist() {

        // given
        Long id = 1L;
        Artist expectedArtist = new Artist();
        expectedArtist.setId(id);

        when(artistRepository.findById(id)).thenReturn(Optional.of(expectedArtist));

        // when
        Artist artist = artistService.deleteArtist(id).get();

        // then
        assertEquals(expectedArtist, artist);
    }
}