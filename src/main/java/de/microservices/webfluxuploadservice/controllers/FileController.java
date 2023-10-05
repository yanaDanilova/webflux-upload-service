package de.microservices.webfluxuploadservice.controllers;


import de.microservices.webfluxuploadservice.dtos.FileDto;
import de.microservices.webfluxuploadservice.dtos.FileDtoFull;
import de.microservices.webfluxuploadservice.services.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/files")
@CrossOrigin
public class FileController {

    private final IFileService fileService;

    @Autowired
    public FileController(IFileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public Mono<HttpStatus> uploadFile(@RequestPart("file") Mono<MultipartFile> fileMono, @RequestPart("fileDto") Mono<FileDto> fileDtoMono) {
        return fileService.saveFile(fileMono,fileDtoMono);

    }

//    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public Mono<String> uploadFile(@RequestPart("file") Mono<FilePart> file) {
//        return file.flatMap(filePart -> fileService.uploadFile(filePart));
//    }
    @DeleteMapping("/{id}")
    public Mono<HttpStatus> deleteFile(@PathVariable(name = "id") Long id) {
        return fileService.deleteFile(id);

    }

    @PutMapping("/{id}")
    public Mono<HttpStatus> updateFile(@PathVariable Long id, @RequestPart("file") Mono<MultipartFile> fileMono, @RequestPart("fileDto") Mono<FileDto> fileDtoMono) {
        return fileService.updateFile(id,fileMono,fileDtoMono);

    }

    @GetMapping(value = "/all/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<FileDtoFull> listAllFiles(@PathVariable Long userId) {
        return fileService.getAllFiles(userId);
    }
}