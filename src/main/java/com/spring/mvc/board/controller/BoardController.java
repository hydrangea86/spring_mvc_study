package com.spring.mvc.board.controller;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.dto.ModBoard;
import com.spring.mvc.board.service.BoardService;
import com.spring.mvc.common.paging.Page;
import com.spring.mvc.common.paging.PageMaker;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/board")
public class BoardController {

    /**
     * 게시물 목록요청: /board/list : GET
     * 게시물 상세조회요청: /board/content : GET
     * 게시물 쓰기 화면요청: /board/write: GET
     * 게시물 등록요청: /board/write: POST
     * 게시물 삭제요청: /board/delete : GET
     * 게시물 수정화면요청: /board/modify: GET
     * 게시물 수정요청: /board/modify: POST
     */

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //board 목록
    @GetMapping("/list")
    public String list(Model model, Page page) {
        log.info("/board/list GET" + page);
        List<Board> boardList = boardService.getList(page);
        model.addAttribute("articles", boardList);
        model.addAttribute("pageInfo", new PageMaker(page, boardService.getCount(page)));
        return "board/list";
    }

    //글쓰기 화면 요청
    @GetMapping("/write")
    public String write() {
        log.info("board/write GET!");
        return "board/write";
    }

    //글쓰기 등록 요청
    @PostMapping("/write")
    public String write(Board board) {
        log.info("/board/write POST! - " + board);
        boardService.write(board);
        return "redirect:/board/list";
    }

    //상세조회 요청
    // /board/content?boardNo=3
    @GetMapping("/content")
    public String content(Long boardNo, @ModelAttribute("p") Page page, Model model) {
        log.info("/board/content Get!" + boardNo);
        Board board = boardService.get(boardNo);
        log.info("board -" + board);
        model.addAttribute("b", board);
        //model.addAttribute("p", page); model에 안담아도 됨 @ModelAttribute 쓸경우 (수정부분 해보기)
        return "/board/content";
    }

    //수정 양식 화면 요청
    @GetMapping("/modify")
    public String modify(Long boardNo, Model model) {
        log.info("/board/update GET! -" + boardNo );
        Board board = boardService.get(boardNo);
        model.addAttribute("board", board);
        return "/board/modify";
    }

    //수정 요청 처리
    @PostMapping("/modify")
    public String modify(ModBoard board) {
        log.info("/board/modify post! -" + board);
        boardService.update(board);
        return "redirect:/board/content?boardNo=" + board.getBoardNo();
    }

    //게시물 삭제 요청
    @GetMapping("/delete")
    public String delete(Long boardNo) {
        log.info("/board/content delete GET " + boardNo);
        boardService.remove(boardNo);
        return "redirect:/board/list";
    }

}
