package com.edm.edmfetchdataplatform.controller;

import com.edm.edmfetchdataplatform.domain.EdmApplyFile;
import com.edm.edmfetchdataplatform.service.DownLoadFileService;
import com.edm.edmfetchdataplatform.service.EdmApplyFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

/**
 * @Date 2019-07-01
 * @Author lifei
 */
@Controller
@RequestMapping(value = "/edmApplyFileController")
public class EdmApplyFileController {

    @Autowired
    private EdmApplyFileService edmApplyFileService;

    @Autowired
    private DownLoadFileService downLoadFileService;


    private static final Logger logger = Logger.getLogger("com.edm.edmfetchdataplatform.controller.EdmApplyFileController");



    /**
     * 根据流转单的oid下载流转单的Excel
     * @param fid
     * @return
     */
    @RequestMapping(value = "/downLoadAccessoryFile/{fid}", method = RequestMethod.GET)
    public void downLoadAccessoryFile(HttpServletResponse response, @PathVariable Long fid) throws FileNotFoundException {

        EdmApplyFile edmApplyFile = edmApplyFileService.findEdmApplyFileByFid(fid);
        File file = new File(edmApplyFile.getFilepath() + File.separator + edmApplyFile.getFilename());
        logger.info("The length of "+file.getName()+" is " + file.length());
        downLoadFileService.downLoadFile(file, response);
    }
}
