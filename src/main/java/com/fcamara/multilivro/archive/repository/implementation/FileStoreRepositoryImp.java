package com.fcamara.multilivro.archive.repository.implementation;

import com.fcamara.multilivro.archive.repository.FileStoreRepository;
import com.fcamara.multilivro.handler.CustomException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
@Transactional
public class FileStoreRepositoryImp implements FileStoreRepository {
    @Value("${fcamara.repo.file-path}")
    private String filePath;
    @Override
    public void createFile(String fileName, byte[] data) {
        try (FileOutputStream fos = new FileOutputStream(new File(filePath, fileName))){
            fos.write(data);
        } catch (IOException e) {
            throw new CustomException("Error saving file");
        }
    }

    @Override
    public byte[] readFile(String fileName) {
        try {
            return Files.readAllBytes(Paths.get(new File(filePath, fileName).toURI()));
        } catch (IOException e) {
            throw new CustomException("Error reading file");
        }
    }

    @Override
    public String readFile(String fileName, Pageable pageable) {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath+"/"+fileName))) {
            StringBuilder sb = new StringBuilder();
            int line = 1;
            String text;
            while (line < pageable.getPageNumber()) {
                br.readLine();
                line ++;
            }

            for (int i = 0; i < pageable.getPageSize(); i++) {
                text = br.readLine();
                if (text != null) {
                    sb.append(text);
                    sb.append("\n");
                } else break;
            }

            return sb.toString();
        } catch (IOException e) {
            throw new CustomException("Error reading file");
        }
    }

    @Override
    public void deleteFile(String fileName) {
        try {
            Files.delete(Paths.get(new File(filePath, fileName).toURI()));
        } catch (IOException e) {
            throw new CustomException("Error deleting file");
        }
    }
}
