/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonToOntology;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author yaya_aye
 */
public class Run {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        String jsonFile = "kesehatan.json";
        String jsonSchema = "schema.json";

        GenerateJsonToSchema generateResult = new GenerateJsonToSchema();
        BuildFile builfFile = new BuildFile();
        if (generateResult.GenerateJsonToSchema(jsonFile, jsonSchema)) {
            builfFile.BuildFile(jsonFile, jsonSchema);
        }

//        String basePath = new File("").getAbsolutePath();
//        File file = new File(basePath);
//        System.out.println(basePath);
//        String replace = basePath.replace("\\", "\\\\");
//        System.out.println(replace);
        //String uri = file.toURI().toString();
        //System.out.println(uri.length());
        //for (int i = 0; i < uri.length(); i++) {
            //System.out.println(uri.substring(6, uri.length()));
        //}
        //System.out.println(file.toURI());

//        String path = new File("src/main/resources/conf.properties").getAbsolutePath();
//        System.out.println(path);
    }

}
