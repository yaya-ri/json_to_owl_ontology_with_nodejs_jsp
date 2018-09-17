/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonToOntology;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author yaya_aye
 */
public class GenerateData {
    public void RootLevel(JsonNode node, ArrayList<String> listRoot) {
        Iterator<String> fieldNames = node.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode fieldValue = node.get(fieldName);
            if (fieldValue.isObject()) {
                listRoot.add(fieldName);
            }
        }
    }

    public void ListClass(JsonNode node, ArrayList<String> listClassFromOB) {
        ArrayList<String> listClass = listClassFromOB;
        Iterator<String> fieldNames = node.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode fieldValue = node.get(fieldName);
            if (fieldValue.isObject() && !fieldName.equalsIgnoreCase("required")) {
                listClassFromOB.add(fieldName);
                ListClass(fieldValue, listClass);
            }
        }
    }

    public void GetTmpObjectProperty(JsonNode node, ArrayList<String> listObjectTypeProperty, String root) {
        ArrayList<String> testList = listObjectTypeProperty;
        Iterator<String> fieldNames = node.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            listObjectTypeProperty.add("break");
            JsonNode fieldValue = node.get(fieldName);
            if (fieldValue.isObject()) {
                listObjectTypeProperty.add(root);
                listObjectTypeProperty.add(fieldName);
                GetTmpObjectProperty(fieldValue, testList, fieldName);
            }
        }
    }

    public void GenerateDataTypeProperty(JsonNode node, ArrayList<String> list2, ArrayList<String> list3) {
        ArrayList<String> listClasstoProperty = list2;
        ArrayList<String> listDataTpyetoinstance = list3;
        Iterator<String> fieldNames = node.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode fieldValue = node.get(fieldName);
            if (fieldValue.isObject() && !fieldName.equalsIgnoreCase("required")) {
                list2.add("break");
                list2.add(fieldName);
                GenerateDataTypeProperty(fieldValue, listClasstoProperty, listDataTpyetoinstance);
            } else if (fieldValue.isArray() && !fieldName.equalsIgnoreCase("required")) {
                System.out.println(fieldName);
                System.out.println("");
                ArrayNode array = (ArrayNode) node.get(fieldName);
                Iterator<JsonNode> fieldNamesArray = array.elements();
                while (fieldNamesArray.hasNext()) {
                    JsonNode names = fieldNamesArray.next();
                    Iterator<String> fieldArray = names.fieldNames();
                    Iterator<JsonNode> value = names.elements();
                    while (fieldArray.hasNext()) {
                        //System.out.print("  " + fieldArray.next() + ": ");
//                        String isi = regex(value.next().toString());
//                        System.out.println(isi);
                        // System.out.println(value.next().asText());
                    }
                    System.out.println("");
                }
            } else {
                if (!fieldName.equalsIgnoreCase("required")) {
                    String value = fieldValue.asText();
                    String dataType = fieldValue.getNodeType().toString();
                    list2.add(fieldName);
                    list3.add("break");
                    list3.add(fieldName);
                    list3.add(value);
                    list3.add(dataType);
                }
            }
        }
    }

}
