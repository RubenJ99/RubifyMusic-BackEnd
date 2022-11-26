package com.rubify.music.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Table(name = "song_category")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SongCategoryEntity implements Serializable {

    private static final long serialVersionUID = 1896356612038137251L;
    private @Id @GeneratedValue Integer id;
    private @Column(name = "category_name") String categoryName;

    private @OneToMany(mappedBy = "category") List<SongJoinCatEntity> categories;
}
