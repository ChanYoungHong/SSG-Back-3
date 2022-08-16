package com.spharosacademy.project.SSGBack.tempoptionlist;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OptionList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionListId;

    private String optionColor;
    private String optionSize;



}
