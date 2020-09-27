package com.kang.project.controller.auth;

import com.kang.project.config.PrincipalDetail;
import com.kang.project.model.Board;
import com.kang.project.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    @Autowired
    private BoardService boardService;

    /*  게시판 리스트 이동  */
    @GetMapping("/board/list")
    public String board(Model model, @PageableDefault(size=3,sort="id",direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boardList",boardService.listAll(pageable));
        return "board/list";
    }

    /*  게시판 글작성 이동  */
    @GetMapping("/board/write")
    public String write(Model model) {
        model.addAttribute("board",new Board());
        return "board/write";
    }

    /*  게시글 상세보기  */
    @GetMapping("/board/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("board",boardService.detail(id));
        return "board/detail";
    }

    /*  게시글 수정 페이지 이동  */
    @GetMapping("/board/post/{id}")
    public String updatePost(@PathVariable Long id, Model model) {
        model.addAttribute("board",boardService.detail(id));
        return "board/update";
    }


}
