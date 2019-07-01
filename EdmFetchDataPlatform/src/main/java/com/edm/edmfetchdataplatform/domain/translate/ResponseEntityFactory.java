package com.edm.edmfetchdataplatform.domain.translate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * @Date 2019-07-01
 * @Author lifei
 */
public class ResponseEntityFactory {

    /***
     * 产生 ResponseEntity<InputStreamReader>
     * @param file
     * @return
     */
    public static ResponseEntity<InputStreamReader> createResponseEntiry(File file) throws FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));

        return ResponseEntity.ok().headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(inputStreamReader);
    }
}
