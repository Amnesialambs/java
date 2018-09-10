package com.yzy.test;

import com.yzy.util.DownUtil;

public class DownUtilTest {

	public static void main(String args[]) throws Exception {
        final DownUtil downUtil = new DownUtil("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536598502223&di=6dc69f6a09a6c55693d94d459ed16206&imgtype=0&src=http%3A%2F%2Ffile30.mafengwo.net%2FM00%2F1A%2FA3%2FwKgBpVUJG8eAGeyyAAJpkfaUlC463.groupinfo.w665_500.jpeg", "w665_500.jpeg", 3);

        downUtil.download();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(downUtil.getCompleteRate()<1){
                    System.out.println("已完成:"+downUtil.getCompleteRate());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }
}
