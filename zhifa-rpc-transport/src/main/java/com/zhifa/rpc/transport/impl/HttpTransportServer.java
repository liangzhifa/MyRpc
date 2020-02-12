package com.zhifa.rpc.transport.impl;

import com.zhifa.rpc.transport.RequestHandler;
import com.zhifa.rpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author lzf
 * @version 1.0
 * @date 2020-02-12 12:09
 */
@Slf4j
public class HttpTransportServer implements TransportServer {

    private RequestHandler requestHandler;

    private Server server;

    @Override
    public void init(int port, RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
        this.server = new Server(port);


        /**
         * 接受请求
         */
        ServletContextHandler ctx = new ServletContextHandler();
        ServletHolder holder = new ServletHolder(new RequestServlet());
        ctx.addServlet(holder, "/*");
        server.setHandler(ctx);


    }

    @Override
    public void start() {


        try {
            server.start();
            server.join();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    class RequestServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            log.info("client connect.....");

            InputStream in = req.getInputStream();
            OutputStream out = resp.getOutputStream();
            if (requestHandler != null) {
                requestHandler.onRequest(in, out);
            }
            out.flush();
        }
    }

}
