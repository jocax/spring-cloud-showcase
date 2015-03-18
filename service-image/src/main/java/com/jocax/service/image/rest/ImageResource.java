package com.jocax.service.image.rest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

@RestController
@RequestMapping("/service/image")
public class ImageResource {

    private Resource image = null;

    //TODO security filter to check access against membership

    @RequestMapping(value="/{groupId}/{type}", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void upload(
            @PathVariable String key,
            @PathVariable String type,
            @RequestParam("file") MultipartFile file)throws Exception {

        if (StringUtils.isEmpty(key)) {
            throw new RuntimeException("Parameter key is null or empty.");
        }
        if (StringUtils.isEmpty(type)) {
            throw new RuntimeException("Parameter type is null or empty.");
        }

        if (file.isEmpty()) {
            throw new RuntimeException("Upload failed because the file was empty.");
        }
        Image image = new Image(file.getInputStream());
        image.setContentType(file.getContentType());
        image.setName(file.getName());
        image.setOriginalFilename(file.getOriginalFilename());
        image.setSize(file.getSize());
        this.image = image;
    }

    @RequestMapping("/{key}/{type}")
    public ResponseEntity<Resource> retrieve(
            @PathVariable String key,
            @PathVariable String type,
            final HttpServletRequest httpServletRequest) throws Exception {

        if (StringUtils.isEmpty(key)) {
            throw new RuntimeException("Parameter key is null or empty.");
        }
        if (StringUtils.isEmpty(type)) {
            throw new RuntimeException("Parameter type is null or empty.");
        }

        URI location = new URI(httpServletRequest.getRequestURI());
        if (this.image == null) {
            this.image = new ClassPathResource("image1.jpg");
        }
        return ResponseEntity.created(location).contentType(MediaType.IMAGE_JPEG).body(this.image);
    }


    @RequestMapping("/text")
    public ResponseEntity<ClassPathResource> text(final HttpServletRequest httpServletRequest) throws Exception {
        URI location = new URI(httpServletRequest.getRequestURI());
        return ResponseEntity.created(location).contentType(MediaType.TEXT_PLAIN).body(new ClassPathResource("text.txt"));
    }

    @RequestMapping("/")
    public ResponseEntity<Resource> get(final HttpServletRequest httpServletRequest) throws Exception {
        URI location = new URI(httpServletRequest.getRequestURI());
        if (this.image == null) {
            this.image = new ClassPathResource("image1.jpg");
        }
        return ResponseEntity.created(location).contentType(MediaType.IMAGE_JPEG).body(this.image);
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET,
            consumes=MediaType.ALL_VALUE,
            produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void download(final HttpServletResponse response) throws Exception {

        InputStream is = new ClassPathResource("image1.jpg").getInputStream();
        response.setHeader("Content-Disposition", "attachment; filename=\"image1.jpg\"");

        int read=0;
        byte[] bytes = new byte[32];
        OutputStream os = response.getOutputStream();

        while((read = is.read(bytes))!= -1){
            os.write(bytes, 0, read);
        }
        os.flush();
        os.close();
    }

}
