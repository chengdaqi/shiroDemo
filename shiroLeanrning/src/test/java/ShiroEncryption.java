import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**shiro的加密算法**/
public class ShiroEncryption {
    @Test
    public void testEncryption(){
        //创建SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        SecurityUtils.setSecurityManager(defaultSecurityManager);

        CustomRealm customRealm = new CustomRealm();

        defaultSecurityManager.setRealm(customRealm);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("FlightingYan","123456");
        subject.login(token);

        System.out.println(subject.isAuthenticated());

        subject.checkPermission("add");

        subject.checkRole("admin");

    }





}
