## 前言
最近一直在看《重构--改善既有的代码设计》这本书，书中第三章的第一小节，在讲解Duplicated Code（重复代码）的时候，阐述了一种引起重复代码的情况。
> 重复代码的另一种常见情况就是“两个互为兄弟的子类内含有相同的表达式”。要避免这种情况需要对两个类使用Extract Method(110)，然后使用Pull Up Method(332)，将他推入超类内。如果代码只是类似，并非完全相同，那么就得应用Extract Method(110)，将其相似和差异的部分割开，单独作为一个函数。然后你可以发现可能可以运用Form Template Method(345)，获得一个Template Method设计模式。

因为对模版方法模式理解不是很深，所以看了一些文章总结一下。

## 定义与特点
**定义：** 
一个操作中的算法骨架，而将算法的一些步骤延迟到子类中，使得子类可以不改变该算法结构的情况下重定义该算法的某些特定步骤。它是一种类行为型模式。本质就是固定算法骨架，让一些步骤延迟加载到子类中。

**优点：** 
1.它将一些公有不变的部分进行封装到超类中进行实现，可变的部分可由子类继承实现，便于扩展。
2.提取了公有方法到超类，便于复用。
3.部分方法由子类实现，可以通过扩展到方式增加相应的功能，符合开闭原则。

**缺点：** 
1.每个实现都需要定义一个子类，会导致类爆炸，系统更加庞大
2.超类中的抽象方法由子类实现，这是一种反向的控制结构，降低了代码的可读性。
***

## 角色与结构
### 主要角色
1.抽象类：负责给一个算法的骨架，它由一个模版方法和若干个基本方法组成。
① 模版方法：定义算法骨架，按某种顺序调用其中的方法。
② 基本方法：是整个算法一个步骤，包含了(抽象方法，具体方法，钩子方法)。
> 抽象方法：在抽象类中申明，由具体子类实现。
  具体方法：在抽象类中已经实现，在具体子类中可以继承或重写它。
  钩子方法：在抽象类中已经实现，包括用于判断的逻辑方法和需要子类重写的空方法两种。

2.具体子类：实现抽象类中所定义的抽象方法和钩子方法，他们是一个顶级逻辑和一个组成步骤。

### 结构
![模版方法模式类图](https://molzhao-pic.oss-cn-beijing.aliyuncs.com/2020-08-12/%E6%A8%A1%E7%89%88%E6%96%B9%E6%B3%95%E6%A8%A1%E5%BC%8F.png)
***

## 学生考试案例
### 抽象类
```java
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
```

### 具体类
```java
package com.zyj.template;

public class StudentA extends Examination {
    @Override
    public void answer() {
        System.out.println("学生A的回答是：ABDC");
    }
}
```

```java
package com.zyj.template;

public class StudentB extends Examination {
    @Override
    public void answer() {
        System.out.println("学生B的回答是：ADCA");
    }
}
```

### 运行结果
```java
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
```
```
拿到试卷
学生A的回答是：ABDC
完成试卷
拿到试卷
学生B的回答是：ADCA
完成试卷
```
这里的例子可能举的并不是很好，模版方法模式算是一个比较容易理解的设计模式，该设计模式主要用于定义模版，比如我们写论文的时候，第一步肯定要写开题报告，有一系列的流程，我们可以抽取公有的流程把他定义在模版抽象类中。然后把一些使用者不同的，让使用者自己去实现就好。
***

> 博主是97年的，在杭州从事Java，如果有小伙伴，想要一起交流学习的，欢迎添加博主微信。

![weChat](https://molzhao-pic.oss-cn-beijing.aliyuncs.com/Common/WeChat.png)