package com.app.fuxion.repositories;

import com.app.fuxion.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(
            value = "SELECT * FROM users u WHERE (:name IS NULL OR u.name LIKE :name)",
            countQuery = "SELECT COUNT(*) " +
                    "FROM users u WHERE (:name IS NULL OR u.name LIKE :name)",
            nativeQuery = true
    )
    Page<UserEntity> findAllUsers(String name, Pageable pageable);

    @Query(
            value = "SELECT * FROM users u WHERE (:name IS NULL OR u.name LIKE :name)",
            countQuery = "SELECT COUNT(*) " +
                    "FROM users u WHERE (:name IS NULL OR u.name LIKE :name)",
            nativeQuery = true
    )
    List<UserEntity> findUserByName(String name);

    @Query(
            value = "SELECT * FROM users u WHERE (:exported IS NULL OR u.exported LIKE :exported)",
            countQuery = "SELECT COUNT(*) " +
                    "FROM users u WHERE (:exported IS NULL OR u.exported LIKE :exported)",
            nativeQuery = true
    )
    Page<UserEntity> findAllUserByExorted(Integer exported, Pageable pageable);
}
