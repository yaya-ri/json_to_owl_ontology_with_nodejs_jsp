/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonToOntology;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class GenerateJsonToSchema {
    public Boolean GenerateJsonToSchema(String jsonFile, String jsonSchema) throws IOException {

        String newfile = "-o";
        String replace = "-f";

        List<String> cmds = Arrays.asList("cmd.exe", "/C", "json-schema-generator json-schema-generator\\" + jsonFile, "-f", "json-schema-generator\\" + jsonSchema);
        ProcessBuilder builder = new ProcessBuilder(cmds);
        builder.directory(new File("C:\\Users\\yaya_aye\\Documents\\NetBeansProjects\\Tugas_akhir_web"));
        Process proc = builder.start();
        BufferedReader r;
        r = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line;
        String keluaran = null;
        while (true) {
            line = r.readLine();
            //System.out.println(line);
            if (line == null) {
                break;
            }
            keluaran = keluaran + "\n" + line;
        }
//        System.out.println(keluaran);
        boolean check = new File("C:\\Users\\yaya_aye\\Documents\\NetBeansProjects\\Tugas_akhir_web\\json-schema-generator\\" + jsonSchema).exists();
        return check;
    }
}
