import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;


public class MyRealm {
    @Test
    public void iniRealm(){
        //创建SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //将Security放入容器
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //Realm 放到
        IniRealm iniRealm = new IniRealm("classpath:user.ini");
        defaultSecurityManager.setRealm(iniRealm);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("FlightingYan","123456");

        subject.login(token);
        System.out.println(subject.isAuthenticated());

        subject.checkRole("admin");

        subject.checkPermissions("user:add");
    }
}
