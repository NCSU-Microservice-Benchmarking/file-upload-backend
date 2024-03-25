package com.yulab.fileuploadapplication.service;

import com.yulab.fileuploadapplication.config.UploadConfig;
import com.yulab.fileuploadapplication.dao.FileDao;
import com.yulab.fileuploadapplication.model.File;
import com.yulab.fileuploadapplication.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

import static com.yulab.fileuploadapplication.utils.FileUtils.generateFileName;
import static com.yulab.fileuploadapplication.utils.UploadUtils.*;


/**
 * File upload service
 */
@Service
public class FileService {

    @Autowired
    private FileDao fileDao;


    /**
     * upload file
     * @param md5
     * @param file
     */
    public void upload(String name,
                       String md5,
                       MultipartFile file) throws IOException {
        String path = UploadConfig.path + generateFileName();
        FileUtils.write(path, file.getInputStream());
        fileDao.save(new File(name, md5, path, new Date(), FileUtils.getExt(file)));
    }

    /**
     * upload file with block
     * @param md5
     * @param size
     * @param chunks
     * @param chunk
     * @param file
     * @throws IOException
     */
    public void uploadWithBlock(String name,
                                String md5,
                                Long size,
                                Integer chunks,
                                Integer chunk,
                                MultipartFile file) throws IOException {
        String fileName = getFileName(md5, chunks);
        FileUtils.writeWithBlock(UploadConfig.path + fileName, size, file.getInputStream(), file.getSize(), chunks, chunk);
        addChunk(md5,chunk);
        if (isUploaded(md5)) {
            removeKey(md5);
            fileDao.save(new File(name, md5,UploadConfig.path + fileName, new Date(), FileUtils.getExt(file)));
        }
    }

    /**
     * check file is uploaded or not by md5
     * true:  didn't upload
     * false: uploaded
     * @param md5
     * @return
     */
    public boolean checkMd5(String md5) {
        File file = new File();
        file.setMd5(md5);
        return fileDao.getByFile(file) == null;
    }

}
