import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class TestCustomRealm {

    @Test
    public void customRealm(){
      //创建SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        SecurityUtils.setSecurityManager(defaultSecurityManager);

        CustomRealm customRealm = new CustomRealm();

        defaultSecurityManager.setRealm(customRealm);

        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        customRealm.setCredentialsMatcher(matcher);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("FlightingYan","123456");
        subject.login(token);

        /*System.out.println(subject.isAuthenticated());

        subject.checkPermission("add");

        subject.checkRole("admin");*/

    }

}
