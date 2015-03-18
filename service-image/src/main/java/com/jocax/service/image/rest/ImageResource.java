package com.jocax.service.image.rest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

@RestController
public class ImageResource {

    @RequestMapping("/text")
    public ResponseEntity<ClassPathResource> text(final HttpServletRequest httpServletRequest) throws Exception {
        URI location = new URI(httpServletRequest.getRequestURI());
        return ResponseEntity.created(location).contentType(MediaType.TEXT_PLAIN).body(new ClassPathResource("text.txt"));
    }

    @RequestMapping("/")
    public ResponseEntity<ClassPathResource> get(final HttpServletRequest httpServletRequest) throws Exception {
        URI location = new URI(httpServletRequest.getRequestURI());
        return ResponseEntity.created(location).contentType(MediaType.IMAGE_JPEG).body(new ClassPathResource("image1.jpg"));
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
