package org.zxb.plun;

import lombok.Data;

@Data
public class Page {

    /* 页数 */
    private int pageNum;

    /* 每页多少数量 */
    private int everypageNum;

    /* 总数 */
    private int total;
}
