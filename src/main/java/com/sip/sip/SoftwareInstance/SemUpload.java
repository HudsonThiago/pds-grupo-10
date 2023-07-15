package com.sip.sip.SoftwareInstance;

import com.sip.sip.framework.service.UploadStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class SemUpload extends UploadStrategy {
    private final Boolean uploadEnabled = false;
    @Override
    public void validate(MultipartFile file) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Upload de arquivos não suportado");
    }

    @Override
    public Boolean getUploadEnabled() {
        return this.uploadEnabled;
    }
}
