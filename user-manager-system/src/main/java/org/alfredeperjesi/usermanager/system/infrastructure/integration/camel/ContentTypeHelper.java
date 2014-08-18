package org.alfredeperjesi.usermanager.system.infrastructure.integration.camel;

public class ContentTypeHelper {
    static final String HTML = "html";

    static final String TEXT_HTML = "text/html";

    static final String JS = "js";

    static final String APPLICATION_JAVASCRIPT = "application/javascript";

    static final String CSS = "css";

    static final String TEXT_CSS = "text/css";

    static final String PNG = "png";

    static final String IMAGE_PNG = "image/png";

    public static String getContentType(final String filePath) {
        if (filePath.endsWith(HTML)) {
            return TEXT_HTML;
        } else if (filePath.endsWith(JS)) {
            return APPLICATION_JAVASCRIPT;
        } else if (filePath.endsWith(CSS)) {
            return TEXT_CSS;
        } else if (filePath.endsWith(PNG)) {
            return IMAGE_PNG;
        }
        return null;
    }
}
