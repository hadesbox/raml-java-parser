package org.raml.parser.utils;

import org.raml.model.Resource;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by luix on 17/12/14.
 */
public class RamlUtils {

    public static Resource findResourceByDisplayName(String displayName, Map<String, Resource> resources, Boolean isRootResources){
        Iterator<String> ite = resources.keySet().iterator();
        while(ite.hasNext()){
            String current_key = ite.next();
            if(displayName.equalsIgnoreCase(resources.get(current_key).getDisplayName())){
                return resources.get(current_key);
            }
            else{
                Resource result = findResourceByDisplayName(displayName, resources.get(current_key).getResources(), false);
                if(result != null)
                    return result;
            }
        }
        return null;
    }
}
