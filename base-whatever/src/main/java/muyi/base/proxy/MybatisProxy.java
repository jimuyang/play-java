package muyi.base.proxy;

import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.binding.MapperProxyFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.ClassPathMapperScanner;
import org.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * @author: Yang Fan
 * @date: 2019/10/28
 * @desc: 一直好奇的：mybatis的代理类生成
 */
@SuppressWarnings("ALL")
public class MybatisProxy {

    /*
    spring的mapper扫描器
     <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.rice.mvc.dao" />
     </bean>
     */

    /**
     * spring生成mybatis mapper实例的过程
     * 主要在于扫描并注册beanDefinition
     * 注册beanDefinition的模式都是一样的 只是扫描和保存mappedStatement的方式不同
     * <p>
     * xml注册mapperScanner {@link MapperScannerConfigurer#postProcessBeanDefinitionRegistry(org.springframework.beans.factory.support.BeanDefinitionRegistry)}
     * 这个mapperScanner继承 {@link org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor} 可以帮忙注册更多的beanDefinition
     * <p>
     * 扫描beanDefinition：{@link ClassPathMapperScanner#doScan(java.lang.String...)}
     * <p>
     * 处理beanDefinition：{@link ClassPathMapperScanner#processBeanDefinitions(java.util.Set)}
     * 在扫描mapper的interface之后 关键是申明了FactoryBean的类型 这里是指 {@link org.mybatis.spring.mapper.MapperFactoryBean}
     * definition.setBeanClass(this.mapperFactoryBeanClass);
     * <p>
     * 例如 有dao interface: com.sample.dao.MyDao
     * mapperScanner扫描到这个beanDefinition后 就将之处理为beanClass为MapperFactoryBean<MyDao>的一个factoryBeanDefinition
     * 而FactoryBean是一类特殊的bean 它们自身不暴露对外使用 它们暴露出它们创建的Object
     * {@link org.mybatis.spring.mapper.MapperFactoryBean#getObject()}
     * 上面的核心代码在：{@link MapperProxyFactory#newInstance(org.apache.ibatis.session.SqlSession)}
     * 使用的是jdk的动态代理 InvocationHandler: {@link MapperProxy#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])}
     *
     * 上述使用到的SqlSession都是{@link SqlSessionTemplate} 它是单例的 线程安全的 也因此有的proxy可以"共享"一个SqlSessionTemplate
     * 当然是因为SqlSessionTemplate借用了它自己生成的: {@link SqlSessionTemplate#sqlSessionProxy} 来完成sqlSession的工作
     * 这个生成也是通过jdk动态代理生成 实际上每次通过sqlSessionProxy执行相关方法时 * 每次会由spring的事务管理和sqlSessionFactory来提供一个专用的sqlSession
     * {@link SqlSessionTemplate.SqlSessionInterceptor#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])}
     * 异常转化也是在这里处理掉了
     */
    public void learn() throws Exception {
        // 扫描到mapper interface之后
        /**
         * @see ClassPathMapperScanner#doScan(java.lang.String...)
         * @see org.mybatis.spring.mapper.ClassPathMapperScanner#processBeanDefinitions(java.util.Set)
         */
    }

}
