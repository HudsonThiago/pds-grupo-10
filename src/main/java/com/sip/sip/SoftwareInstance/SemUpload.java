package com.sip.sip.SoftwareInstance;

import com.sip.sip.service.UploadStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class SemUpload implements UploadStrategy {
    @Override
    public void validate(MultipartFile file) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Upload de arquivos não suportado");
    }
}
