package me.eduspace.util;

import lombok.extern.slf4j.Slf4j;
import me.eduspace.exceptions.AppBadRequestException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.apache.http.entity.ContentType.*;

@Slf4j
public class AmazonUtil {

    public static void isImage(MultipartFile file) {
        if (!Arrays.asList(
                IMAGE_JPEG.getMimeType(),
                IMAGE_PNG.getMimeType(),
                IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
            throw new AppBadRequestException("File must be an image [" + file.getContentType() + "]");
        }
    }

    public static void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new AppBadRequestException("Cannot upload empty file [ " + file.getSize() + "]");
        }
    }

    public static Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }
}
