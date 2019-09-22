package muyi.base.concurrent;

import muyi.base.thread.SimpleThreadPool;
import muyi.base.thread.ThreadPool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: Yang Fan
 * @date: 2019-09-11
 * @desc: 一个简单的web server
 */
public class SimpleHttpServer {

    /**
     * 处理HttpRequest的线程池
     */
    static ThreadPool<HttpRequestHandler> threadPool = new SimpleThreadPool<>(1);

    /**
     * 服务器根路径
     */
    static String basePath;

    static ServerSocket serverSocket;

    /**
     * 服务器监听端口
     */
    static int port = 8080;

    public static void setPort(int port) {
        if (port > 0) {
            SimpleHttpServer.port = port;
        }
    }

    public static void setBasePath(String basePath) {
        if (basePath != null && new File(basePath).exists() && new File(basePath).isDirectory()) {
            SimpleHttpServer.basePath = basePath;
        }
    }

    public static void start() throws Exception {
        serverSocket = new ServerSocket(port);
        Socket socket = null;

        while ((socket = serverSocket.accept()) != null) {
            // 接受一个客户端socket 生成一个新的httpRequestHandler 放进线程池
            threadPool.execute(new HttpRequestHandler(socket));
        }
        serverSocket.close();
    }

    public static void main(String[] args) throws Exception {
        setBasePath("/Users/muyi/life/github/play-java/base-whatever/src/main/resources");
        setPort(8080);
        start();
    }


    static class HttpRequestHandler implements Runnable {

        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null;
            InputStream in = null;

            try {
                reader = new BufferedReader((new InputStreamReader(socket.getInputStream())));
                String header = reader.readLine();
                // 由相对路径计算出绝对路径
                String filePath = basePath + header.split(" ")[1];
                System.out.println("filePath: " + filePath);
                out = new PrintWriter(socket.getOutputStream());
                in = new FileInputStream(filePath);
                br = new BufferedReader(new InputStreamReader(in));

                if (filePath.endsWith("jpg") || filePath.endsWith("ico")) {
                    // 如果请求资源的后缀为jpg或者ico 则读取资源并输出
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: image/jpeg");
//                    out.println("Content-Length: " + array.length);
                    out.println("");

                    byte[] bytes = new byte[in.available()];
                    in.read(bytes);
                    socket.getOutputStream().write(bytes);
                } else {
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: text/html; charset=UTF-8");
                    out.println("");
                    while ((line = br.readLine()) != null) {
                        out.println(line);
                    }
                }
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
                out.println("HTTP/1.1 500");
                out.println("");
                out.flush();
            } finally {
                close(br, in, reader, out, socket);
            }
        }

        /**
         * 批量关闭资源
         */
        private static void close(Closeable... closeables) {
            if (closeables != null) {
                for (Closeable closeable : closeables) {
                    try {
                        closeable.close();
                    } catch (Exception ignore) {
                    }
                }
            }
        }
    }

}

