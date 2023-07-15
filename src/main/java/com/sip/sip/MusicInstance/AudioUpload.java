package com.sip.sip.MusicInstance;

import com.sip.sip.framework.service.UploadStrategy;
import org.springframework.web.multipart.MultipartFile;
//@Component
public class AudioUpload extends UploadStrategy {
    @Override
    public void validate(MultipartFile file) throws UnsupportedOperationException {
        if (!isAudioFile(file)) {
            throw new UnsupportedOperationException("Não foi possível fazer upload. Só é permitido arquivos de audio.");
        }
    }

    @Override
    public Boolean getUploadEnabled() {
        return true;
    }

    private boolean isAudioFile(MultipartFile file) {
        String contentType = file.getContentType();
        String fileExtension = getFileExtension(file.getOriginalFilename());


        return contentType != null && contentType.startsWith("audio/") || isAudioFileExtension(fileExtension);
    }

    private String getFileExtension(String fileName) {
        if (fileName != null && fileName.lastIndexOf(".") != -1) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return null;
    }

    private boolean isAudioFileExtension(String fileExtension) {
        // Lista de extensões comuns
        String[] audioExtensions = {"mp3", "wav", "ogg", "flac"};

        if (fileExtension != null) {
            for (String extension : audioExtensions) {
                if (fileExtension.equalsIgnoreCase(extension)) {
                    return true;
                }
            }
        }
        return false;
    }
}
