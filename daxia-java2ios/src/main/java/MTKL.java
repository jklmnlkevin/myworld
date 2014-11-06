import java.util.Random;


/**
 * 正方形100*100
 * 随便取一点，如果这两点在内切圆内，就计数
 * （怎么判断是在内切圆内？到圆心的距离不要大于50）
 */
public class MTKL {
    
    public static void main(String[] args) {
        int r = 1;
        int in = 0;
        int out = 0;
        
        int count = 10000000;
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            double x = random.nextDouble();
            double y = random.nextInt(r);
            double d = Math.sqrt(x*x + y*y);
            if (d <= r) {
                in ++;
            } else {
                out ++;
            }
        }
        
        System.out.println("in: " + in);
        System.out.println("out: " + out);
        System.out.println("in/count: " + (in)*1.0/count);
        System.out.println("area: " + (Math.PI * r * r)/(r*2)*(r*2));
        
        
    }
}
