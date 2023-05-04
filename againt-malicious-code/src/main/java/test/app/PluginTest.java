package test.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;

import test.api.Plugin;

public class PluginTest {
    public static void pluginTest(String pathToJar) {
        try {
            File file = new File(pathToJar);
            URL url = file.toURI().toURL();
//            URLClassLoader cl = new URLClassLoader(new java.net.URL[]{url});
//            Class<?> clazz = cl.loadClass("test.plugin.MyPlugin");

            ClassLoader cl = PluginTest.class.getClassLoader();
            Class<?> clazz = cl.loadClass("test.plugin.MyPlugin");

            final Plugin plugin = (Plugin) clazz.newInstance();
            PluginThread thread = new PluginThread(new Runnable() {
                @Override
                public void run() {
                    plugin.go();
                }
            });
            thread.start();
            //thread.join();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        pluginTest("/Users/projects/againt-malicious-code/target/againt-malicious-code-1.0-SNAPSHOT.jar");

//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
        try {
            File file = new File("/Users/projects/againt-malicious-code/text.txt");
            if (file.isFile() && file.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String lineTxt = null;
                int num = 0;
                //
                //
                long time1 = System.currentTimeMillis();
                while ((lineTxt = br.readLine()) != null) {
                    System.out.println("------- MAIN " + lineTxt);
                    Thread.sleep(30);
                    num++;
                    System.out.println("----总共" + num + "条数据！");
                }
                //System.out.println("总共"+num+"条数据！");
                long time2 = System.currentTimeMillis();
                long time = time1 - time2;
                System.out.println("----共花费" + time + "秒");
                br.close();
            } else {
                System.out.println("----文件不存在!");
            }
        } catch (Exception e) {
            System.out.println("----文件读取错误!");
        }

    }
//        }

//        t.start();
//        t.join();
//    }
}