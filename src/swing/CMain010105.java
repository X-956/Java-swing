/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;
import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.Timer;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

// 测试接口 - 所有作业测试类都要实现这个接口
interface HomeworkTest {
    String executeTest();
    
    // 默认方法：捕获控制台输出
    default String captureOutput(Runnable action) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        
        try {
            System.setOut(ps);
            action.run();
            ps.flush();
            return baos.toString();
        } finally {
            System.setOut(oldOut);
        }
    }
}

// 作业1测试类
class CHw1Test010105 implements HomeworkTest {
    @Override
    public String executeTest() {
        return captureOutput(() -> {
            CCalculate010105 cal = new CCalculate010105();
            double a = 2.1, b = 5.14, c = 1.19, d = 8.10;
            System.out.println("a + b = " + cal.add(a, b) + ", a=" + a + ", b=" + b);
            System.out.println("c * d = " + cal.mul(c, d) + ", c=" + c + ", d=" + d);
            System.out.println("b - d = " + cal.minus(b, d) + ", b=" + b + ", d=" + d);
            System.out.println("a / c = " + cal.div(a, c) + ", a=" + a + ", c=" + c);
        });
    }
}

// 作业2测试类
class CHw2Test010105 implements HomeworkTest {
    @Override
    public String executeTest() {
        return captureOutput(() -> {
            CCalucArray010105 calculator = new CCalucArray010105();
            calculator.Number = 50;
            calculator.genArray();
            double total = calculator.total();
            double average = calculator.average();
            
            System.out.println("执行结果:");
            System.out.println("随机生成的50个数字的总和: " + total);
            System.out.println("平均值: " + average);
        });
    }
}

// 作业3测试类
class CHw3Test010105 implements HomeworkTest {
    @Override
    public String executeTest() {
        return captureOutput(() -> {
            CElephant010105 elephant = new CElephant010105();
            elephant.setWeight(5000); // 5吨重的大象
            
            CBingxiang010105 fridge = new CBingxiang010105();
            
            if (fridge.open()) {
                System.out.println("冰箱已经打开");
                
                if (elephant.getWeight() < 4000) { // 假设冰箱承重4吨
                    System.out.println("大象已经装进冰箱");
                } else {
                    System.out.println("大象装不进冰箱");
                }
                
                if (fridge.close()) {
                    System.out.println("冰箱已经关闭");
                }
            }
        });
    }
}

/// 作业4测试类
class CHw4Test010105 implements HomeworkTest {
    @Override
    public String executeTest() {
        return captureOutput(() -> {
            System.out.println("===== 立方体体积测试 =====");
            
            CCube010105 cube1 = new CCube010105(1, 2, 3);
            System.out.println("立方体1 (1x2x3)");
            System.out.println("体积1-1：" + cube1.getVolume());
            System.out.println("体积1-2：" + cube1.v);
            
            CCube010105 cube2 = new CCube010105();
            System.out.println("\n立方体2 (默认5x5x6)");
            System.out.println("体积2-1：" + cube2.getVolume());
            
            cube2.setabc(3, 4, 5);
            System.out.println("\n修改立方体2尺寸为3x4x5");
            System.out.println("体积2-2：" + cube2.v);
            
            System.out.println("\n===== 边界值测试 =====");
            CCube010105 cube3 = new CCube010105(0, 2, 3);
            System.out.println("立方体3 (0x2x3) 体积：" + cube3.getVolume());
            
            CCube010105 cube4 = new CCube010105(10, 20, 30);
            System.out.println("立方体4 (10x20x30) 体积：" + cube4.getVolume());
        });
    }
}

// 作业5测试类
class CHw5Test010105 implements HomeworkTest {
    @Override
    public String executeTest() {
        return captureOutput(() -> {
            // 创建不同类型的汽车
            CCar010105 tesla = new CElecCar010105("Tesla Model 3", 30.9f, 60);
            CCar010105 bmw = new CFuelCar010105("BMW 330i", 42.5f, 2.0f);
            CCar010105 toyota = new CHybridCar010105("Toyota Prius", 28.8f, 60);
            
            // 1. 重写演示
            System.out.println("=== 重写行为演示 ===");
            tesla.actionBrake();
            bmw.actionBrake();
            toyota.actionBrake();
            System.out.println();
            
            tesla.qidong();
            bmw.qidong();
            toyota.qidong();
            System.out.println();
            
            // 2. 上转型演示
            System.out.println("=== 上转型演示 ===");
            CCar010105[] cars = {
                new CElecCar010105("BYD Han", 29.9f, 76),
                new CFuelCar010105("Audi A4", 39.8f, 2.0f),
                new CHybridCar010105("Honda Accord Hybrid", 26.8f, 70)
            };
            
            // 3. 多态演示
            for (CCar010105 car : cars) {
                car.showInfo();
                car.qidong();
                car.actionBrake();
                System.out.println();
            }
            
            System.out.println("=== 类型检查和转换演示 ===");
            for (CCar010105 car : cars) {
                if (car instanceof CElecCar010105) {
                    ((CElecCar010105) car).charge();
                } else if (car instanceof CFuelCar010105) {
                    ((CFuelCar010105) car).refuel();
                } else if (car instanceof CHybridCar010105) {
                    ((CHybridCar010105) car).switchMode();
                }
            }
        });
    }
}

// 作业6测试类
class CHw6Test010105 implements HomeworkTest {
    @Override
    public String executeTest() {
        return captureOutput(() -> {
            System.out.println("=== 使用接口类型引用对象 ===");
            Sound cat = new CCat010105();
            cat.makeSound("喵喵~");
            
            Sound dog = new CDog010105();
            dog.makeSound("汪汪！");
            
            // 使用Lambda表达式实现接口
            System.out.println("\n=== 使用Lambda表达式实现接口 ===");
            Sound duck = (sound) -> System.out.println("鸭子叫: " + sound);
            duck.makeSound("嘎嘎嘎...");
            
            // 使用抽象类方法
            System.out.println("\n=== 使用抽象类方法 ===");
            CAnimal010105 animal = new CCat010105();
            animal.shout();
            animal = new CDog010105();
            animal.shout();
            animal = new CDuck010105();
            animal.shout();
        });
    }
}

// 作业7测试类
class CHw7Test010105 implements HomeworkTest {
    @Override
    public String executeTest() {
        return captureOutput(() -> {
            // 创建农场并管理
            CFarm010105 happyFarm = new CFarm010105("快乐农场");
            happyFarm.manageFarm();
        });
    }
}

// 作业8测试类
class CHw8Test010105 implements HomeworkTest {
    @Override
    public String executeTest() {
        return captureOutput(() -> {
            System.out.println("能点到这个按钮，看到这行文本，说明本作业已经在执行了。");
        });
    }
}

// 作业9测试类
class CHw9Test010105 implements HomeworkTest {
    @Override
    public String executeTest() {
        return captureOutput(() -> {
            System.out.println("=== 日志功能测试 ===");
            System.out.println("日志功能已在主界面实现");
        });
    }
}


abstract class HomeworkPanel extends JPanel {
    protected final String title;
    protected final String content;
    protected JButton executeButton;
    protected JTextArea resultArea;
    
    // 公共的测试方法
    protected String runTest(HomeworkTest test) {
        return test.executeTest();
    }
     
    // 添加公共的捕获输出方法
    protected String captureOutput(Runnable action) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        try {
            // 重定向输出
            System.setOut(ps);
            
            // 执行动作
            action.run();
            
            // 刷新并获取输出
            ps.flush();
            return baos.toString();
        } finally {
            // 恢复原始输出
            System.setOut(oldOut);
        }
    }
    
    public HomeworkPanel(String title, String content) {
        this.title = title;
        this.content = content;
        initComponents();
    }
    
    protected void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(220, 240, 255));
        setPreferredSize(new Dimension(950, 540));
        
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
        titleLabel.setForeground(new Color(50, 100, 150));
        
        JTextArea contentArea = new JTextArea(content);
        contentArea.setFont(new Font("宋体", Font.PLAIN, 16));
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        
        // 添加执行按钮和结果区域
        executeButton = new JButton("执行任务");
        resultArea = new JTextArea(10, 80);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setFont(new Font("宋体", Font.PLAIN, 14));
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        executeButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        executeButton.setBackground(new Color(45, 130, 222));
        executeButton.setForeground(Color.PINK);
        
        bottomPanel.add(executeButton, BorderLayout.NORTH);
        bottomPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);
        
        add(titleLabel, BorderLayout.NORTH);
        add(new JScrollPane(contentArea), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    // 抽象方法 - 子类实现具体任务逻辑,作业10进行复制粘贴
//    protected abstract void executeTask();
}

class Hw1Panel extends HomeworkPanel {
    public Hw1Panel() {
        super("作业1: 计算器实现", 
            "这是一个计算器应用的实现作业。\n功能：\n- 基本四则运算\nclass CCalculate010105 {\n" +
            "    double add(double a, double b){\n" +
            "        return a+b;\n" +
            "    }\n" +
            "    double mul(double a, double b){\n" +
            "        return a*b;\n" +
            "    }\n" +
            "    double div(double a, double b){\n" +
            "        return a/b;\n" +
            "    }\n" +
            "    double minus(double a, double b){\n" +
            "        return a-b;\n" +
            "    }\n" +
            "}");
        // 添加执行按钮的监听器
        executeButton.addActionListener(e -> {
            // 使用测试类执行任务
            resultArea.setText(runTest(new CHw1Test010105()));
        });
    }
}

class Hw2Panel extends HomeworkPanel {
    public Hw2Panel() {
        super("作业2: 数组统计", 
            "随机生成100个浮点数并计算总和与平均值\n\n" +
            "class CCalucArray010105 {\n" +
            "    int Number;\n" +
            "    double[] data;\n" +
            "    \n" +
            "    void genArray() {\n" +
            "        data = new double[Number];\n" +
            "        Random random = new Random();\n" +
            "        for (int i = 0; i < data.length; i++) {\n" +
            "            data[i] = 100 * random.nextDouble();\n" +
            "        }\n" +
            "    }\n" +
            "    \n" +
            "    double total() {\n" +
            "        double sum = 0;\n" +
            "        for (double value : data) {\n" +
            "            sum += value;\n" +
            "        }\n" +
            "        return sum;\n" +
            "    }\n" +
            "    \n" +
            "    double average() {\n" +
            "        return total() / Number;\n" +
            "    }\n" +
            "}");
        // 添加执行按钮的监听器
        executeButton.addActionListener(e -> {
            // 使用测试类执行任务
            resultArea.setText(runTest(new CHw2Test010105()));
        });
    }  
}

class Hw3Panel extends HomeworkPanel {
    public Hw3Panel() {
        super("作业3:大象装进冰箱",
        "class CElephant010105 {\n" +
        "    private int weight;\n" +
        "    void setWeight(int weight){\n" +
        "        this.weight=weight;\n" +
        "    }\n" +
        "    int getWeight(){\n" +
        "        return weight;\n" +
        "    }\n" +
        "}\n" +
        "//修改把大象装进冰箱的行为，\n" +
        "//增加判断冰箱是否打开、大象是否装进冰箱、冰箱是否关闭三个判断,最终设计一个可以编译的类\n" +
        "//方法体里执行具体过程用system.out.println()表示\n" +
        "class CBingxiang010105 {\n" +
        "    float volum=1000;\n" +
        "    boolean open(){\n" +
        "        return true;\n" +
        "    }\n" +
        "    void ifopen( boolean op){\n" +
        "        if(op){\n" +
        "            System.out.println(\"冰箱已经打开\");\n" +
        "        }else {\n" +
        "            System.out.println(\"冰箱还没有打开\");\n" +
        "        }\n" +
        "    }\n" +
        "    void in(){\n" +
        "        System.out.println(\"大象已经装进冰箱\");\n" +
        "    }\n" +
        "    void cantin(){\n" +
        "        System.out.println(\"大象装不进冰箱\");\n" +
        "    }\n" +
        "    boolean close(){\n" +
        "        return true;\n" +
        "    }\n" +
        "    void ifclose( boolean cl){\n" +
        "        if(cl){\n" +
        "            System.out.println(\"冰箱已经关闭\");\n" +
        "        }else {\n" +
        "            System.out.println(\"冰箱还没有关闭\");\n" +
        "        }\n" +
        "    }\n" +
        "}");
        executeButton.addActionListener(e -> {
            // 使用测试类执行任务
            resultArea.setText(runTest(new CHw3Test010105()));
        });
    }
}

class Hw4Panel extends HomeworkPanel {
    public Hw4Panel() {
        super("作业4: 立方体体积计算", 
            "计算立方体的体积\n\n" +
            "class CCube010105 {\n" +
            "    float a, b, c, v;\n" +
            "    \n" +
            "    CCube010105(float a, float b, float c) {\n" +
            "        this.a = a;\n" +
            "        this.b = b;\n" +
            "        this.c = c;\n" +
            "        v = this.getVolume();\n" +
            "    }\n" +
            "    \n" +
            "    CCube010105() {\n" +
            "        setabc(5, 5, 6);\n" +
            "    }\n" +
            "    \n" +
            "    void setabc(float a, float b, float c) {\n" +
            "        this.a = a;\n" +
            "        this.b = b;\n" +
            "        this.c = c;\n" +
            "        this.v = this.getVolume();\n" +
            "    }\n" +
            "    \n" +
            "    float getVolume() {\n" +
            "        return a * b * c;\n" +
            "    }\n" +
            "}");
        executeButton.addActionListener(e -> {
            // 使用测试类执行任务
            resultArea.setText(runTest(new CHw4Test010105()));
        });
    }
}

class Hw5Panel extends HomeworkPanel {
    public Hw5Panel() {
        super("作业5: 汽车类", 
            "// 抽象汽车类 - 定义汽车的共性和抽象行为\n" +
            "abstract class CCar010105 {\n" +
            "    protected String name;\n" +
            "    protected float price;\n" +
            "    \n" +
            "    public CCar010105(String name, float price) {\n" +
            "        this.name = name;\n" +
            "        this.price = price;\n" +
            "    }\n" +
            "    \n" +
            "    // 抽象方法 - 刹车行为\n" +
            "    abstract void actionBrake();\n" +
            "    \n" +
            "    // 抽象方法 - 启动行为\n" +
            "    abstract void qidong();\n" +
            "    \n" +
            "    // 公共方法 - 显示车辆信息\n" +
            "    public void showInfo() {\n" +
            "        System.out.println(\"车辆型号: \" + name + \", 价格: \" + price + \"万元\");\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "// 电动车子类\n" +
            "class CElecCar010105 extends CCar010105 {\n" +
            "    private int batteryCapacity; // 电池容量 (kWh)\n" +
            "    \n" +
            "    public CElecCar010105(String name, float price, int batteryCapacity) {\n" +
            "        super(name, price);\n" +
            "        this.batteryCapacity = batteryCapacity;\n" +
            "    }\n" +
            "    \n" +
            "    // 重写刹车方法 - 体现再生制动特性\n" +
            "    @Override\n" +
            "    public void actionBrake() {\n" +
            "        System.out.println(\"电动车[\" + name + \"]使用再生制动系统刹车，回收能量中...\");\n" +
            "    }\n" +
            "    \n" +
            "    // 重写启动方法 - 体现电动特性\n" +
            "    @Override\n" +
            "    public void qidong() {\n" +
            "        System.out.println(\"电动车[\" + name + \"]无声启动，0-100km/h加速仅需4.5秒!\");\n" +
            "    }\n" +
            "    \n" +
            "    // 电动车特有方法\n" +
            "    public void charge() {\n" +
            "        System.out.println(\"电动车[\" + name + \"]正在充电，电池容量: \" + batteryCapacity + \"kWh\");\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "// 燃油车子类\n" +
            "class CFuelCar010105 extends CCar010105 {\n" +
            "    private float engineDisplacement; // 发动机排量 (L)\n" +
            "    \n" +
            "    public CFuelCar010105(String name, float price, float engineDisplacement) {\n" +
            "        super(name, price);\n" +
            "        this.engineDisplacement = engineDisplacement;\n" +
            "    }\n" +
            "    \n" +
            "    // 重写刹车方法 - 体现传统制动特性\n" +
            "    @Override\n" +
            "    public void actionBrake() {\n" +
            "        System.out.println(\"燃油车[\" + name + \"]使用传统液压制动系统刹车\");\n" +
            "    }\n" +
            "    \n" +
            "    // 重写启动方法 - 体现燃油车特性\n" +
            "    @Override\n" +
            "    public void qidong() {\n" +
            "        System.out.println(\"燃油车[\" + name + \"]引擎启动，发出澎湃声浪!\");\n" +
            "    }\n" +
            "    \n" +
            "    // 燃油车特有方法\n" +
            "    public void refuel() {\n" +
            "        System.out.println(\"燃油车[\" + name + \"]正在加油，发动机排量: \" + engineDisplacement + \"L\");\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "// 混合动力车子类 - 体现多态和继承\n" +
            "class CHybridCar010105 extends CCar010105 {\n" +
            "    private int electricRange; // 纯电续航 (km)\n" +
            "    \n" +
            "    public CHybridCar010105(String name, float price, int electricRange) {\n" +
            "        super(name, price);\n" +
            "        this.electricRange = electricRange;\n" +
            "    }\n" +
            "    \n" +
            "    // 重写刹车方法 - 混合动力车特性\n" +
            "    @Override\n" +
            "    public void actionBrake() {\n" +
            "        System.out.println(\"混合动力车[\" + name + \"]使用复合制动系统，结合再生制动和传统制动\");\n" +
            "    }\n" +
            "    \n" +
            "    // 重写启动方法 - 混合动力车特性\n" +
            "    @Override\n" +
            "    public void qidong() {\n" +
            "        System.out.println(\"混合动力车[\" + name + \"]静默电动启动，纯电续航可达\" + electricRange + \"km\");\n" +
            "    }    \n" +
            "    // 混合动力车特有方法\n" +
            "    public void switchMode() {\n" +
            "        System.out.println(\"混合动力车[\" + name + \"]正在切换驱动模式\");\n" +
            "    }\n" +
            "}");
         executeButton.addActionListener(e -> {
            // 使用测试类执行任务
            resultArea.setText(runTest(new CHw5Test010105()));
        });
    }
}


class Hw6Panel extends HomeworkPanel {
    public Hw6Panel() {
        super("作业6: 抽象类，接口，Lambda表达式", 
            "// 抽象类定义公共行为\n" +
            "abstract class Animal {\n" +
            "    abstract void shout(); // 抽象方法：动物特有的叫声\n" +
            "}\n" +
            "// 接口定义公共行为\n" +
            "interface Sound {\n" +
            "    void makeSound(String sound); // 接口方法：发出指定声音\n" +
            "}\n" +
            "// 猫\n" +
            "class Cat extends Animal implements Sound {\n" +
            "    @Override\n" +
            "    public void makeSound(String sound) {\n" +
            "        System.out.println(\"猫叫: \" + sound);\n" +
            "    }\n" +
            "    @Override\n" +
            "    void shout() {\n" +
            "        System.out.println(\"猫在叫: 喵~\");\n" +
            "    }\n" +
            "}\n" +
            "// 狗\n" +
            "class Dog extends Animal implements Sound {\n" +
            "    @Override\n" +
            "    public void makeSound(String sound) {\n" +
            "        System.out.println(\"狗吠: \" + sound);\n" +
            "    }\n" +
            "    @Override\n" +
            "    void shout() {\n" +
            "        System.out.println(\"狗在叫: 汪！\");\n" +
            "    }\n" +
            "}\n" +
            "// 鸭子\n" +
            "class Duck extends Animal implements Sound {\n" +
            "    @Override\n" +
            "    public void makeSound(String sound) {\n" +
            "        System.out.println(\"鸭子在叫: \" + sound);\n" +
            "    }\n" +
            "    @Override\n" +
            "    void shout() {\n" +
            "        System.out.println(\"鸭子在叫: 嘎~\");\n" +
            "    }\n" +
            "}");
        executeButton.addActionListener(e -> {
            // 使用测试类执行任务
            resultArea.setText(runTest(new CHw6Test010105()));
        });
    }
}

class Hw7Panel extends HomeworkPanel {
    public Hw7Panel() {
        super("作业7: 内部类和异常类", 
            "//7.设计一种场景，具体用内部类，匿名类的两种方式和异常处理来设计这个大类，场景自定义。\n" +
            "// 自定义异常类\n" +
            "class CAnimalAgeException010105 extends Exception {\n" +
            "    public CAnimalAgeException010105(String message) {\n" +
            "        super(message);\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "class CAnimalHungerException010105 extends Exception {\n" +
            "    public CAnimalHungerException010105(String message) {\n" +
            "        super(message);\n" +
            "    }\n" +
            "}\n" +
            "// 动物行为接口\n" +
            "interface CAnimalAction010105 {\n" +
            "    void eat() throws CAnimalHungerException010105;\n" +
            "    void makeSound();\n" +
            "    void sleep();\n" +
            "}\n" +
            "// 农场主类\n" +
            "class CFarm010105 {\n" +
            "    private String farmName;\n" +
            "    \n" +
            "    public CFarm010105(String farmName) {\n" +
            "        this.farmName = farmName;\n" +
            "        System.out.println(\"=== 欢迎来到\" + farmName + \"农场 ===\");\n" +
            "    }\n" +
            "    \n" +
            "    // 内部类：通用动物\n" +
            "    public class CAnimal010105 implements CAnimalAction010105 {\n" +
            "        protected String name;\n" +
            "        protected int age;\n" +
            "        protected int hungerLevel; // 0-10, 10表示非常饥饿\n" +
            "        \n" +
            "        public CAnimal010105(String name, int age) throws CAnimalAgeException010105 {\n" +
            "            if (age < 0) {\n" +
            "                throw new CAnimalAgeException010105(\"动物年龄不能为负数: \" + name);\n" +
            "            }\n" +
            "            this.name = name;\n" +
            "            this.age = age;\n" +
            "            this.hungerLevel = 8; // 初始饥饿值\n" +
            "            System.out.println(name + \"加入了农场，年龄: \" + age + \"岁\");\n" +
            "        }\n" +
            "        \n" +
            "        @Override\n" +
            "        public void eat() throws CAnimalHungerException010105 {\n" +
            "            if (hungerLevel <= 2) {\n" +
            "                throw new CAnimalHungerException010105(name + \"已经吃饱了，不能再吃了！\");\n" +
            "            }\n" +
            "            hungerLevel -= 2;\n" +
            "            System.out.println(name + \"正在吃东西... 饥饿值: \" + hungerLevel);\n" +
            "        }\n" +
            "        \n" +
            "        @Override\n" +
            "        public void makeSound() {\n" +
            "            System.out.println(name + \"发出了声音\");\n" +
            "        }\n" +
            "        \n" +
            "        @Override\n" +
            "        public void sleep() {\n" +
            "            hungerLevel++;\n" +
            "            System.out.println(name + \"正在睡觉... Zzz\");\n" +
            "        }\n" +
            "        \n" +
            "        public void displayInfo() {\n" +
            "            System.out.println(\"[\" + name + \"] 年龄: \" + age + \"岁, 饥饿值: \" + hungerLevel);\n" +
            "        }\n" +
            "    }\n" +
            "    \n" +
            "    // 管理农场动物\n" +
            "    public void manageFarm() {\n" +
            "        try {\n" +
            "            // 创建内部类动物实例\n" +
            "            CFarm010105.CAnimal010105 cow = new CFarm010105.CAnimal010105(\"奶牛\", 3);\n" +
            "            CFarm010105.CAnimal010105 chicken = new CFarm010105.CAnimal010105(\"鸡\", 1);            \n" +
            "            // 使用匿名类创建特殊动物\n" +
            "            CAnimalAction010105 specialAnimal = new CAnimalAction010105() {\n" +
            "                private String name = \"魔法羊\";\n" +
            "                private int hungerLevel = 5;                \n" +
            "                @Override\n" +
            "                public void eat() throws CAnimalHungerException010105 {\n" +
            "                    if (hungerLevel <= 1) {\n" +
            "                        throw new CAnimalHungerException010105(name + \"正在施展魔法，不能吃东西！\");\n" +
            "                    }\n" +
            "                    hungerLevel -= 1;\n" +
            "                    System.out.println(name + \"正在吃魔法草... 饥饿值: \" + hungerLevel);\n" +
            "                }               \n" +
            "                @Override\n" +
            "                public void makeSound() {\n" +
            "                    System.out.println(name + \"发出七彩光芒\");\n" +
            "                }               \n" +
            "                @Override\n" +
            "                public void sleep() {\n" +
            "                    hungerLevel++;\n" +
            "                    System.out.println(name + \"在云朵上睡觉\");\n" +
            "                }\n" +
            "            };                       \n" +
            "            // 管理动物\n" +
            "            System.out.println(\"\\n===== 日常照料开始 =====\");           \n" +
            "            // 照料奶牛\n" +
            "            cow.eat();\n" +
            "            cow.makeSound();\n" +
            "            cow.sleep();           \n" +
            "            // 照料鸡\n" +
            "            chicken.eat();\n" +
            "            chicken.makeSound();\n" +
            "            chicken.eat(); // 再吃一次           \n" +
            "            // 照料特殊动物（匿名类）\n" +
            "            specialAnimal.eat();\n" +
            "            specialAnimal.makeSound();\n" +
            "            specialAnimal.sleep();            \n" +
            "            // 测试异常情况\n" +
            "            System.out.println(\"\\n===== 测试异常情况 =====\");\n" +
            "            try {\n" +
            "                // 尝试创建无效年龄的动物\n" +
            "                CFarm010105.CAnimal010105 invalidAnimal = new CFarm010105.CAnimal010105(\"幽灵动物\", -2);\n" +
            "            } catch (CAnimalAgeException010105 e) {\n" +
            "                System.out.println(\"错误: \" + e.getMessage());\n" +
            "            }\n" +
            "            \n" +
            "            try {\n" +
            "                // 让已经吃饱的动物再吃\n" +
            "                System.out.println(\"\\n尝试过度喂食:\");\n" +
            "                cow.eat();\n" +
            "                cow.eat();\n" +
            "                cow.eat();\n" +
            "                cow.eat(); // 这会抛出异常\n" +
            "            } catch (CAnimalHungerException010105 e) {\n" +
            "                System.out.println(\"错误: \" + e.getMessage());\n" +
            "            }\n" +
            "            \n" +
            "        } catch (CAnimalHungerException010105 | CAnimalAgeException010105 e) {\n" +
            "            System.out.println(\"农场管理错误: \" + e.getMessage());\n" +
            "        }\n" +
            "    }");
        executeButton.addActionListener(e -> {
            // 使用测试类执行任务
            resultArea.setText(runTest(new CHw7Test010105()));
        });
    }
}

class Hw8Panel extends HomeworkPanel {
    public Hw8Panel() {
        super("作业8: GUI设计", 
            "设计思路（整个代码有几千行，此处不再展示）\n" +
            "1.​​左侧导航栏 (side_pane)​\n" +
            "彩虹色渐变按钮（Hw1-Hw10使用不同颜色）\n" +
            "功能实现​​：\n" +
            "// 按钮点击事件处理\n" +
            "jButton1.addActionListener(e -> {\n" +
            "    cardLayout.show(contentPanel, \"Hw1\"); // 切换内容面板\n" +
            "    writeLog(getCurrentTime() + \": 点击了 Hw1 按钮\"); // 记录日志\n" +
            "});" +
            "点击按钮切换右侧内容面板\n" +
            "同时记录操作日志\n" +
            "2.​​顶部信息栏 (jPanel2)​\n" +
            "居中显示lky的GitHub开源地址\n" +
            "支持窗口拖动功能\n" +
            "3.​​右侧内容区域 (contentPanel)​\n" +
            "采用​​卡片布局(CardLayout)​​实现多面板切换：\n" +
            "// 卡片布局初始化\n" +
            "cardLayout = new CardLayout();\n" +
            "contentPanel = new JPanel(cardLayout);\n" +
            "\n" +
            "// 添加各个作业面板\n" +
            "contentPanel.add(new Hw1Panel(), \"Hw1\");" +
            "// 切换面板示例\n" +
            "cardLayout.show(contentPanel, \"Hw5\"); // 显示作业5面板\n" +
            "作业面板设计 (HomeworkPanel)\n" +
            "abstract class HomeworkPanel extends JPanel {\n" +
            "    protected JButton executeButton;\n" +
            "    protected JTextArea resultArea;\n" +
            "    \n" +
            "    public HomeworkPanel(String title, String content) {\n" +
            "        // 统一布局设置\n" +
            "        setLayout(new BorderLayout());\n" +
            "        setBackground(new Color(220, 240, 255));\n" +
            "        \n" +
            "        // 标题区域\n" +
            "        JLabel titleLabel = new JLabel(title, JLabel.CENTER);\n" +
            "        titleLabel.setFont(new Font(\"微软雅黑\", Font.BOLD, 24));\n" +
            "        \n" +
            "        // 内容区域\n" +
            "        JTextArea contentArea = new JTextArea(content);\n" +
            "        contentArea.setEditable(false);\n" +
            "        \n" +
            "        // 执行按钮和结果区域\n" +
            "        executeButton = new JButton(\"执行任务\");\n" +
            "        resultArea = new JTextArea(10, 80);\n" +
            "        \n" +
            "        // 组装组件\n" +
            "        add(titleLabel, BorderLayout.NORTH);\n" +
            "        add(new JScrollPane(contentArea), BorderLayout.CENTER);\n" +
            "        add(createBottomPanel(), BorderLayout.SOUTH);\n" +
            "    }\n" +
            "    \n" +
            "    // 子类实现具体任务逻辑\n" +
            "    protected abstract void executeTask();\n" +
            "}\n" +
            "}");
        executeButton.addActionListener(e -> {
            // 使用测试类执行任务
            resultArea.setText(runTest(new CHw8Test010105()));
        });
    }
}

class Hw9Panel extends HomeworkPanel {
    public Hw9Panel() {
        super("作业9: 日志文件", 
            "存储 (buttonlog.log 文件)\n" +
            "代码实现\n" +
            "   writeLog()：写入日志\n" +
            "   loadLogFromFile()：加载历史日志\n" +
            "   时间获取：getCurrentTime()\n" +
            "日志记录\n" +
            "   按钮点击事件\n" +
            "   任务执行终端显示日志\n" +
            "清空日志");
        executeButton.addActionListener(e -> {
            // 使用测试类执行任务
            resultArea.setText(runTest(new CHw9Test010105()));
        });
    }
}

public class CMain010105 extends javax.swing.JFrame {
    
    private void writeLogToFile(String message) {
    try (FileWriter fw = new FileWriter(LOG_FILE, true);
         BufferedWriter bw = new BufferedWriter(fw);
         PrintWriter out = new PrintWriter(bw)) {
        out.println(message);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
    class Hw10Panel extends JPanel {
        private final JTextArea taskLogArea = new JTextArea();

        public Hw10Panel() {
            setLayout(new BorderLayout());
            setBackground(new Color(220, 240, 255));

            JLabel title = new JLabel("作业10: 线程池任务执行", JLabel.CENTER);
            title.setFont(new Font("微软雅黑", Font.BOLD, 24));
            title.setForeground(new Color(50, 100, 150));

            taskLogArea.setEditable(false);
            taskLogArea.setFont(new Font("宋体", Font.PLAIN, 14));
            JScrollPane scrollPane = new JScrollPane(taskLogArea);

            JButton executeButton = new JButton("执行所有作业");
            executeButton.setFont(new Font("微软雅黑", Font.BOLD, 16));
            executeButton.setBackground(new Color(70, 130, 180));
            executeButton.setForeground(Color.PINK);

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(executeButton);

            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.add(scrollPane, BorderLayout.CENTER);
            contentPanel.add(buttonPanel, BorderLayout.SOUTH);

            add(title, BorderLayout.NORTH);
            add(contentPanel, BorderLayout.CENTER);

            executeButton.addActionListener(e -> executeAllTasks());
        }

        private void executeAllTasks() {
            taskLogArea.setText(""); // 清空日志
            taskLogArea.append("开始执行所有作业任务...\n");

            // 为每个作业创建一个Runnable任务
            executor.submit(createTask("Hw1", new CHw1Test010105()));
            executor.submit(createTask("Hw2", new CHw2Test010105()));
            executor.submit(createTask("Hw3", new CHw3Test010105()));
            executor.submit(createTask("Hw4", new CHw4Test010105()));
            executor.submit(createTask("Hw5", new CHw5Test010105()));
            executor.submit(createTask("Hw6", new CHw6Test010105()));
            executor.submit(createTask("Hw7", new CHw7Test010105()));
            executor.submit(createTask("Hw8", new CHw8Test010105()));
            executor.submit(createTask("Hw9", new CHw9Test010105()));

            taskLogArea.append("[" + CMain010105.getCurrentTime() + "] 已提交9个作业任务到线程池...\n");
        }
        // 创建Runnable任务的方法
        private Runnable createTask(String taskName, HomeworkTest test) {
        return () -> {
            // 在任务执行前记录开始时间
            String startTime = "[" + CMain010105.getCurrentTime() + "]";
            taskLogArea.append(startTime + " " + taskName + " 开始执行...\n");

            // 将开始信息写入日志文件
            CMain010105.this.writeLogToFile(startTime + " " + taskName + " 开始执行...");

            // 执行测试
            String result = test.executeTest();

            // 将结果写入日志文件
            CMain010105.this.writeLogToFile(taskName + " 执行结果:");
            String[] resultLines = result.split("\n");
            for (String line : resultLines) {
                CMain010105.this.writeLogToFile(line);
            }
            CMain010105.this.writeLogToFile("");

            // 更新UI显示结果
            SwingUtilities.invokeLater(() -> {
                taskLogArea.append(startTime + " " + taskName + " 执行完成\n");
                taskLogArea.append("执行结果:\n" + result + "\n");
            });
        };
    }
}

    
    abstract class HomeworkTask implements Runnable {
        protected final String taskName;
        protected final String description;
        protected final JTextArea logArea;

        public HomeworkTask(String taskName, String description, JTextArea logArea) {
            this.taskName = taskName;
            this.description = description;
            this.logArea = logArea;
        }

        public void log(String message) {
          String fullMessage = "[" + getCurrentTime() + "] " + message;
          SwingUtilities.invokeLater(() -> {
              logArea.append(fullMessage + "\n");
              logArea.setCaretPosition(logArea.getDocument().getLength());
          });
          // 使用主类的 writeLog 方法写入日志
          CMain010105.this.writeLog(fullMessage);
        }
        @Override
        public void run() {
            log(taskName + " 开始执行: " + description);
            long startTime = System.currentTimeMillis();

            try {
                executeTask();
                long duration = System.currentTimeMillis() - startTime;
                log(taskName + " 执行成功! (耗时: " + duration + "ms)");
            } catch (Exception ex) {
                log(taskName + " 执行失败: " + ex.getMessage());
            }
        }

        protected abstract void executeTask() throws Exception;
    }
    
    class ClearLogTask extends HomeworkTask {
        public ClearLogTask(JTextArea logArea) {
            super("ClearLog", "清空日志", logArea);
        }

        @Override
        protected void executeTask() throws Exception {
            // 清空日志显示区域
            SwingUtilities.invokeLater(() -> {
                logTextArea.setText("");
                logTextArea.append("[" + getCurrentTime() + "] 日志已清空\n");
            });

            // 清空日志文件
            try (PrintWriter writer = new PrintWriter(LOG_FILE)) {
                writer.print(""); // 清空文件内容
            } catch (FileNotFoundException ex) {
                log("清空日志文件失败: " + ex.getMessage());
            }
        }
    }
   
  
 
    private static ExecutorService executor;
    private static final String LOG_FILE = "buttonlog.log";
    private javax.swing.JTextArea logTextArea;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    public CMain010105() {
        initComponents();  
        initLogArea();
        updateTime();
        updateDate();
        
        
        
        // 初始化线程池（固定9个线程）
        executor = Executors.newFixedThreadPool(9);
        
        Timer timer=new Timer(1000,e->{
            updateTime();
        });
        timer.start();
        AutorInfo();
        ButtonLog();
        
        initCardLayout();
    }
    
    // 初始化卡片布局
    private void initCardLayout() {
        // 创建卡片布局容器
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // 创建各个面板
        JPanel overviewPanel = createOverviewPanel();
        
        HomeworkPanel hw1Panel = new Hw1Panel();
        HomeworkPanel hw2Panel = new Hw2Panel();
        HomeworkPanel hw3Panel = new Hw3Panel();
        HomeworkPanel hw4Panel = new Hw4Panel();
        HomeworkPanel hw5Panel = new Hw5Panel();
        HomeworkPanel hw6Panel = new Hw6Panel();
        HomeworkPanel hw7Panel = new Hw7Panel();
        HomeworkPanel hw8Panel = new Hw8Panel();    
        HomeworkPanel hw9Panel = new Hw9Panel();
        Hw10Panel hw10Panel = new Hw10Panel();

        // 添加面板到卡片容器
        contentPanel.add(overviewPanel, "Overview");
        contentPanel.add(hw1Panel, "Hw1");
        contentPanel.add(hw2Panel, "Hw2");
        contentPanel.add(hw3Panel, "Hw3");
        contentPanel.add(hw4Panel, "Hw4");
        contentPanel.add(hw5Panel, "Hw5");
        contentPanel.add(hw6Panel, "Hw6");
        contentPanel.add(hw7Panel ,"Hw7");
        contentPanel.add(hw8Panel, "Hw8");
        contentPanel.add(hw9Panel ,"Hw9");
        contentPanel.add(hw10Panel, "Hw10");

        // 移除原有的jPanel3和jPanel6
        getContentPane().remove(jPanel3);
        getContentPane().remove(jPanel6);

        // 添加到主界面，使用AbsoluteConstraints
        getContentPane().add(contentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 950, 540));

        // 默认显示概览界面
        cardLayout.show(contentPanel, "Overview");

        // 刷新界面
        revalidate();
        repaint();
    }

    // 创建Overview面板（重用原有组件）
    private JPanel createOverviewPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // 直接重用原有的jPanel3和jPanel6
        panel.add(jPanel3, BorderLayout.WEST);
        panel.add(jPanel6, BorderLayout.CENTER);

        return panel;
    }   
    
    
    private void initLogArea() {
        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        logTextArea.setLineWrap(true);
        logTextArea.setWrapStyleWord(true);
        loadLogFromFile();      
        jScrollPane2.setViewportView(logTextArea);
    }
    
    private void loadLogFromFile() {
        try {
            Path path = Paths.get(LOG_FILE);
            if (Files.exists(path)) {
                String content = new String(Files.readAllBytes(path));
                logTextArea.setText(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(fmt);
    }
    
    private void writeLog(String message) {
        String logMessage=message+"\n";
        logTextArea.append(logMessage);
        logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
        
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void ButtonLog() {
        ActionListener logListener = e -> {
            JButton source = (JButton) e.getSource();
            String buttonText = source.getText();
            
            // 特殊处理"Overview"按钮
            if("Overview".equals(buttonText)) {
                cardLayout.show(contentPanel, "Overview");
            } 
             // 其他作业按钮
            else if(buttonText.startsWith("Hw")) {
                cardLayout.show(contentPanel, buttonText);
            }
            
            writeLog(getCurrentTime() + ": 点击了 " + buttonText + " 按钮");
            // 切换到对应作业面板
            cardLayout.show(contentPanel, buttonText);
        };
        
        jButton1.addActionListener(logListener);
        jButton2.addActionListener(logListener);
        jButton3.addActionListener(logListener);
        jButton4.addActionListener(logListener);
        jButton5.addActionListener(logListener);
        jButton6.addActionListener(logListener);
        jButton7.addActionListener(logListener);
        jButton8.addActionListener(logListener);
        jButton9.addActionListener(logListener);
        jButton10.addActionListener(logListener);
        jButton11.addActionListener(logListener);
    }
    
    private void AutorInfo(){
        jComboBox1.removeAllItems();
        jComboBox1.addItem("Check Author Info");
        jComboBox1.addItem("Class");
        jComboBox1.addItem("Student ID");
        
        jTextField2.setText("Waiting to choose.");
        
        jComboBox1.addActionListener(e -> {
            String selected = (String)jComboBox1.getSelectedItem();
            if ("Class".equals(selected)) {
                jTextField2.setText("IOT2301");
            } else if ("Student ID".equals(selected)) {
                jTextField2.setText("1034230105");
            } else {
                jTextField2.setText("Waiting to choose.");
            }
        });
        jTextField2.setEditable(false);
        
    }
    
    //16 static
    private void updateTime(){
        //get now Time 
        LocalTime currentTime =LocalTime.now();
        DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("HH:mm:ss");
        jLabel16.setText(currentTime.format(timeFormatter));
    }
    
    private void updateDate() {
    // 获取当前日期并格式化为yyyy-MM-dd
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(dateFormatter);

        // 分割年月日
        String[] parts = formattedDate.split("-");
        jLabel13.setText("Year"+parts[0]+":");  // 年份
        jLabel6.setText(parts[1] + "-" + parts[2]);  // 月-日
    }   
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        side_pane = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        button1 = new java.awt.Button();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setLocationByPlatform(true);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        side_pane.setBackground(new java.awt.Color(255, 204, 204));
        side_pane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(102, 255, 102));
        jButton1.setForeground(new java.awt.Color(51, 0, 51));
        jButton1.setText("Hw4");
        jButton1.setToolTipText("");
        jButton1.setBorder(null);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        side_pane.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 120, 40));

        jButton2.setBackground(new java.awt.Color(0, 255, 204));
        jButton2.setText("Hw5");
        jButton2.setBorder(null);
        side_pane.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 120, 40));

        jButton3.setBackground(new java.awt.Color(0, 255, 255));
        jButton3.setText("Hw6");
        jButton3.setBorder(null);
        side_pane.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 120, 40));

        jButton4.setBackground(new java.awt.Color(0, 102, 255));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Hw7");
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        side_pane.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 120, 40));

        jButton5.setBackground(new java.awt.Color(255, 102, 255));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Hw10");
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        side_pane.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 120, 40));

        jButton6.setBackground(new java.awt.Color(255, 153, 153));
        jButton6.setText("Overview");
        jButton6.setBorder(null);
        side_pane.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 50));

        jButton7.setBackground(new java.awt.Color(204, 255, 102));
        jButton7.setText("Hw3");
        jButton7.setBorder(null);
        side_pane.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 120, 40));

        jButton8.setBackground(new java.awt.Color(255, 255, 102));
        jButton8.setText("Hw2");
        jButton8.setBorder(null);
        side_pane.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 120, 40));

        jButton9.setBackground(new java.awt.Color(51, 51, 255));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Hw8");
        jButton9.setBorder(null);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        side_pane.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 120, 40));

        jButton10.setBackground(new java.awt.Color(153, 51, 255));
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Hw9");
        jButton10.setBorder(null);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        side_pane.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 120, 40));

        jButton11.setBackground(new java.awt.Color(255, 204, 102));
        jButton11.setText("Hw1");
        jButton11.setBorder(null);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        side_pane.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 120, 40));

        getContentPane().add(side_pane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 590));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        jTextField1.setBackground(new java.awt.Color(123, 156, 225));
        jTextField1.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("作业已上传至：https://github.com/X-956，求Star^^");
        jTextField1.setBorder(null);
        jTextField1.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField1.setPreferredSize(new java.awt.Dimension(2, 20));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(280, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(275, 275, 275))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 950, 50));

        jPanel3.setBackground(new java.awt.Color(71, 120, 197));

        jPanel4.setBackground(new java.awt.Color(120, 168, 252));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(84, 127, 206));

        jComboBox1.setBackground(new java.awt.Color(204, 204, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Check Author Info" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField2.setBackground(new java.awt.Color(84, 128, 205));
        jTextField2.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 24)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 204));
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("jTextField2");
        jTextField2.setBorder(null);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 85, 300, 130));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 204));
        jLabel12.setText("Author : Liang Kaiyan");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 200, 60));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("06-02");
        jLabel6.setToolTipText("1");

        button1.setBackground(new java.awt.Color(71, 120, 197));
        button1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setLabel("Clean logs");
        button1.setName("清空日志"); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Year2025");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("21:11:16");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        jLabel6.getAccessibleContext().setAccessibleName("null");
        jLabel16.getAccessibleContext().setAccessibleName("");

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 300, 540));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(242, 247, 247));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jTable1.setBackground(new java.awt.Color(255, 255, 204));
        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Hw1", "Calculator"},
                {"Hw2", "Sum & Average"},
                {"Hw3", "Elephant Bingxiang"},
                {"Hw4", "Cube V"},
                {"Hw5", "Car"},
                {"Hw6", "Abstract & Lambda"},
                {"Hw7", "Inner & Anonymous Class"},
                {"Hw8", "GUI"},
                {"Hw9", "Log"},
                {"Hw*10*", "Thread"}
            },
            new String [] {
                "Homework ID", "Content"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTable1.setDoubleBuffered(true);
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setRowHeight(22);
        jScrollPane1.setViewportView(jTable1);

        jScrollPane2.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 650, 540));

        jMenuBar1.setBorder(null);
        jMenuBar1.setOpaque(true);

        jMenu3.setBackground(new java.awt.Color(51, 0, 51));
        jMenu3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenu3.setText("点击这里退出");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenu3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu3MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    int xx,xy;
    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        // TODO add your handling code here:
        //drag this pane
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        // TODO add your handling code here:
        
        //source to drag
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-xx,y-xy);
    }//GEN-LAST:event_jPanel2MouseDragged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        // 创建并执行清空日志任务
        executor.submit(new ClearLogTask(logTextArea));
    }//GEN-LAST:event_button1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jMenu3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3MouseExited

    private void jMenu3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MousePressed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenu3MousePressed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CMain010105.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CMain010105.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CMain010105.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CMain010105.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CMain010105().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPanel side_pane;
    // End of variables declaration//GEN-END:variables
}


//Hw1的类
//1.设计一个类，计算两个数的加减乘除。
class CCalculate010105 {
    double add(double a, double b){
        return a+b;
    }
    double mul(double a, double b){
        return a*b;
    }
    double div(double a, double b){
        return a/b;
    }
    double minus(double a, double b){
        return a-b;
    }
}

//Hw2的类
//2.设计一个类，定义一个50个元素的浮点数组，随机生成50个数, 赋给数组，计算总成绩和平均成绩。
//计算总成绩
//计算平均成绩
class CCalucArray010105 {
    int Number;
    double[] data;
    void genArray(){
        data = new double[Number];
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = 100*random.nextDouble();
        }
    }
    double total(){
        double sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
        }
        return sum;
    }
    //直接调用total()
    double average(){
        return total()/Number;
    }
}



//Hw3的类
class CElephant010105 {
    private int weight;
    void setWeight(int weight){
        this.weight=weight;
    }
    int getWeight(){
        return weight;
    }
}
//修改把大象装进冰箱的行为，
//增加判断冰箱是否打开、大象是否装进冰箱、冰箱是否关闭三个判断,最终设计一个可以编译的类
//方法体里执行具体过程用system.out.println()表示
class CBingxiang010105 {
    float volum=1000;
    boolean open(){
        return true;
    }
    void ifopen( boolean op){
        if(op){
            System.out.println("冰箱已经打开");
        }else {
            System.out.println("冰箱还没有打开");
        }
    }
    void in(){
        System.out.println("大象已经装进冰箱");
    }
    void cantin(){
        System.out.println("大象装不进冰箱");
    }
    boolean close(){
        return true;
    }
    void ifclose( boolean cl){
        if(cl){
            System.out.println("冰箱已经关闭");
        }else {
            System.out.println("冰箱还没有关闭");
        }
    }
}

//Hw4
//作业4.长方体类，求长方体的体积，写两个构造方法，一个不带参数，一个包含三个参数（长宽高）
//创建对象且输出体积
class CCube010105 {
    float a,b,c,v;
    CCube010105(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
        v=this.getVolume();
    }
    CCube010105() {
        //需要初始化
       setabc(5,5,6);//这句没用了，测试用
    }
    void setabc(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.v=this.getVolume();
    }
    float getVolume() {
        return a*b*c;
    }
}

//hw5
// 抽象汽车类 - 定义汽车的共性和抽象行为
abstract class CCar010105 {
    protected String name;
    protected float price;
    
    public CCar010105(String name, float price) {
        this.name = name;
        this.price = price;
    }
    
    // 抽象方法 - 刹车行为
    abstract void actionBrake();
    
    // 抽象方法 - 启动行为
    abstract void qidong();
    
    // 公共方法 - 显示车辆信息
    public void showInfo() {
        System.out.println("车辆型号: " + name + ", 价格: " + price + "万元");
    }
}

// 电动车子类
class CElecCar010105 extends CCar010105 {
    private int batteryCapacity; // 电池容量 (kWh)
    
    public CElecCar010105(String name, float price, int batteryCapacity) {
        super(name, price);
        this.batteryCapacity = batteryCapacity;
    }
    
    // 重写刹车方法 - 体现再生制动特性
    @Override
    public void actionBrake() {
        System.out.println("电动车[" + name + "]使用再生制动系统刹车，回收能量中...");
    }
    
    // 重写启动方法 - 体现电动特性
    @Override
    public void qidong() {
        System.out.println("电动车[" + name + "]无声启动，0-100km/h加速仅需4.5秒!");
    }
    
    // 电动车特有方法
    public void charge() {
        System.out.println("电动车[" + name + "]正在充电，电池容量: " + batteryCapacity + "kWh");
    }
}

// 燃油车子类
class CFuelCar010105 extends CCar010105 {
    private float engineDisplacement; // 发动机排量 (L)
    
    public CFuelCar010105(String name, float price, float engineDisplacement) {
        super(name, price);
        this.engineDisplacement = engineDisplacement;
    }
    
    // 重写刹车方法 - 体现传统制动特性
    @Override
    public void actionBrake() {
        System.out.println("燃油车[" + name + "]使用传统液压制动系统刹车");
    }
    
    // 重写启动方法 - 体现燃油车特性
    @Override
    public void qidong() {
        System.out.println("燃油车[" + name + "]引擎启动，发出澎湃声浪!");
    }
    
    // 燃油车特有方法
    public void refuel() {
        System.out.println("燃油车[" + name + "]正在加油，发动机排量: " + engineDisplacement + "L");
    }
}
// 混合动力车子类 - 体现多态和继承
class CHybridCar010105 extends CCar010105 {
    private int electricRange; // 纯电续航 (km)
    
    public CHybridCar010105(String name, float price, int electricRange) {
        super(name, price);
        this.electricRange = electricRange;
    }
    
    // 重写刹车方法 - 混合动力车特性
    @Override
    public void actionBrake() {
        System.out.println("混合动力车[" + name + "]使用复合制动系统，结合再生制动和传统制动");
    }
    
    // 重写启动方法 - 混合动力车特性
    @Override
    public void qidong() {
        System.out.println("混合动力车[" + name + "]静默电动启动，纯电续航可达" + electricRange + "km");
    }    
    // 混合动力车特有方法
    public void switchMode() {
        System.out.println("混合动力车[" + name + "]正在切换驱动模式");
    }
}

//Hw6，任意列举三个不同对象（不包括已讲过了），抽象出公共的一个行为，
//  分别按照抽象类和接口的方法，完成三个对象对应的三个类的设计，实现
//  对应的抽象方法和接口，实现三个对象对公共行为的调用，并用Lambda
//  表达式对接口变量赋值，实现接口方法。
// 抽象类定义公共行为
abstract class CAnimal010105 {
    abstract void shout(); // 抽象方法：动物特有的叫声
}
// 接口定义公共行为
interface Sound {
    void makeSound(String sound); // 接口方法：发出指定声音
}
// 猫
class CCat010105 extends CAnimal010105 implements Sound {
    @Override
    public void makeSound(String sound) {
        System.out.println("猫叫: " + sound);
    }
    @Override
    void shout() {
        System.out.println("猫在叫: 喵~");
    }
}
// 狗
class CDog010105 extends CAnimal010105 implements Sound {
    @Override
    public void makeSound(String sound) {
        System.out.println("狗吠: " + sound);
    }
    @Override
    void shout() {
        System.out.println("狗在叫: 汪！");
    }
}
// 鸭子
class CDuck010105 extends CAnimal010105 implements Sound {
    @Override
    public void makeSound(String sound) {
        System.out.println("鸭子在叫: " + sound);
    }
    @Override
    void shout() {
        System.out.println("鸭子在叫: 嘎~");
    }
}

//Hw7
//7.设计一种场景，具体用内部类，匿名类的两种方式和异常处理来设计这个大类，场景自定义。
// 自定义异常类
class CAnimalAgeException010105 extends Exception {
    public CAnimalAgeException010105(String message) {
        super(message);
    }
}
class CAnimalHungerException010105 extends Exception {
    public CAnimalHungerException010105(String message) {
        super(message);
    }
}
// 动物行为接口
interface CAnimalAction010105 {
    void eat() throws CAnimalHungerException010105;
    void makeSound();
    void sleep();
}
// 农场主类
class CFarm010105 {
    private String farmName;   
    public CFarm010105(String farmName) {
        this.farmName = farmName;
        System.out.println("=== 欢迎来到" + farmName + "农场 ===");
    }   
    // 内部类：通用动物
    public class CAnimal010105 implements CAnimalAction010105 {
        protected String name;
        protected int age;
        protected int hungerLevel; // 0-10, 10表示非常饥饿       
        public CAnimal010105(String name, int age) throws CAnimalAgeException010105 {
            if (age < 0) {
                throw new CAnimalAgeException010105("动物年龄不能为负数: " + name);
            }
            this.name = name;
            this.age = age;
            this.hungerLevel = 8; // 初始饥饿值
            System.out.println(name + "加入了农场，年龄: " + age + "岁");
        }       
        @Override
        public void eat() throws CAnimalHungerException010105 {
            if (hungerLevel <= 2) {
                throw new CAnimalHungerException010105(name + "已经吃饱了，不能再吃了！");
            }
            hungerLevel -= 2;
            System.out.println(name + "正在吃东西... 饥饿值: " + hungerLevel);
        }
        
        @Override
        public void makeSound() {
            System.out.println(name + "发出了声音");
        }       
        @Override
        public void sleep() {
            hungerLevel++;
            System.out.println(name + "正在睡觉... Zzz");
        }       
        public void displayInfo() {
            System.out.println("[" + name + "] 年龄: " + age + "岁, 饥饿值: " + hungerLevel);
        }
    }    
    // 管理农场动物
    public void manageFarm() {
        try {
            // 创建内部类动物实例
            CFarm010105.CAnimal010105 cow = new CFarm010105.CAnimal010105("奶牛", 3);
            CFarm010105.CAnimal010105 chicken = new CFarm010105.CAnimal010105("鸡", 1);            
            // 使用匿名类创建特殊动物
            CAnimalAction010105 specialAnimal = new CAnimalAction010105() {
                private String name = "魔法羊";
                private int hungerLevel = 5;                
                @Override
                public void eat() throws CAnimalHungerException010105 {
                    if (hungerLevel <= 1) {
                        throw new CAnimalHungerException010105(name + "正在施展魔法，不能吃东西！");
                    }
                    hungerLevel -= 1;
                    System.out.println(name + "正在吃魔法草... 饥饿值: " + hungerLevel);
                }               
                @Override
                public void makeSound() {
                    System.out.println(name + "发出七彩光芒");
                }               
                @Override
                public void sleep() {
                    hungerLevel++;
                    System.out.println(name + "在云朵上睡觉️");
                }
            };                       
            // 管理动物
            System.out.println("\n===== 日常照料开始 =====");           
            // 照料奶牛
            cow.eat();
            cow.makeSound();
            cow.sleep();           
            // 照料鸡
            chicken.eat();
            chicken.makeSound();
            chicken.eat(); // 再吃一次           
            // 照料特殊动物（匿名类）
            specialAnimal.eat();
            specialAnimal.makeSound();
            specialAnimal.sleep();            
            // 测试异常情况
            System.out.println("\n===== 测试异常情况 =====");
            try {
                // 尝试创建无效年龄的动物
                CFarm010105.CAnimal010105 invalidAnimal = new CFarm010105.CAnimal010105("幽灵动物", -2);
            } catch (CAnimalAgeException010105 e) {
                System.out.println("错误: " + e.getMessage());
            }
            
            try {
                // 让已经吃饱的动物再吃
                System.out.println("\n尝试过度喂食:");
                cow.eat();
                cow.eat();
                cow.eat();
                cow.eat(); // 这会抛出异常
            } catch (CAnimalHungerException010105 e) {
                System.out.println("错误: " + e.getMessage());
            }
            
        } catch (CAnimalHungerException010105 | CAnimalAgeException010105 e) {
            System.out.println("农场管理错误: " + e.getMessage());
        }
    }
}