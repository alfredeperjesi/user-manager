package org.alfredeperjesi.usermanager.system.infrastructure.integration.camel;

import static org.alfredeperjesi.usermanager.system.infrastructure.integration.camel.ContentTypeHelper.APPLICATION_JAVASCRIPT;
import static org.alfredeperjesi.usermanager.system.infrastructure.integration.camel.ContentTypeHelper.IMAGE_PNG;
import static org.alfredeperjesi.usermanager.system.infrastructure.integration.camel.ContentTypeHelper.TEXT_CSS;
import static org.alfredeperjesi.usermanager.system.infrastructure.integration.camel.ContentTypeHelper.TEXT_HTML;
import static org.alfredeperjesi.usermanager.system.infrastructure.integration.camel.ContentTypeHelper.getContentType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ContentTypeHelperTest {

    private static final String FILE_HTML = "file.html";

    private static final String FILE_JS = "file.js";

    private static final String FILE_CSS = "file.css";

    private static final String FILE_PNG = "file.png";

    private static final String FILE_UNKNOWN = "file";

    @Test
    public void getContentTypeReturnsTextHtmlWhenExtensionIsHtml() {
        String contentType = getContentType(FILE_HTML);

        assertThat(contentType, equalTo(TEXT_HTML));
    }

    @Test
    public void getContentTypeReturnsApplicationJavascriptWhenExtensionIsJs() {
        String contentType = getContentType(FILE_JS);

        assertThat(contentType, equalTo(APPLICATION_JAVASCRIPT));
    }

    @Test
    public void getContentTypeReturnsTextCssWhenExtensionIsHtml() {
        String contentType = getContentType(FILE_CSS);

        assertThat(contentType, equalTo(TEXT_CSS));
    }

    @Test
    public void getContentTypeReturnsImagePngWhenExtensionIsPng() {
        String contentType = getContentType(FILE_PNG);

        assertThat(contentType, equalTo(IMAGE_PNG));
    }

    @Test
    public void getContentTypeReturnsNullWhenExtensionIsNotKnown() {
        String contentType = getContentType(FILE_UNKNOWN);

        assertThat(contentType, nullValue());
    }

}
