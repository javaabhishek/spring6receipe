package com.asoft.spring6receipe.enumeration;

/***
 * Reason: Some one can add new enum value in between two enum value
 * Eg: Adding TO_HARD value in between KIND_OF_HARD and HARD
 * So If you use EnumType.ORDINAL then in database 0,1,2,3 values are stored
 * if you have existing data and someone add new enum like in example then all existing data get mess.
 * */
public enum Difficulty {
    EASY, MODERATE, KIND_OF_HARD, HARD
}
