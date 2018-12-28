package com.platform.health.mail;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api/mail")
public class TestMailController {
	@Autowired
	public JavaMailSender emailSender;

	private VelocityEngine velocityEngine = new VelocityEngine();

	@RequestMapping("/sendMail")
	public String sendMail() {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("title", "hi");
			model.put("body", "hello hi there");
			String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/email.vm", "UTF-8",
					model);
			System.out.println(text);

			helper.setTo("bmgroupbali@gmail.com");
			helper.setText(text, true);
			helper.setSubject("Mail From Spring Boot");
		} catch (Exception e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		emailSender.send(message);
		return "Mail Sent Success!";
	}

}
