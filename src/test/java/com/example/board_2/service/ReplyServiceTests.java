package com.example.board_2.service;

import com.example.board_2.dto.ReplyDTO;
import com.example.board_2.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ReplyServiceTests {

    @Autowired
    private ReplyService replyService;

    @Test
    public void testGetList(){
        Long bno = 100L;
        List<ReplyDTO> replyDTOList = replyService.getList(bno);
        replyDTOList.forEach(replyDTO -> System.out.println(replyDTO));
    }
}
