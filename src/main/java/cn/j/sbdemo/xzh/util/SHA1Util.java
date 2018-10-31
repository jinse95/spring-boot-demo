package cn.j.sbdemo.xzh.util;

import cn.j.sbdemo.xzh.exception.XzhErrorEnum;
import cn.j.sbdemo.xzh.exception.XzhException;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * SHA1Util 签名
 *
 * @author xzh
 * @date 2018/2/25 下午4:13
 */
class SHA1Util {
    private static Logger LOGGER = Logger.getLogger(SHA1Util.class);

    /**
     * 用SHA1算法生成安全签名
     *
     * @param token     票据
     * @param timestamp 时间戳
     * @param nonce     随机字符串
     * @param encrypt   密文
     * @return 安全签名
     * @throws XzhException 生成sign异常
     */
    public static String getSHA1(String token, String timestamp, String nonce, String encrypt) throws XzhException {
        try {
            String[] array = new String[]{token, timestamp, nonce, encrypt};
            StringBuffer sb = new StringBuffer();
            // 字符串排序
            Arrays.sort(array);
            for (int i = 0; i < 4; i++) {
                sb.append(array[i]);
            }
            String str = sb.toString();

            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuffer hexStr = new StringBuffer();
            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexStr.append(0);
                }
                hexStr.append(shaHex);
            }
            return hexStr.toString();
        } catch (Exception e) {
            LOGGER.error(e);
            throw new XzhException(XzhErrorEnum.SIGN_SHA1_ERROR);
        }
    }
}