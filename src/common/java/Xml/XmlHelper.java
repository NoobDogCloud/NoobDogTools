package common.java.Xml;

import common.java.nLogger.nLogger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.gsc.JSONArray;
import org.json.gsc.JSONObject;

import java.util.Iterator;

public class XmlHelper {
	public static JSONObject toJson(String xmlstr) {
		return toJson(xmlstr, new JSONObject());
	}

	/**
	 * xml转json
	 *
	 * @param xmldoc
	 * @return
	 */
	public static JSONObject toJson(Document xmldoc) {
		return toJson(xmldoc, new JSONObject());
	}

	public static JSONObject toJson(String xmldoc, JSONObject rs) {
		JSONObject json;
		try {
			json = toJson(DocumentHelper.parseText(xmldoc), rs);
		} catch (DocumentException e) {
			json = null;
		}
		return json;
	}

	/**
	 * 把xmldoc转换成JSON后添加到rs里
	 *
	 * @param xmldoc
	 * @param rs
	 * @return
	 */
	public static JSONObject toJson(Document xmldoc, JSONObject rs) {
		JSONObject robj;
		Element root = xmldoc.getRootElement();
		robj = childrenToJson(root);
		rs.putAll(robj);
		return rs;
	}

	public static Document toXml(String jsonStr) {
		return toXml(JSONObject.toJSON(jsonStr));
	}

	public static Document toXml(JSONObject json) {
		Element element;
		Document document = DocumentHelper.createDocument();
		if (json != null) {
			for (String _obj : json.keySet()) {
				element = DocumentHelper.createElement(_obj);
				element.add(DocumentHelper.createCDATA((String) json.get(_obj)));
				document.add(element);
			}
		}
		return document;
	}

	public static Element appendJsonToXml(Element fatherNode, JSONObject json) {
		String key;
		Object _item;
		Element contentNode;
		for (Object _obj : json.keySet()) {
			key = _obj.toString();
			contentNode = DocumentHelper.createElement(key);//创建
			_item = json.get(key);
			if (_item instanceof JSONObject) {//内容是JSON
				contentNode = appendJsonToXml(contentNode, (JSONObject) _item);
			} else if (_item instanceof JSONArray) {
				JSONArray ary = (JSONArray) _item;
				for (Object _json : ary) {
					contentNode = appendJsonToXml(contentNode, (JSONObject) _json);
				}
			} else {
				contentNode.add(DocumentHelper.createCDATA(json.get(key).toString()));
			}
			fatherNode.add(contentNode);
		}
		return fatherNode;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject childrenToJson(Element fatherNode) {
		String key;
		Element element;
		Object tmp;
		Object array;
		JSONObject rs = new JSONObject();//生成的结果只有最后一个节点
		JSONObject tmpRS;
		for (Iterator<Element> i = fatherNode.elementIterator(); i.hasNext(); ) {
			element = i.next();
			tmpRS = childrenToJson(element);
			//遇到名字相同的，生成jsonarray;
			key = element.getName();
			if (tmpRS.containsKey(key)) {
				tmp = tmpRS.get(key);
				array = (tmp instanceof JSONArray) ? ((JSONArray) tmp) : new JSONArray();
				((JSONArray) array).add(element.getData());
			} else {
				array = element.getData();
			}
			rs.put(element.getName(), array);
		}
		return rs;
	}

	public static Element createCDATA(String nodeName, String nodeValue) {
		Element node = DocumentHelper.createElement(nodeName);
		node.add(DocumentHelper.createCDATA(nodeValue));
		return node;
	}

	public static Element createLong(String nodeName, Long nodeValue) {
		Element node = DocumentHelper.createElement(nodeName);
		node.add(DocumentHelper.createText(String.valueOf(nodeValue)));
		return node;
	}

	public static Document string2xml(String xmlString) {
		try {
			return DocumentHelper.parseText(xmlString);
		} catch (DocumentException e) {
			nLogger.logInfo(e);
			return null;
		}
	}

	public static String toString(Document xmldoc) {
		return xmldoc.asXML();
	}

	public static Element createElement(String nodeName) {
		return DocumentHelper.createElement(nodeName);
	}

}
