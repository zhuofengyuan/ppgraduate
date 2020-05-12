package com.fengtoos.ppgraduate.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fengtoos.ppgraduate.auth.entity.File;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fengtoos
 * @since 2019-08-27
 */
public interface IFileService extends IService<File> {

    File uploadReactive(FilePart filePart) throws IOException;

    File upload(MultipartFile file) throws IOException;
}
