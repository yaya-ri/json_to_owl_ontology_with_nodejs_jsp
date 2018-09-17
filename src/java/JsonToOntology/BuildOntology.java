/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonToOntology;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.util.PrintUtil;

/**
 *
 * @author yaya_aye
 */
public class BuildOntology {
    public void BuildOWLOntology(ArrayList<String> listClass, ArrayList<String>[] listObjectProperty, ArrayList<String>[] listDataTypeProperty) throws FileNotFoundException {
        org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);

        String mergeURI = "http://www.example.com/merge.owl#";
        PrintUtil.registerPrefix("merge", mergeURI);

        OntModel mo = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        mo.setNsPrefix("", mergeURI);
        mo.setNsPrefix("base", mergeURI);

//        membuat class
        for (int i = 0; i < listClass.size(); i++) {
            mo.createClass(mergeURI + listClass.get(i));
        }

//        membuat objek property
        for (int i = 0; i < listObjectProperty.length; i++) {
            ObjectProperty oop = mo.createObjectProperty(mergeURI + "punya");
            oop.addDomain(mo.getResource(mergeURI + listObjectProperty[i].get(0)));
            oop.addRange(mo.getResource(mergeURI + listObjectProperty[i].get(1)));
        }

        //membuat data type property
        for (int i = 0; i < listDataTypeProperty.length; i++) {
            DatatypeProperty dt = mo.createDatatypeProperty(mergeURI + listDataTypeProperty[i].get(3));
            dt.addDomain(mo.getResource(mergeURI + listDataTypeProperty[i].get(0)));
            dt.addRange(mo.getResource(mergeURI + listDataTypeProperty[i].get(1)));
        }

        for (int i = 0; i < listDataTypeProperty.length; i++) {
            OntClass newclass = mo.getOntClass(mergeURI + listDataTypeProperty[i].get(0));
            mo.createIndividual(mergeURI + listDataTypeProperty[i].get(2), newclass);
        }

        int aye = 0;
        for (int i = 0; i < listDataTypeProperty.length; i++) {
            Resource ex = mo.getResource(mergeURI + listDataTypeProperty[i].get(0));
            Property ex1 = mo.getProperty(mergeURI + listDataTypeProperty[i].get(1));
            Statement news = mo.createStatement(ex, ex1, "punya");
            System.out.println(news);
            mo.add(news);
            aye++;
        }
//        
//        String basePath = new File("").getAbsolutePath();
//        String path = new File("output-merge.owl").getAbsolutePath();

        String basePath = new File("").getAbsolutePath();
//        String path = new File("output.owl").getAbsolutePath();
//        File file = new File(bas);
        System.out.println(basePath);
        String finalPath = basePath.replace("\\", "\\\\");
        System.out.println(finalPath);
        String fileName="OntologyFromJsonFile1.owl";
        mo.write(new FileOutputStream("C:\\Users\\yaya_aye\\Documents\\NetBeansProjects\\Tugas_akhir_web\\"+fileName));
        //mo.write(new FileOutputStream(finalPath+"\\"+a));

    }
}
