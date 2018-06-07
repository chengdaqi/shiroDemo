import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;


//授权
public class Shiro_Authorizer {
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser(){
        //设置角色
        simpleAccountRealm.addAccount("FlightingYan","123456","admin","user");
    }

    @Test
    public void shiroAuthorizer(){

        //创建SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        //容器中放置defaultSecurityManager
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //将Realm放在SecurityManager中
        defaultSecurityManager.setRealm(simpleAccountRealm);
        //用户名密码token
        UsernamePasswordToken token = new UsernamePasswordToken("FlightingYan","123456");
        //从SecurityUtils中获取主体subject
        Subject subject = SecurityUtils.getSubject();
        //主体提交认证请求——>SecurityManager会去realm拿对应的数据对比认证
        subject.login(token);
        //判断是否 为认证用户
        System.out.println("isAuthenticated:"+subject.isAuthenticated());

        //检查角色
        subject.checkRoles("admin","user");

    }
}
