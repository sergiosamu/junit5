package com.sergiosanchez.katas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.InvalidParameterException;

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
        assertEquals(6,target.add("//;1;2;3"));
    }     

    @DisplayName("Detect one negative")
    @Test
    public void detectNegative() {
        Exception exception = assertThrows(InvalidParameterException.class,
                () -> target.add("2,-1"));
        
        assertEquals("negatives not allowed -1",exception.getMessage());
    }     

    @DisplayName("Detect multiple negative")
    @Test
    public void detectMultipleNegative() {
        Exception exception = assertThrows(InvalidParameterException.class,
                () -> target.add("2,-1,4,-2"));
        
        assertEquals("negatives not allowed -1, -2",exception.getMessage());
    }         

    @DisplayName("Detect execution count")
    @Test
    public void getExecutionCount() {
        assertEquals(0, target.getCalledCount());
    }         

    @DisplayName("Ignore big numbers")
    @Test
    public void discardBigNumbers() {
        assertEquals(5, target.add("2,1001,3"));
    }    

    @DisplayName("Multi-char delimiter")
    @Test
    public void multicharDelimiter() {
        assertEquals(6, target.add("//[***]\n1***2***3"));
    }

    @DisplayName("Multiple single-char delimiter")
    @Test
    public void multipleSingleCharDelimiter() {
        assertEquals(6, target.add("//[*][%]\n1*2%3"));
    }

    @DisplayName("Multiple multi-char delimiter")
    @Test
    public void multipleMultiCharDelimiter() {
        assertEquals(6, target.add("//[**][%%]\n1**2%%3"));
    }    
    
}