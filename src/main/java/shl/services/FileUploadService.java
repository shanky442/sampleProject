package shl.services;

import org.springframework.web.multipart.MultipartFile;
import shl.models.FileDetails;
import shl.models.FileDetailsResponse;

public interface FileUploadService {
    public FileDetailsResponse uploadFile(MultipartFile file);
}
