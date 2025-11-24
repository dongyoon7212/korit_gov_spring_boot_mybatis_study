package com.korit.mybatis_study.service;

import com.korit.mybatis_study.dto.AddBoardReqDto;
import com.korit.mybatis_study.dto.ApiRespDto;
import com.korit.mybatis_study.dto.EditBoardReqDto;
import com.korit.mybatis_study.entity.Board;
import com.korit.mybatis_study.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public ApiRespDto<?> addBoard(AddBoardReqDto addBoardReqDto) {
        //title 중복검사
        //title로 조회
        Optional<Board> foundBoard = boardRepository.findBoardByTitle(addBoardReqDto.getTitle());
        if (foundBoard.isPresent()) {
            return new ApiRespDto<>("failed", "제목이 중복되었습니다.", null);
        }
        //중복이 되지 않으면 추가
        Optional<Board> board = boardRepository.addBoard(addBoardReqDto.toEntity());
        if (board.isEmpty()) {
            return new ApiRespDto<>("failed", "추가하는데 문제가 발생했습니다.", null);
        }
        return new ApiRespDto<>("success", "게시물 추가 완료", board.get());
    }

    public ApiRespDto<?> getBoardList() {
        return new ApiRespDto<>("success", "전체 조회 완료", boardRepository.getBoardList());
    }

    public ApiRespDto<?> getBoardByBoardId(Integer boardId) {
        Optional<Board> foundBoard = boardRepository.getBoardByBoardId(boardId);
        if (foundBoard.isEmpty()) {
            return new ApiRespDto<>("failed", "해당 게시물이 존재하지 않습니다.", null);
        }
        return new ApiRespDto<>("success", "해당 게시물 조회 완료", foundBoard.get());
    }

    public ApiRespDto<?> editBoard(EditBoardReqDto editBoardReqDto) {
        //해당 board가 존재하는지 확인
        Optional<Board> foundBoard = boardRepository.getBoardByBoardId(editBoardReqDto.getBoardId());
        if (foundBoard.isEmpty()) {
            return new ApiRespDto<>("failed", "해당 게시물이 존재하지 않습니다.", null);
        }
        //있으면 수정을 진행
        int result = boardRepository.editBoard(editBoardReqDto.toEntity());
        if (result != 1) {
            return new ApiRespDto<>("failed", "게시물을 수정하는데 문제가 발생했습니다.", null);
        }
        return new ApiRespDto<>("success", "게시물 수정 성공", null);
    }

    public ApiRespDto<?> removeBoard(Integer boardId) {
        Optional<Board> foundBoard = boardRepository.getBoardByBoardId(boardId);
        if (foundBoard.isEmpty()) {
            return new ApiRespDto<>("failed", "해당 게시물이 존재하지 않습니다.", null);
        }
        int result = boardRepository.removeBoard(foundBoard.get().getBoardId());
        if (result != 1) {
            return new ApiRespDto<>("failed", "게시물을 삭제하는데 문제가 발생했습니다.", null);
        }
        return new ApiRespDto<>("success", "게시물이 삭제되었습니다.", null);
    }
}








