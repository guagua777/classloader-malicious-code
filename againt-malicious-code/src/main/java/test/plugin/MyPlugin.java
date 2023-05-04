package test.plugin;

import test.api.Plugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MyPlugin implements Plugin {
    @Override
    public void go() {
        //new AnotherClassInTheSamePlugin(); // ClassNotFoundException with a SecurityManager
        doSomethingDangerous(); // permitted without a SecurityManager
    }

    private void doSomethingDangerous() {
        // use your imagination
        System.out.println("go body ");
        try {
            File file = new File("/Users/projects/againt-malicious-code/text.txt");
            if(file.isFile() && file.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String lineTxt = null;
                int num =0;
                long time1 = System.currentTimeMillis();
                while ((lineTxt = br.readLine()) != null) {
                    System.out.println(lineTxt);
                    Thread.sleep(500);
                    num++;
                    System.out.println("总共"+num+"条数据！");
                }
                //System.out.println("总共"+num+"条数据！");
                long time2 = System.currentTimeMillis();
                long time = time1 - time2;
                System.out.println("共花费"+time+"秒");
                br.close();
            } else {
                System.out.println("文件不存在!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("文件读取错误!");
        }
    }
}