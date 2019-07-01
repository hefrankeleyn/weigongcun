package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.service.DownLoadFileService;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Logger;

/**
 * @Date 2019-07-01
 * @Author lifei
 */
@Service
public class DownLoadFileServiceImpl implements DownLoadFileService {


    private static final Logger logger = Logger.getLogger("com.edm.edmfetchdataplatform.service.impl.DownLoadFileServiceImpl");
    @Override
    public void downLoadFile(File file, HttpServletResponse response) {

        if (file.exists()) {
            try {
                String mimeType = URLConnection.guessContentTypeFromName(file.getName());
                logger.info(mimeType);
                response.setContentType("application/octet-stream; CHARSET=utf8");
                /**
                 * Here we have mentioned it to show inline
                 */
                response.setHeader("Content-Disposition", "inline; filename=" + URLEncoder.encode(file.getName(), "UTF-8"));

                //Here we have mentioned it to show as attachment
//                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(file.getName(), "UTF-8"));

                response.setContentLength((int) file.length());

                try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                    FileCopyUtils.copy(inputStream, response.getOutputStream());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
