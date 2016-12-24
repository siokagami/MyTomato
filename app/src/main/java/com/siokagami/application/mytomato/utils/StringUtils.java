package com.siokagami.application.mytomato.utils;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtils {


    public static final String DOT_SIGN = ".";
    public static final String UNDERLINE_SIGN = "_";
    public static final String COLON_SIGN = ":";
    public static final String MORE_THAN_SIGN = ">";
    public static final String WELL_NUMBER_SIGN = "#";
    public static final String PERCENT_SIGN = "％";


    public static final String EMPTY = "";
    public static final String BLANK = " ";
    public static final String TWO_BLANK = "  ";

    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd hh:mm:ss";

    private static final String decimalRegExp = "[\\x00-\\x20]*[+-]?(((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*";
    /**
     * 用于生成文件
     */
    private static final String DEFAULT_FILE_PATTERN = "yyyy-MM-dd-HH-mm-ss";
    private static final double KB = 1024.0;
    private static final double MB = 1048576.0;
    private static final double GB = 1073741824.0;
    public static final SimpleDateFormat DATE_FORMAT_PART = new SimpleDateFormat("HH:mm");

    public static String currentTimeString() {
        return DATE_FORMAT_PART.format(Calendar.getInstance().getTime());
    }

    public static char chatAt(String pinyin, int index) {
        if (pinyin != null && pinyin.length() > 0)
            return pinyin.charAt(index);
        return ' ';
    }

    /**
     * 获取字符串宽度
     */
    public static float GetTextWidth(String Sentence, float Size) {
        if (isEmpty(Sentence))
            return 0;
        TextPaint FontPaint = new TextPaint();
        FontPaint.setTextSize(Size);
        return FontPaint.measureText(Sentence.trim()) + (int) (Size * 0.1); // 留点余地
    }

    /**
     * 格式化日期字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static String formatDate(long date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date(date));
    }

    /**
     * 格式化日期字符串
     *
     * @param date
     * @return 例如2011-3-24
     */
    public static String formatDate(Date date) {
        return formatDate(date, DEFAULT_DATE_PATTERN);
    }

    public static String formatDate(long date) {
        return formatDate(new Date(date), DEFAULT_DATE_PATTERN);
    }

    /**
     * 获取当前时间 格式为yyyy-MM-dd 例如2011-07-08
     *
     * @return
     */
    public static String getDate() {
        return formatDate(new Date(), DEFAULT_DATE_PATTERN);
    }

    /**
     * 生成一个文件名，不含后缀
     */
    public static String createFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FILE_PATTERN);
        return format.format(date);
    }

    /**
     * 拼接数组
     *
     * @param array
     * @param separator
     * @return
     */
    public static String join(final ArrayList<String> array,
                              final String separator) {
        StringBuffer result = new StringBuffer();
        if (array != null && array.size() > 0) {
            for (String str : array) {
                result.append(str);
                result.append(separator);
            }
            result.delete(result.length() - 1, result.length());
        }
        return result.toString();
    }

    public static String join(final Iterator<String> iter,
                              final String separator) {
        StringBuffer result = new StringBuffer();
        if (iter != null) {
            while (iter.hasNext()) {
                String key = iter.next();
                result.append(key);
                result.append(separator);
            }
            if (result.length() > 0)
                result.delete(result.length() - 1, result.length());
        }
        return result.toString();
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0 || str.trim().equalsIgnoreCase("null");
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * @param str
     * @return
     */
    public static String trim(String str) {
        return str == null ? EMPTY : str.trim();
    }

    public static boolean isBlank(String s) {
        return TextUtils.isEmpty(s);
    }

    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }

    /**
     * 根据秒速获取时间格式
     */
    public static String gennerTime(int totalSeconds) {
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * 转换文件大小
     *
     * @param size
     * @return
     */
    public static String generateFileSize(long size) {
        String fileSize;
        if (size < KB)
            fileSize = size + "B";
        else if (size < MB)
            fileSize = String.format("%.1f", size / KB) + "KB";
        else if (size < GB)
            fileSize = String.format("%.1f", size / MB) + "MB";
        else
            fileSize = String.format("%.1f", size / GB) + "GB";

        return fileSize;
    }

    /**
     * 查找字符串，找到返回，没找到返回空
     */
    public static String findString(String search, String start, String end) {
        int start_len = start.length();
        int start_pos = StringUtils.isEmpty(start) ? 0 : search.indexOf(start);
        if (start_pos > -1) {
            int end_pos = StringUtils.isEmpty(end) ? -1 : search.indexOf(end,
                    start_pos + start_len);
            if (end_pos > -1)
                return search.substring(start_pos + start.length(), end_pos);
        }
        return "";
    }

    /**
     * 截取字符串
     *
     * @param search       待搜索的字符串
     * @param start        起始字符串 例如：<title>
     * @param end          结束字符串 例如：</title>
     * @param defaultValue
     * @return
     */
    public static String substring(String search, String start, String end,
                                   String defaultValue) {
        int start_len = start.length();
        int start_pos = StringUtils.isEmpty(start) ? 0 : search.indexOf(start);
        if (start_pos > -1) {
            int end_pos = StringUtils.isEmpty(end) ? -1 : search.indexOf(end,
                    start_pos + start_len);
            if (end_pos > -1)
                return search.substring(start_pos + start.length(), end_pos);
            else
                return search.substring(start_pos + start.length());
        }
        return defaultValue;
    }

    /**
     * 截取字符串
     *
     * @param search 待搜索的字符串
     * @param start  起始字符串 例如：<title>
     * @param end    结束字符串 例如：</title>
     * @return
     */
    public static String substring(String search, String start, String end) {
        return substring(search, start, end, "");
    }

    /**
     * 拼接字符串
     *
     * @param strs
     * @return
     */
    public static String concat(String... strs) {
        StringBuffer result = new StringBuffer();
        if (strs != null) {
            for (String str : strs) {
                if (StringUtils.isNotEmpty(str))
                    result.append(str);
            }
        }
        return result.toString();
    }

    public static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static String nullToEmpty(Object object) {
        String result = String.valueOf(object);
        if (result == null || result.endsWith("null") || result.endsWith("Null") || result.endsWith("NULL"))
            return "";
        return result;
    }

    public static String hideMobileNum(String mobile) {
        return StringUtils.concat(mobile.substring(0, 3), "****", mobile.substring(7, 11));
    }

    /**
     * 获取输入内容的长度
     *
     * @param chineseUnit 一个中文几个字符
     * @return
     */
    public static int getLength(String content, int chineseUnit) {
        if (chineseUnit == 1) {
            return content != null ? content.length() : 0;
        }

        int length = content.length();
        int result = length;
        for (int i = 0; i < length; i++) {
            if (isChineseCharacter(content.charAt(i))) {
                result += (chineseUnit - 1);
            }
        }
        return result;
    }

    // GENERAL_PUNCTUATION 判断中文的“号
    // CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号
    // HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
    public static boolean isChineseCharacter(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    /**
     * 删除最后一个字符
     *
     * @param sourceBuilder
     * @return
     */
    public static StringBuilder deleteLast(StringBuilder sourceBuilder) {
        if (sourceBuilder != null) {
            return sourceBuilder.deleteCharAt(sourceBuilder.length() - 1);
        } else {
            return new StringBuilder();
        }
    }


    /**
     * //对长度n的数组 求 m 组合
     * //求数组 str元素的 所有组合
     *
     * @param str    ids
     * @param skuMap key "id:id:..."
     * @param sku    TODO 自定义实体   简化 保存内容
     */


    /**
     * TODO 优化为正则
     *
     * @param str     1:2:3:4
     * @param spliter :
     * @return {"1:","2:","3:","4:"}
     */
    private static String[] splitStrToArr(String str, String spliter) {
        String[] arr = str.split(spliter);
        String[] resultArr = new String[arr.length];
        for (int i = 0, len = arr.length; i < len; i++) {
            resultArr[i] = arr[i] + spliter;
        }
        return resultArr;
    }




    public static SpannableString strTextView(String needShowText, int textSize, int displayColor) {
        SpannableString spannableString = new SpannableString(needShowText);
        //字体大小
        spannableString.setSpan(new AbsoluteSizeSpan(textSize, true), 0, needShowText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //字体颜色
        spannableString.setSpan(new ForegroundColorSpan(displayColor), 0, needShowText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    public static SpannableString strTextView(String firstStr, int firstSize, int firstColor, String secStr, int secSize, int secColor) {
        String textStr = firstStr + secStr;
        SpannableString spannableString = new SpannableString(textStr);
        //字体大小
        spannableString.setSpan(new AbsoluteSizeSpan(firstSize, true), 0, firstStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //字体颜色
        spannableString.setSpan(new ForegroundColorSpan(firstColor), 0, firstStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(secSize, true), firstStr.length(), textStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(secColor), firstStr.length(), textStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    //是否是正确链接
    public static boolean isLinkRight(String urlString) {
        String regex = "(?:https?://)?[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)+(?:[-A-Z0-9a-z_\\$\\.\\+!\\*\\(?:\\)/,:;@&=\\?~#%]*)";
        Pattern patt = Pattern.compile(regex);
        Matcher matcher = patt.matcher(urlString);
        return matcher.matches();
    }

    /**
     * 复制到剪贴板
     *
     * @param context
     * @param content
     */
    public static void copyToClip(Context context, String content) {
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content);
    }

    /**
     * 检查是否是整数(不超过15位数 Long的值范围内)
     */
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        if (length > 15) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public static boolean isDecimal(String str) {
        Pattern pattern = Pattern.compile(decimalRegExp);
        Matcher m = pattern.matcher(str);
        return m.matches();
    }

    public static boolean notNull(String... strings) {
        if (strings != null && strings.length > 0) {
            return true;
        }
        return false;
    }

    public static int parseColor(String colorCode) {
        return Color.parseColor("#" + colorCode);
    }

    public static byte[] getByte(String content, String encodingType) {
        try {
            return content.getBytes("utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String https2http(String httpsURL) {
        if (httpsURL.startsWith("https")) {
            return httpsURL.replaceFirst("https", "http");
        }
        return httpsURL;
    }

    public static String encodeUrl(String str) {
        if (StringUtils.isEmpty(str)) return "";
        else return URLEncoder.encode(str);
    }

    /**
     * <PRE>
     * 全角字符->半角字符转换
     * 只处理全角的空格，全角！到全角～之间的字符，忽略其他
     * </PRE>
     */
    /**
     * 全角对应于ASCII表的可见字符从！开始，偏移值为65281
     */
    static final char SBC_CHAR_START = 65281; // 全角！
    /**
     * 全角对应于ASCII表的可见字符到～结束，偏移值为65374
     */
    static final char SBC_CHAR_END = 65374; // 全角～
    /**
     * ASCII表中除空格外的可见字符与对应的全角字符的相对偏移
     */
    static final int CONVERT_STEP = 65248; // 全角半角转换间隔
    /**
     * 全角空格的值，它没有遵从与ASCII的相对偏移，必须单独处理
     */
    static final char SBC_SPACE = 12288; // 全角空格 12288

    /**
     * 半角空格的值，在ASCII中为32(Decimal)
     */
    static final char DBC_SPACE = ' '; // 半角空格

    public static String qj2bj(String src) {
        if (src == null) {
            return src;
        }
        StringBuilder buf = new StringBuilder(src.length());
        char[] ca = src.toCharArray();
        for (int i = 0; i < src.length(); i++) {
            if (ca[i] >= SBC_CHAR_START && ca[i] <= SBC_CHAR_END) { // 如果位于全角！到全角～区间内
                buf.append((char) (ca[i] - CONVERT_STEP));
            } else if (ca[i] == SBC_SPACE) { // 如果是全角空格
                buf.append(DBC_SPACE);
            } else { // 不处理全角空格，全角！到全角～区间外的字符
                buf.append(ca[i]);
            }
        }
        return buf.toString();
    }

    public static String removeXMLTag(String origin) {
        return origin.replaceAll("\\<\\S.*?>", "");
    }

    public static boolean parseBoolean(Object object) {
        return "true".equalsIgnoreCase(nullToEmpty(object));
    }

    public static boolean parseBooleanNullToTrue(Object object) {
        if (object == null)
            return true;
        return "true".equalsIgnoreCase(String.valueOf(object));
    }
}
