package org.viiinzzz.pharma.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.nio.charset.StandardCharsets;

import org.viiinzzz.pharma.repository.UploadRepository;
import org.viiinzzz.pharma.service.FileStorageService;
import org.viiinzzz.pharma.repository.MeasureRepository;
import org.viiinzzz.pharma.entity.Measure;
import org.viiinzzz.pharma.entity.Upload;

import org.viiinzzz.pharma.help.csv.CsvHelper;


@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    
    @Autowired
    private MeasureRepository measureRepository;

    @Autowired
    private UploadRepository uploadRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/file/upload")
    public UploadFileResponse uploadFile(
            @RequestParam("file") MultipartFile file
            //, @RequestHeader("client-id") String clientId
    ) {
        String clientId = "anonymous";
        LocalDateTime uploadTime = LocalDateTime.now();

        Upload upload = new Upload(clientId, uploadTime);
        uploadRepository.save(upload);

        try {
            String text = new String(file.getBytes(), StandardCharsets.UTF_8);
            //System.out.println("received file:\n" + text);

            upload.setText(text);
            System.out.println("received: " + upload);


            try (Reader reader =  new StringReader(text)) {
                List<Measure> measures =
                        CsvHelper.ReadCsv(reader, CsvMeasure.class).stream()
                        .map(line -> {
                            try {
                                return new Measure(
                                        upload,
                                        line.getTime(),
                                        line.getValue()
                                );
                            } catch(Exception eee) {
                                return null;
                                //TODO
                                // decide to continue and log line issues or reject whole file
                                //for now just filter out line issues
                            }
                        })
                                .filter(measure -> measure != null)
                                .collect(Collectors.toList());

                //System.out.println("received: " + measures);
                for(Measure measure : measures) {
                    System.out.println(measure);

                    measureRepository.save(measure);
                }

                String fileName = fileStorageService.storeFile(file, upload.getId() + ".dat");

                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/file/download/")
                        .path(fileName)
                        .toUriString();

                upload.setValid(true);
                uploadRepository.save(upload);

                return new UploadFileResponse(fileName, fileDownloadUri,
                        file.getContentType(), file.getSize());

            } catch(Exception ee) {
                System.out.println("upload error" + ee);

                upload.setValid(false);
                uploadRepository.save(upload);

                throw new IOException(ee.getMessage());
            }

        } catch(IOException e) {

            throw new ResponseStatusException(
                HttpStatus.NOT_ACCEPTABLE, "file not acceptable", e);
        }
        


    }

    @PostMapping("/file/upload/many")
    public List<UploadFileResponse> uploadMultipleFiles(
            @RequestParam("files") MultipartFile[] files
            //, @RequestHeader("client-id") String clientId
            ) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file
                        //, clientId
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/file/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
