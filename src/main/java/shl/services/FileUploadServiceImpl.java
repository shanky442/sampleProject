package shl.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import shl.constants.Constants;
import shl.customexceptions.MyCustomException;
import shl.models.FileDetails;
import shl.models.FileDetailsResponse;
import shl.repositories.FileRepository;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Service for uploading files to file system and
 * making entry in the database
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${fileUploadPath}")
    private String fileUploadPath;

    @Value("${fileSize}")
    private Integer fileSize;

    @Value("${fileTypes}")
    private String fileTypes;

    @Autowired
    FileRepository fileRepository;

    private Logger log = LoggerFactory.getLogger(FileUploadServiceImpl.class);
    /**
     * Service for uploading a file and updating database
     * @param file
     * @return filename, file id and status of file upload
     */
    public FileDetailsResponse uploadFile(MultipartFile file) {
        FileDetailsResponse uploadResponse = new FileDetailsResponse();
        String name = file.getOriginalFilename();
        String actualFileName = name+System.currentTimeMillis();
        String filename[] = name.split("\\.");
        if(file.getSize()>fileSize) {//2 MB
            //uploadResponse.setMessage(Constants.FILE_SIZE_MORE_THAN_ALLOWED_LIMIT);
            throw new MyCustomException(Constants.FILE_SIZE_MORE_THAN_ALLOWED_LIMIT);
        }
        String fileTypeArray[] = fileTypes.split(",");
        if(!(filename[1].equalsIgnoreCase(fileTypeArray[0]) || filename[1].equalsIgnoreCase(fileTypeArray[1]) || filename[1].equalsIgnoreCase(fileTypeArray[2]))) {
            //uploadResponse.setMessage(Constants.FILE_IS_NEITHER_JPEG_JPG_NOR_PDF);
            throw new MyCustomException(Constants.FILE_IS_NEITHER_JPEG_JPG_NOR_PDF);
        }
        actualFileName = filename[0].concat("-")+System.currentTimeMillis()+".".concat(filename[1]);
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                File dir = new File(fileUploadPath);
                if (!dir.exists())
                    dir.mkdirs();
                File serverFile = new File(dir.getPath()
                        + File.separator + actualFileName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                uploadResponse.setMessage(Constants.UPLOADED_SUCCESSFULLY);
            } catch (IOException e) {
                uploadResponse.setMessage(Constants.ERROR_OCCURRED_DURING_FILE_UPLOAD);
                log.error(Constants.ERROR_OCCURRED_DURING_FILE_UPLOAD,e);
                throw new MyCustomException(Constants.ERROR_OCCURRED_DURING_FILE_UPLOAD,e);
            }
        }
        FileDetails fd = new FileDetails();
        fd.setFileName(actualFileName);
        fd = fileRepository.insert(fd);
        uploadResponse.setId(fd.getId());
        uploadResponse.setFileName(actualFileName);
        return uploadResponse;
    }
}
