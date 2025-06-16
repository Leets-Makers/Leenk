package leets.leenk.domain.media.domain.service;

import leets.leenk.domain.media.domain.entity.Media;
import leets.leenk.domain.media.domain.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaSaveService {

    private final MediaRepository mediaRepository;

    public void save(Media media) {
        mediaRepository.save(media);
    }

    public void saveAll(List<Media> mediaList) {
        mediaRepository.saveAll(mediaList);
    }
}
