package com.spharosacademy.project.SSGBack.address.exception;

public class AddressNotFoundException extends RuntimeException {

    public static final String MESSAGE = "주소가 존재하지 않습니다";

    public AddressNotFoundException(){
        super(MESSAGE);
    }
}
