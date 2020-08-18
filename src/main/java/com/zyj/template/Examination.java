package com.zyj.template;

public abstract class Examination {
    public void exam() {
        GetTheTestPaper();
        answer();
        FinishTheTestPaper();
    }

    public void GetTheTestPaper() {
        System.out.println("拿到试卷");
    }

    public abstract void answer();

    public void FinishTheTestPaper() {
        System.out.println("完成试卷");
    }
}
