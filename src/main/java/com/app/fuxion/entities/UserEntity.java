package com.app.fuxion.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Column(name = "address")
    @JsonProperty("address")
    private String address;

    @Column(name = "exported")
    @JsonProperty("exported")
    private Integer exported;

    @Column(name = "file_name")
    @JsonProperty("file_name")
    private String fileName;

    @Column(name = "document_file")
    @JsonProperty("document_file")
    @Lob
    private String documentFile;
}
