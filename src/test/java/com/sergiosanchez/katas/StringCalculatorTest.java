package com.sergiosanchez.katas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sergiosanchez.katas.tdd.StringCalculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

    StringCalculator target = new StringCalculator();

    @DisplayName("Return 0 for empty String")
    @Test
    public void emptyString() {
        assertEquals(0,target.add(""));
    }

    @DisplayName("Return same number")
    @Test
    public void singleString() {
        assertEquals(4,target.add("4"));
    }   
    
    @DisplayName("Add two numbers")
    @Test
    public void addTwoNumbers() {
        assertEquals(7,target.add("4,3"));
    }      
    
    @DisplayName("Add multiple numbers")
    @Test
    public void addMultipleNumbers() {
        assertEquals(7,target.add("4,2,1"));
    }    

    @DisplayName("Add with multi lines")
    @Test
    public void handleMultiLines() {
        assertEquals(6,target.add("1\n2,3"));
    }     
    
    @DisplayName("Define delimiter")
    @Test
    public void dynamicDelimiter() {
        assertEquals(6,target.add("//;1;2,3"));
    }     
    
}