package muyi.base.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Yang Fan
 * @date: 2019/10/31
 * @desc: java中的原子类学习 atomic包下的类基本都是用Unsafe实现的
 */
public class AtomicClassLearn {

    /**
     * 关于 AtomicInteger AtomicBoolean AtomicLong 很常见
     * 看下 {@link AtomicInteger#lazySet(int)}} 不保证结果立刻对其他线程可见 是一个很low的优化选择 了解即可
     * {@link AtomicInteger#set(int)} 可以保证结果对其他线程可见 (volatile写 立刻刷新缓冲区
     */

}
