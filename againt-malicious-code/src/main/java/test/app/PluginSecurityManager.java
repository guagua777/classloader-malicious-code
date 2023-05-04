package test.app;

import java.io.FilePermission;
import java.security.Permission;

public class PluginSecurityManager extends SecurityManager {

    private String _classSource;

    @Override
    public void checkPermission(Permission perm) {
        System.out.println("checkPermission 1 ");
        check(perm);
    }

    @Override
    public void checkPermission(Permission perm, Object context) {
        System.out.println("checkPermission 2 ");
        check(perm);
    }

    private void check(Permission perm) {
        if (_classSource == null) {
            // Not running plugin code
            return;
        }

        if (perm instanceof FilePermission) {
            // Is the request inside the class source?
            String path = perm.getName();
            boolean inClassSource = path.startsWith(_classSource);

            // Is the request for read-only access?
            boolean readOnly = "read".equals(perm.getActions());

            if (inClassSource && readOnly) {
                return;
            }
            throw new SecurityException("Permission denied: " + perm);
        }

//        if (perm instanceof RuntimePermission) {
//            if (perm.getName().equals("setSecurityManager")) {
//                return ;
//            }
//        }


       // throw new SecurityException("Permission denied: " + perm);
    }

    void setClassSource(String classSource) {
        _classSource = classSource;
    }
}











//    private boolean _sandboxed;
//
//    @Override
//    public void checkPermission(Permission perm) {
//        System.out.println("checkPermission 1 ");
//        check(perm);
//    }
//
//    @Override
//    public void checkPermission(Permission perm, Object context) {
//        System.out.println("checkPermission 2 ");
//        check(perm);
//    }
//
//    private void check(Permission perm) {
//        if (!_sandboxed) {
//            return;
//        }
//
//        // I *could* check FilePermission here, but why doesn't
//        // URLClassLoader handle it like it says it does?
//
//        throw new SecurityException("Permission denied");
//    }
//
//    void enableSandbox() {
//        System.out.println("enableSandbox");
//        _sandboxed = true;
//    }
//
//    void disableSandbox() {
//        System.out.println("disableSandbox");
//        _sandboxed = false;
//    }
//}