package com.example.board_2.entity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String content;

//    @ManyToOne (fetch = FetchType.LAZY)
@ManyToOne (cascade = CascadeType.ALL)
    private Member writer;

    public void changeTitle(String title){
        this.title = title;
    }
    public void changeContent(String content){
        this.content = content;
    }

}
