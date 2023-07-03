package com.sip.sip.service;

import jakarta.transaction.NotSupportedException;
import org.springframework.web.multipart.MultipartFile;

public interface UploadStrategy {
    void validate(MultipartFile file) throws UnsupportedOperationException;
}
