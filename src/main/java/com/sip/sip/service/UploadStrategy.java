package com.sip.sip.service;

import org.springframework.web.multipart.MultipartFile;

public abstract class UploadStrategy {
    private final Boolean uploadEnabled = true;
    public abstract void validate(MultipartFile file) throws UnsupportedOperationException;
    public abstract Boolean getUploadEnabled();
}
