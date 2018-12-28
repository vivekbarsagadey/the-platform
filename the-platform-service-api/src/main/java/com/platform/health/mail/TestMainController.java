/*package com.platform.health.mail;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mail")
public class TestMainController {
	@Autowired
	public JavaMailSender emailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	@RequestMapping("/sendMail")
	public String sendMail() {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {

			
			 * VelocityContext context = new VelocityContext(); StringWriter stringWriter =
			 * new StringWriter(); velocityEngine.mergeTemplate("email.vm", "UTF-8",
			 * context, stringWriter);
			 
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("title", "hi");
			model.put("body", "hello hi there");
			String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/email.vm", "UTF-8",
					model);
			// System.out.println("sdasdad >>>> " + stringWriter.toString() );
			System.out.println(
					"_____######################################################################################"
							+ text);

			helper.setTo("vivek.bnb@gmail.com");
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
*/