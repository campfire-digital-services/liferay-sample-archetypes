#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.portlet.controller;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.StreamUtil;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

@Controller("sampleportletController")
@RequestMapping("view")
public class SamplePortletController {

    private static Log _log = LogFactoryUtil.getLog(SamplePortletController.class);

    @RenderMapping
    public String handleViewPage(RenderRequest request, ModelMap model) {
        _log.info("In View");
        return "view";
    }

    @ActionMapping("action")
    public void doExecute(ActionRequest actionRequest, ActionResponse actionResponse, ModelMap model) {
        try {
            _log.info("In Action");
            SessionMessages.add(actionRequest, "success");
        } catch (Exception e) {
            _log.error(e);
            SessionErrors.add(actionRequest, e.toString());
        }
    }

    @ResourceMapping
    public void doResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
        _log.info("In Resource");
        OutputStream out = null;
        String resource = "Test Stream";
        String filename = "test.txt";

        try {
            resourceResponse.setContentType("text/plain");
            resourceResponse.addProperty(HttpHeaders.CACHE_CONTROL, "max-age=3600, must-revalidate");
            resourceResponse.addProperty(HttpHeaders.CONTENT_DISPOSITION, "filename=" + filename);

            out = resourceResponse.getPortletOutputStream();
            StreamUtil.transfer(new ByteArrayInputStream(resource.getBytes("utf-8")), out);
        } catch (Exception e) {
            _log.error(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
            }
        }

    }
}
