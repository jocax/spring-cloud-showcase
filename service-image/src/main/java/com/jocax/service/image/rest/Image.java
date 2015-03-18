package com.jocax.service.image.rest;

import org.springframework.core.io.InputStreamResource;

import java.io.InputStream;


public class Image extends InputStreamResource {

    private String originalFilename;
    private String name;
    private String contentType;
    private long size;

    public Image(InputStream inputStream) {
        super(inputStream);
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
