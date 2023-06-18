package com.example.board_2.service;

import com.example.board_2.dto.ReplyDTO;
import com.example.board_2.entity.Board;
import com.example.board_2.entity.Reply;
import com.example.board_2.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{
    private final ReplyRepository replyRepository;
    @Override
    public Long register(ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);
        replyRepository.save(reply);
        return reply.getRno();
    }

    @Override
    public List<ReplyDTO> getList(Long bno) {
       List<Reply> result = replyRepository.getRepliesByBoardOrderByRno(Board.builder().bno(bno).build());
       return result.stream().map(reply -> entityToDTO(reply)).collect(Collectors.toList());
    }

    @Override
    public void modify(ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);
        replyRepository.save(reply);
    }

    @Override
    public void remove(Long rno) {
        replyRepository.deleteById(rno);

    }
}
