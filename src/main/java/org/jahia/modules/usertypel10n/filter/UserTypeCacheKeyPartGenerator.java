package org.jahia.modules.usertypel10n.filter;

import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.filter.cache.CacheKeyPartGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import java.util.Properties;

/**
 * Created by tarek on 9/9/14.
 */
public class UserTypeCacheKeyPartGenerator implements CacheKeyPartGenerator {

    private transient static Logger logger = LoggerFactory.getLogger(UserTypeCacheKeyPartGenerator.class);

    public String getKey() {
      //Jitendra : Needs to check if this is required, in geolocation it is 'geolocationRegion'
        
      return "currentUserType";
    }

    public String getValue(Resource resource, RenderContext renderContext, Properties properties) {
        
      	try {
            JCRNodeWrapper node = resource.getNode();
            if (node.isNodeType("jmix:userTypeLocalizable")) {
                // Retrieve list of countries set in geo-localized node
                String userTypes = node.getPropertyAsString("userType");
                logger.info("UserTypeCacheKeyPartGenerator User Type Cache - Adding placeholder: " + userTypes);
				
                return userTypes;
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String replacePlaceholders(RenderContext renderContext, String s) {
        if (s.isEmpty()) {
            return "";
        }

        logger.info("Retreived placeholder: " + s);

        // Check if region in session matches one region in cache key placeholder
        String requestedUserType = (String) renderContext.getRequest().getSession().getAttribute("sessionUserType");

      	//Jitendra: Needs to check if the below block is required, What is $geoip-nodisplay and where it is used
     	
        if (requestedUserType !=null && !requestedUserType.isEmpty() && s.indexOf(requestedUserType)>=0) {
            // Match found: display content
            logger.info("Generating cache key for region: " + requestedUserType + " / $geopip-display");
            return "$geopip-display";
        }

        // No match found: don't display content
        logger.info("Generating cache key for region: " + requestedUserType + " / $geopip-nodisplay");
        return "$geoip-nodisplay";
      
     	// return "";
    }
}
