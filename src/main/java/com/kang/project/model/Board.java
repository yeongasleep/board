package com.kang.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=50)
    private String title;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"board"})
    private List<Reply> replys;

    @Column(nullable = false)
    private int count;

    @CreationTimestamp
    private Timestamp writeDate;

}


