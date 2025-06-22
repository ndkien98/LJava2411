package vn.com.t3h.utils;

public class Constant {

    public static String ROLE_ADMIN_CODE="ADMIN";
    public static final String CODE_START="C";

    public static String PREFIX_ROLE="ROLE_";

    public static final String CACHE_JWT="JWT_CACHE";

    public static String PREFIX_CODE_CLAIM="CLM";

    public static String VALUE_CODE_CLAIM="xxxx";

    private static String VALUE_REPLACE = "x";

    public static String createCodeClaim(Long totalClaim){
        String total = String.valueOf(totalClaim + 1);
        String preValue = VALUE_CODE_CLAIM.substring(total.length());
        String finalValue = preValue.replaceAll(VALUE_REPLACE,"0") + total;
        return PREFIX_CODE_CLAIM + finalValue;
    }

    public enum CLAIM_STATUS {
        NEW
    }
}
