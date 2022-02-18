package com.mywork.discoman.global.common.service;

import com.mywork.discoman.domain.artist.domain.Artist;
import com.mywork.discoman.domain.masterAlbum.domain.MasterAlbum;
import com.mywork.discoman.domain.artist.dao.ArtistRepository;
import com.mywork.discoman.domain.masterAlbum.dao.MasterAlbumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestImageService {
    private final ArtistRepository artistRepository;
    private final MasterAlbumRepository MAlbumRepository;

    public boolean artistImageUpload(MultipartFile multipartFile, Long id){
        String fileExtension = multipartFile.getContentType();
        fileExtension = fileExtension.substring(fileExtension.indexOf("/")+1);

        Artist artist = artistRepository.findById(id).get();
        artist.setImage("artist_cover_"+id+"."+fileExtension);
        artistRepository.save(artist);
        try {
            FileOutputStream writer = new FileOutputStream("/Users/markkang05/Develop/Discoman/image/artists/"
                    + artist.getImage());
            writer.write(multipartFile.getBytes());
            writer.close();
        } catch (Exception e){
            log.error(e+"");
            return false;
        }
        return true;
    }

    public boolean masterImageUpload(MultipartFile multipartFile, Long id){
        String fileExtension = multipartFile.getContentType();
        fileExtension = fileExtension.substring(fileExtension.indexOf("/")+1);

        MasterAlbum masterAlbum = MAlbumRepository.findById(id).get();
        masterAlbum.setImages("master_cover_"+id+"."+fileExtension);
        MAlbumRepository.save(masterAlbum);

        try {
            FileOutputStream writer = new FileOutputStream("/Users/markkang05/Develop/Discoman/image/masters/"
                    + masterAlbum.getImages());
            writer.write(multipartFile.getBytes());
            writer.close();
        } catch (Exception e){
            log.error(e+"");
            return false;
        }
        return true;
    }
}
