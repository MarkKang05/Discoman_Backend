package com.mywork.discoman.controller;

import com.mywork.discoman.dto.RequestMasterAlbumsDto;
import com.mywork.discoman.dto.ResponseMasterAlbumDto;
import com.mywork.discoman.response.BasicResponse;
import com.mywork.discoman.response.CommonResponse;
import com.mywork.discoman.response.ErrorResponse;
import com.mywork.discoman.service.MasterAlbumServiceImpl;
import com.mywork.discoman.service.RequestImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/masters")
@Slf4j
public class MasterAlbumController {

    private final MasterAlbumServiceImpl masterAlbumService;
    private final RequestImageService requestImageService;

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
    public ResponseEntity<? extends BasicResponse> createMAlbum(@RequestBody RequestMasterAlbumsDto albumsDto){
//        return ResponseEntity.ok(new CommonResponse<Label>(label));
//        albumsDto.getMusics().forEach(s-> log.debug("id: "+s));
        ResponseMasterAlbumDto masterAlbum = masterAlbumService.createMasterAlbum(albumsDto);
        return ResponseEntity.ok(new CommonResponse<ResponseMasterAlbumDto>(masterAlbum));
    }

    @PutMapping("/{id}")
    public ResponseEntity<? extends BasicResponse> modifyMAlbum(
            @PathVariable Long id,
            @RequestBody RequestMasterAlbumsDto albumsDto){
        ResponseMasterAlbumDto masterAlbum = masterAlbumService.modifyMAlbum(id, albumsDto);
        if(masterAlbum == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Error"));
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

    @PostMapping("/image")
    public ResponseEntity<? extends BasicResponse> fileUpload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("id") Long id){
        if(!requestImageService.masterImageUpload(multipartFile, id)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Failed Image Upload", ""+HttpStatus.INTERNAL_SERVER_ERROR));
        }
        return ResponseEntity.ok(new CommonResponse<String>("success Image Upload"));
    }
}
