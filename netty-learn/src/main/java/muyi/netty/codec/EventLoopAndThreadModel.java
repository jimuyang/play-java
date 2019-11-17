package muyi.netty.codec;

/**
 * @author: Yang Fan
 * @date: 2019/11/3
 * @desc: 内容来自netty实战
 */
public class EventLoopAndThreadModel {

    // 原始的new Thread()方式开启并发

    // 基本的线程池化模型 从线程池中获取可用的thread来执行任务


    /**
     * Netty
     * {@link io.netty.channel.EventLoop}
     */

    // 运行任务来处理连接的生命周期内的所有事件 -- 事件循环 EventLoop
    // EventLoop由一个final线程驱动 一个EventLoop可以服务多个channel (io多路复用)



}
