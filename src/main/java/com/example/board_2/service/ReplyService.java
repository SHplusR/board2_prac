package com.example.board_2.service;

import com.example.board_2.dto.ReplyDTO;
import com.example.board_2.entity.Board;
import com.example.board_2.entity.Reply;

import java.util.List;

public interface ReplyService {

    Long register(ReplyDTO replyDTO); //댓글의 등록
    List<ReplyDTO> getList (Long bno); //특정게시물에 대한 댓글 목록

    void modify(ReplyDTO replyDTO); // 댓글 수정
    void remove(Long rno); //댓글삭제

    //replydto -> replyentity
    default Reply dtoToEntity(ReplyDTO replyDTO){
        Board board = Board.builder().bno(replyDTO.getBno()).build();
        Reply reply = Reply.builder()
                .rno(replyDTO.getRno())
                .text(replyDTO.getText())
                .replyer(replyDTO.getReplyer())
                .board(board)
                .build();

        return reply;
    }


    //replyentity -> replydto
    default ReplyDTO entityToDTO (Reply reply){

        ReplyDTO replyDTO = ReplyDTO.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .replyer(reply.getReplyer())
                .modDate(reply.getModDate())
                .regDate(reply.getRegDate())
                .build();

        return replyDTO;
    }



}
