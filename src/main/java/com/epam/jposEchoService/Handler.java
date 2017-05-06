package com.epam.jposEchoService;

import org.jpos.iso.ISOMsg;

public class Handler {

    private IMsgProcessor processor;

    public Handler(IMsgProcessor proc) {
        this.processor = proc;
    }

    public void setProcessor(IMsgProcessor processor) {
        this.processor = processor;
    }

    public IMsgProcessor getProcessor() {
        return processor;
    }

    public void process(ISOMsg rqst, ISOMsg resp) {
        this.processor.process(rqst, resp);
    }
}
