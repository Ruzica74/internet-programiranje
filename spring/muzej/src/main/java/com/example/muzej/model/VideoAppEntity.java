package com.example.muzej.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "video_app", schema = "muzeji", catalog = "")
public class VideoAppEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "video")
    private String video;

}
