package com.fcamara.multilivro.archive.controller;

import com.fcamara.multilivro.archive.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/archive")
@RequiredArgsConstructor
public class ArchiveController {
    private final FileService fileService;
    @GetMapping("/cover/{fileName}")
    public ResponseEntity<byte[]> getArchive(@PathVariable String fileName) {
        byte[] file = fileService.findFile(fileName);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(file, header, HttpStatus.OK);
    }
    @GetMapping("/book/{fileName}")
    public ResponseEntity<String> getArchive(
            @PathVariable String fileName,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        String text = fileService.findFile(fileName, pageable);

        return ResponseEntity.ok(text);
    }
}
