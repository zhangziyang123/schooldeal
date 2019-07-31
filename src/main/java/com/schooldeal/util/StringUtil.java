package com.schooldeal.util;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * <p>FileName: StringUtil.java</p>
 * 
 * <p>Title: �ַ�����������</p>
 *
 * <p>Description: ���ڶ��ַ��������滻��ת���Ȳ���</p>
 *
 * <p>CreateDate: 2009-8-19</p>
 *
 * @author <a href="mailto:njxyf007@hotmail.com">������</a>
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
	 * �ַ����滻
	 * 
	 * @param 	strSrc		String		Դ�ַ���
	 * @param 	strOld		String		���滻���ַ���
	 * @param 	strNew		String		���滻���ַ���
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
	 * ���ַ��е�html�������"<",">"���ɶ�Ӧ��ת���
	 * 
	 * @param s		String	�ַ���
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
	 * ���ַ�����"\n","\r\n"����hmtl��ǩ
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
     * ��Щ��Ӧ��ת����sql�ַ���
     * 
     * @param s		String		Ҫת�����ַ���
     * @param s1	String		like������
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
                if("NOT".equals(as[i]) || "!".equals(as[i]) || "��".equals(as[i]))
                {
                    s2 = s2 + " not ";
                    flag = true;
                    continue;
                }
                if("(".equals(as[i]) || "��".equals(as[i]))
                {
                    s2 = s2 + " ( ";
                    flag = true;
                    continue;
                }
                if(")".equals(as[i]) || "��".equals(as[i]))
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
     * ���ַ�������html����
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
     * ���ַ���ת����html�ַ���
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
     * ��string ת��Ϊint
     * @param     str      Ҫת�����ַ���
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
      * ����Ҫ��ȡ���ַ����еķ�����
      * @param   sz_theChar    	String	Ҫ��ȡ���ַ���
      * @param   sz_Separator	String	�ָ���
      * @return  int
      * eg:
      * 	��sz_theChar="1,2,3,4";sz_Separator=",";��ô�ͷ���4
      * 
      */
      public static int getCharTolNum( String sz_theChar, String sz_Separator )
      {
          return sz_theChar.split(sz_Separator).length;
      }
     
      /**
       * ����Ҫ��ȡ���ַ����еķ�����
       * @param   sz_theChar    	String	Ҫ��ȡ���ַ���
       * @param   sz_Separator	String	�ָ���
       * @return  String[]
       * eg:
       * 	��sz_theChar="1,2,3,4";sz_Separator=",";��ô�ͷ���String[]={"1","2","3","4"}
       * 
       */
       public static String[] getSepStr( String sz_theChar, String sz_Separator )
       {
           return sz_theChar.split(sz_Separator);
       }
      
     /**
      * ����Ҫ��ȡ���ַ����еķ���
      * @param    sz_theChar 		String	Ҫ��ȡ���ַ���
      * @param    sz_Separator		String	�ָ���
      * @param    i_CharNum		String	Ҫȡ�õ��ַ���λ��
      * @return   String
      * eg:
      * 	��sz_theChar="1,3,2,4";sz_Separator=",";i_CharNum=2;��ô�ͷ���"3"
      * 
      */
      public static String getSeparatorChar( String sz_theChar, String sz_Separator, int i_CharNum )
      {
          String[] str = sz_theChar.split(sz_Separator);
          return str[i_CharNum - 1];
      }
      
      /**
       * ���·ݵĸ�ʽ������ת����Ӣ�ļ�ƴ
       * @param    i_theMonth int		���ָ�ʽ���·�
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
        * ת���ַ����м�ķָ���(��Ҫ����sql�����in(xx,xx))
        * 
        * @param     szAllString      String    ��Ҫת�����ַ���       <p>
        * @param     iFlag            int       1 ������ 0 ��������    <P>
        * @param     szCharFirst      String    ���滻���ַ�           <p>
        * @param     szCharSecond     String    �滻Ϊ���ַ�           <P>
        * @return    String                     �滻�Ժ���ַ���       <P>
        */
      /* public static String getRecombineStr(String szAllString,int iFlag,String szCharFirst,String szCharSecond)
       {
           String szReformString = "" ;
           // String szTempString = "" ;
           int iIndexOf;
           // iFlag==1 varchar , iFlag==0 number
           // szCharFirst ��ʼ�ָ���
           // szCharSecond �����ķָ���
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
        * ����varchar,date(ʵ���ж�Ӧ������String)
        * 
        * @param htSource Hashtable	���ݽ����
        * @param szValue	String ��ֵ����
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
        * ����long,int,float,double
        * 
        * @param htSource	Hashtable	���ݽ����
        * @param szValue	String ��ֵ����
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
		* ��ȡһ�����ȵ��ַ���
		* 
		* @param str String Դ�ַ���
		* @param len int	 ����
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
		* ��ȡһ�����ȵ��ַ���
		* 
		* @param str String Դ�ַ���
		* @param len int	 ����
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
	   	 * �滻ORACLE SQL�����õ�String�Ͳ����еĵ����ź�'&'����
	   	 * @param 	strSrc		String		Դ�ַ���
	   	 * @return	String
	   	 */
	   	public static final String formatOraSql(String strSrc) {
	   	     if(strSrc==null||"".equals(strSrc)) {return strSrc;}
	   	     return replace(strSrc,"'","''");
	   	} 
	   	
	   	/**
	   	 * �ж��ַ����Ƿ���Դ�ַ����Ѿ�����
	   	 * @param source Դ�ַ���
	   	 * @param curStr Ҫ�жϵ��ַ���
	   	 * @param sep �ָ���
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
	   	 * �ж��ַ����Ƿ�Ϊ�ջ�""
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
	   	 * �ж��ַ����Ƿ�Ϊ�ջ�""
	   	 * 
	   	 * @param str
	   	 * @return boolean
	   	 */
	   	public static boolean isNotBlank(String str){
	   	  return !isNull(str);
	   	}
	   	
	   	
	   	/**
	   	 * �ж��ַ������Ƿ���������֣��Ƿ���true�����Ƿ���false
	   	 * @param str
	   	 * @return
	   	 */
	   	public static boolean isOnlyChinese(String str) {
			  String regex = "^[\u4e00-\u9fa5]{0,}$";
			  return str.matches(regex);
		}
	   	/*
	   	 * ת����Сд
	   	 */
	   	private String ConvertSumTotal( String sz_Number, int i_Type ) {
	        String sz_RtnSumTol = "";
	        String sz_Temp;
	        String charSum[] ;

	        if ( "".equals(sz_Number) ) return sz_RtnSumTol;
	        if ( i_Type == 0 ) {
	            charSum = new String[]{"��", "Ҽ", "��", "��", "��", "��", "½", "��", "��", "��", "ʰ"};
	        } else if ( i_Type == 1 ) {
	            charSum = new String[]{"��", "һ", "��", "��", "��", "��", "��", "��", "��", "��", "ʮ"};
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
	   	 * ���ַ�������
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
	   	 * ���ַ�������,���Ϊ�շ���0
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
          * ���ŷָ��ַ���
          * @param str	�ַ�������
          * @param sign	�ָ�����
          * ��:str[] = {"����","����","����"}	sign=@
          * @return ����@����@����
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
    	String[] res = s.trim().replaceAll("��",",").split(",");
        if(null != res){
            for(int i=0;i<res.length;i++){
                res[i] = res[i].trim();
            }
        }
        return res;
    }
		 
		 
		 /**
			 * ���ַ��������ضϵ��ַ�����ʹ��ﵽ�̶��ĳ���
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
     * �ж��Ƿ�Ϊ����
     * 
     * @param str
     * @return
     */
    public static boolean isMessyCode(String str) {
     for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      // ����Unicode������ĳ���ַ���ת��ʱ������ڸ��ַ�����û�ж�Ӧ�ı��룬��õ�0x3f�����ʺ��ַ�?��
      //�������ַ�����Unicode����ת��ʱ�����������������ڸ��ַ�����û�б�ʶ�κε��ַ�����õ��Ľ����0xfffd
      //System.out.println("--- " + (int) c);
      if ((int) c == 0xfffd) {
       // ��������
       //System.out.println("�������� " + (int) c);
       return true;
      }
     }
     return false; 
    }
}

