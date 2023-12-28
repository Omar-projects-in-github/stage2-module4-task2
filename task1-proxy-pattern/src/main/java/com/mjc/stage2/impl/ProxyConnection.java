package com.mjc.stage2.impl;


import com.mjc.stage2.Connection;

public class ProxyConnection implements Connection {
    private RealConnection realConnection;
    private boolean isClosed;

    public ProxyConnection(RealConnection realConnection) {
        this.realConnection = realConnection;
        this.isClosed = false;
    }

    public void reallyClose() {
        if (realConnection != null && !realConnection.isClosed()) {
            realConnection.close();
            isClosed = true;
        }
    }

    public void close() {
        this.isClosed = true;
        ConnectionPool.getInstance().releaseConnection(this);
    }

    public boolean isClosed() {
        return isClosed;
    }
}