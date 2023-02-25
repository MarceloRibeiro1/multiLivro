package com.fcamara.multilivro.book.service;

import com.fcamara.multilivro.book.model.BookCover;
import org.springframework.web.multipart.MultipartFile;

public interface AppendService {
    void appendCover(BookCover cover, MultipartFile coverFile);
}
