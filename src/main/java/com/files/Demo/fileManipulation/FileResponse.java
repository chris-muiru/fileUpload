package com.files.Demo.fileManipulation;

import lombok.*;

@Builder
public record FileResponse(
        String fileName,
        String downloadUrl,
         String fileType,
         long fileSize
) {}
