package com.app.fuxion.services;
import com.app.fuxion.dto.UserDto;
import com.app.fuxion.entities.UserEntity;
import com.app.fuxion.exceptions.ResourceNotFoundException;
import com.app.fuxion.repositories.UserRepository;
import com.app.fuxion.requests.UserRequest;
import com.app.fuxion.responses.Response;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    Date date = new Date();

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found!")
        );
    }

    @Override
    public Map<String, Object> findAllUserPage(Integer pageNo, Integer pageSize, String sortBy, String sort, String name) {
        Map<String, Object> result = new HashMap<>();

        Sort.Direction sorts;
        if (sortBy.equalsIgnoreCase("desc"))
            sorts = Sort.Direction.DESC;
        else
            sorts = Sort.Direction.ASC;

        Page<UserEntity> list = null;

        Pageable paging = PageRequest.of(pageNo, pageSize, sorts, sortBy);

        if (name.equalsIgnoreCase(""))
            name = null;
        else
            name = "%" + name + "%";

        list = userRepository.findAllUsers(name, paging);
        result.put("data", list.getContent());
        result.put("total_result", list.getTotalElements());
        result.put("total_page", list.getTotalPages());
        return result;
    }

    @Override
    public Response save(UserRequest userRequest) {
        Response response = null;
        UserEntity userEntity = new UserEntity();
        List<UserEntity> list = null;
        list = userRepository.findUserByName(userRequest.getName());
        if (list.isEmpty()){
            userEntity.setName(userRequest.getName());
            userEntity.setEmail(userRequest.getEmail());
            userEntity.setAddress(userRequest.getAddress());
            userEntity.setExported(0); // 0 is not exported ; 1 is exported
            userEntity = userRepository.save(userEntity);
            UserDto userDto = new UserDto(
                    userEntity.getName(),
                    userEntity.getEmail(),
                    userEntity.getAddress(),
                    userEntity.getExported()
            );
            response = userEntity != null ?  new Response( "Success", userDto) : new Response(null);
        } else {
            response = userEntity != null ?  new Response( "Failed", "Data is exist !") : new Response(null);
        }
        return response ;
    }

    @Override
    public Response exportUser(List<Long> id) {
        Response response = null;
        List<UserEntity> usrSaveAll = null;
        List<UserEntity> objUser = new ArrayList<>();
        Document document = new Document();
        String path = "src/main/resources/document/";
        try {
            id.forEach(arrId -> {
                UserEntity usr = this.findById(arrId);
                String fileName = format.format(date) + "_" + usr.getName() + "_document.pdf";
                try {
                    if (usr.getExported() != 1) {
                        String documentName = path + fileName;
                        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(documentName));
                        document.open();
                        addMetaData(document);
                        addContent(document, usr);
                        document.close();
                        writer.close();
                        // ENCODE FILE DOCUMENT
                        byte[] byteFile = getByteArrayFromFile(documentName);
                        String file = Base64.getEncoder().encodeToString(byteFile);
                        // SET ON OBJ USER , MAPING
                        usr.setExported(1);
                        usr.setFileName(fileName);
                        usr.setDocumentFile(file);
                        objUser.add(usr);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }

        if (!objUser.isEmpty()){
            usrSaveAll = userRepository.saveAll(objUser);
            response = usrSaveAll != null ? new Response("Success", usrSaveAll) : new Response(null);
        } else {
            response = new Response("Success", "this row data has been exported before it");
        }
        return response;
    }

    @Override
    public UserDto download(Long id) {
        UserEntity userEntity = this.findById(id);
        UserDto userDto = new UserDto(
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getAddress(),
                userEntity.getExported(),
                userEntity.getFileName(),
                userEntity.getDocumentFile()
        );
        return userDto;
    }

    @Override
    public Map<String, Object> findAllUserExportedPage(Integer pageNo, Integer pageSize, String sortBy, String sort) {
        Map<String, Object> result = new HashMap<>();

        Sort.Direction sorts;
        if (sortBy.equalsIgnoreCase("desc"))
            sorts = Sort.Direction.DESC;
        else
            sorts = Sort.Direction.ASC;

        Page<UserEntity> list = null;

        Pageable paging = PageRequest.of(pageNo, pageSize, sorts, sortBy);

        list = userRepository.findAllUserByExorted(1, paging);
        result.put("data", list.getContent());
        result.put("total_result", list.getTotalElements());
        result.put("total_page", list.getTotalPages());
        return result;
    }

    private static void addMetaData(Document document) {
        document.addTitle("Document AppFuxion");
        document.addSubject("Assestment");
        document.addKeywords("Java");
        document.addAuthor("AppFuxion");
        document.addCreator("Sigit Galih f");
    }

    private static void addContent(Document document, UserEntity userEntity) throws DocumentException {
        Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
                Font.BOLD);
        Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                Font.NORMAL, BaseColor.RED);
        Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
                Font.BOLD);
        Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                Font.BOLD);
        Anchor anchor = new Anchor("Assesment Java Developer", catFont);
        anchor.setName("Assesment Java Developer");
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);
        Paragraph subPara = new Paragraph("Profile", subFont);
        Section subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Name : " + userEntity.getName()));
        subCatPart.add(new Paragraph("Email : " + userEntity.getEmail()));
        subCatPart.add(new Paragraph("Address : " + userEntity.getAddress()));
        // now add all this to the document
        document.add(catPart);

    }

    private byte[] getByteArrayFromFile(String document) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final InputStream in = new FileInputStream(document);
        final byte[] buffer = new byte[2048000];

        int read = -1;
        while ((read = in.read(buffer)) > 0) {
            baos.write(buffer, 0, read);
        }
        in.close();
        return baos.toByteArray();
    }

}
