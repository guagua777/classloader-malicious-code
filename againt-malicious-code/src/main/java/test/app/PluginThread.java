package test.app;

class PluginThread extends Thread {
    PluginThread(Runnable target) {
        super(target);
    }

    @Override
    public void run() {
        SecurityManager old = System.getSecurityManager();
        PluginSecurityManager psm = new PluginSecurityManager();
        psm.setClassSource("/Users/projects/againt-malicious-code/target");
        System.setSecurityManager(psm);
        System.out.println("set sec begin ..........");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //psm.enableSandbox();
        super.run();
        //psm.disableSandbox();
        System.setSecurityManager(old);
        System.out.println("set sec end ..........");
        System.out.println("end");
    }
}