package br.com.authorization.server.controller;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.ServletWebRequest;

@Controller
public class InterceptorErrorController implements ErrorController {

    @Autowired
    private ErrorAttributes errorAttributes;

	@GetMapping(value = "/error")
	public String handleError(Model model, HttpServletRequest request) {
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(servletWebRequest, true);
		
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		model.addAttribute("status", errorAttributes.get("status"));
		model.addAttribute("timestamp", errorAttributes.get("timestamp"));
		model.addAttribute("exception", errorAttributes.get("error"));
		model.addAttribute("message", errorAttributes.get("message") == null ? "" : errorAttributes.get("message"));

		if (status != null) {

			Integer statusCode = Integer.valueOf(status.toString());

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				return "/error/error-404";
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "/error/error-500";
			} else {
				return "/error/error";
			}
		}
		return "error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}