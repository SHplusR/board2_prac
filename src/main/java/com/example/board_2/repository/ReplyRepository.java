package com.example.board_2.repository;

import com.example.board_2.entity.Board;
import com.example.board_2.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,Long> {

    @Modifying
    @Query("delete from Reply r where r.board.bno = :bno")
    void deleteByBno(@Param("bno") Long bno);

    //Board객체를 param으로 받고, 해당 Board의 댓글들(Replies)를 댓글번호(Rno)에 따라 가져온다.
    List<Reply> getRepliesByBoardOrderByRno(Board board);
}
