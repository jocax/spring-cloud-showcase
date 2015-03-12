package com.jocax.client.angularjs;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/service/image")
public class ImageRest {
    private static final Logger LOG = LoggerFactory.getLogger(ImageRest.class);
    private static final Map<String, ImageQueryResponse> images = new HashMap<String, ImageQueryResponse>();

    @PostConstruct
    private void init() throws Exception {

        byte[] bytes1 =  copy(new ClassPathResource("image1.jpg").getInputStream());
        byte[] bytes2 =  copy(new ClassPathResource("image2.jpg").getInputStream());

        images.put("1", new ImageRest.ImageQueryResponse("image1", "image/jpeg", bytes1));
        images.put("2", new ImageRest.ImageQueryResponse("image2", "image/jpeg", bytes2));
        images.put("3", new ImageRest.ImageQueryResponse("image3", "image/jpeg", bytes1));
        images.put("4", new ImageRest.ImageQueryResponse("image4", "image/jpeg", bytes2));

    }

    private byte[] copy(InputStream inputStream) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        IOUtils.copy(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }


    @RequestMapping(value = "/{id}",   method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ImageQueryResponse getFullImage(@PathVariable("id") final String id) {
        LOG.info("Get image with ID: {}", id);
        ImageQueryResponse imageQueryResponse = images.get(getRandomKey());
        if (imageQueryResponse == null) {
           return images.get("1");
        }
        return imageQueryResponse;
    }

    @RequestMapping(value = "/data/{id}",   method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public byte[] getDataImage(@PathVariable("id") final String id) {
        LOG.info("Get image with ID: {}", id);
        ImageQueryResponse imageQueryResponse = images.get(getRandomKey());
        if (imageQueryResponse == null) {
            return images.get("1").getContent();
        }
        return imageQueryResponse.getContent();
    }


    private String getRandomKey() {
        Random random = new Random();
        int randomNumber = random.nextInt(images.size()) + 1;
        return "" + randomNumber;
    }

    public static class ImageQueryResponse {
        private final String name;
        private final String contentType;
        private final byte[] content;

        public ImageQueryResponse(String name, String contentType, byte[] content) {
            this.name = name;
            this.contentType = contentType;
            this.content = content;
        }

        public String getName() {
            return name;
        }

        public String getContentType() {
            return contentType;
        }

        public byte[] getContent() {
            return content;
        }
    }
}
