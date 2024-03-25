package com.yulab.fileuploadapplication.dao;


import com.yulab.fileuploadapplication.model.File;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileDao {
    /**
     * get row by id
     * @return
     */
    File getById(Long id);

    /**
     * inset a row
     * @param file
     * @return
     */
    int save(File file);

    /**
     * update a row
     * @param file
     * @return
     */
    int update(File file);

    /**
     * delete a row by id
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * get row by one or more fields
     * @param file
     * @return
     */
    File getByFile(File file);
}
