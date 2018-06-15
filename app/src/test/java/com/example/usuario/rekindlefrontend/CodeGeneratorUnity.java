package com.example.usuario.rekindlefrontend;

import static org.junit.Assert.assertEquals;

import com.example.usuario.rekindlefrontend.utils.CodeGenerator;

import org.junit.Test;

import java.util.Random;

public class CodeGeneratorUnity {

    private CodeGenerator codeGenerator;

    @Test
    public void testGetCode () {
        int lenght = 5;
        String symbols = "abc";
        Random random = new Random();

        codeGenerator = new CodeGenerator(lenght, random, symbols);

        String res = codeGenerator.getCode();
        char resChar;
        boolean correct = true;
        for(int i = 0; i < res.length(); i++) {
            resChar = res.charAt(i);
            if(resChar != 'a' && resChar != 'b' && resChar != 'c') correct = false;
        }

        assertEquals("Lenght incorrect, must be 5", res.length(), lenght);
        assertEquals("Code incorrect, only can contain the characters: a, b or c", true, correct);
    }
}
