package com.mywork.discoman.service;

import com.mywork.discoman.domain.Label;
import com.mywork.discoman.domain.MasterAlbum;
import com.mywork.discoman.domain.ReleaseAlbum;
import com.mywork.discoman.dto.RequestReleaseAlbumDto;
import com.mywork.discoman.dto.ResponseMasterAlbumDto;
import com.mywork.discoman.dto.ResponseReleaseAlbumDto;
import com.mywork.discoman.repository.LabelRepository;
import com.mywork.discoman.repository.album.MasterAlbumRepository;
import com.mywork.discoman.repository.album.ReleaseAlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReleaseAlbumServiceImpl {

    private final MasterAlbumRepository MAlbumRepository;
    private final LabelRepository labelRepository;
    private final ReleaseAlbumRepository RAlbumRepository;

    public List<ResponseReleaseAlbumDto> getAllRAlbum(){
        List<ReleaseAlbum> releaseAlbums = RAlbumRepository.findAll();
        List<ResponseReleaseAlbumDto> albumDtos = new ArrayList<>();

        releaseAlbums.forEach(s-> {
            albumDtos.add(new ResponseReleaseAlbumDto(s, s.getMasterAlbum().getId(), s.getLabel().getId()));
        });

        return albumDtos;
    }

    public ResponseReleaseAlbumDto getRAlbum(Long id){
        if (!RAlbumRepository.existsById(id))
            return null;
        ReleaseAlbum releaseAlbum = RAlbumRepository.findById(id).get();

        return new ResponseReleaseAlbumDto(releaseAlbum);
    }

    public ResponseReleaseAlbumDto createRAlbum(RequestReleaseAlbumDto albumDto){
        ReleaseAlbum releaseAlbum = albumDto.toEntity();
//        if (MAlbumRepository.existsById( albumDto.getMasterAlbum()) || labelRepository.existsById(albumDto.getLabel()) ){ re
//        }

        MasterAlbum masterAlbum = MAlbumRepository.findById(albumDto.getMasterAlbum()).get();
        Label label = labelRepository.findById(albumDto.getLabel()).get();
        releaseAlbum.setMasterAlbum(masterAlbum);
        releaseAlbum.setLabel(label);

        ReleaseAlbum save = RAlbumRepository.save(releaseAlbum);

        return new ResponseReleaseAlbumDto(save);
    }

    public boolean deleteRAlbum(Long id){
        if (!RAlbumRepository.existsById(id) )
            return false;
        RAlbumRepository.deleteById(id);
        return true;
    }
}
