package com.depromeet.tifyapi.service;

import java.io.InputStream;

public interface StorageService {
    void downloadObject(String url);
    String uploadObject(InputStream inputStream, String fileName);
}
