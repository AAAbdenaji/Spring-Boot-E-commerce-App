package com.misc.sandboxproj.Helpers;

import java.io.IOException;
import java.util.Set;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.misc.sandboxproj.execeptions.ValidationException;

@Component
public class ImageHelper {

    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;
    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
        "image/jpeg",
        "image/png",
        "image/webp"
);


    private final Tika tika = new Tika();

    public void validateImage(MultipartFile file) {

        if (file.isEmpty())
            throw new ValidationException("File is Empty");
        if (file.getSize() > MAX_FILE_SIZE)
            throw new ValidationException("Too large");

        try {
            String detectedType = tika.detect(file.getInputStream());

            if (!ALLOWED_CONTENT_TYPES.contains(detectedType)) {
                throw new ValidationException("Invalid image type: " + detectedType);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to read file for validation", e);
        }   
    }
}
