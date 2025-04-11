package com.effort.util;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;



import com.effort.settings.Constants;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

public class SecurityUtils {
	public static final int INPUT_TYPE_TEXT = 7;
	public static final int INPUT_TYPE_NAME = 1;
	public static final int INPUT_TYPE_ADDRESS = 2;
	public static final int INPUT_TYPE_EMAIL = 3;
	public static final int INPUT_TYPE_COMPANY_NAME = 4;
	public static final int INPUT_TYPE_PHONE = 5;
	public static final int INPUT_TYPE_COMPANY_LABEL = 6;
	public static final int INPUT_TYPE_FORM_OR_ENTITY_FIELD_LABEL = 8;
	public static final int INPUT_TYPE_FORM_OR_ENTITY_TITLE = 9;
	public static final int INPUT_TYPE_OTHER_TITLE = 10;
	public static final int INPUT_TYPE_COMPANY_LEVEL_ID = 11;
	public static final int INPUT_TYPE_CAPTCHA = 12;
	public static final int INPUT_TYPE_NUMBER = 13;
	public static final int INPUT_TYPE_NUMBER_CSV = 14;
	public static final int INPUT_TYPE_ALPAHBET = 15;
	public static final int INPUT_TYPE_ALPAHBET_WITH_UNDERSCORE = 16;
	public static final int INPUT_TYPE_ALPAHBET_WITH_HYPHEN = 17;
	public static final int INPUT_TYPE_ALPAHBET_WITH_HYPHEN_AND_UNDERSCORE = 18;
	public static final int INPUT_TYPE_DATE_TIME = 19;
	public static final int INPUT_TYPE_JSON = 20;
	public static final int INPUT_TYPE_BOOLEAN = 21;
	public static final int INPUT_TYPE_LOCATION = 22;
	public static final int INPUT_TYPE_SQL_ORDER = 23;
	public static final int INPUT_TYPE_URL = 24;
	public static final int INPUT_TYPE_ALPHA_NUMERIC = 25;
	public static final int INPUT_TYPE_EXPRESSION = 26;
	public static final int INPUT_TYPE_POSITIVE_NUMBER_CSV = 27;
	public static final int INPUT_TYPE_DURATION = 28;
	public static final int INPUT_TYPE_PRINT_TEMPLATE = 29;
	
	
	//Getting from xml file                                                                                                                    *Resource: Deva*/
		public static String exprForTypeName;
		public static String exprForTypeAddress; 
		public static String exprForTypeEmail;
		public static String exprForTypeCompanyName; 
		public static String exprForTypePhone;
		public static String exprForTypeCompanyLabel;
		public static String exprForTypeText;
		public static String exprForTypeFormOrEntityFieldlabel;
		public static String exprForTypeFormOrEntityTitle;
		public static String exprForTypeOtherTitle;
		public static String exprForTypeCompanyLevelId;
		public static String exprForTypeCaptcha;
		public static String exprForTypeNumber; 
		public static String exprForTypeNumberCsv; 
		public static String exprForTypeAlphabet; 
		public static String exprForTypeAlpahbetWithUnderscore; 
		public static String exprForTypeAlpahbetWithHyphen; 
		public static String exprForTypeAlpahbetWithHyphenAndUnderscore; 
		public static String exprForTypeDatetime; 
		public static String exprForTypeJson;
		public static String exprForTypeBoolean; 
		public static String exprForTypeLocation; 
		public static String exprForTypeSqlOrder; 
		public static String exprForSkipNewLine;
		public static String exprForTypeUrl;
		public static String exprForTypeAlphaNumeric;
		public static String exprForTypeExpression;
		public static String exprForTypePositiveNumberCsv; 
		public static String exprForTypeDuration;
		
		
	
	public static int getUserInputTypeForField(int fieldType) 
	{
		switch(fieldType)
		{
			case Constants.FORM_FIELD_TYPE_TEXT : return INPUT_TYPE_TEXT;
			case Constants.FORM_FIELD_TYPE_NUMBER : return INPUT_TYPE_NUMBER;
			case Constants.FORM_FIELD_TYPE_DATE : return INPUT_TYPE_DATE_TIME;
			case Constants.FORM_FIELD_TYPE_YES_NO : return INPUT_TYPE_BOOLEAN;
			case Constants.FORM_FIELD_TYPE_SINGLE_SELECT_LIST : return INPUT_TYPE_NUMBER;
			case Constants.FORM_FIELD_TYPE_MULTI_SELECT_LIST : return INPUT_TYPE_NUMBER_CSV;
			case Constants.FORM_FIELD_TYPE_CUSTOMER : return INPUT_TYPE_NUMBER;
			case Constants.FORM_FIELD_TYPE_EMAIL : return INPUT_TYPE_EMAIL;
			case Constants.FORM_FIELD_TYPE_PHONE : return INPUT_TYPE_PHONE;
			case Constants.FORM_FIELD_TYPE_TIME : return INPUT_TYPE_DATE_TIME;
			case Constants.FORM_FIELD_TYPE_LIST : return INPUT_TYPE_NUMBER;
			case Constants.FORM_FIELD_TYPE_EMPLOYEE : return INPUT_TYPE_NUMBER;
			case Constants.FORM_FIELD_TYPE_CURRENCY : return INPUT_TYPE_NUMBER;
			case Constants.FORM_FIELD_TYPE_MULTIPICK_LIST : return INPUT_TYPE_NUMBER_CSV;
			case Constants.FORM_FIELD_TYPE_LOCATION : return INPUT_TYPE_LOCATION;
			case Constants.FORM_FIELD_TYPE_DATE_TIME : return INPUT_TYPE_DATE_TIME;
			case Constants.FORM_FIELD_TYPE_COUNTRY : return INPUT_TYPE_TEXT;
			case Constants.FORM_FIELD_TYPE_NUMBER_TO_WORD : return INPUT_TYPE_TEXT;
			case Constants.FORM_FIELD_TYPE_CUSTOMER_TYPE : return INPUT_TYPE_NUMBER;
			case Constants.FORM_FIELD_TYPE_FORM : return INPUT_TYPE_NUMBER;
			case Constants.FORM_FIELD_TYPE_TIMESPAN : return INPUT_TYPE_DATE_TIME;
			case Constants.FORM_FIELD_TYPE_AUTOGENERATED : return INPUT_TYPE_TEXT;
			case Constants.FORM_FIELD_TYPE_LABEL : return INPUT_TYPE_TEXT;
			case Constants.FORM_FIELD_TYPE_MULTIPICK_CUSTOMER : return INPUT_TYPE_NUMBER_CSV;
			case Constants.FORM_FIELD_TYPE_TERRITORY : return INPUT_TYPE_NUMBER;
			case Constants.FORM_FIELD_TYPE_CUSTOM_ENTITY : return INPUT_TYPE_NUMBER;
			case Constants.FORM_FIELD_TYPE_URL : return INPUT_TYPE_URL;
		}
		return -1;
	}

	
	
	
	private static String getRegexPattern(int userInputType) {
	    switch (userInputType) {
	        case INPUT_TYPE_NAME: return exprForTypeName;
	        case INPUT_TYPE_ADDRESS: return exprForTypeAddress;
	        case INPUT_TYPE_EMAIL: return exprForTypeEmail;
	        case INPUT_TYPE_COMPANY_NAME: return exprForTypeCompanyName;
	        case INPUT_TYPE_PHONE: return exprForTypePhone;
	        case INPUT_TYPE_COMPANY_LABEL: return exprForTypeCompanyLabel;
	        case INPUT_TYPE_TEXT: return exprForTypeText;
	        case INPUT_TYPE_FORM_OR_ENTITY_FIELD_LABEL: return exprForTypeFormOrEntityFieldlabel;
	        case INPUT_TYPE_FORM_OR_ENTITY_TITLE: return exprForTypeFormOrEntityTitle;
	        case INPUT_TYPE_OTHER_TITLE: return exprForTypeOtherTitle;
	        case INPUT_TYPE_COMPANY_LEVEL_ID: return exprForTypeCompanyLevelId;
	        case INPUT_TYPE_CAPTCHA: return exprForTypeCaptcha;
	        case INPUT_TYPE_NUMBER: return exprForTypeNumber;
	        case INPUT_TYPE_NUMBER_CSV: return exprForTypeNumberCsv;
	        case INPUT_TYPE_ALPAHBET: return exprForTypeAlphabet;
	        case INPUT_TYPE_ALPAHBET_WITH_UNDERSCORE: return exprForTypeAlpahbetWithUnderscore;
	        case INPUT_TYPE_ALPAHBET_WITH_HYPHEN: return exprForTypeAlpahbetWithHyphen;
	        case INPUT_TYPE_ALPAHBET_WITH_HYPHEN_AND_UNDERSCORE: return exprForTypeAlpahbetWithHyphenAndUnderscore;
	        case INPUT_TYPE_DATE_TIME: return exprForTypeDatetime;
	        case INPUT_TYPE_JSON: return exprForTypeJson;
	        case INPUT_TYPE_BOOLEAN: return exprForTypeBoolean;
	        case INPUT_TYPE_LOCATION: return exprForTypeLocation;
	        case INPUT_TYPE_SQL_ORDER: return exprForTypeSqlOrder;
	        case INPUT_TYPE_URL: return exprForTypeUrl;
	        case INPUT_TYPE_ALPHA_NUMERIC: return exprForTypeAlphaNumeric;
	        case INPUT_TYPE_EXPRESSION: return exprForTypeExpression;
	        default: return null;
	    }
	}
	public static String stripInvalidCharacters(String value, int userInputType) {
	    if (value == null) {
	        return null;
	    }

	    value = value.trim(); 

	    String regexPattern = getRegexPattern(userInputType);
	    if (regexPattern != null) {
	        value = value.replaceAll(regexPattern, "");
	    }

	    // Special case for SQL Order: If invalid, default to "ASC"
	    if (userInputType == INPUT_TYPE_SQL_ORDER && !"ASC".equalsIgnoreCase(value) && !"DESC".equalsIgnoreCase(value)) {
	        value = "ASC";
	    }

	    return value;
	}
	/***** This method strip XSS and SQL codes by removing malicious code*/
	public static String stripXSSAndSqlForPrintTemplate(String value, boolean noQuotes) {
        if (value != null) {
            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
            // avoid encoded attacks.
            // value = ESAPI.encoder().canonicalize(value);

            // Avoid null characters
            value = value.replaceAll("", "");

            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            
         // Avoid anything in a alert() functions type of expression
            scriptPattern = Pattern.compile("alert\\((.*)\\)", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            
            /*Date: 2016-05-09
			*Method Purpose: hadling confirm and prompt 
			*Resource: Deva*/
            // Avoid anything in a alert() functions type of expression
            scriptPattern = Pattern.compile("confirm\\((.*)\\)", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            
            // Avoid anything in a alert() functions type of expression
            scriptPattern = Pattern.compile("prompt\\((.*)\\)", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid eval(...) expressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid javascript:... expressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid vbscript:... expressions
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid onload= expressions
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            
         // Avoid onclick= expressions
            scriptPattern = Pattern.compile("onclick(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            
            
            String ipPingPattern  = "ping(.*)(?:[0-9]{1,3}\\.){3}[0-9]{1,3}"; //OScommand injection
            scriptPattern = Pattern.compile(ipPingPattern, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            
            value = StringUtils.replaceEach(value, new String[]{"startstarspoorsLessthanstartstar", "startstarspoorsGreaterthanstartstar"}, new String[]{"<", ">"});
        }
        return value;
    }
	
	
	/***** This method strip XSS and SQL codes by removing malicious code*/
	public static String stripXSSAndSql(String value, boolean noQuotes) {
        if (value != null) {
            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
            // avoid encoded attacks.
            // value = ESAPI.encoder().canonicalize(value);

            // Avoid null characters
            value = value.replaceAll("", "");

            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            
         // Avoid anything in a alert() functions type of expression
            scriptPattern = Pattern.compile("alert\\((.*)\\)", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            
            /*Date: 2016-05-09
			*Method Purpose: hadling confirm and prompt 
			*Resource: Deva*/
            // Avoid anything in a alert() functions type of expression
            scriptPattern = Pattern.compile("confirm\\((.*)\\)", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            
            // Avoid anything in a alert() functions type of expression
            scriptPattern = Pattern.compile("prompt\\((.*)\\)", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            

            // Avoid anything in a src='...' type of expression
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid eval(...) expressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid expression(...) expressions
            scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid javascript:... expressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid vbscript:... expressions
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid onload= expressions
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            
         // Avoid onclick= expressions
            scriptPattern = Pattern.compile("onclick(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            
            
            String ipPingPattern  = "ping(.*)(?:[0-9]{1,3}\\.){3}[0-9]{1,3}"; //OScommand injection
            scriptPattern = Pattern.compile(ipPingPattern, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            
            if(noQuotes)
            {
	            value = value.replace("\"", "**spoorsDoubleQuote**");
	            value = value.replace("&", "**spoorsAmp**");
	            
	            value = value.replace("’", "**rightSingleQuote**");
	            value = value.replace("”", "**rightDoubleQuote**");
            }
			/* value = stringUtilEscapeHtml(value); */
            //value = escapeHtml(value);
           // value = escapeSql(value);
            value = value.replaceAll("&#39;", "'");
            /*value = value.replaceAll("&auml;", "ä");
            value = value.replaceAll("&ouml;", "ö");
            value = value.replaceAll("&uuml;", "ü");
            value = value.replaceAll("&eacute;", "é");
            value = value.replaceAll("&egrave;", "è");
            value = value.replaceAll("&ccedil;", "ç");
            value = value.replaceAll("&ntilde;", "ñ");
            
            value = value.replaceAll("&Auml;", "Ä");
            value = value.replaceAll("&Ouml;", "Ö");
            value = value.replaceAll("&Uuml;", "Ü");
            value = value.replaceAll("&igrave;", "ì");
            value = value.replaceAll("&ecirc;", "ê");
            value = value.replaceAll("&Agrave;", "À");
            
            value = value.replaceAll("&Atilde;", "Ã");
            
            value = value.replaceAll("&Egrave;", "È");
            
            value = value.replaceAll("&Ecirc;", "Ê");
            
            value = value.replaceAll("&Igrave;", "Ì");
            
            value = value.replaceAll("&Iacute;", "Í");
            
            
            value = value.replaceAll("&Ograve;", "Ò");
            value = value.replaceAll("&Otilde;", "Õ");
            value = value.replaceAll("&Ugrave;", "Ù");
            
            value = value.replaceAll("&Uacute;", "Ú");
            value = value.replaceAll("&Yacute;", "Ý");
            
            
            value = value.replaceAll("&agrave;", "à");
            value = value.replaceAll("&atilde;", "ã");
            
            value = value.replaceAll("&egrave;", "è");            
            value = value.replaceAll("&ecirc;", "ê");
            value = value.replaceAll("&igrave;", "ì");          
            value = value.replaceAll("&iacute;", "í");                        
            value = value.replaceAll("&ograve;", "ò");
            value = value.replaceAll("&otilde;", "õ");
            value = value.replaceAll("&ugrave;", "ù");            
            value = value.replaceAll("&uacute;", "ú");
            value = value.replaceAll("&yacute;", "ý");
            value = value.replaceAll("&ocirc;", "ô");
            value = value.replaceAll("&aacute;", "á");
            value = value.replaceAll("&acirc;", "â");*/

            if(noQuotes)
            {
	            value = value.replace("**spoorsDoubleQuote**", "\"");
	            value = value.replace("**spoorsAmp**", "&");
	            
	            value = value.replace("**rightSingleQuote**","’");
	            value = value.replace("**rightDoubleQuote**","”");
            }
            //value = StringUtils.replaceEach(value, new String[]{"&lt;", "&gt;"}, new String[]{"<", ">"});
            value = StringUtils.replaceEach(value, new String[]{"startstarspoorsLessthanstartstar", "startstarspoorsGreaterthanstartstar"}, new String[]{"<", ">"});
        }
        return value;
    }
	
	
	

}
