package de.microservices.webfluxuploadservice.services;

import de.microservices.webfluxuploadservice.dtos.FileDto;
import de.microservices.webfluxuploadservice.dtos.FileDtoFull;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IFileService {

    Mono<HttpStatus> saveFile(Mono<MultipartFile> fileMono, Mono<FileDto> fileDtoMono);

    Mono<HttpStatus> deleteFile(Long id);

    Mono<HttpStatus> updateFile(Long id, Mono<MultipartFile> fileMono, Mono<FileDto> fileDtoMono);

    Flux<FileDtoFull> getAllFiles(Long userId);
}
