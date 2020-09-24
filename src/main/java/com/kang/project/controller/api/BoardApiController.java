package com.kang.project.controller.api;

import com.kang.project.config.PrincipalDetail;
import com.kang.project.model.Board;
import com.kang.project.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseEntity<Board> write(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.write(board, principal.getUser());
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @DeleteMapping("/api/board/{id}")
    public void delete(@PathVariable Long id) {
        boardService.delete(id);
    }

    @PutMapping("/api/board/{id}")
    public void update(@PathVariable Long id, @RequestBody Board board) {
        boardService.update(id, board);
    }


}
