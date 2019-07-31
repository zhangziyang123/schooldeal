package com.schooldeal.util;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * <p>FileName: StringUtil.java</p>
 * 
 * <p>Title: 字符串处理工具类</p>
 *
 * <p>Description: 用于对字符串进行替换和转换等操作</p>
 *
 * <p>CreateDate: 2009-8-19</p>
 *
 * @author <a href="mailto:njxyf007@hotmail.com">许永夫</a>
 *
 * @version 1.00
 */
public class StringUtil {
    
    private static final char QUOTE_ENCODE[] = "&quot;".toCharArray();
    private static final char AMP_ENCODE[] = "&amp;".toCharArray();
    private static final char LT_ENCODE[] = "&lt;".toCharArray();
    private static final char GT_ENCODE[] = "&gt;".toCharArray();
    private static final char APOS_ENCODE[] = "&apos;".toCharArray();
    private static final char BR_TAG[] = "<BR>".toCharArray();
    //private static Object initLock = new Object();

	/**
	 * 字符串替换
	 * 
	 * @param 	strSrc		String		源字符串
	 * @param 	strOld		String		被替换的字符串
	 * @param 	strNew		String		欲替换的字符串
	 * @return	String
	 */
	 public static final String replace(
		  		String strSrc, 
				String strOld, 
				String strNew){
		  	
	    String szReturn = "";
        String szTemp;
        int iIndex;
        String neoStr=strSrc;
        boolean bFirst = true;

        if (strSrc == null) {
            return null;
        }
        while ((iIndex = strSrc.indexOf(strOld)) != -1) {
            szTemp = strSrc.substring(0, iIndex);
            if (bFirst) {
                szReturn = "";
                bFirst = false;
            }
            szReturn += szTemp + strNew;
            neoStr = strSrc.substring(iIndex + strOld.length());
        }
        szReturn += neoStr;
        return szReturn;
	}
	
	 
	/**
	 * 把字符中的html特殊符号"<",">"换成对应的转义符
	 * 
	 * @param s		String	字符串
	 * @return	String
	 */
    public static final String escapeHTMLTags(String s)
    {
        if(s == null)
            return null;
        int i = 0;
        int j = 0;
        char ac[] = s.toCharArray();
        int k = ac.length;
        StringBuilder stringbuffer = new StringBuilder((int)((double)k * 1.3D));
        for(; i < k; i++)
        {
            char c = ac[i];
            if(c > '>')
                continue;
            if(c == '<')
            {
                if(i > j)
                    stringbuffer.append(ac, j, i - j);
                j = i + 1;
                stringbuffer.append(LT_ENCODE);
                continue;
            }
            if(c != '>')
                continue;
            if(i > j)
                stringbuffer.append(ac, j, i - j);
            j = i + 1;
            stringbuffer.append(GT_ENCODE);
        }

        if(j == 0)
            return s;
        if(i > j)
            stringbuffer.append(ac, j, i - j);
        return stringbuffer.toString();
    }

	/**
	 * 把字符串的"\n","\r\n"换成hmtl标签
	 * 
	 * @param s		String
	 * @return	String
	 */
    public static String convertNewlines(String s)
    {
        char ac[] = s.toCharArray();
        int i = 0;
        int j = ac.length;
        StringBuilder stringbuffer = new StringBuilder(j);
        for(int k = 0; k < j; k++)
        {
            if(ac[k] == '\n')
            {
                stringbuffer.append(ac, i, k - i).append(BR_TAG);
                i = k + 1;
                continue;
            } else if(ac[k] == '\r' && k < j - 1 && ac[k + 1] == '\n') {
                stringbuffer.append(ac, i, k - i).append(BR_TAG);
                i = ++k + 1;
            }
        }

        stringbuffer.append(ac, i, j - i);
        return stringbuffer.toString();
    }

    /**
     * 作些相应的转化成sql字符串
     * 
     * @param s		String		要转换的字符串
     * @param s1	String		like的主体
     * @return String
     */
    public static String getTranslateStr(String s, String s1)
    {
        String s2 = "";
        if(s.indexOf(" ") > -1)
        {
            boolean flag = true;
            String as[] = s.split(" ");
            for(int i = 0; i < as.length; i++)
            {
                if("AND".equals(as[i]) || "&".equals(as[i]))
                {
                    s2 = s2 + " and ";
                    flag = true;
                    continue;
                }
                if("OR".equals(as[i]) || "|".equals(as[i]))
                {
                    s2 = s2 + " or ";
                    flag = true;
                    continue;
                }
                if("NOT".equals(as[i]) || "!".equals(as[i]) || "！".equals(as[i]))
                {
                    s2 = s2 + " not ";
                    flag = true;
                    continue;
                }
                if("(".equals(as[i]) || "（".equals(as[i]))
                {
                    s2 = s2 + " ( ";
                    flag = true;
                    continue;
                }
                if(")".equals(as[i]) || "）".equals(as[i]))
                {
                    s2 = s2 + " ) ";
                    flag = true;
                    continue;
                }
                if("".equals(as[i]))
                    continue;
                if(!flag)
                    s2 = s2 + " and ";
                if(as[i].indexOf("%") > -1)
                    s2 = s2 + " " + s1 + " like '" + as[i].replaceAll("'", "''") + "' ";
                else
                    s2 = s2 + " " + s1 + " like '%" + as[i].replaceAll("'", "''") + "%' ";
                flag = false;
            }

            return s2;
        }
        if(s.indexOf("%") > -1)
            s2 = s2 + " " + s1 + " like '" + s.replaceAll("'", "''") + "' ";
        else
            s2 = s2 + " " + s1 + " like '%" + s.replaceAll("'", "''") + "%' ";
        return s2;
    }
    
    /**
     * 对字符串进行html过滤
     * 
     * @param s		String
     * @return	String
     */
    public static String toHtmlInput(String s)
    {
        if(s == null)
        {
            return null;
        } else{
            String s1 = new String(s);
            s1 = replace(s1, "&", "&amp;");
            s1 = replace(s1, "<", "&lt;");
            s1 = replace(s1, ">", "&gt;");
            s1 = replace(s1, "\"", "&quot;");
            s1 = replace(s1, "'", "''");
            return s1;
        }
    }

    /**
     * 把字符串转换成html字符串
     * 
     * @param s		String
     * @return	String
     */
    public static String toHtml(String s)
    {
        if(s == null)
        {
            return null;
        } else{
            String s1 = new String(s);
            s1 = toHtmlInput(s1);
            s1 = replace(s1, "\r\n", "\n");
            s1 = replace(s1, "\n", "<br>\n");
            s1 = replace(s1, "\t", "    ");
            s1 = replace(s1, "  ", " &nbsp;");
            return s1;
        }
    }
    
    /**
     * 将string 转换为int
     * @param     str      要转换的字符串
     * @return    int
     */
     public static int StringToInt(String str){
         if(str==null)
         {
             return 0;
         }
         return (Integer.valueOf(str.trim())).intValue();
     }

     /**
      * 返回要截取的字符串中的分量数
      * @param   sz_theChar    	String	要截取的字符串
      * @param   sz_Separator	String	分隔符
      * @return  int
      * eg:
      * 	若sz_theChar="1,2,3,4";sz_Separator=",";那么就返回4
      * 
      */
      public static int getCharTolNum( String sz_theChar, String sz_Separator )
      {
          return sz_theChar.split(sz_Separator).length;
      }
     
      /**
       * 返回要截取的字符串中的分量数
       * @param   sz_theChar    	String	要截取的字符串
       * @param   sz_Separator	String	分隔符
       * @return  String[]
       * eg:
       * 	若sz_theChar="1,2,3,4";sz_Separator=",";那么就返回String[]={"1","2","3","4"}
       * 
       */
       public static String[] getSepStr( String sz_theChar, String sz_Separator )
       {
           return sz_theChar.split(sz_Separator);
       }
      
     /**
      * 返回要截取的字符串中的分量
      * @param    sz_theChar 		String	要截取的字符串
      * @param    sz_Separator		String	分隔符
      * @param    i_CharNum		String	要取得的字符串位置
      * @return   String
      * eg:
      * 	若sz_theChar="1,3,2,4";sz_Separator=",";i_CharNum=2;那么就返回"3"
      * 
      */
      public static String getSeparatorChar( String sz_theChar, String sz_Separator, int i_CharNum )
      {
          String[] str = sz_theChar.split(sz_Separator);
          return str[i_CharNum - 1];
      }
      
      /**
       * 将月份的格式由数字转换成英文简拼
       * @param    i_theMonth int		数字格式的月份
       * @return   String
       */
       public static String convertMonthToChar( int i_theMonth )
       {
           String sz_theChar = "";
           String charMonth[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct","Nov", "Dec"};
           switch ( i_theMonth )
           {
               case 1:
                   sz_theChar = charMonth[0];
                   break;
               case 2:
                   sz_theChar = charMonth[1];
                   break;
               case 3:
                   sz_theChar = charMonth[2];
                   break;
               case 4:
                   sz_theChar = charMonth[3];
                   break;
               case 5:
                   sz_theChar = charMonth[4];
                   break;
               case 6:
                   sz_theChar = charMonth[5];
                   break;
               case 7:
                   sz_theChar = charMonth[6];
                   break;
               case 8:
                   sz_theChar = charMonth[7];
                   break;
               case 9:
                   sz_theChar = charMonth[8];
                   break;
               case 10:
                   sz_theChar = charMonth[9];
                   break;
               case 11:
                   sz_theChar = charMonth[10];
                   break;
               case 12:
                   sz_theChar = charMonth[11];
                   break;
               default:
                   break;
           }
           return sz_theChar;
       }
       
       /**
        * 转换字符串中间的分隔符(主要用于sql子语句in(xx,xx))
        * 
        * @param     szAllString      String    需要转换的字符串       <p>
        * @param     iFlag            int       1 用引号 0 不用引号    <P>
        * @param     szCharFirst      String    被替换的字符           <p>
        * @param     szCharSecond     String    替换为的字符           <P>
        * @return    String                     替换以后的字符串       <P>
        */
      /* public static String getRecombineStr(String szAllString,int iFlag,String szCharFirst,String szCharSecond)
       {
           String szReformString = "" ;
           // String szTempString = "" ;
           int iIndexOf;
           // iFlag==1 varchar , iFlag==0 number
           // szCharFirst 初始分隔符
           // szCharSecond 重组后的分隔符
           if (szAllString==null || (szAllString!=null && "".equals(szAllString.trim())))
           {
               return "";
           }
           if (iFlag=='')
           {
               iFlag = 1;
           }
           if (szCharFirst==null || (szCharFirst!=null &&"".equals(szCharFirst.trim())))
           {
               szCharFirst = ";";
           }
           if (szCharSecond==null || (szCharSecond!=null && "".equals(szCharSecond.trim())))
           {
               szCharSecond = ",";
           }
           if (szAllString!=null && szAllString.startsWith(";"))
           {
               szAllString = szAllString.substring(1);
           }
           iIndexOf = szAllString.indexOf(szCharFirst);
           if (iIndexOf==-1)
           {
               szReformString = szAllString;
               if (iFlag==1)
               {
                   szReformString = "'"+szReformString+"'";
               }
               return szReformString;
           }
           if (szAllString.endsWith(";")==false)
           {
               szAllString = szAllString + ";";
           }
           for (int i=0; ;i++ )
           {
               iIndexOf = szAllString.indexOf(szCharFirst);
               if (iIndexOf==-1)
               {
                   break;
               }
               if (iFlag==1)
               {
                   szReformString = szReformString +"'"+szAllString.substring(0,iIndexOf)+"'" + ""+szCharSecond+"";
               }
               else
               {
                   szReformString = szReformString +szAllString.substring(0,iIndexOf) + ""+szCharSecond+"";
               }

               szAllString = szAllString.substring((iIndexOf+1),szAllString.length());
           }
           if (szReformString!=null && szReformString.startsWith(szCharSecond))
           {
               szReformString = szReformString.substring(1);
           }
           if (szReformString!=null && szReformString.endsWith(szCharSecond))
           {
               szReformString = szReformString.substring(0,szReformString.length()-1);
           }
           //bug("szReformString="+szReformString) ;
           return szReformString;
       }*/
       
       /**
        * 适用varchar,date(实体中对应类型是String)
        * 
        * @param htSource Hashtable	数据结果集
        * @param szValue	String 键值名称
        * @return String
        * @throws BusiException
        */
       public static String getHashtableValue(HashMap htSource, String szValue){
           if (htSource == null)
               return "";
           String szGetVal = (String) htSource.get(szValue);
           if (szGetVal == null)
               szGetVal = "";
           else
               szGetVal = szGetVal.trim();
           //new
           return szGetVal;
       }

       /**
        * 适用long,int,float,double
        * 
        * @param htSource	Hashtable	数据结果集
        * @param szValue	String 键值名称
        * @return String
        * @throws BusiException
        */
       public static String getNotStrHashtableValue(HashMap htSource, String szValue){
           if (htSource == null)
               return "0";
           String szGetVal = (String) htSource.get(szValue);
           if (szGetVal == null)
               szGetVal = "0";
           else
               szGetVal = szGetVal.trim();
           return szGetVal;
       }
       
	   /**
		* 截取一定长度的字符串
		* 
		* @param str String 源字符串
		* @param len int	 长度
		* @return String
		*/
	   public static String getSubString(String str,int len){
	       int i = str.length();
	       if(i<=len) {
	           return str;
	       } else {
	           return str.substring(0,len)+"...";
	       }
	   }
       
	   /**
		* 截取一定长度的字符串
		* 
		* @param str String 源字符串
		* @param len int	 长度
		* @return String
		*/
	   public static String getSubLenString(String str,int len){
	       int i = str.length();
	       if(i<=len) {
	           return str;
	       } else {
	           return str.substring(0,len)+"<br>"+getSubLenString(str.substring(len),len);
	       }
	   }

	   /**
	   	 * 替换ORACLE SQL中所用到String型参数中的单引号和'&'符号
	   	 * @param 	strSrc		String		源字符串
	   	 * @return	String
	   	 */
	   	public static final String formatOraSql(String strSrc) {
	   	     if(strSrc==null||"".equals(strSrc)) {return strSrc;}
	   	     return replace(strSrc,"'","''");
	   	} 
	   	
	   	/**
	   	 * 判断字符串是否在源字符串已经存在
	   	 * @param source 源字符串
	   	 * @param curStr 要判断的字符串
	   	 * @param sep 分隔符
	   	 * @return boolean
	   	 */
	   	public static boolean isInStr(String source,String curStr,String sep){
	   	    if(isNull(source)){
	   	        return false;
	   	    } else {
	   	        String[] str = source.split(sep);
	   	        for(int i=0;i<str.length;i++){
	   	            if(curStr.equals(str[i])){
	   	                return true;
	   	            }
	   	        }
	   	    }
	   	    return false;
	   	}
	   	
	   	/**
	   	 * 判断字符串是否为空或""
	   	 * 
	   	 * @param str
	   	 * @return boolean
	   	 */
	   	public static boolean isNull(String str){
	   	    if(str==null||"".equals(str.trim())||"null".equalsIgnoreCase(str)){
	   	        return true;	   	     
	   	    } else {
	   	        return false;
	   	    }
	   	}
	   	
	   	
	 	/**
	   	 * 判断字符串是否为空或""
	   	 * 
	   	 * @param str
	   	 * @return boolean
	   	 */
	   	public static boolean isNotBlank(String str){
	   	  return !isNull(str);
	   	}
	   	
	   	
	   	/**
	   	 * 判断字符串中是否仅包含汉字，是返回true，不是返回false
	   	 * @param str
	   	 * @return
	   	 */
	   	public static boolean isOnlyChinese(String str) {
			  String regex = "^[\u4e00-\u9fa5]{0,}$";
			  return str.matches(regex);
		}
	   	/*
	   	 * 转换大小写
	   	 */
	   	private String ConvertSumTotal( String sz_Number, int i_Type ) {
	        String sz_RtnSumTol = "";
	        String sz_Temp;
	        String charSum[] ;

	        if ( "".equals(sz_Number) ) return sz_RtnSumTol;
	        if ( i_Type == 0 ) {
	            charSum = new String[]{"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾"};
	        } else if ( i_Type == 1 ) {
	            charSum = new String[]{"○", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十"};
	        } else {
	            charSum = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ""};
	        }
	        for ( int i = 0; i < sz_Number.length(); i ++ ) {
	            sz_Temp = sz_Number.substring( i, i + 1 );
	            if ( !".".equals(sz_Temp) ) {
	                sz_RtnSumTol = sz_RtnSumTol + charSum[ Integer.parseInt(sz_Temp) ];
	            } else {
	                sz_RtnSumTol = sz_RtnSumTol + ".";
	            }
	        }
	        return sz_RtnSumTol;
	    }
	   	
	   	/**
	   	 * 空字符串处理
	   	 * */
	   	public static String NullToBlank(String s)
		{
			if((s==null)||"".equals(s)||"null".equals(s))
			{
				return "";
			}
			else
			{
				return s;
			}
		}
	   	
	   	/**
	   	 * 空字符串处理,如果为空返回0
	   	 * */
	   	public static String isZero(String s)
		{
			if((s==null)||"".equals(s)||"null".equals(s))
			{
				return "0";
			}
			else
			{
				return s;
			}
		}
	   	
	   	public static String NullBlankToNbsp(String s)
		{
			if(s==null)
			{
				return "&nbsp;";
			}
			else if("".equals(s.trim()))
			{
				return "&nbsp;";
			}
			else
			{
				return s;
			}
		}
		  public static String trimNull(float num){
		  	
		  	if(num==0.0){
		  		
		  		return "";
		  	}
		  	return String.valueOf(num);
		  }
		  public static float trimTo(String num){
		  	
		  	if("".equals(num.trim())){
		  		
		  		return 0;
		  	}
		  	return Float.parseFloat(num);
		  }
		  
		   	public static String NullToBlanPercent(String s)
			{
				if(s==null)
				{
					return "";
				}
				else
				{
					return s+"%";
				}
			}

         /**
          * 符号分隔字符串
          * @param str	字符串数组
          * @param sign	分隔符号
          * 例:str[] = {"张三","李四","王二"}	sign=@
          * @return 张三@李四@王二
          * */
		 public static String signCompartStr(String[] str, String sign){
			 String signStr = "";
			 if (str.length>0){
				 for (int i=0; i<str.length; i++){
					 
					 if (!"".equals(str[i].trim())){
						 
						 signStr = signStr + str[i] + sign;
					 }
				 }
				 
				 if (signStr.length()>sign.length()){
					 
					 signStr = signStr.substring(0, (signStr.length()-sign.length()));
				 }
				 
			 }
			 return signStr;
		 }

    public static String[] getSplitByDou(String s){
    	String[] res = s.trim().replaceAll("，",",").split(",");
        if(null != res){
            for(int i=0;i<res.length;i++){
                res[i] = res[i].trim();
            }
        }
        return res;
    }
		 
		 
		 /**
			 * 在字符左边填充或截断的字符串，使其达到固定的长度
			 * @param srcStr
			 * @param num
			 * @param ch
			 * @return
			 */
			public static String lpad(String srcStr, int num, char ch) {
				String destStr = srcStr;
				if (srcStr.length() > num) {
					destStr = srcStr.substring(srcStr.length() - num);
				} else if (srcStr.length() < num) {
					StringBuilder strBuf = new StringBuilder(16);
					for (int i = 0; i < num - srcStr.length(); i++) {
						strBuf.append(ch);
					}
					destStr = strBuf.toString() + srcStr;
				}
				return destStr;
			}



    /**
     * Code shared by String and StringBuffer to do searches. The
     * source is the character array being searched, and the target
     * is the string being searched for.
     *
     */
    public static Integer[] indexsOf(String s_source, String s_target) {
        
        char[] source = s_source.toCharArray();
        int sourceOffset=0;
        int sourceCount =source.length;
        int fromIndex =0;
        char[] target = s_target.toCharArray();
        int targetCount = target.length;
        ArrayList ret = new ArrayList();
        if (fromIndex >= sourceCount) {
                return null;
        }
            if (fromIndex < 0) {
                fromIndex = 0;
            }
        if (targetCount == 0) {
            return null;
        }

        int max = sourceOffset + (sourceCount - targetCount);

        for (int i = sourceOffset + fromIndex; i <= max; i++) {
            /* Look for first character. */
            /*if (source[i] != first) {
                while (++i <= max && source[i] != first);
            }*/

            /* Found first character, now look at the rest of v2 */
                int j = i + 1;
                int end = j + targetCount - 1;
                /*for (int k = targetOffset + 1; j < end && source[j] ==
                         target[k]; j++, k++);*/

                if (j == end) {
                    /* Found whole string. */
                    ret.add(new Integer(i - sourceOffset)) ;
                }
        }
        if(!ret.isEmpty()){
            return (Integer[])ret.toArray(new Integer[0]);
        }else{
            return null;
        }
    }
    
    public static String replaceHtml(String str) {
        String tmpstr = str;
        tmpstr = replace(tmpstr, "<br>", "\n");
        tmpstr = replace(tmpstr, "&lt;", "<");
        tmpstr = replace(tmpstr, "&gt;", ">");
        tmpstr = replace(tmpstr, "&amp;", "&");
        tmpstr = replace(tmpstr, "&apos;", "\'");
        tmpstr = replace(tmpstr, "&quot;", "\"");
        tmpstr = replace(tmpstr, "&nbsp;", " ");
        return tmpstr;
    }
    public static String appendStr(Object ... args){
		if(args!=null){
			StringBuilder sb=new StringBuilder();
			for (int i = 0; i < args.length; i++) {
				sb.append(args[i]);
			}
			return sb.toString();
		}
		return "";
	}
	public static String getStringForObj(Object str){
		if(str==null){
			return "";
		}else{
			return String.valueOf(str);
		}
	}
	/**
     * 判断是否为乱码
     * 
     * @param str
     * @return
     */
    public static boolean isMessyCode(String str) {
     for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      // 当从Unicode编码向某个字符集转换时，如果在该字符集中没有对应的编码，则得到0x3f（即问号字符?）
      //从其他字符集向Unicode编码转换时，如果这个二进制数在该字符集中没有标识任何的字符，则得到的结果是0xfffd
      //System.out.println("--- " + (int) c);
      if ((int) c == 0xfffd) {
       // 存在乱码
       //System.out.println("存在乱码 " + (int) c);
       return true;
      }
     }
     return false; 
    }
}

