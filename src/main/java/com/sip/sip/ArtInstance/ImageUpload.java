package com.sip.sip.ArtInstance;

import com.sip.sip.framework.service.UploadStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class ImageUpload extends UploadStrategy {
    @Override
    public void validate(MultipartFile file) throws UnsupportedOperationException {
        if (!isImageFile(file)) {
            throw new UnsupportedOperationException("Não foi possível fazer upload. Só é permitido arquivos de imagem.");
        }
    }

    @Override
    public Boolean getUploadEnabled() {
        return true;
    }

    private boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        String fileExtension = getFileExtension(file.getOriginalFilename());


        return contentType != null && contentType.startsWith("image/") || isImageFileExtension(fileExtension);
    }

    private String getFileExtension(String fileName) {
        if (fileName != null && fileName.lastIndexOf(".") != -1) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return null;
    }

    private boolean isImageFileExtension(String fileExtension) {
        // Lista de extensões comuns
        String[] imageExtensions = {"svg", "gif", "png", "jpg"};

        if (fileExtension != null) {
            for (String extension : imageExtensions) {
                if (fileExtension.equalsIgnoreCase(extension)) {
                    return true;
                }
            }
        }
        return false;
    }
}
