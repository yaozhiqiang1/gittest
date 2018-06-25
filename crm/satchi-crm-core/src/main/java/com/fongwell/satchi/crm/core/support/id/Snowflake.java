package com.fongwell.satchi.crm.core.support.id;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Random;

/**
 * Created by docker on 2/25/18.
 */

public class Snowflake {
    private static Logger log = LogManager.getLogger(Snowflake.class);
    private static final Snowflake INSTANCE = new Snowflake();
    private final long datacenterIdBits;
    private final long maxDatacenterId;
    private final long sequenceBits;
    private final long datacenterIdShift;
    private final long timestampLeftShift;
    private final long sequenceMask;
    private final long twepoch;
    private long datacenterId;
    private volatile long lastTimestamp;
    private volatile long sequence;

    public Snowflake(Long datacenterId) {
        this.datacenterIdBits = 6L;
        this.maxDatacenterId = 63L;
        this.sequenceBits = 8L;
        this.datacenterIdShift = 8L;
        this.timestampLeftShift = 14L;
        this.sequenceMask = 255L;
        this.twepoch = 1288834974657L;
        this.lastTimestamp = -1L;
        this.sequence = 0L;
        if (datacenterId != null && datacenterId.longValue() != 0L) {
            this.datacenterId = datacenterId.longValue();
        } else {
            try {
                this.datacenterId = this.getDatacenterId();
            } catch (UnknownHostException | NullPointerException | SocketException var4) {
                log.warn("SNOWFLAKE: could not determine machine address; using random datacenter ID");
                Random rnd1 = new Random();
                this.datacenterId = (long) (rnd1.nextInt(63) + 1);
            }
        }

        if (this.datacenterId > 63L || this.datacenterId < 0L) {
            log.warn("SNOWFLAKE: datacenterId > maxDatacenterId; using random datacenter ID");
            Random rnd = new Random();
            this.datacenterId = (long) (rnd.nextInt(63) + 1);
        }

        log.info("SNOWFLAKE: initialised with datacenter ID {}", Long.valueOf(this.datacenterId));
    }

    public Snowflake() {
        this((Long) null);
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp;
        for (timestamp = System.currentTimeMillis(); timestamp <= lastTimestamp; timestamp = System.currentTimeMillis()) {
            ;
        }

        return timestamp;
    }

    protected long getDatacenterId() throws SocketException, UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        NetworkInterface network = null;
        Enumeration en = NetworkInterface.getNetworkInterfaces();

        while (en.hasMoreElements()) {
            NetworkInterface mac = (NetworkInterface) en.nextElement();
            if (!mac.isLoopback() && mac.getHardwareAddress() != null) {
                network = mac;
                break;
            }
        }

        byte[] mac1 = network.getHardwareAddress();
        Random rnd = new Random();
        byte rndByte = (byte) (rnd.nextInt() & 255);
        long id = (255L & (long) mac1[mac1.length - 1] | 65280L & (long) rndByte << 8) >> 6;
        return id;
    }

    public synchronized long getId() {
        long timestamp = System.currentTimeMillis();
        if (timestamp < this.lastTimestamp) {
            log.warn("Clock moved backwards. Refusing to generate id for {} milliseconds.", Long.valueOf(this.lastTimestamp - timestamp));

            try {
                Thread.sleep(this.lastTimestamp - timestamp);
            } catch (InterruptedException var5) {
                ;
            }
        }

        if (this.lastTimestamp == timestamp) {
            this.sequence = this.sequence + 1L & 255L;
            if (this.sequence == 0L) {
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0L;
        }

        this.lastTimestamp = timestamp;
        long id = timestamp - 1288834974657L << 14 | this.datacenterId << 8 | this.sequence;
        if (id < 0L) {
            log.warn("ID is smaller than 0: {}", Long.valueOf(id));
        }

        return id;
    }

    public static long id() {
        return INSTANCE.getId();
    }

    public static void main(String[] args) {
        System.out.println(id());
    }
}
