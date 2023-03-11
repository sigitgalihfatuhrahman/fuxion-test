package com.app.fuxion.controller;

import com.app.fuxion.dto.UserDto;
import com.app.fuxion.entities.UserEntity;
import com.app.fuxion.requests.UserRequest;
import com.app.fuxion.responses.Response;
import com.app.fuxion.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest request_data) {
        Response response = userService.save(request_data);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/export")
    public ResponseEntity<?> exportUser(
            HttpServletRequest request,
            @Valid @RequestBody List<Long> id
    ) {
        Response response = userService.exportUser(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/view-exported")
    public ResponseEntity<?> getAllUserExported(
            HttpServletRequest request,
            @RequestParam(name = "page_no", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") Integer pageSize,
            @RequestParam(name = "sort_by", defaultValue = "id") String sortBy,
            @RequestParam(name = "sort", defaultValue = "ASC") String sort) throws ParseException {
        Response response = null;
        Map<String, Object> result = new HashMap<>();
        result = userService.findAllUserExportedPage(pageNo, pageSize, sortBy, sort);
        return ResponseEntity.ok(new Response(Long.valueOf(result.get("total_result").toString()),
                Long.valueOf(result.get("total_page").toString()), result.get("data")));
    }

    @GetMapping(value = "/view-all")
    public ResponseEntity<?> getAllUserPage(
            HttpServletRequest request,
            @RequestParam(name = "name", defaultValue = "") Optional<String> name,
            @RequestParam(name = "page_no", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") Integer pageSize,
            @RequestParam(name = "sort_by", defaultValue = "id") String sortBy,
            @RequestParam(name = "sort", defaultValue = "ASC") String sort) throws ParseException {
        Response response = null;
        Map<String, Object> result = new HashMap<>();
        result = userService.findAllUserPage(pageNo, pageSize, sortBy, sort, name.orElse(null));
        return ResponseEntity.ok(new Response(Long.valueOf(result.get("total_result").toString()),
                Long.valueOf(result.get("total_page").toString()), result.get("data")));
    }

    @GetMapping("/download/{documentId}")
    public ResponseEntity<?> downloadImage(@PathVariable Long documentId) {
        UserDto response = userService.download(documentId);
        if (response.getFileName() != null){
            byte[] files = Base64.getDecoder().decode(response.getDocumentFile());
            String contentType = "application/octet-stream";
            String headerValue = "attachment; filename=\"" +response.getFileName()+ "\"";
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.valueOf(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                    .body(files);
        } else {
            return ResponseEntity.ok(new Response("the data has not been exported !",null));
        }

    }

}
