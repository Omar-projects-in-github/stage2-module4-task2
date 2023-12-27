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
            realConnection = null;
        }
    }

    public void close() {
        if (!isClosed) {
            ConnectionPool.getInstance().releaseConnection(this);
            isClosed = true;
        }
    }

    public boolean isClosed() {
        return isClosed || (realConnection != null && realConnection.isClosed());
    }
}
