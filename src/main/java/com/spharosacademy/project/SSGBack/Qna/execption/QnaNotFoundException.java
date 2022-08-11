package com.spharosacademy.project.SSGBack.Qna.execption;

public class QnaNotFoundException extends RuntimeException {
    public static final String MESSAGE = "문의가 존재하지 않습니다";

    public QnaNotFoundException(){
        super(MESSAGE);
    }
}


//예외처리는 잘 모르겠는데?