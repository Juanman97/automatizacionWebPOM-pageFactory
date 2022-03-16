package com.parasoft.parabank.page.common;

import org.apache.log4j.Logger;
import org.sikuli.script.Screen;

public class BaseSikuli {
    private static final Logger LOGGER = Logger.getLogger(BaseSikuli.class);

    public BaseSikuli() {
    }

    protected void clickON(String path) {
        Screen s = new Screen();
        try {
            s.wait(path);
            s.click(path);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    protected void insertInto(String path, String text) {
        Screen s = new Screen();
        try {
            s.wait(path);
            s.write(text);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }
}
