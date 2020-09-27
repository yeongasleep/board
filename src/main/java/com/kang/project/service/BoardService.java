package com.kang.project.service;

import com.kang.project.model.Board;
import com.kang.project.model.Reply;
import com.kang.project.model.User;
import com.kang.project.repository.BoardRepository;
import com.kang.project.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    public Page<Board> listAll(Pageable pageable) {
        Page<Board> boardList = boardRepository.findAll(pageable);
        return boardList;
    }

    @Transactional
    public Board detail(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException(id + "번의 게시글은 존재하지 않습니다.");
                });
        int plusCount = board.getCount();
        plusCount += 1;
        board.setCount(plusCount);
        return board;
    }

    @Transactional
    public void write(Board board, User user) {
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional
    public void delete(Long id) {
        System.out.println("글삭제하기 " + id);
        boardRepository.deleteById(id);
    }

    @Transactional
    public Board update(Long id, Board board) {
        System.out.println("update 함수 실행 =====" + id + "====");
        Board findBoard = boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("해당 번호의 게시글은 존재하지 않습니다." + id);
                });
        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());
        return findBoard;
    }

    @Transactional
    public void replyWrite(Long id, Reply reply, User user) {
        System.out.println("BoardService.replyWrite 함수 호출============");
        Board board = boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("해당 번호의 게시글이 존재하지 않습니다.");
                });
        reply.setUser(user);
        reply.setBoard(board);
        replyRepository.save(reply);
    }
}
