import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

//认证
public class Shiro_Authenticated {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser(){
        //模拟从数据库中查找用户放在realm中
        simpleAccountRealm.addAccount("FlightingYan","123456");
    }

    @Test
    public void testAuthentication(){

        //1创建SecurityManager
        DefaultSecurityManager defultSecurityManager = new DefaultSecurityManager();

        //将SecurityManager放在容器中
        SecurityUtils.setSecurityManager(defultSecurityManager);

        //将Realm放在SecurityManager中
        defultSecurityManager.setRealm(simpleAccountRealm);

        //获取传来的subject
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("FlightingYan","123456");
        //主体提交认证请求——>SecurityManager会去realm拿对应的数据对比认证
        subject.login(usernamePasswordToken);

        System.out.println("isAuthenticated:"+subject.isAuthenticated());

        subject.logout();

        System.out.println("isAuthenticated:"+subject.isAuthenticated());

    }
}
