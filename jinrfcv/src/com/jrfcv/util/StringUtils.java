package com.jrfcv.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

/**
 * String工具 主要对 StringUtils 的一些方法进行重写,达到更方便的使用
 * 
 * @author pj
 * 
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	private final static Logger logger = Logger.getLogger(StringUtils.class);

	/**
	 * 一次性判断多个或单个对象为空。
	 * 
	 * @param objects
	 * @author pj
	 * @return 只要有一个元素为Blank，则返回true
	 */
	public static Boolean isBlank(Object... objects) {
		Boolean result = false;
		for (Object object : objects) {
			if (object == null || "".equals(object.toString().trim())
					|| "null".equals(object.toString().trim())) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * 一次性判断多个或单个对象不为空。
	 * 
	 * @param objects
	 * @author zhou-baicheng
	 * @return 只要有一个元素不为Blank，则返回true
	 */
	public Boolean isNotBlank(Object... objects) {
		return !isBlank(objects);
	}

	public static String getRandom(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			// 输出字母还是数字
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 字符串
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 取得大写字母还是小写字母
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val.toLowerCase();
	}

	/**
	 * 判断一个字符串是否为JSONObject,是返回JSONObject,不是返回null
	 * 
	 * @param args
	 * @return
	 */
	public static JSONObject isJSONObject(String args) {
		JSONObject result = null;
		if (isBlank(args)) {
			return result;
		}
		try {
			return JSONObject.fromObject(args.trim());
		} catch (Exception e) {
			return result;
		}
	}

	/**
	 * 判断一个字符串是否为JSONArray,是返回JSONArray,不是返回null
	 * 
	 * @param args
	 * @return
	 */
	public static net.sf.json.JSONArray isJSONArray(Object args) {
		JSONArray result = new JSONArray();
		if (isBlank(args)) {
			return null;
		}
		if (args instanceof net.sf.json.JSONArray) {

			net.sf.json.JSONArray arr = (net.sf.json.JSONArray) args;
			for (Object json : arr) {
				if (json != null && json instanceof net.sf.json.JSONObject) {
					result.add(json);
					continue;
				} else {
					result.add(JSONObject.fromObject(json));
				}
			}
			return result;
		} else {
			return null;
		}

	}

	public static String trimToEmpty(Object str) {
		return (isBlank(str) ? "" : str.toString().trim());
	}

	/**
	 * 将 Strig 进行 BASE64 编码
	 * 
	 * @param str
	 *            [要编码的字符串]
	 * @param bf
	 *            [true|false,true:去掉结尾补充的'=',false:不做处理]
	 * @return
	 */
	public static String getBASE64(String str, boolean... bf) {
		if (StringUtils.isBlank(str))
			return null;
		String base64 = new sun.misc.BASE64Encoder().encode(str.getBytes());
		// 去掉 '='
		if (isBlank(bf) && bf[0]) {
			base64 = base64.replaceAll("=", "");
		}
		return base64;
	}

	/** 将 BASE64 编码的字符串 s 进行解码 **/
	public static String getStrByBASE64(String s) {
		if (isBlank(s))
			return "";
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return "";
		}
	}

	public static String decodeUnicode(final String dataStr) {
		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		while (start > -1) {
			end = dataStr.indexOf("\\u", start + 2);
			String charStr = "";
			if (end == -1) {
				charStr = dataStr.substring(start + 2, dataStr.length());
			} else {
				charStr = dataStr.substring(start + 2, end);
			}
			char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
			buffer.append(new Character(letter).toString());
			start = end;
		}
		return buffer.toString();
	}

	/**
	 * 转换成Unicode
	 * 
	 * @param str
	 * @return
	 */
	public static String deUnicode(String str) {
		String as[] = new String[str.length()];
		String s1 = "";
		for (int i = 0; i < str.length(); i++) {
			as[i] = Integer.toHexString(str.charAt(i) & 0xffff);
			s1 = s1 + "\\u" + as[i];
		}
		return s1;
	}

	public static String enUnicode(String theString) {

		char aChar;

		int len = theString.length();

		StringBuffer outBuffer = new StringBuffer(len);

		for (int x = 0; x < len;) {

			aChar = theString.charAt(x++);

			if (aChar == '\\') {

				aChar = theString.charAt(x++);

				if (aChar == 'u') {

					// Read the xxxx

					int value = 0;

					for (int i = 0; i < 4; i++) {

						aChar = theString.charAt(x++);

						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed   \\uxxxx   encoding.");
						}

					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}

	/**
	 * 字符串转urlcode
	 * 
	 * @param value
	 * @return
	 */
	public static String strToUrlcode(String value) {
		try {
			value = java.net.URLEncoder.encode(value, "utf-8");
			return value;
		} catch (UnsupportedEncodingException e) {
			logger.error("字符串转换为URLCode失败,value:"+ value);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * urlcode转字符串
	 * 
	 * @param value
	 * @return
	 */
	public static String urlcodeToStr(String value) {
		try {
			value = java.net.URLDecoder.decode(value, "utf-8");
			return value;
		} catch (UnsupportedEncodingException e) {
			logger.error("URLCode转换为字符串失败;value:"+ value);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 判断字符串是否包含汉字
	 * 
	 * @param txt
	 * @return
	 */
	public static Boolean containsCN(String txt) {
		if (isBlank(txt)) {
			return false;
		}
		for (int i = 0; i < txt.length(); i++) {

			String bb = txt.substring(i, i + 1);

			boolean cc = java.util.regex.Pattern.matches("[\u4E00-\u9FA5]", bb);
			if (cc)
				return cc;
		}
		return false;
	}

	public static String removeHtml(String news) {
		String s = news.replaceAll("amp;", "").replaceAll("<", "<")
				.replaceAll(">", ">");

		Pattern pattern = Pattern.compile(
				"<(span)?\\sstyle.*?style>|(span)?\\sstyle=.*?>",
				Pattern.DOTALL);
		Matcher matcher = pattern.matcher(s);
		String str = matcher.replaceAll("");

		Pattern pattern2 = Pattern.compile("(<[^>]+>)", Pattern.DOTALL);
		Matcher matcher2 = pattern2.matcher(str);
		String strhttp = matcher2.replaceAll(" ");

		String regEx = "(((http|https|ftp)(\\s)*((\\:)|：))(\\s)*(//|//)(\\s)*)?"
				+ "([\\sa-zA-Z0-9(\\.|．)(\\s)*\\-]+((\\:)|(:)[\\sa-zA-Z0-9(\\.|．)&%\\$\\-]+)*@(\\s)*)?"
				+ "("
				+ "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])"
				+ "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)"
				+ "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)"
				+ "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])"
				+ "|([\\sa-zA-Z0-9\\-]+(\\.|．)(\\s)*)*[\\sa-zA-Z0-9\\-]+(\\.|．)(\\s)*[\\sa-zA-Z]*"
				+ ")"
				+ "((\\s)*(\\:)|(：)(\\s)*[0-9]+)?"
				+ "(/(\\s)*[^/][\\sa-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*";
		Pattern p1 = Pattern.compile(regEx, Pattern.DOTALL);
		Matcher matchhttp = p1.matcher(strhttp);
		String strnew = matchhttp.replaceAll("").replaceAll(
				"(if[\\s]*\\(|else|elseif[\\s]*\\().*?;", " ");

		Pattern patterncomma = Pattern.compile("(&[^;]+;)", Pattern.DOTALL);
		Matcher matchercomma = patterncomma.matcher(strnew);
		String strout = matchercomma.replaceAll(" ");
		String answer = strout.replaceAll("[\\pP‘’“”]", " ")
				.replaceAll("\r", " ").replaceAll("\n", " ")
				.replaceAll("\\s", " ").replaceAll("　", "");

		return answer;
	}

	/**
	 * @Title: revert
	 * @Description: unicode解码
	 * @author zcs
	 * @date 2015-12-24 上午9:18:46
	 * @param str
	 * @return
	 */
	public static String revert(String str) {
		str = (str == null ? "" : str);
		if (str.indexOf("\\u") == -1)// 如果不是unicode码则原样返回
			return str;

		StringBuffer sb = new StringBuffer(1000);

		for (int i = 0; i < str.length() - 6;) {
			String strTemp = str.substring(i, i + 6);
			String value = strTemp.substring(2);
			int c = 0;
			for (int j = 0; j < value.length(); j++) {
				char tempChar = value.charAt(j);
				int t = 0;
				switch (tempChar) {
				case 'a':
					t = 10;
					break;
				case 'b':
					t = 11;
					break;
				case 'c':
					t = 12;
					break;
				case 'd':
					t = 13;
					break;
				case 'e':
					t = 14;
					break;
				case 'f':
					t = 15;
					break;
				default:
					t = tempChar - 48;
					break;
				}

				c += t * ((int) Math.pow(16, (value.length() - j - 1)));
			}
			sb.append((char) c);
			i = i + 6;
		}
		return sb.toString();
	}

	/**
	 * @Title: convert
	 * @Description: unicode转码
	 * @author zcs
	 * @date 2015-12-24 上午9:18:59
	 * @param str
	 * @return
	 */
	public static String convert(String str) {
		str = (str == null ? "" : str);
		String tmp;
		StringBuffer sb = new StringBuffer(1000);
		char c;
		int i, j;
		sb.setLength(0);
		for (i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			sb.append("\\u");
			j = (c >>> 8); // 取出高8位
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1)
				sb.append("0");
			sb.append(tmp);
			j = (c & 0xFF); // 取出低8位
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1)
				sb.append("0");
			sb.append(tmp);

		}
		return (new String(sb));
	}

	public final byte[] input2byte(InputStream inStream) throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = inStream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		return in2b;
	}

	public final InputStream byte2Input(byte[] buf) {
		return new ByteArrayInputStream(buf);
	}

	public final String inputStream2String(InputStream is) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}

	public void inputstreamToFile(InputStream ins, File file)
			throws IOException {
		OutputStream os = new FileOutputStream(file);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		ins.close();
	}

	/**
	 * 字符串转二进制
	 * 
	 * @param str
	 *            要转换的字符串
	 * @return 转换后的二进制数组
	 */
	public static byte[] hex2byte(String str) { // 字符串转二进制
		if (str == null)
			return null;
		str = str.trim();
		int len = str.length();
		if (len == 0 || len % 2 == 1)
			return null;
		byte[] b = new byte[len / 2];
		try {
			for (int i = 0; i < str.length(); i += 2) {
				b[i / 2] = (byte) Integer
						.decode("0X" + str.substring(i, i + 2)).intValue();
			}
			return b;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将InputStream转换成String
	 * 
	 * @param in
	 *            InputStream
	 * @return String
	 * @throws Exception
	 * 
	 */
	public static String InputStreamTOString(InputStream in) throws Exception {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[8094];
		int count = -1;
		while ((count = in.read(data, 0, 8094)) != -1)
			outStream.write(data, 0, count);

		data = null;
		return new String(outStream.toByteArray(), "utf-8");
	}

	/**
	 * 将String转换成InputStream
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static InputStream StringTOInputStream(String in) throws Exception {

		ByteArrayInputStream is = new ByteArrayInputStream(in.getBytes("utf-8"));
		return is;
	}

	/**
	 * 
	 * <b>方法名</b>：getStreamToString<br>
	 * <b>功能</b>：输入流转字符串<br>
	 * 
	 * @author <font color='blue'>Shenbin</font>
	 * @date 2014-4-19 下午11:52:49
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public static String getStreamToString(InputStream input)
			throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(input,
				"utf-8"));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}
	
	/**
	 * 判断字符串是否是JSON对象字符串
	 * @Date:2013-6-13
	 * @author wangk
	 * @param str
	 * @return
	 * @Description:
	 * @return boolean
	 */
	public static boolean isJsonObjectString(String str) {
		return str != null && str.matches("^\\{.*\\}$");
	}
	
	/**
	 * 判断字符串是否是JSON数组字符串
	 * @Date:2013-6-13
	 * @author wangk
	 * @param str
	 * @return
	 * @Description:
	 * @return boolean
	 */
	public static boolean isJsonArrayString(String str) {
		return str != null && str.matches("^\\[.*\\]$");
	}
	/**
	* @Title: replaceGtAndLt
	* @Description: 将<>转义
	* @author wdz
	* @date 2018-1-3 下午2:57:39
	* @param str
	* @return
	 */
	public static String replaceGtAndLt(String str){
		if(!isBlank(str)){
			if(str.contains("<")){
				str = str.replaceAll("<", "&lt;");
			}else if(str.contains(">")){
				str = str.replaceAll("<", "&gt;");
			}
		}
		return str;
	}
	/**
	* @Title: replaceGtAndLtBlank
	* @Description: 将<>清空
	* @author wdz
	* @date 2018-1-3 下午2:58:08
	* @param str
	* @return
	 */
	public static String replaceGtAndLtBlank(String str){
		if(!isBlank(str)){
			if(str.contains("<")){
				str = str.replaceAll("<", " ");
			}else if(str.contains(">")){
				str = str.replaceAll("<", " ");
			}
		}
		return str;
	}
}
