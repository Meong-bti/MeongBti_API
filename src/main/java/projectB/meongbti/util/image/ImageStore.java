package projectB.meongbti.util.image;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import projectB.meongbti.util.image.Entity.ImageMapping;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class ImageStore {

    private String fileDir;


    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public ImageMapping storeFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String storeFilename = createStoreFileName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFilename)));
        return new ImageMapping(originalFilename, storeFilename);
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

}
