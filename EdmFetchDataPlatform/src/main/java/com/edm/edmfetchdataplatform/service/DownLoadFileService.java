package com.edm.edmfetchdataplatform.service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @Date 2019-07-01
 * @Author lifei
 */
public interface DownLoadFileService {

    void downLoadFile(File file, HttpServletResponse response);
}
