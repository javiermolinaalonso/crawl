package com.marfeel.crawler.processor.impl;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by javier on 15/08/15.
 */
public abstract class BaseProcessor<T extends TimerTask> {

    private Timer timer;

    public BaseProcessor(Timer timer) {
        this.timer = timer;
    }

    public void init(T task, int delay) {
        timer.schedule(task, delay, delay);
    }
}
