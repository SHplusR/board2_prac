package com.example.board_2.service;

import com.example.board_2.dto.BoardDTO;
import com.example.board_2.dto.PageRequestDTO;
import com.example.board_2.dto.PageResultDTO;
import com.example.board_2.entity.Board;
import com.example.board_2.entity.Member;
import com.example.board_2.repository.BoardRepository;
import com.example.board_2.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    private final ReplyRepository replyRepository;

    @Override
    public Long register(BoardDTO dto){
        log.info(dto);
        Board board = dtoToEntity(dto);
        boardRepository.save(board);
        return board.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO,Object[]> getList(PageRequestDTO pageRequestDTO){

        log.info(pageRequestDTO);
        Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board)en[0],(Member)en[1],(Long)en[2]));
//        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));
        Page<Object[]> result = boardRepository.searchPage(
                pageRequestDTO.getType(),
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getPageable(Sort.by("bno").descending())
        );

        return new PageResultDTO<>(result,fn);
    }

    @Override
    public BoardDTO get(Long bno){
        Object result = boardRepository.getBoardByBno(bno);
        Object [] arr = (Object[])result;
        return entityToDTO((Board)arr[0], (Member)arr[1], (Long)arr[2]);
    }

    @Transactional
    @Override
    public void removeWithReplies(Long bno){
        //삭제 기능 구현, 트랜잭션 추가.
        boardRepository.deleteById(bno);
        replyRepository.deleteByBno(bno); //댓글 삭제


    }

    @Transactional
    @Override
    public void modify(BoardDTO boardDTO){
        Board board = boardRepository.getOne(boardDTO.getBno());

        board.changeTitle(boardDTO.getTitle());
        board.changeContent(boardDTO.getContent());

        boardRepository.save(board);
    }
}
