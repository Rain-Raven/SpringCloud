public class la {
    static class Demo1{
        int func(Demo1 demo1){return 1;}
        int func(Demo4 demo4){return 4;}
    }
    static class Demo2 extends Demo1{
        int func(Demo2 demo2){return 2;}
        int func(Demo3 demo3){return 3;}
    }
    static class Demo3 extends Demo2{
    }
    static class Demo4 extends Demo2{
    }
    public static void main(String[] args) {
        Demo1 demo1=new Demo1();
        Demo2 demo2=new Demo2();
        Demo3 demo3=new Demo3();
        Demo4 demo4=new Demo4();
        System.out.println(demo1.func(demo2)+demo1.func(demo3)+demo1.func(demo4)+demo2.func(demo1)+demo2.func(demo3)+demo2.func(demo4));

    }
}
