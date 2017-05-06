package com.epam.jposEchoService;

import org.jpos.iso.ISOMsg;

public interface IMsgProcessor {
    void process(ISOMsg rqst, ISOMsg resp);

}
