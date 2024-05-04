package ru.itis.inf304.lab7;

import org.junit.Test;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;

import static org.junit.Assert.assertTrue;

public class Method3Test {
    @Test
    public void NullPointerExceptionTest(){
        boolean exceptionThrown = false;
        String fileName = null;
        try {
            Method3.isValidJSONFile(fileName);
        } catch (NullPointerException e) {
            exceptionThrown = true;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        Assert.assertTrue(exceptionThrown);
    }
    @Test
    public void FileNotFoundExceptionTest() throws Exception {
        boolean exceptionThrown = false;
        String fileName = "scobki";
        try {
            Method3.isValidJSONFile(fileName);
        } catch (FileNotFoundException e) {
            exceptionThrown = true;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void ExceptionTest() {
        boolean exceptionThrown = false;
        String fileName = "barcketsWithPunctMarks.json";
        try {
            Method3.isValidJSONFile(fileName);
        } catch (Exception e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
}
