package com.epam.jposEchoService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jpos.iso.ISOMsg;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service {

    private final static int DEF_PORT = 8080;
    private final static int DEF_POOLSIZE = 5;
    private static final int DEF_HANDLER = 0;
    private static final Logger LOG = LogManager.getLogger(Service.class);


    private List<Handler> handlers = new ArrayList<>();
    private ExecutorService pool;
    private int port = 80;
    private int poolSize = 5;

    public Service() {
        this.port = DEF_PORT;
        this.poolSize = DEF_POOLSIZE;
    }

    public Service(int port, int poolSize) {
        this.port = port;
        this.poolSize = poolSize;
    }

    public void start() {
        ServerSocket serverSock = null;
        try {
            pool = Executors.newFixedThreadPool(this.poolSize);
            serverSock = new ServerSocket(this.port);
            LOG.info("Listening for connection on port " + serverSock.getLocalPort());
        } catch (IOException e) {
            LOG.error("Port" + this.port + "is blocked");
            System.exit(-1);
        }
        while (true) {
            try {
                Socket clientSock = serverSock.accept();
                pool.submit(new SocketProcessor(clientSock));
            } catch (IOException e) {
                LOG.error("Failed to establish connection on port" + this.port);
                LOG.error(e);
                System.exit(-1);
            }


        }

    }

    public void stop() {
        pool.shutdown();
    }

    public Handler choseHandler(ISOMsg rq) {
        return handlers.get(DEF_HANDLER);
    }


}
