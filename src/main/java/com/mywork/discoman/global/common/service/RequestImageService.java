package com.mywork.discoman.global.common.service;

import com.mywork.discoman.domain.artist.domain.Artist;
import com.mywork.discoman.domain.masterAlbum.domain.MasterAlbum;
import com.mywork.discoman.domain.artist.dao.ArtistRepository;
import com.mywork.discoman.domain.masterAlbum.dao.MasterAlbumRepository;
import com.mywork.discoman.infra.S3Storage.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestImageService {
    private final ArtistRepository artistRepository;
    private final MasterAlbumRepository MAlbumRepository;
    private final FileService fileService;

    public boolean artistImageUpload(MultipartFile multipartFile, Long id) {
        String fileExtension = multipartFile.getContentType();
        fileExtension = fileExtension.substring(fileExtension.indexOf("/")+1);
        String fileName = "artist_"+UUID.randomUUID()+"."+fileExtension;
        fileService.upload(multipartFile, fileName);

        Artist artist = artistRepository.findById(id).get();
        artist.setImage(fileName);
        artistRepository.save(artist);

        return true;
    }

    public boolean masterImageUpload(MultipartFile multipartFile, Long id){
        String fileExtension = multipartFile.getContentType();
        fileExtension = fileExtension.substring(fileExtension.indexOf("/")+1);

        String fileName = "master_"+UUID.randomUUID()+"."+fileExtension;
        fileService.upload(multipartFile, fileName);

        MasterAlbum masterAlbum = MAlbumRepository.findById(id).get();
        masterAlbum.setImages(fileName);
        MAlbumRepository.save(masterAlbum);

        return true;
    }
}
