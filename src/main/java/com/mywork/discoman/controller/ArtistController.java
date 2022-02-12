package com.mywork.discoman.controller;

import com.mywork.discoman.domain.Artist;
import com.mywork.discoman.dto.CreateArtistDto;
import com.mywork.discoman.response.BasicResponse;
import com.mywork.discoman.response.CommonResponse;
import com.mywork.discoman.response.ErrorResponse;
import com.mywork.discoman.service.ArtistServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistServiceImpl artistService;

    @GetMapping("")
    public ResponseEntity<? extends BasicResponse> getAllArtists(){
        List<Artist> artists = artistService.getAllArtist();
        return ResponseEntity.ok(new CommonResponse<List<Artist>>(artists));
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends BasicResponse> getArtist(@PathVariable("id") Long id){
        Optional<Artist> artist = artistService.getArtist(id);
        if (artist==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("None exist id"));
        return ResponseEntity.ok(new CommonResponse<Artist>(artist.get()));
    }

    @PostMapping("")
    public ResponseEntity<? extends BasicResponse> createArtist(@RequestBody CreateArtistDto createArtistDto){
        Artist artist = artistService.createArtist(createArtistDto);
        if (artist == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("exist same name Artist"));

        return ResponseEntity.ok(new CommonResponse<Artist>(artist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<? extends BasicResponse> deleteArtist(@PathVariable("id") Long id){
        Optional<Artist> artist = artistService.deleteArtist(id);
        if (artist==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("None exist id"));
//        return ResponseEntity.ok(new CommonResponse<Artist>(artist.get()));
        return ResponseEntity.noContent().build();
    }

}