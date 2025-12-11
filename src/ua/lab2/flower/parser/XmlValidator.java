package ua.lab2.flower.parser;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;

public class XmlValidator {

    public static boolean validate(String xmlPath, String xsdPath) {
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();

            try (InputStream is = new FileInputStream(xmlPath)) {
                validator.validate(new StreamSource(is));
            }

            return true;
        } catch (Exception e) {
            System.out.println("XML is NOT valid: " + e.getMessage());
            return false;
        }
    }
}
