package org.zxb.common.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zxb.common.utils.LoggerUtil;

public class LoggerUtilTest {

    private static Logger log = LoggerFactory.getLogger(LoggerUtilTest.class);

    @Test
    public void testInfo() {
        LoggerUtil.info(log, "test3333############################");
    }

}
