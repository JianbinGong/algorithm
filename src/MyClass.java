public class MyClass {
    public static int num;

    public static int getNum() {
        num = 3;
        if (true) {
            int num;
            num = 10;
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(getNum());
    }
}
