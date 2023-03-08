package projectB.meongbti.util.image.Entity;

import lombok.*;

import javax.persistence.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageMapping {
    private static final String UPLOAD_DIR = "/path/to/upload/directory/";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imId;
    private String imUpload;
    private String imStore;

    public ImageMapping(String imUpload, String imStore) {
        this.imUpload = imUpload;
        this.imStore = imStore;
    }



}
