<%@ page language="java" import="JsonToOntology.BuildOntology"%>
<%@ page language="java" import="JsonToOntology.GenerateData"%>
<%@ page language="java" import="JsonToOntology.BuildFile"%>
<%@ page language="java" import="JsonToOntology.GenerateJsonToSchema"%>
<% 
    String jsonFile = "kesehatan.json";
    String jsonSchema = "schema.json";
    GenerateJsonToSchema generateResult = new GenerateJsonToSchema();
    BuildFile builfFile = new BuildFile();
%>
        

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert title here</title>
    </head>
    <body>
<!--        <form action="${pageContext.request.contextPath}/response" method="post" enctype="multipart/form-data" >
            <input type="submit" name="button" value="button" />
        </form>-->
        <% if (generateResult.GenerateJsonToSchema(jsonFile, jsonSchema)) {
                 builfFile.BuildFile(jsonFile, jsonSchema);
             } %>
    </body>
</html>