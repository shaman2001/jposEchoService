package com.epam.jposEchoService;

import org.jpos.iso.ISOMsg;

import java.net.Socket;


public class SocketProcessor implements Runnable {

    private Socket socket;
    private ISOMsg rqst;
    private ISOMsg resp;

    public SocketProcessor (Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {

    }
}
