package com.kang.project.service;

import com.kang.project.model.Board;
import com.kang.project.model.User;
import com.kang.project.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public List<Board> listAll() {
        List<Board> boardList = boardRepository.findAll();
        return boardList;
    }

    @Transactional
    public Board detail(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException(id + "번의 게시글은 존재하지 않습니다.");
                });
        return board;
    }

    @Transactional
    public void write(Board board, User user) {
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
}
