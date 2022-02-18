package com.mywork.discoman.domain.releaseAlbum.api;

import com.mywork.discoman.domain.releaseAlbum.dto.request.RequestReleaseAlbumDto;
import com.mywork.discoman.domain.releaseAlbum.dto.response.ResponseReleaseAlbumDto;
import com.mywork.discoman.global.common.response.BasicResponse;
import com.mywork.discoman.global.common.response.CommonResponse;
import com.mywork.discoman.global.common.response.ErrorResponse;
import com.mywork.discoman.domain.releaseAlbum.service.ReleaseAlbumServiceImpl;
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

    @PutMapping("/{id}")
    public ResponseEntity<? extends BasicResponse> modifyRAlbum(
            @PathVariable Long id,
            @RequestBody RequestReleaseAlbumDto albumsDto){
        ResponseReleaseAlbumDto releaseAlbumDto = releaseAlbumService.modifyRAlbum(id, albumsDto);
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
