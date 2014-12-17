package org.raml;

import org.raml.model.Action;
import org.raml.model.ActionType;
import org.raml.model.Raml;
import org.raml.model.Resource;
import org.raml.parser.utils.RamlUtils;
import org.raml.parser.visitor.RamlDocumentBuilder;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by luix on 17/12/14.
 */
public class RamlExample {

    public static void main(String args[]){
        System.out.println("Inicio del proceso...");
        //Cargamos el RAML desde la URL publica del servidor
        Raml raml = new RamlDocumentBuilder().build("http://apiraml.digitalservices.es/daas/datathon_mx_tiles_zipcodes.raml");
        Map<String, Resource> resources = raml.getResources();
        String serviceName = "Customer zipcodes by tile";

        //Buscamos el servicio que nos interesa por su nombre lógico (displayName).
        Resource zipCodesByTitle = RamlUtils.findResourceByDisplayName(serviceName, resources, true);
        System.out.println("Data for: " + serviceName);
        System.out.println("Descripción: "  + zipCodesByTitle.getDescription());
        System.out.println("Path: " + zipCodesByTitle.getUri());
        Iterator<ActionType> actionsIte = zipCodesByTitle.getActions().keySet().iterator();
        while(actionsIte.hasNext()){
            ActionType action = actionsIte.next();
            Iterator<String> queryParIte = zipCodesByTitle.getAction(action).getQueryParameters().keySet().iterator();
            System.out.println("For METHOD " + action.name());
            System.out.println("================================");
            while(queryParIte.hasNext()){
                String queryParName = queryParIte.next();
                System.out.println("Name: " + queryParName);
                System.out.println("DisplayName: " + zipCodesByTitle.getAction(action).getQueryParameters().get(queryParName).getDisplayName());
                System.out.println("Description: " + zipCodesByTitle.getAction(action).getQueryParameters().get(queryParName).getDescription());
                System.out.println("Example: " + zipCodesByTitle.getAction(action).getQueryParameters().get(queryParName).getExample());
                System.out.println("Type: " + zipCodesByTitle.getAction(action).getQueryParameters().get(queryParName).getType().name());
                System.out.println("================================");
            }
        }
        System.out.println("EL FIN!");
    }

}
