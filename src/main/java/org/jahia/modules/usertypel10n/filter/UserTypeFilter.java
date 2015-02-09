package org.jahia.modules.usertypel10n.filter;

import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.filter.AbstractFilter;
import org.jahia.services.render.filter.RenderChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tarek on 9/8/14.
 */
public class UserTypeFilter extends AbstractFilter {

    private transient static Logger logger = LoggerFactory.getLogger(UserTypeFilter.class);

    public String prepare(RenderContext renderContext, Resource resource, RenderChain chain) throws Exception {
        if (! renderContext.isLiveMode() && ! renderContext.isPreviewMode() ) {
            // Not live mode: always show content
            resource.pushWrapper("userTypeset");
            return null;
        }

        // Get region from request
        String requestedUserType = (String) renderContext.getRequest().getSession().getAttribute("sessionUserType");

        // Get region from current node
        String nodeUserType = resource.getNode().getPropertyAsString("userType");
      	logger.info("Visitor userType: " + requestedUserType);
        logger.info("Content userType: " + nodeUserType);

      	if (requestedUserType != null) {
        	if (nodeUserType == null || nodeUserType == "" || nodeUserType.contains(requestedUserType)) {
            	return null;
        	}
        }

        return "<div style=\"display:none\"></div>";
    }
}



