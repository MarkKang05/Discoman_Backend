package com.mywork.discoman.controller;

import com.mywork.discoman.dto.RequestMasterAlbumsDto;
import com.mywork.discoman.dto.RequestReleaseAlbumDto;
import com.mywork.discoman.dto.ResponseMasterAlbumDto;
import com.mywork.discoman.dto.ResponseReleaseAlbumDto;
import com.mywork.discoman.response.BasicResponse;
import com.mywork.discoman.response.CommonResponse;
import com.mywork.discoman.response.ErrorResponse;
import com.mywork.discoman.service.ReleaseAlbumServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/releases")
@RequiredArgsConstructor
public class ReleaseAlbumController {

    private final ReleaseAlbumServiceImpl releaseAlbumService;

    @GetMapping("")
    public ResponseEntity<? extends BasicResponse> getAllRAlbums(){
        List<ResponseReleaseAlbumDto> albumDtos = releaseAlbumService.getAllRAlbum();
        return ResponseEntity.ok(new CommonResponse<List<ResponseReleaseAlbumDto>>(albumDtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends BasicResponse> getRAlbum(@PathVariable Long id){
        ResponseReleaseAlbumDto albumDto = releaseAlbumService.getRAlbum(id);
        if(albumDto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("NotFoundId"));
        }
        return ResponseEntity.ok(new CommonResponse<ResponseReleaseAlbumDto>(albumDto));
    }

    @PostMapping("")
    public ResponseEntity<? extends BasicResponse> createRAlbum(@RequestBody RequestReleaseAlbumDto albumsDto){
        ResponseReleaseAlbumDto releaseAlbumDto = releaseAlbumService.createRAlbum(albumsDto);
        return ResponseEntity.ok(new CommonResponse<ResponseReleaseAlbumDto>(releaseAlbumDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<? extends BasicResponse> deleteMAlbum(@PathVariable Long id){
        if( releaseAlbumService.deleteRAlbum(id) ){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("NotFoundId"));
    }

}