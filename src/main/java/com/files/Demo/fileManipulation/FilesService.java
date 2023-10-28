//https://medium.com/@patelsajal2/how-to-create-a-spring-boot-rest-api-for-multipart-file-uploads-a-comprehensive-guide-b4d95ce3022b
package com.files.Demo.fileManipulation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilesService {
    private final FileRepo fileRepo;

    // upload Image
    public FileEntity saveAttachments(MultipartFile multipartFile) throws Exception{
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        FileEntity fileEntity = FileEntity.builder()
                .fileName(fileName)
                .fileType(multipartFile.getContentType())
                .data(multipartFile.getBytes())
                .build();

        return fileRepo.save(fileEntity);

    }


    public List<FileEntity> getAllFiles() {
        return fileRepo.findAll();
    }
}