package com.fcamara.multilivro.archive.service.implementation;

import com.fcamara.multilivro.archive.model.Archive;
import com.fcamara.multilivro.archive.service.ArchiveService;
import com.fcamara.multilivro.archive.service.FileService;
import com.fcamara.multilivro.exception.DefaultAbstractException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class FileArchiveFacadeImpTest {
    @Mock
    private FileService fileService;
    @Mock
    private ArchiveService archiveService;
    @InjectMocks
    private FileArchiveFacadeImp facadeImp;


    @Test
    public void ShouldSaveFileWithCorrectName() {
        MockMultipartFile mockFile = new MockMultipartFile("file", "test.txt", "text/plain", "Test file content".getBytes());
        ArgumentCaptor<Archive> archiveArg = ArgumentCaptor.forClass(Archive.class);

        Archive mockReturn = new Archive();
        UUID randomId = UUID.randomUUID();
        mockReturn.setId(randomId);
        Mockito.when(archiveService.saveArchive(Mockito.any())).thenReturn(mockReturn);

        facadeImp.saveFileAndArchive(mockFile, "FileNewName");

        Mockito.verify(archiveService).saveArchive(archiveArg.capture());
        assertEquals("FileNewName", archiveArg.getValue().getName());
        assertEquals(".txt", archiveArg.getValue().getType());
        Mockito.verify(fileService,Mockito.times(1)).saveFile(mockFile,randomId);
    }

    @Test
    public void ShouldThrowWithoutFileOriginalName() {
        MockMultipartFile mockFile = new MockMultipartFile("file", null, "text/plain", "Test file content".getBytes());
        assertThrows(DefaultAbstractException.class, () -> facadeImp.saveFileAndArchive(mockFile, ""));
    }

}