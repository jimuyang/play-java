package muyi.netty.rpc.server;

import muyi.netty.rpc.client.HelloService;

/**
 * @author: Jimu Yang
 * @date: 2018/11/25 1:06 PM
 * @descricption: want more.
 */
@RpcService(HelloService.class) // 因为实现类可能实现多个接口，这里指定远程接口为HelloService
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello! " + name;
    }
}
