package com.imfbp.rz.util;

import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Snowflake Twitter
 *0           41     51     64
 +-----------+------+------+
 |time       |pc    |inc   |
 +-----------+------+------+
 前41bits是以微秒为单位的timestamp。
 接着10bits是事先配置好的机器ID。
 最后12bits是累加计数器。
 */
public class PrimaryKeyIdWorker{

    private final long workerId;
    // 1376 1103 3134 6 13*4
    private final long twepoch = 1303895660503L;
    private long sequence = 0L;
    private final long workerIdBits = 10L;
    private final long maxWorkerId = -1L ^ -1L << this.workerIdBits;
    private final long sequenceBits = 12L;

    private final long workerIdShift = this.sequenceBits;
    private final long timestampLeftShift = this.sequenceBits + this.workerIdBits;
    private final long sequenceMask = -1L ^ -1L << this.sequenceBits;

    private final static String RANDOM_CHAR = "ABCDEFGHIJKLMNPQRSTUVWXYZ";

    private long lastTimestamp = -1L;

//	public PrimaryKeyIdWorker(long workerId) {
//		super();
//		if (workerId > this.maxWorkerId || workerId < 0) {
//			throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",
//					this.maxWorkerId));
//		}
//		this.workerId = workerId;
//	}

    public PrimaryKeyIdWorker() {
        super();
        this.workerId = 1L;
    }

    public synchronized String  nextId() {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) {
            this.sequence = this.sequence + 1 & this.sequenceMask;
            if (this.sequence == 0) {
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }
        if (timestamp < this.lastTimestamp) {
            new Exception(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    (this.lastTimestamp - timestamp)));
        }

        this.lastTimestamp = timestamp;
        return RandomStringUtils.random(2, RANDOM_CHAR) + (timestamp - this.twepoch << this.timestampLeftShift
                | this.workerId << this.workerIdShift | this.sequence);

    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }


    //生成随机数字和字母,
    public static String getStringRandom(int length) {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

//	public static void main(String[] args) {
//		long workerIdBits = 10L;
//		long maxWorkerId = -1L ^ -1L << workerIdBits;
//		PrimaryKeyIdWorker w = new PrimaryKeyIdWorker(maxWorkerId);
//		// 生成随机数字和字母,
//		for (int i = 0; i < 10000; i++) {
//
//			System.out.println(w.nextId());
//		}
//	}

    public String getPrimaryKey() {
        return nextId();
    }

}

