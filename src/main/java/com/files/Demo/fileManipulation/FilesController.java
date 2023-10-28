package com.files.Demo.fileManipulation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/files")
public class FilesController {
    @Autowired
    private FilesService filesService;

    @PostMapping("/upload")
    public FileResponse uploadFile(@RequestPart("file")MultipartFile multipartFile) throws Exception{
        FileEntity attachment;
        String downloadUrl;
        attachment = filesService.saveAttachments(multipartFile);
        downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(attachment.getId().toString())
                .toUriString();

        return FileResponse.builder()
                .fileName(attachment.getFileName())
                .downloadUrl(downloadUrl)
                .fileType(attachment.getFileType())
                .build();
    }
    @GetMapping("")
    public List<FileResponse> fileResponse(){
        List<FileEntity> products = filesService.getAllFiles();
        List<FileResponse> fileResponses = products.stream().map(
                product->{
                    String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/download/")
                            .path(product.getId().toString())
                            .toUriString();
                    return FileResponse.builder()
                            .fileName(product.getFileName())
                            .downloadUrl(downloadUrl)
                            .fileType(product.getFileType())
                            .build();
                }
        ).toList();

        return fileResponses;
    }
}