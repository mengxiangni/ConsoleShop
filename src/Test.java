import java.io.File;
import java.io.InputStream;
import java.util.Scanner;
public class Test {
    public static void main(String []args) throws ClassNotFoundException {
        int l = 0;
        while(l==0) {
            System.out.println("请输入用户名");
            Scanner in = new Scanner(System.in);
            String username = in.next();
            System.out.println("请输入密码");
            String password = in.next();
            /*File file=new File("C:\\Users\\lenovo\\IdeaProjects\\ConsoleShop\\src\\1.xlsx");**/
            InputStream kl = Class.forName("Test").getResourceAsStream("/User.xlsx");
            ReadeExcel readeExcel = new ReadeExcel();
            User user[] = readeExcel.readExcel(kl);

            for (int i = 0; i < user.length; i++) {
                if (user[i].getUsername().equals(username) && user[i].getPassword().equals(password)) {
                    l = 1;
                }
            }
            if (l == 1) {
                System.out.println("登录成功");
                ReadProductExcel readProductExcel=new ReadProductExcel();
                InputStream k2 = Class.forName("Test").getResourceAsStream("/Product.xlsx");
                Product[]products=readProductExcel.readProductExcel(k2);


            } else {
                System.out.println("登录失败");
            }
        }

    }
}
