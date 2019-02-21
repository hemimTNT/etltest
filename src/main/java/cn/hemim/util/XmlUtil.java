package cn.hemim.util;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @description: xxx</   br>
 * @author: Hemim
 * @date: 2019-02-16
 */
public class XmlUtil {

    private static SAXReader reader = new SAXReader();
    private static Map<String, String> mapping = new HashMap<>();
    private static Map<String, String> classMap = new HashMap<>();

    private static void initMapping() {
        try {
            Document document = reader.read(XmlUtil.class.getResourceAsStream("/output-mapping.xml"));
            Document documentClass = reader.read(XmlUtil.class.getResourceAsStream("/output-writter.xml"));
            Element configuration = document.getRootElement();
            Element configurationClass = documentClass.getRootElement();
            // 迭代property元素
            List<Element> list = configuration.elements();
            List<Element> listClass = configurationClass.elements();
            for (Element property : list) {
                mapping.put(property.element("name").getStringValue(), property.element("value").getStringValue());
            }
            for (Element property : listClass) {
                classMap.put(property.element("name").getStringValue(), property.element("value").getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static String readSql(String name) {
        if (mapping.size() == 0) {
            initMapping();
        }
        return mapping.get(name);
    }

    public static String readClassName(String name) {
        if (mapping.size() == 0) {
            initMapping();
        }
        return classMap.get(name);
    }
}
