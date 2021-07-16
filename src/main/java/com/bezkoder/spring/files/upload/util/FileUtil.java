package com.bezkoder.spring.files.upload.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

public class FileUtil {

    public static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        is.close();
        return bytes;
    }

    public static File base64ToFile(Path path, String fileName, String base64String) {
        File outputFile = new File(path.toFile(),fileName);
        byte[] bytes = Base64.decodeBase64(base64String);
        try{FileUtils.writeByteArrayToFile(outputFile, bytes);}catch(IOException e){}
        return outputFile;
    }
}
