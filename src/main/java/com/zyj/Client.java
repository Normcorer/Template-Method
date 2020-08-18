package com.zyj;

import com.zyj.template.Examination;
import com.zyj.template.StudentA;
import com.zyj.template.StudentB;

public class Client {
    public static void main(String[] args) {
        Examination studentA = new StudentA();
        Examination studentB = new StudentB();

        studentA.exam();
        studentB.exam();
    }
}
