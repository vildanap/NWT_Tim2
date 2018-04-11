package com.nwt2.like.nwt2_ms_like.Services;

import com.nwt2.like.nwt2_ms_like.Model.Photo;
import com.nwt2.like.nwt2_ms_like.Repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@Service
public class PhotosService {
    @Autowired
    private PhotoRepository photoRepository;

    public Optional<Photo> findById(Long id) {
        return photoRepository.findById(id);
    }
    public Collection<Photo> findAll() {
        return photoRepository.findAll();
    }

    public void deleteById(Long id)
    {
        photoRepository.deleteById(id);
    }

    public boolean existsByUrl(String url) {
        return photoRepository.existsByUrl(url);
    }

    public void savePhoto(@Valid Photo photo) {
        photoRepository.save(photo);
    }
}
