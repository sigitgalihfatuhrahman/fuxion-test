package com.app.fuxion.services;

import com.app.fuxion.dto.UserDto;
import com.app.fuxion.entities.UserEntity;
import com.app.fuxion.requests.UserRequest;
import com.app.fuxion.responses.Response;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    Response save(UserRequest userRequest);
    Response exportUser(List<Long> id);
    UserEntity findById (Long id);
    Map<String, Object> findAllUserPage(Integer pageNo, Integer pageSize, String sortBy, String sort, String name);
    Map<String, Object> findAllUserExportedPage(Integer pageNo, Integer pageSize, String sortBy, String sort);
    UserDto download(Long id);
}
