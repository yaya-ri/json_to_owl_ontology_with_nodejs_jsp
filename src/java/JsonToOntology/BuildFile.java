/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonToOntology;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author yaya_aye
 */
public class BuildFile {

    /**
     * @param jsonFIle
     * @param JsonSchema
     * @return 
     * @throws java.io.FileNotFoundException
     */
    public String BuildFile(String jsonFIle, String JsonSchema) throws FileNotFoundException, IOException {
        //instance generate data class
        GenerateData getData = new GenerateData();
        BuildOntology ontologyInstance = new BuildOntology();

        //---------------------------------------------------------------------------------------------------------------------------------------- 
        //load json schema and get root node
        ArrayList<String> listRoot = new ArrayList();
        JsonNode json_schema = new ObjectMapper().readTree(new FileReader("C:\\Users\\yaya_aye\\Documents\\NetBeansProjects\\Tugas_akhir_web\\json-schema-generator\\"+JsonSchema));
        String root_schema = "properties";
        JsonNode schema_node = json_schema.get(root_schema);
        getData.RootLevel(schema_node, listRoot);
        System.out.println("");
        System.out.println("Root Node: ");
        System.out.println("    " + listRoot.toString());
        System.out.println("");
        //----------------------------------------------------------------------------------------------------------------------------------------

        //get Class from object with root node parameter
        ArrayList<String> listClass = new ArrayList();
        JsonNode json_file = new ObjectMapper().readTree(new FileReader("C:\\Users\\yaya_aye\\Documents\\NetBeansProjects\\Tugas_akhir_web\\json-schema-generator\\"+jsonFIle));
        for (int i = 0; i < listRoot.size(); i++) {
            JsonNode data_node = json_file.get(listRoot.get(i));
            getData.ListClass(data_node, listClass);
        }
        System.out.println("List Class: ");
        System.out.println("    " + listClass.toString());
        System.out.println("");
        //----------------------------------------------------------------------------------------------------------------------------------------

        //get Object Property || (class : class)
        ArrayList<String> tmpObjectProperty = new ArrayList();
        JsonNode json = new ObjectMapper().readTree(new FileReader("C:\\Users\\yaya_aye\\Documents\\NetBeansProjects\\Tugas_akhir_web\\json-schema-generator\\"+jsonFIle));
        for (int i = 0; i < listRoot.size(); i++) {
            JsonNode parse = json.get(listRoot.get(i));
            getData.GetTmpObjectProperty(parse, tmpObjectProperty, listRoot.get(i));
            System.out.println("Object Property || (class : class): ");
        }
        //System.out.println(tmpObjectProperty.toString());
        //System.out.println("");
        int sizeObjectProperty = 0;

        for (int j = 0; j < tmpObjectProperty.size(); j++) {
            if (!tmpObjectProperty.get(j).equalsIgnoreCase("break")) {
                //System.out.println("    [" + tmpObjectProperty.get(j) + "],[" + tmpObjectProperty.get(j + 1) + "]");
                j++;
                sizeObjectProperty = sizeObjectProperty + 1;
            }
        }

        ArrayList<String>[] listObjectProperty = new ArrayList[sizeObjectProperty];
        for (int i = 0; i < sizeObjectProperty; i++) {
            listObjectProperty[i] = new ArrayList<>();
        }

        int pointer = 0;
        for (int i = 0; i < tmpObjectProperty.size(); i++) {
            if (!tmpObjectProperty.get(i).equalsIgnoreCase("break")) {
                listObjectProperty[pointer].add(tmpObjectProperty.get(i));
                listObjectProperty[pointer].add(tmpObjectProperty.get(i + 1));
                i++;
                pointer++;
            }
        }

        for (int i = 0; i < listObjectProperty.length; i++) {
            System.out.println("    " + listObjectProperty[i].toString());
        }

        //----------------------------------------------------------------------------------------------------------------------------------------
        //get DataType Property
        JsonNode get_dataType = new ObjectMapper().readTree(new FileReader("C:\\Users\\yaya_aye\\Documents\\NetBeansProjects\\Tugas_akhir_web\\json-schema-generator\\"+jsonFIle));
        JsonNode node = json.get(listRoot.get(0));
        listClass.add(listRoot.get(0));
        ArrayList<String> listTmpClasstoProperty = new ArrayList();
        ArrayList<String> listTmpDataTypetoInstance = new ArrayList();
        getData.GenerateDataTypeProperty(node, listTmpClasstoProperty, listTmpDataTypetoInstance);

        int sizeDataType = listTmpClasstoProperty.size();
        for (int i = 0; i < listTmpClasstoProperty.size(); i++) {
            if (listTmpClasstoProperty.get(i).equalsIgnoreCase("break")) {
                sizeDataType = sizeDataType - 2;
            }
        }

        //list
        System.out.println("");
        System.out.println("datatype property || (class - dayatype - instantClass - tipe)");
        ArrayList<String>[] listDataTypeProperty = new ArrayList[sizeDataType];
        for (int i = 0; i < sizeDataType; i++) {
            listDataTypeProperty[i] = new ArrayList<>();
        }
        int loop = 0;
        for (int i = 0; i < listTmpClasstoProperty.size(); i++) {

            if (listTmpClasstoProperty.get(i).equalsIgnoreCase("break")) {
                int dataType = i + 1;
                int count = 0;
                for (int j = dataType + 1; j < listTmpClasstoProperty.size(); j++) {
                    if (listTmpClasstoProperty.get(j).equalsIgnoreCase("break")) {
                        break;
                    } else {
                        listDataTypeProperty[loop].add(listTmpClasstoProperty.get(dataType));
                        String search = listTmpClasstoProperty.get(j);
                        listDataTypeProperty[loop].add(search);
                        for (int k = 0; k < listTmpDataTypetoInstance.size(); k++) {
                            if (search.equalsIgnoreCase(listTmpDataTypetoInstance.get(k)) && listDataTypeProperty[loop].size() < 4) {
                                listDataTypeProperty[loop].add(listTmpDataTypetoInstance.get(k + 1));
                                listDataTypeProperty[loop].add(listTmpDataTypetoInstance.get(k + 2));
                            }
                        }
                        loop++;
                    }
                }
            }
        }
        for (int i = 0; i < listDataTypeProperty.length; i++) {
            System.out.println("    " + listDataTypeProperty[i].toString());
        }
        System.out.println("");
        //----------------------------------------------------------------------------------------------------------------------------------------    

        //build owl ontology file
        ontologyInstance.BuildOWLOntology(listClass, listObjectProperty, listDataTypeProperty);
        //----------------------------------------------------------------------------------------------------------------------------------------
        return "bisa";
    }

    
}
