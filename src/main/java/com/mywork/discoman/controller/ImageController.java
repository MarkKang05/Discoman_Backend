package com.mywork.discoman.controller;

import com.mywork.discoman.domain.MasterAlbum;
import com.mywork.discoman.repository.album.MasterAlbumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.List;

@RestController
@RequestMapping(value = "/file")
@Slf4j
@RequiredArgsConstructor
public class ImageController {

    private final MasterAlbumRepository masterAlbumRepository;

    @PostMapping("/uploads")
    public String fileUpload2(@RequestBody MultipartFile multipartFile){
        try {
            FileOutputStream writer = new FileOutputStream("/Users/markkang05/Develop/Discoman/image/" + multipartFile.getOriginalFilename());
            writer.write(multipartFile.getBytes());
            writer.close();
        } catch (Exception e){
            log.error(e+"");
            return "upload fail";
        }

        return String.format("file %s uploaded successfully", multipartFile.getOriginalFilename());
    }



    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file")MultipartFile multipartFile, @RequestParam("id") Long id){
        MasterAlbum masterAlbum = masterAlbumRepository.findById(id).get();
        masterAlbum.setImages("master_cover_"+id);
        masterAlbumRepository.save(masterAlbum);
        try {
            log.debug(masterAlbum.getImages());
            FileOutputStream writer = new FileOutputStream("/Users/markkang05/Develop/Discoman/image/" + masterAlbum.getImages());
            writer.write(multipartFile.getBytes());
            writer.close();
        } catch (Exception e){
            log.error(e+"");
            return "upload fail";
        }

        return String.format("file %s uploaded successfully", multipartFile.getOriginalFilename());
    }



    @PostMapping(value = "/upload/profile-img")
    public @ResponseBody String requestUploadFile(@RequestParam("fileList") List<MultipartFile> fileList) {
        try {
            for (MultipartFile multipartFile : fileList) {
//                FileOutputStream writer = new FileOutputStream("./images/" + multipartFile.getOriginalFilename());
//                writer.write(multipartFile.getBytes());
//                writer.close();
            }
        } catch (Exception e) {
            return "upload fail";
        }
        return "upload success";
    }
}
