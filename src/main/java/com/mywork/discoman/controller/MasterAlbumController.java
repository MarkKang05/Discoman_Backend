package com.mywork.discoman.controller;

import com.mywork.discoman.domain.Label;
import com.mywork.discoman.domain.MasterAlbum;
import com.mywork.discoman.dto.CreateLabelDto;
import com.mywork.discoman.dto.CreateMasterAlbumsDto;
import com.mywork.discoman.dto.ResponseMasterAlbumDto;
import com.mywork.discoman.response.BasicResponse;
import com.mywork.discoman.response.CommonResponse;
import com.mywork.discoman.response.ErrorResponse;
import com.mywork.discoman.service.MasterAlbumServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/masters")
@Slf4j
public class MasterAlbumController {

    private final MasterAlbumServiceImpl masterAlbumService;

    @GetMapping("")
    public ResponseEntity<? extends BasicResponse> getAllMAlbums(){
        List<ResponseMasterAlbumDto> albumDtos = masterAlbumService.getAllMasterAlbums();
        return ResponseEntity.ok(new CommonResponse<List<ResponseMasterAlbumDto>>(albumDtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends BasicResponse> getMAlbum(@PathVariable Long id){
        ResponseMasterAlbumDto albumDto = masterAlbumService.getMasterAlbums(id);
        if(albumDto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("NotFoundId"));
        }
        return ResponseEntity.ok(new CommonResponse<ResponseMasterAlbumDto>(albumDto));
    }

    @PostMapping("")
    public ResponseEntity<? extends BasicResponse> createMAlbum(@RequestBody CreateMasterAlbumsDto albumsDto){
//        return ResponseEntity.ok(new CommonResponse<Label>(label));
//        albumsDto.getMusics().forEach(s-> log.debug("id: "+s));
        ResponseMasterAlbumDto masterAlbum = masterAlbumService.createMasterAlbum(albumsDto);
        return ResponseEntity.ok(new CommonResponse<ResponseMasterAlbumDto>(masterAlbum));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<? extends BasicResponse> deleteMAlbum(@PathVariable Long id){
        if( masterAlbumService.deleteMasterAlbums(id) ){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("NotFoundId"));
    }
}
