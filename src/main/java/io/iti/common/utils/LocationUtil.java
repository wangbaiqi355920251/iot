package io.iti.common.utils;

import cn.hutool.core.io.IoUtil;
import org.lionsoul.ip2region.*;

import java.io.IOException;

/**
 * @param
 * @author Jing
 * @time 2022-05-26  18:51
 * @return
 */
public class LocationUtil {
    private static final String dbRelativePath = "/ip2region.db";

    private static byte[] dbData = null;

    static {
        dbData = IoUtil.readBytes(LocationUtil.class.getResourceAsStream(dbRelativePath));
    }

    /**
     * 根据 ip 获取地里位置
     * @param ip
     * @return
     * @throws DbMakerConfigException
     * @throws IOException
     */
    public static String getRegionFromIp(String ip) throws DbMakerConfigException, IOException {
        DbSearcher searcher = new DbSearcher(new DbConfig(), dbData);
        String region = null;
        if (Util.isIpAddress(ip)) {
            DataBlock dataBlock = searcher.memorySearch(ip);
            region = dataBlock.getRegion();
            return region;
        }
        return null;
    }
}
