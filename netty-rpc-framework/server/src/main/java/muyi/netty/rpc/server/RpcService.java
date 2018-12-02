package muyi.netty.rpc.server;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: Jimu Yang
 * @date: 2018/11/25 1:03 PM
 * @descricption: want more.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component // 表明可被spring扫描
public @interface RpcService {

    Class<?> value();
}
