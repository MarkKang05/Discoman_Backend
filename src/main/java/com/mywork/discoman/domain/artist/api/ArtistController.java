package com.mywork.discoman.domain.artist.api;

import com.mywork.discoman.domain.artist.domain.Artist;
import com.mywork.discoman.domain.artist.dto.request.RequestArtistDto;
import com.mywork.discoman.global.common.response.BasicResponse;
import com.mywork.discoman.global.common.response.CommonResponse;
import com.mywork.discoman.global.common.response.ErrorResponse;
import com.mywork.discoman.domain.artist.service.ArtistServiceImpl;
import com.mywork.discoman.global.common.service.RequestImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistServiceImpl artistService;
    private final RequestImageService requestImageService;

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
    public ResponseEntity<? extends BasicResponse> createArtist(@RequestBody RequestArtistDto createArtistDto){
        Artist artist = artistService.createArtist(createArtistDto);
        if (artist == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("exist same name Artist"));

        return ResponseEntity.ok(new CommonResponse<Artist>(artist));
    }

    @PutMapping("/{id}")
    public ResponseEntity<? extends BasicResponse> modifyArtist(
            @PathVariable("id") Long id,
            @RequestBody RequestArtistDto requestArtistDto){
        Artist artist = artistService.modifyArtist(id, requestArtistDto);
        if(artist == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Not found id"));
        return ResponseEntity.ok().body(new CommonResponse<Artist>(artist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<? extends BasicResponse> deleteArtist(@PathVariable("id") Long id){
        Optional<Artist> artist = artistService.deleteArtist(id);
        if (artist==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("None exist id"));
//        return ResponseEntity.ok(new CommonResponse<Artist>(artist.get()));
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/image")
    public ResponseEntity<? extends BasicResponse> fileUpload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("id") Long id){
        if(!requestImageService.artistImageUpload(multipartFile, id)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Failed Image Upload", ""+HttpStatus.INTERNAL_SERVER_ERROR));
        }
        return ResponseEntity.ok(new CommonResponse<String>("success Image Upload"));
    }
}
