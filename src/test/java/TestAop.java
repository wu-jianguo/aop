import com.xiaolao.aop.AopApplication;
import com.xiaolao.aop.constant.CompanyEnum;
import com.xiaolao.aop.service.ITestService;
import com.xiaolao.aop.service.impl.TestServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: WuJianGuo
 * @Date: 2020/9/18 16:33
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AopApplication.class)
public class TestAop {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name = "TestServiceImpl")
    private ITestService testService;



    @Test
    public void testAop(){
        // 级别由低到高 trace<debug<info<warn<error
        logger.trace("这是一个trace日志...");
        logger.debug("这是一个debug日志...");
        // SpringBoot默认是info级别，只会输出info及以上级别的日志
        logger.info("这是一个info日志...");
        logger.warn("这是一个warn日志...");
        logger.error("这是一个error日志...");
        String str = "https://www.cnblogs.com/steveshao/";
        logger.info("======欢迎访问无脚鸟的博客：{}\n", str);

        System.out.println(testService.say());
    }

    @Test
    public void testEnum(){
        System.out.println(CompanyEnum.SF.getCode());
        System.out.println(CompanyEnum.SF.getName());
        System.out.println(CompanyEnum.getCodeByName("韵达"));
        System.out.println(CompanyEnum.getNameByCode(1004));
        System.out.println(CompanyEnum.getNameByCode(1004));
        System.out.println(CompanyEnum.getNameByCode(1004));
    }
}
